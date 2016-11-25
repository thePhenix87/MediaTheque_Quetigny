/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CommentaireDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
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
    
    public CommentaireController()
    {
        commentaires=new ArrayList<Commentaire>();
    }
    
    @PostConstruct
    public void init()
    {
        commentaires = commentaireDao.getAll();
        Collections.sort(commentaires);
    }
    
    public void supprimerCommentaire(Commentaire c)
    {
        commentaireDao.delete(c);
        commentaires.remove(c);
        System.out.println("Suppression de "+c+" ("+c.getTexte()+")");
    }
    
    public void validerCommentaire(Commentaire c)
    {
        commentaireDao.update(c);
        System.out.println("ok");
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
}
