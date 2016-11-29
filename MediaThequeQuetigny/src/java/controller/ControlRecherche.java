/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controller;

import dao.CategorieDao;
import dao.LivreDao;
import dao.NouvelleDao;
import dao.UtilisateurDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.Categorie;
import model.Livre;
import model.Nouvelle;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author aka
 */

@Named
@SessionScoped
public class ControlRecherche implements Serializable {
    @Inject
    private LivreDao livreDao;
    
    private String termeRecherche;
    
    //un set pour éviter les doublons
    Set setResultatsRecherche = new LinkedHashSet();
    
    //une liste car <p:autoComplete> veut une liste
    List listeResultatsRecherche = new ArrayList();
    
    
    
    public LivreDao getLivreDao() {
        return livreDao;
    }
    
    public void setLivreDao(LivreDao livreDao) {
        this.livreDao = livreDao;
    }
    
    public String getTermeRecherche() {
        return termeRecherche;
    }
    
    public void setTermeRecherche(String termeRecherche) {
        this.termeRecherche = termeRecherche;
    }
    
    public List rechercher(){
        System.out.println("Il faut chercher "+termeRecherche);
        //on cherche dans le titre
        return recupererListeLivresSelonRecherche(termeRecherche);
    }
    
    public List recupererListeLivresSelonRecherche(String recherche){
        
        setResultatsRecherche.clear();
        listeResultatsRecherche.clear();
        
        setResultatsRecherche.addAll(this.livreDao.listerLivresTitreDebutantPar(recherche));
        setResultatsRecherche.addAll(this.livreDao.listerLivresTitreContenant(recherche));
        
        listeResultatsRecherche.addAll(setResultatsRecherche);
        return listeResultatsRecherche;
        
    }
    
    public List completeText(String query){
        return recupererListeLivresSelonRecherche(query);
    }
    
}
