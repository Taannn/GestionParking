package fr.usmb.m2isc.javaee.comptes.jpa;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Ticket implements Serializable {

    public static final double prixMinute = 2.5;
    public static final Duration dureePaiement = Duration.ofMinutes(15);
    @Id @GeneratedValue
    private long numTicket;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEntree;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSortie;
    @OneToMany(fetch = FetchType.EAGER) @JoinColumn
    private List<Paiement> paiements = new ArrayList<Paiement>();

    public Ticket() {
        super();
        this.dateEntree = new Date();
    }
    public double montant(){
        if(this.getPaiements().size() == 0)
            return Duration.between(this.dateEntree.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() ,LocalDateTime.now()).toMinutes() * prixMinute;
        Duration depuisDernierPaiement = Duration.between(this.getPaiements().get(-1).getDatePaiement().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() ,LocalDateTime.now());
        return dureePaiement.compareTo(depuisDernierPaiement) == -1 ? depuisDernierPaiement.toMinutes() * prixMinute : 0.0;
    }
    public double montantTotal(){
        return this.getPaiements().stream().mapToDouble(i -> i.getMontant()).sum();
    }
    public boolean autoriserSortie(){
        return dureePaiement.compareTo(Duration.between(this.getPaiements().get(-1).getDatePaiement().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() ,LocalDateTime.now())) == -1 ? false : true;
    }
    public Justificatif creerJustificatif(){
        return new Justificatif(this.getNumTicket(), this.getDateEntree(), this.getPaiements().get(-1).getDatePaiement(), this.montantTotal());
    }

    public long getNumTicket() {
        return numTicket;
    }
    public Date getDateEntree() {
        return dateEntree;
    }
    public List<Paiement> getPaiements() {
        return paiements;
    }
    public void setDateSortie(Date dateSortie){
        this.dateSortie = dateSortie;
    }
    public void addPaiement(double montant, Paiement.TypePaiement typePaiement){
        this.paiements.add(new Paiement(montant, typePaiement));
    }
}
