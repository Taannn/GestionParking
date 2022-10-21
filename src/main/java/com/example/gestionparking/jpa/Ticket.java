package com.example.gestionparking.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


/*@NamedQueries ({
        @NamedQuery(name="all", query="SELECT c FROM Ticket c"),
        @NamedQuery(name="findWithNum", query="SELECT c FROM Ticket c WHERE c.numero LIKE :partialNum ORDER BY c.numero ASC")
})*/

@Entity
public class Ticket implements Serializable {

    @Id
    private String numero;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEntree;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSortie;
    @OneToMany
    @JoinColumn
    private List<Paiement> paiements =new ArrayList<Paiement>();

    public Ticket(String numero) {
        super();
        this.numero = numero;
        this.dateEntree = new Date();
    }

    public Ticket() {

    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
}

