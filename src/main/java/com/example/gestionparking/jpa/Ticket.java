package com.example.gestionparking.jpa;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Ticket implements Serializable {

    public static final double prixMinute = 2.5;
    public static final Duration dureePaiement = Duration.ofMinutes(15);
    @Id @GeneratedValue
    private long numTicket;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateEntree;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateSortie;
    @OneToMany @JoinColumn
    private List<Paiement> paiements =new ArrayList<Paiement>();

    public Ticket() {
        super();
        this.dateEntree = LocalDateTime.now();
    }
    public double montant(){
        if(this.getPaiements().size() == 0)
            return Duration.between(this.dateEntree ,LocalDateTime.now()).toMinutes() * prixMinute;
        Duration depuisDernierPaiement = Duration.between(this.getPaiements().get(-1).getDatePaiement() ,LocalDateTime.now());
        return dureePaiement.compareTo(depuisDernierPaiement) == -1 ? depuisDernierPaiement.toMinutes() * prixMinute : 0;
    }
    public double montantTotal(){
        return this.getPaiements().stream().mapToDouble(i -> i.getMontant()).sum();
    }
    public boolean autoriserSortie(){
        return dureePaiement.compareTo(Duration.between(this.getPaiements().get(-1).getDatePaiement() ,LocalDateTime.now())) == -1 ? false : true;
    }
    public Justificatif creerJustificatif(){
        return new Justificatif(this.getNumTicket(), this.getDateEntree(), this.getPaiements().get(-1).getDatePaiement(), this.montantTotal());
    }

    public long getNumTicket() {
        return numTicket;
    }
    public LocalDateTime getDateEntree() {
        return dateEntree;
    }
    public List<Paiement> getPaiements() {
        return paiements;
    }
    public void setDateSortie(LocalDateTime dateSortie){
        this.dateSortie = dateSortie;
    }
    public void addPaiement(double montant, Paiement.TypePaiement typePaiement){
        this.paiements.add(new Paiement(montant, typePaiement));
    }
}

