# Info706 – TP JEE (JavaEE/JakartaEE) #
### DEPOISIER Antoine M1-Groupe2

On souhaite simuler une application de gestion d'un parking.

Le parking comporte 3 bornes (qui seront simulées à l'aide de pages WEB).

* 1 borne d'entrée qui distribue des tickets,
* 1 borne de paiement qui permet de payer avant de sortir et d'éditer des justificatifs,
* 1 borne de sortie.

Les tickets seront représentés sous la forme d'entités JPA
qui seront conservés dans la base de données afin de pouvoir être exploités ultérieurement
(statistiques d'utilisation du parking, exploitation par la justice ou la police, … ).

Un ticket comportera 4 champs :

* un identifiant unique (un numéro)
* une date d'entrée,
* une date de sortie.

Pour chaque ticket, on gardera également en base de données la liste des paiment effectués.  
Pour chaque paiement on conservera :

* un identifiant du paiement (numéro),
* la date du paiement,
* le montant payé (en euro),
* le type de paiement utilisé (CB, espèces).

## Fonctionnement des bornes ##
La première borne sert juste à créer un ticket.

La deuxième servira à payer avant de sortir :
elle affichera le ticket, calculera le prix à payer (2 centimes d'euro la minute, par exemple)
et permettra à l'utilisateur de payer (ou non).  
Elle permettra également d'éditer, à la demande du client, un justificatif indiquant :

* le numero du ticket,
* la date d'entrée,
* la date du dernier paiement,
* le montant total payé par le client (somme des différents paiements effctués).

La troisième borne permettra de valider la sortie de l'utilisateur :
après avoir payé l'utilisateur disposera en effet d'un temps limité pour sortir (typiquement un quart d'heure).
Si l'utilisateur n'est pas sorti à temps, il devra alors retourner payer le complément à la borne de paiement
avant de pouvoir revenir à la borne de sortie.

**Remarque :** afin de pouvoir plus facilement tester l'application,
on pourra simuler un ecoulement du temps accéléré, par exemple d'un facteur 10.

## Rendu : ##
Le rendu du TP se fera sous la forme d'un dépot git (votre enseignant _Stéphane Talbot_ est connu sous pseudo _stalb_ sur github/gitlab/bitbucket).

Il y aura un rendu partiel, à la fin de chaque séance de TP, et un rendu final.  
Outre les sources du TP le rendu final comportera un compte-rendu expliquant :

* les choix d'implantation,
* comment utiliser l'application.
