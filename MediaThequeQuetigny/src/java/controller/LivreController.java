/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import dao.CategorieDao;
import model.Livre;
import dao.LivreDao;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;


import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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
@SessionScoped
public class LivreController implements Serializable {
    
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
//      listLivres =  livreDao.selectWhere(new SqlParam( 
//                "titre =>harry"
//          
//        ));  
        listLivres=livreDao.getAll();
    }
    
    public Livre getLivreByTitle(String title){
        params.put("titre", title);
        return (Livre) livreDao.execNamedQuery("Livre.findByTitre",params ).get(0);
    }
    
     //AJOUTER UNE NOTE
   /*  public void ajouterLivre() {
        
         livre.setIdEditeur(this.editeurCtrl.getEditeur());
         livre.setIdAuteur(auteurCtrl.getAuteur());
         livre.setIdCategorie(categorieCtrl.getCategorie());
         livre.setTitre(titre);
         livre.setAnneeEdition(anneeEdition);
         livre.setDescription(description);
         
         note. setIdCours(this.cours);
         note.setIdEleve(this.eleve);      
         note.setDate(new Date());  
         daon.ecrire(note);
         daoe.maj(eleve);
         listNotes.add(note);
         FacesMessage msg = new FacesMessage("La note de "+note.getIdEleve().getNom()+" "+note.getIdEleve().getPrenom()+" matière: '"+note.getIdCours().getNom()+" "+"':"+note.getNote()+" a bien été Ajouté" );
         FacesContext.getCurrentInstance().addMessage(null, msg);     
    }   */
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

    public HashMap getParams() {
        return params;
    }

    public void setParams(HashMap params) {
        this.params = params;
    }

    public AuteurController getAuteurCtrl() {
        return auteurCtrl;
    }

    public void setAuteurCtrl(AuteurController auteurCtrl) {
        this.auteurCtrl = auteurCtrl;
    }

    public EditeurController getEditeurCtrl() {
        return editeurCtrl;
    }

    public void setEditeurCtrl(EditeurController editeurCtrl) {
        this.editeurCtrl = editeurCtrl;
    }

    public CategorieController getCategorieCtrl() {
        return categorieCtrl;
    }

    public void setCategorieCtrl(CategorieController categorieCtrl) {
        this.categorieCtrl = categorieCtrl;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public List<Livre> getLivres(){     
     
        return listLivres;
    }

    public void setListLivres(List<Livre> listLivres) {
        this.listLivres = listLivres;
    }

    public FacesContext getContext() {
        return context;
    }

    public void setContext(FacesContext context) {
        this.context = context;
    }
}
