package fr.usmb.m2isc.javaee.comptes.jpa;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Paiement implements Serializable {

    @Id @GeneratedValue
    private long numPaiement;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaiement;
    private double montant;
    public enum TypePaiement { CB, Especes }
    private TypePaiement typePaiement;

    public Paiement(double montant, TypePaiement typePaiement) {
        super();
        this.datePaiement = new Date();
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
    public Date getDatePaiement() {
        return datePaiement;
    }
    public void setDatePaiement(Date datePaiement){
        this.datePaiement = datePaiement;
    }
    public void setTypePaiement(TypePaiement typePaiement){
        this.typePaiement = typePaiement;
    }
}
