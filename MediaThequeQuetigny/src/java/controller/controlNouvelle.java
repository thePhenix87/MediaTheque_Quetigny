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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private String sousTitreEcrireModifier;
    

    @PostConstruct
    public void init(){
        nouvelle=new Nouvelle();
        sousTitreEcrireModifier="Ecrire";
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
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("on poste");
        try{
            nouvelleDao.update(nouvelle);
            context.addMessage(null, new FacesMessage("Nouvelle ajoutée"));
        } catch (Exception e){
            context.addMessage(null, new FacesMessage("Successful",  e.getMessage()));

        }
    }
    
     public void modifier(){
         sousTitreEcrireModifier="Modifier";
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

    public String getSousTitreEcrireModifier() {
        return sousTitreEcrireModifier;
    }

    public void setSousTitreEcrireModifier(String sousTitreEcrireModifier) {
        this.sousTitreEcrireModifier = sousTitreEcrireModifier;
    }
    
    public void instancierNouvelleVierge(){
        nouvelle=new Nouvelle();
        sousTitreEcrireModifier="Modifier";
    }



}
