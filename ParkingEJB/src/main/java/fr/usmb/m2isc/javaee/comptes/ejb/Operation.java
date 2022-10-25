package fr.usmb.m2isc.javaee.comptes.ejb;

import java.util.List;

import fr.usmb.m2isc.javaee.comptes.jpa.Justificatif;
import fr.usmb.m2isc.javaee.comptes.jpa.Paiement;
import fr.usmb.m2isc.javaee.comptes.jpa.Ticket;

public interface Operation {
    Ticket creerTicket();
    Ticket getTicket(long numTicket);
    Ticket payerTicket(long numTicket, Paiement.TypePaiement typePaiement);
    Ticket validerSortie(long numTicket);
    Justificatif creerJustificatif(int numTicket);
}