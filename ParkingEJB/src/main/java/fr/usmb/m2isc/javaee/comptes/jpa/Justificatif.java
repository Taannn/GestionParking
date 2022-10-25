package fr.usmb.m2isc.javaee.comptes.jpa;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Justificatif implements Serializable {
    private long numTicket;
    private Date dateEntree;
    private Date datePaiement;
    private double montant;

    public Justificatif(long numTicket, Date dateEntree, Date datePaiement, double montant) {
        this.numTicket = numTicket;
        this.dateEntree = dateEntree;
        this.datePaiement = datePaiement;
        this.montant = montant;
    }

    public long getNumTicket() {
        return numTicket;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public double getMontant() {
        return montant;
    }
}
