# Compte Rendu INFO706 TP JakartaEE
#### Antoine DEPOISIER - M1 INFO Groupe 2

# Choix d'implantation

Pour la réalisation de ce projet j'ai décidé de me baser sur le projet de Gestion de compte que vous aviez créé.
J'ai donc trois projet, un projet web, un ejb et un ear me permettant de déployer les deux projets en même temps.

## JPA

Pour ce projet, il y a 2 JPA et une classe me permettant d'afficher un justificatif.
Mes deux JPA sont Ticket et Paiement.

### Paiement

La classe paiement possède 4 attributs et une énumération :

* @Id @GeneratedValue  
    private long numPaiement;
* @Temporal(TemporalType.TIMESTAMP)  
    private Date datePaiement;
* private double montant;
* public enum TypePaiement { CB, Especes }
* private TypePaiement typePaiement;
    
### Justificatif

La classe ticket possède 4 attributs, mais n'est pas un JPA :

* private long numTicket
* private Date dateEntree;
* private Date datePaiement;
* private double montant;

### Ticket

La classe ticket possède 4 attributs :

* @Id @GeneratedValue  
    private long numTicket;
* @Temporal(TemporalType.TIMESTAMP)  
    private Date dateEntree;
* @Temporal(TemporalType.TIMESTAMP)  
    private Date dateSortie;
* @OneToMany(fetch = FetchType.EAGER) @JoinColumn  
    private List<Paiement> paiements = new ArrayList<Paiement>();

Elle possède également divers attribut statiques:

* public static final double prixMinute = 2.5;
* public static final Duration dureePaiement = Duration.ofMinutes(2);

dureePaiement est la durée de validité d'un ticket après le paiement. Si la personne présente sont ticket pour sortir, 2 minutes après l'avoir payé, il ne pourra pas sortir

Elle possèdes aussi diverses méthodes :

* public double montant()
    * Cette méthode regarde  la date du dernier paiement, et le moment où la méthode et appelée pour calculer le montant à payer. S'il n'y a pas de dernier paiement, elle regarde dateEntree. Si la durée entre le dernier paiement et le moment actuelle est inférieur à dureePaiement, elle retourne 0.
* public double montantTotal()
    * Cette méthode fait la somme de tous les paiements effectués, elle sert à créer le justificatif.
* public boolean autoriserSortie()
    * Cette méthode compare la date du dernier paiement et le moment actuelle et le compare à dureePaiement. Si le résultat est inférieur à dureePaiement, cette méthode retourne vrai, sinon faux.
* public Justificatif creerJustificatif()
    * Cette méthode créer un justificatif avec le numéro du ticket, la date d'entrée, la date du dernier paiement et le montant total payer grâce à la méthode montantTotal()

## EJB

L'ejb est composé d'une classe OperationBean implémantant les différentes méthodes de l'interface Opertation :

* Ticket creerTicket();
* Ticket getTicket(long numTicket);
* Ticket payerTicket(long numTicket, Paiement.TypePaiement typePaiement);
* Ticket validerSortie(long numTicket);
    * Cette méthode prend en compte l'exeption losque la personne n'a pas payé son ticket
    * Cette méthode prend en compte l'exeption lorsque la durée du paiement est dépassée
* Justificatif creerJustificatif(long numTicket);
    * Cette méthode prend en compte l'exeption losque la personne n'a pas payé son ticket

## JSP

* index.html
    * page d'accueil montrant les 3 différentes bornes
* AfficherJustificatif.jsp
    * page montrant un justificatif
* AfficherTicket.jsp
    * page montrant un ticket, mais seulement la date d'entrée et le numro de ticket, cette page est affiché quand on a créé un ticket
* AfficherTicketPaiement.jsp
    * page montrant un ticket et le montant à payer, si l'utilisateur a payé, un bouton pour avoir un justificatif apparait
* SortirParking.jsp
    * page nous disant s'il est possible de sortir du parking.

## Servlet

* AfficherTicketPaiementServlet
    * cette servlet cherche un ticket dans l'ejb, et l'affiche avec son montant calculé
* CreerJustificatifServlet
    * cette servlet crée un justificatif par rapport à un numero de ticket
* CreerTicketServlet
    * créer un ticket, et affiche certaines informations
* PayerTicketServlet
    * cette servlet paie un ticket, et recharge la page AfficherTicketPaiement.jsp
* SortirParkingServlet
    * cette servlet demande à l'ejb si un ticket a le droit de sortir

# Utilisation de l'application :

Sur la page principale de l'application nous avons plusieurs bornes disponnible à notre disposition :
* Tout d'abord, la première borne nous servant à créer un ticket. Il faut cliquer sur le bouton "prendre un ticket", ce qui va nous rediriger vers une page nous informant de notre numéro de ticket et la date à laquelle il a été créé. Il faut conserver le numéro du ticket pour la suite.
Après celà nous pouvons partir de la borne

* Ensuite la borne pour payer un ticket. Pour acceder à cette borne il faut scanner son ticket, ce que j'ai modélisé par un champ texte où nous entrons notre numéro de ticket. Nous somme ensuite redirigé vers une autre page. Cette page nous affiche les informations du ticket et son montant. Nous pouvons ensuite payer le ticket par notre choix de paiement. Un nouveau bouton "Avoir un justificatif" est apparu. Ce bouton n'apparait que si nous avons au moins payé une fois. Quand on clique sur le bouton nous somme redirigé vers une page nous montrant le justificatif. Nous pouvons ensuite partir de la borne.

* Enfin, la dernière borne, pour sortir du parking. Comme pour l'étape de payer son ticket, il faut scanner son tciket. Nous sommes ensuite redirigé vers une page nous disant si l'on peut sortir du parking ou bien s'il y a une erreur. Nous pouvons ensuite partir de la borne si une erreur est affichée.