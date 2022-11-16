# Compte Rendu INFO706 TP JakartaEE
#### Antoine DEPOISIER - M1 INFO Groupe 2

# Choix d'implémentation

Pour la réalisation de ce projet j'ai décidé de me baser sur le projet de Gestion de comptes que vous aviez créé.
J'ai donc trois projets: un projet web, un EJB et un EAR me permettant de déployer les deux autres projets en même temps.

## JPA

Pour ce projet, il y a 2 JPA et une classe me permettant d'afficher un justificatif.
Mes deux JPA sont ``Ticket`` et ``Paiement``.

### Paiement

La classe ``Paiement`` possède 4 attributs et une énumération :

```java
@Id @GeneratedValue  
private long numPaiement;
@Temporal(TemporalType.TIMESTAMP)  
private Date datePaiement;
private double montant;
public enum TypePaiement { CB, Especes }
private TypePaiement typePaiement;
```
    
### Justificatif

La classe ``Justificatif`` possède 4 attributs, mais n'est pas un JPA :

```java
private long numTicket
private Date dateEntree;
private Date datePaiement;
private double montant;
```

### Ticket

La classe ``Ticket`` possède 4 attributs :

```java
@Id @GeneratedValue  
private long numTicket;
@Temporal(TemporalType.TIMESTAMP)  
private Date dateEntree;
@Temporal(TemporalType.TIMESTAMP)  
private Date dateSortie;
@OneToMany(fetch = FetchType.EAGER) @JoinColumn  
private List<Paiement> paiements = new ArrayList<Paiement>();
```

Elle possède également divers attributs statiques:

```java
public static final double prixMinute = 2.5;
public static final Duration dureePaiement = Duration.ofMinutes(2);
```

``dureePaiement`` correspond à la durée de validité d'un ticket après le paiement. Si la personne présente son ticket pour sortir 2 minutes après l'avoir payé, il ne pourra pas sortir.

La classe ``Ticket`` possède aussi diverses méthodes :

```java
public double montant()
```
Cette méthode regarde la date du dernier paiement, et le moment où la méthode et appelée pour calculer le montant à payer. S'il n'y a pas de dernier paiement, elle regarde ``dateEntree``. Si la durée entre le dernier paiement et le moment actuel est inférieur à ``dureePaiement``, elle retourne 0.

```java
public double montantTotal()
```
Cette méthode fait la somme de tous les paiements effectués, elle sert à créer le justificatif.

```java
public boolean autoriserSortie()
```
Cette méthode compare la date du dernier paiement et le moment actuel et le compare à ``dureePaiement``. Si le résultat est inférieur à ``dureePaiement``, cette méthode retourne ``true``, sinon ``false``.

```java
public Justificatif creerJustificatif()
```
Cette méthode crée un justificatif avec le numéro du ticket, la date d'entrée, la date du dernier paiement et le montant total payé grâce à la méthode ``montantTotal()``.

## EJB

L'EJB est composé d'une classe ``OperationBean`` implémentant les différentes méthodes de l'interface ``Operation`` :

```java
Ticket creerTicket();
```
```java
Ticket getTicket(long numTicket);
```
```java
Ticket payerTicket(long numTicket, Paiement.TypePaiement typePaiement);
```
```java
Ticket validerSortie(long numTicket);
```
Cette méthode prend en compte l'exception lorsque la personne n'a pas payé son ticket  
Cette méthode prend en compte l'exception lorsque la durée du paiement est dépassée
```java
Justificatif creerJustificatif(long numTicket);
```
Cette méthode prend en compte l'exception lorsque la personne n'a pas payé son ticket

## JSP

* index.html
    * Page d'accueil montrant les 3 différentes bornes
* AfficherJustificatif.jsp
    * Page montrant un justificatif
* AfficherTicket.jsp
    * Page montrant un ticket, mais seulement la date d'entrée et le numéro de ticket, cette page est affichée quand on crée un ticket
* AfficherTicketPaiement.jsp
    * Page montrant un ticket et le montant à payer. Si l'utilisateur a payé, un bouton pour obtenir un justificatif apparait
* SortirParking.jsp
    * Page nous disant s'il est possible de sortir du parking.

## Servlet

* AfficherTicketPaiementServlet
    * Cette servlet cherche un ticket dans l'EJB, et l'affiche avec son montant calculé
* CreerJustificatifServlet
    * Cette servlet crée un justificatif par rapport à un numéro de ticket
* CreerTicketServlet
    * Cette servlet crée un ticket, et affiche certaines informations
* PayerTicketServlet
    * Cette servlet paie un ticket, et recharge la page ``AfficherTicketPaiement.jsp``
* SortirParkingServlet
    * Cette servlet demande à l'EJB si un ticket permet de sortir

# Utilisation de l'application :

Sur la page principale de l'application nous avons plusieurs bornes disponnibles à notre disposition :
* Tout d'abord, la première borne nous servant à créer un ticket. Il faut cliquer sur le bouton "prendre un ticket", ce qui va nous rediriger vers une page nous informant de notre numéro de ticket et de la date à laquelle il a été créé. Il faut conserver le numéro du ticket pour la suite.

* Ensuite la borne pour payer un ticket. Pour accéder à cette borne il faut scanner son ticket, ce que j'ai modélisé par un champ texte où nous entrons notre numéro de ticket. Nous sommes ensuite redirigé vers une autre page. Cette page nous affiche les informations du ticket et son montant. Nous pouvons ensuite payer le ticket avec le moyen de paiement de notre choix. Un nouveau bouton "Avoir un justificatif" apparaît alors. Ce bouton n'apparaît que si nous avons au moins payé une fois. Quand on clique sur le bouton nous sommes redirigés vers une page nous montrant le justificatif.

* Enfin, la dernière borne, pour sortir du parking. Comme pour l'étape du paiement du ticket, il faut scanner son ticket. Nous sommes ensuite redirigé vers une page nous indiquant si on peut sortir du parking ou bien s'il y a une erreur.