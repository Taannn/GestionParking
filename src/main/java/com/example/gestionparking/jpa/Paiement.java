package com.example.gestionparking.jpa;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Paiement implements Serializable {

    @Id @GeneratedValue
    private long numPaiement;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime datePaiement;
    private double montant;
    public enum TypePaiement { CB, Especes }
    private TypePaiement typePaiement;

    public Paiement(double montant, TypePaiement typePaiement) {
        super();
        this.datePaiement = LocalDateTime.now();
        this.montant = montant;
        this.typePaiement = typePaiement;
    }

    public Paiement() {
    }

    public long getNumPaiement() {
        return numPaiement;
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
    public LocalDateTime getDatePaiement() {
        return datePaiement;
    }
    public void setDatePaiement(LocalDateTime datePaiement){
        this.datePaiement = datePaiement;
    }
    public void setTypePaiement(TypePaiement typePaiement){
        this.typePaiement = typePaiement;
    }
}
