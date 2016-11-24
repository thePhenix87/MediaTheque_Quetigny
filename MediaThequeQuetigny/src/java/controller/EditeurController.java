/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EditeurDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Editeur;

/**
 *
 * @author 21011-46-09
 */

@Named
@RequestScoped
public class EditeurController {
   
    @Inject
    private EditeurDao editeurDao;
   
    private Editeur editeur;
    private List<Editeur> listEditeur;

    public EditeurController() {
          editeur = new Editeur(); 
          listEditeur = new ArrayList<>();
      
    }
      /*Initialise la liste des éditeurs*/
    @PostConstruct
    public void initEditeur(){
         listEditeur = editeurDao.getAll();
         System.out.println(listEditeur);
    }
    //Getters & Setters

    public EditeurDao getEditeurDao() {
        return editeurDao;
    }

    public void setEditeurDao(EditeurDao editeurDao) {
        this.editeurDao = editeurDao;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public List<Editeur> getListEditeur() {
        return listEditeur;
    }

    public void setListEditeur(List<Editeur> listEditeur) {
        this.listEditeur = listEditeur;
    }
    
}
