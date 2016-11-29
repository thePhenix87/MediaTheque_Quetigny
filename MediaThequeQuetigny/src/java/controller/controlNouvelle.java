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
import java.util.List;
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
    private boolean boutonSupprimerVraiOuFaux;
    private List<Nouvelle> listeNouvelles;
    

    @PostConstruct
    public void init(){
        nouvelle=new Nouvelle();
        sousTitreEcrireModifier="Créer";
        boutonSupprimerVraiOuFaux=false;
        listeNouvelles=nouvelleDao.getAll();
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
            //update ou créé
            nouvelleDao.update(nouvelle);
            context.addMessage(null, new FacesMessage("Nouvelle ajoutée"));
            listeNouvelles.clear();
            listeNouvelles.addAll(nouvelleDao.getAll());
            this.instancierNouvelleVierge();
        } catch (Exception e){
            context.addMessage(null, new FacesMessage("Erreur :",  e.getMessage()));

        }
    }
    
    public void supprimer(){
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("on supprime");
        try{
            nouvelleDao.delete(nouvelle);
            context.addMessage(null, new FacesMessage("Nouvelle supprimée"));
            listeNouvelles.clear();
            listeNouvelles.addAll(nouvelleDao.getAll());
            this.instancierNouvelleVierge();
        } catch (Exception e){
            context.addMessage(null, new FacesMessage("Erreur :",  e.getMessage()));
        }
    }
    
     public void modifier(){
         sousTitreEcrireModifier="Modifier";
         boutonSupprimerVraiOuFaux=true;
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
        sousTitreEcrireModifier="Créer";
        boutonSupprimerVraiOuFaux=false;
    }

    public boolean isBoutonSupprimerVraiOuFaux() {
        return boutonSupprimerVraiOuFaux;
    }

    public void setBoutonSupprimerVraiOuFaux(boolean boutonSupprimerVraiOuFaux) {
        this.boutonSupprimerVraiOuFaux = boutonSupprimerVraiOuFaux;
    }

    public List<Nouvelle> getListeNouvelles() {
        return listeNouvelles;
    }

    public void setListeNouvelles(List<Nouvelle> listeNouvelle) {
        this.listeNouvelles = listeNouvelle;
    }



}
