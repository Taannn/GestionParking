package com.example.gestionparking.jpa;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Justificatif implements Serializable {
    private long numTicket;
    private LocalDateTime dateEntree;
    private LocalDateTime datePaiement;
    private double montant;

    public Justificatif(long numTicket, LocalDateTime dateEntree, LocalDateTime datePaiement, double montant) {
        this.numTicket = numTicket;
        this.dateEntree = dateEntree;
        this.datePaiement = datePaiement;
        this.montant = montant;
    }

    public long getNumTicket() {
        return numTicket;
    }

    public LocalDateTime getDateEntree() {
        return dateEntree;
    }

    public LocalDateTime getDatePaiement() {
        return datePaiement;
    }

    public double getMontant() {
        return montant;
    }
}
