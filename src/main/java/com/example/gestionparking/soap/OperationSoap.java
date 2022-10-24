package com.example.gestionparking.soap;

import com.example.gestionparking.ejb.Operation;
import com.example.gestionparking.jpa.Justificatif;
import com.example.gestionparking.jpa.Paiement;
import com.example.gestionparking.jpa.Ticket;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class OperationSoap {

    @EJB
    private Operation ejb;

    @WebMethod
    public Ticket creerTicket() {
        return ejb.creerTicket();
    }
    public Ticket getTicket(int numTicket) {
        return ejb.getTicket(numTicket);
    }

    public Ticket payerTicket(int numTicket, Paiement.TypePaiement typePaiement) {
        return ejb.payerTicket(numTicket, typePaiement);
    }
    public Ticket validerSortie(int numTicket) {
        return ejb.validerSortie(numTicket);
    }
    public Justificatif creerJustificatif(int numTicket) {
        return ejb.creerJustificatif(numTicket);
    }
}
