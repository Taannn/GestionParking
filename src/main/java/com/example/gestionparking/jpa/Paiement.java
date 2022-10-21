package com.example.gestionparking.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/*@NamedQueries ({
        @NamedQuery(name="all", query="SELECT c FROM Ticket c"),
        @NamedQuery(name="findWithNum", query="SELECT c FROM Ticket c WHERE c.numero LIKE :partialNum ORDER BY c.numero ASC")
})*/

@Entity
public class Paiement implements Serializable {

    @Id
    private String numero;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaiement;
    private double montant;
    private String typePaiement;

    public Paiement(String numero, double montant) {
        super();
        this.numero = numero;
        this.montant = montant;
    }

    public Paiement() {

    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
}
