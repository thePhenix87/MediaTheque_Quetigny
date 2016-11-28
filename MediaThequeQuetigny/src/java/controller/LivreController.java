/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import dao.CategorieDao;
import model.Livre;
import dao.LivreDao;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.inject.Inject;
import javax.inject.Named;
import model.Auteur;
import model.Categorie;
import model.Editeur;
import org.primefaces.event.FileUploadEvent;
import utilitaires.SqlParam;

/**
 *
 * @author Tarik
 */
@Named
@RequestScoped
public class LivreController {
    
    @Inject
    LivreDao livreDao;
    private HashMap params;
   
    @Inject
    private AuteurController auteurCtrl;
    @Inject
    private EditeurController editeurCtrl;
    @Inject
    private CategorieController categorieCtrl;
    private Livre livre;
    private List<Livre> listLivres ;
    private FacesContext context;
    
    public LivreController() {
        this.params = new HashMap<String,String>();
         // SqlParam sp = new SqlParam("titre=>livre","date=>21/13/14","date=>21/13/14");  
        livre = new Livre();    
        context=FacesContext.getCurrentInstance(); //gestion des messages   
    }

    @PostConstruct
    public void  init(){
        String titre = "Mon titre";
      listLivres =  livreDao.selectWhere(new SqlParam( 
                "titre =>harry"
          
        ));   
    }
  
    public List<Livre> getLivres(){     
     
        return listLivres;
    }
    
    public Livre getLivreByTitle(String title){
        params.put("titre", title);
        return (Livre) livreDao.execNamedQuery("Livre.findByTitre",params ).get(0);
    }
    
    /*TELECHARGER UNE IMAGE*/
     public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Le téléchargement", event.getFile().getFileName() + " est terminé.");
        FacesContext.getCurrentInstance().addMessage(null, message);
         System.out.println("TELECHARGEMENT");
    }
    
    
    //GETTERS & SETTERS
    public LivreDao getLivreDao() {
        return livreDao;
    }
    
    public void setLivreDao(LivreDao livreDao) {
        this.livreDao = livreDao;
    } 
}
