/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CommentaireDao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.Commentaire;

/**
 *
 * @author Samuel
 */
@Named
@RequestScoped
public class CommentaireController {
    
    @Inject
    private CommentaireDao commentaireDao;
    private List<Commentaire> commentaires;
    private SimpleDateFormat sdf;
    
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
}
