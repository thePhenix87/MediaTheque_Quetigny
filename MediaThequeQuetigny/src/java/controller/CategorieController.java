/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategorieDao;
import dao.LivreDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Categorie;
import model.Livre;

/**
 *
 * @author 21011-46-09
 */
@Named
@RequestScoped
public class CategorieController {
   
    @Inject
    CategorieDao catDao;
    private HashMap params;
    
    private Categorie categorie;
    private List<Categorie> listCategorie;

    public CategorieController() {
        this.params = new HashMap<String,String>();
         // SqlParam sp = new SqlParam("titre=>livre","date=>21/13/14","date=>21/13/14");
          categorie = new Categorie(); 
          listCategorie = new ArrayList<>();
    }
      /*Initialise la liste de catégorie*/
    public void initCategorie(){
         listCategorie = catDao.getAll();
         System.out.println(listCategorie);
    }
    
    //GETTERS & SETTERS

    public CategorieDao getCatDao() {
        return catDao;
    }

    public void setCatDao(CategorieDao catDao) {
        this.catDao = catDao;
    }

    public HashMap getParams() {
        return params;
    }

    public void setParams(HashMap params) {
        this.params = params;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Categorie> getListCategorie() {
        return listCategorie;
    }

    public void setListCategorie(List<Categorie> listCategorie) {
        this.listCategorie = listCategorie;
    }
    
}