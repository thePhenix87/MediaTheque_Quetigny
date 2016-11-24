/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategorieDao;
import dao.NouvelleDao;
import dao.UtilisateurDao;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Categorie;
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
    @Inject
    private CategorieDao categorieDao;
    @Inject
    private UtilisateurDao utilisateurDao;
    private Nouvelle nouvelle;
    private int idNouvelleSelectionnee;
    

    @PostConstruct
    public void init(){
        nouvelle=new Nouvelle();
        idNouvelleSelectionnee=0;
    }
    
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
    
    public void poster(){
        System.out.println("on poste");
        nouvelle.setIdNouvelle(1);
        nouvelleDao.create(nouvelle);
    }

    public int getIdNouvelleSelectionnee() {
        return idNouvelleSelectionnee;
    }

    public void setIdNouvelleSelectionnee(int idNouvelleSelectionnee) {
        this.idNouvelleSelectionnee = idNouvelleSelectionnee;
    }

    public CategorieDao getCategorieDao() {
        return categorieDao;
    }

    public void setCategorieDao(CategorieDao categorieDao) {
        this.categorieDao = categorieDao;
    }

    public UtilisateurDao getUtilisateurDao() {
        return utilisateurDao;
    }

    public void setUtilisateurDao(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }



}
