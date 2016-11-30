/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CommentaireDao;
import dao.LivreDao;
import dao.UtilisateurDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;
import model.Commentaire;
import model.Livre;
import model.Utilisateur;
import org.primefaces.event.RateEvent;

/**
 *
 * @author yasser
 */
@Named
@SessionScoped
public class ControlHome implements Serializable{
    
   @Inject
   private LivreDao daol;
   @Inject
   private CommentaireDao daocm;
   @Inject
   private UtilisateurDao daou;
   private List<Livre> livres;
   private Livre selectedlivre;
   
   private Commentaire cm;
   private Utilisateur user;
   private int note;
   private int stars;
   private String commentaire;
   private List<Commentaire> comvalides;
     public ControlHome(){
            cm=new Commentaire();
            System.out.println("Constructor ");
     }

    /**
     * @return the livres
     */
   @PostConstruct
    public void init() {
      livres=daol.getAll();
      user=new Utilisateur();
      selectedlivre=new Livre();
      commentaire=" lol";
      comvalides=new ArrayList();
    
    }
  
    
    
    public void ajoutercommentaire(){
      
      
        user=daou.find(1);
        
      cm.setAffiche(false);
      //  getCm().setTexte(commentaire);*/
        cm.setCodeLivre(this.selectedlivre);
        cm.setIdUtilisateur(user);
        cm.setDateCommentaire(new Date());
       // System.out.println(cm+"from controller");
        //cm.setNote(note);
        daocm.create(cm);
        //this.setNote(0);
        cm = new Commentaire();
       
      //  System.out.println(selectedlivre.getCodeLivre());
      
       
    }

       

    
    
    public List<Livre> getLivres() {
        return livres;
    }

    /**
     * @param livres the livres to set
     */
    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    /**
     * @return the selectedlivre
     */
    public Livre getSelectedlivre() {
        return selectedlivre;
    }

    /**
     * @param selectedlivre the selectedlivre to set
     */
    public void setSelectedlivre(Livre selectedlivre) {
        this.selectedlivre = selectedlivre;
    }

    /**
     * @return the cm
     */
    public Commentaire getCm() {
        return cm;
    }

    /**
     * @param cm the cm to set
     */
    public void setCm(Commentaire cm) {
        this.cm = cm;
    }

   

    /**
     * @return the stars
     */
    public int getStars() {
      // System.out.println(selectedlivre.getCodeLivre());
        
        
        if (selectedlivre.getCodeLivre()!=null){
            
          stars=daocm.stars(selectedlivre.getCodeLivre());
         
        }
            
        
        return stars;
    }
    

    /**
     * @param stars the stars to set
     */
    public void setStars(int stars) {
        this.stars = stars;
    }

    /**
     * @return the note
     */
    public int getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(int note) {
        this.note = note;
    }

    /**
     * @return the note
     */
    

   public void onrate(RateEvent rateEvent) {
        note=((Integer) rateEvent.getRating()).intValue();
  
    }
     
    public void oncancel() {
       note=0;
    }

    /**
     * @return the commentaire
     */
    public String getCommentaire() {
      
        return commentaire;
    }

    /**
     * @param commentaire the commentaire to set
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * @return the comvalides
     */
    public List<Commentaire> getComvalides() {
         
        if (selectedlivre.getCodeLivre()!=null){
            
          this.comvalides=daocm.comvalides(selectedlivre.getCodeLivre());
         
        }
        return comvalides;
    }

    /**
     * @param comvalides the comvalides to set
     */
    public void setComvalides(List<Commentaire> comvalides) {
        this.comvalides = comvalides;
    }

 
   
    
}
