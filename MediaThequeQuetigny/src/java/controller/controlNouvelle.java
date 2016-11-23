/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.NouvelleDao;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Nouvelle;

/**
 *
 * @author aka
 */

@Named
@SessionScoped
public class controlNouvelle implements Serializable {
    @Inject
    private NouvelleDao nouvelleDao;
    private Nouvelle nouvelle;

    public NouvelleDao getNouvelleDao() {
        return nouvelleDao;
    }

    public void setNouvelleDao(NouvelleDao nouvelleDao) {
        this.nouvelleDao = nouvelleDao;
    }

    public Nouvelle getNouvelle() {
        return nouvelle;
    }

    public void setNouvelle(Nouvelle nouvelle) {
        this.nouvelle = nouvelle;
    }
    
}
