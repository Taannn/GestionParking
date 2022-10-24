package com.example.gestionparking.ejb;

import com.example.gestionparking.jpa.Paiement;
import com.example.gestionparking.jpa.Ticket;
import com.example.gestionparking.jpa.Justificatif;

public interface Operation {
    Ticket creerTicket();
    Ticket getTicket(int numTicket);
    Ticket payerTicket(int numTicket, Paiement.TypePaiement typePaiement);
    Ticket validerSortie(int numTicket);
    Justificatif creerJustificatif(int numTicket);
}
