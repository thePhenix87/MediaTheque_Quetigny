/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AuteurDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Auteur;

/**
 *
 * @author 21011-46-09
 */
@Named
@RequestScoped
public class AuteurController {
   
    @Inject
    private AuteurDao auteurDao;
   
    private Auteur auteur;
    private List<Auteur> listAuteur;

    public AuteurController() {
          auteur = new Auteur(); 
          listAuteur = new ArrayList<>();
    }
      /*Initialise la liste des auteurs*/
    @PostConstruct
    public void initAuteur(){
         listAuteur = auteurDao.getAll();     
    }
    
    //Getters & Setters

    public AuteurDao getAuteurDao() {
        return auteurDao;
    }
    public void setAuteurDao(AuteurDao auteurDao) {
        this.auteurDao = auteurDao;
    }
    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public List<Auteur> getListAuteur() {
        return listAuteur;
    }

    public void setListAuteur(List<Auteur> listAuteur) {
        this.listAuteur = listAuteur;
    }
    

}