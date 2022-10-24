package com.example.gestionparking.ejb;

import com.example.gestionparking.jpa.Justificatif;
import com.example.gestionparking.jpa.Paiement;
import com.example.gestionparking.jpa.Ticket;

import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Stateless
@Remote
public class OperationBean implements Operation {

    @PersistenceContext
    private EntityManager em;

    public  OperationBean(){
        super();
    }

    @Override
    public Ticket creerTicket() {
        Ticket tck = new Ticket();
        em.persist(tck);
        return tck;
    }
    @Override
    public Ticket getTicket(int numTicket) {
        return em.find(Ticket.class, numTicket);
    }
    @Override
    public Ticket payerTicket(int numTicket, Paiement.TypePaiement typePaiement) {
        Ticket tck = em.find(Ticket.class, numTicket);
        tck.addPaiement(tck.montant(), typePaiement);
        return tck;
    }
    @Override
    public Ticket validerSortie(int numTicket) {
        Ticket tck = em.find(Ticket.class, numTicket);
        if(!tck.autoriserSortie()){
            throw new EJBException("Impossible de sortir, votre paiement a été effectué il y a plus de 15 minutes");
        }
        tck.setDateSortie(LocalDateTime.now());
        return tck;
    }
    @Override
    public Justificatif creerJustificatif(int numTicket) {
        Ticket tck = em.find(Ticket.class, numTicket);
        if(tck.getPaiements().size() == 0)
            throw new EJBException("Impossible de créer un justificatif, vous n'avez pas payé");
        return tck.creerJustificatif();
    }

}
