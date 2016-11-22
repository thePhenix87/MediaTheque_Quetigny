/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afpa.mediatheque.controller;
import fr.afpa.mediatheque.model.Livre;
import fr.afpa.mediatheque.dao.LivreDao;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;


import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

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
    
    private List<Livre> livres ;

    public LivreController() {
        this.params = new HashMap<String,String>();
    }

    @PostConstruct
    public void  init(){
     livres =  livreDao.execNamedQuery("Livre.findAll", null);
    }

    public List<Livre> getLivres(){     
     
        return livres;
    }
    
    public Livre getLivreByTitle(String title){
        params.put("titre", title);
        return (Livre) livreDao.execNamedQuery("Livre.findByTitre",params ).get(0);
    }
    
    
}
