/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CommentaireDao;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Commentaire;
import model.Livre;

/**
 *
 * @author Samuel
 */
@Named
@ViewScoped
public class CommentaireController implements Serializable {
    
    @Inject
    private CommentaireDao commentaireDao;
    private List<Commentaire> commentaires;
    private Commentaire commentaire;
    private SimpleDateFormat sdf;
    
    private String textetemp;
    
    public CommentaireController()
    {
        commentaires=new ArrayList<>();
        sdf = new SimpleDateFormat("d MMMM yyyy");
    }
    
    @PostConstruct
    public void init()
    {
        commentaires = commentaireDao.getAll();
        Collections.sort(commentaires,Collections.reverseOrder());
    }
    
    public void supprimerCommentaire(Commentaire c)
    {
        commentaireDao.delete(c);
        commentaires.remove(c);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Commentaire n°"+c.getIdCommentaire()+" supprimé"));
    }
    
    public void validerCommentaire(Commentaire c)
    {
        commentaireDao.update(c);
        if (c.getAffiche())
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Commentaire n°"+c.getIdCommentaire()+" affiché"));
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Commentaire n°"+c.getIdCommentaire()+" caché"));
    }
    
    public void annoterCommentaire ()
    {
        commentaire.setTexte(commentaire.getTexte()+" Annotation le "+sdf.format(new Date())+" : "+textetemp);
        commentaireDao.update(commentaire);
        textetemp=null;
    }
    
    public CommentaireDao getCommentaireDao() {
        return commentaireDao;
    }

    public void setCommentaireDao(CommentaireDao commentaireDao) {
        this.commentaireDao = commentaireDao;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }

    public String getTextetemp() {
        return textetemp;
    }

    public void setTextetemp(String textetemp) {
        this.textetemp = textetemp;
    }    
}
