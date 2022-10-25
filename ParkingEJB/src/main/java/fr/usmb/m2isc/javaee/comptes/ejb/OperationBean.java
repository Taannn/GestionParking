package fr.usmb.m2isc.javaee.comptes.ejb;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.usmb.m2isc.javaee.comptes.jpa.Justificatif;
import fr.usmb.m2isc.javaee.comptes.jpa.Paiement;
import fr.usmb.m2isc.javaee.comptes.jpa.Paiement.TypePaiement;
import fr.usmb.m2isc.javaee.comptes.jpa.Ticket;

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
    public Ticket getTicket(long numTicket) {
    	Ticket tck = em.find(Ticket.class, numTicket);
        return tck;
    }
    @Override
    public Ticket payerTicket(long numTicket, Paiement.TypePaiement typePaiement) {
        Ticket tck = em.find(Ticket.class, numTicket);
        tck.addPaiement(tck.montant(), typePaiement);
        return tck;
    }
    @Override
    public Ticket validerSortie(long numTicket) {
        Ticket tck = em.find(Ticket.class, numTicket);
        if(!tck.autoriserSortie()){
            throw new EJBException("Impossible de sortir, votre paiement a été effectué il y a plus de 15 minutes");
        }
        tck.setDateSortie(new Date());
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
