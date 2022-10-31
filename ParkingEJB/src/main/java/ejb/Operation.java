package ejb;

import java.util.List;

import jpa.Justificatif;
import jpa.Paiement;
import jpa.Ticket;

public interface Operation {
    Ticket creerTicket();
    Ticket getTicket(long numTicket);
    Ticket payerTicket(long numTicket, Paiement.TypePaiement typePaiement);
    Ticket validerSortie(long numTicket);
    Justificatif creerJustificatif(long numTicket);
}