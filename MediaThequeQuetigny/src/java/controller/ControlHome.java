/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CommentaireDao;
import dao.LivreDao;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Commentaire;
import model.Livre;
import model.Utilisateur;

/**
 *
 * @author yasser
 */
@Named
@RequestScoped
public class ControlHome implements Serializable{
    
   @Inject
   private LivreDao daol;
   @Inject
   private CommentaireDao daocm;
   private List<Livre> livres;
   private Livre selectedlivre;
   private Commentaire cm;
   private Utilisateur user;
   private int note;

    /**
     * @return the livres
     */
   @PostConstruct
    public void init() {
      livres=daol.getAll();
      user=new Utilisateur();
    }
    
    
    public void ajoutercommentaire(){
        cm.setAffiche(false);
        cm.setCodeLivre(selectedlivre);
        cm.setIdUtilisateur(user);
        cm.setDateCommentaire(new Date());
        cm.setNote(note);
        cm.setTexte(cm.getTexte());
     
       daocm.create(cm);
        
      
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
   
    
}
