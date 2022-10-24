package com.example.gestionparking.soap;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class OperationSoap {

    @EJB
    private Operation ejb;

    @WebMethod
    public Compte creerCompte(String number, double depot) {
        return ejb.creerCompte(number, depot);
    }

    public Compte getCompte(String number) {
        return ejb.getCompte(number);
    }

    public Compte crediter(String number, double val) {
        return ejb.crediter(number, val);
    }

    public List<Compte> findAllComptes() {
        return ejb.findAllComptes();
    }

    public OperationSoap() {
        super();
        // TODO Auto-generated constructor stub
    }

}
