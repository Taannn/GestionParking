package fr.usmb.m2isc.javaee.comptes.soap;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import fr.usmb.m2isc.javaee.comptes.ejb.Operation;
import fr.usmb.m2isc.javaee.comptes.jpa.Justificatif;
import fr.usmb.m2isc.javaee.comptes.jpa.Paiement;
import fr.usmb.m2isc.javaee.comptes.jpa.Ticket;

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
