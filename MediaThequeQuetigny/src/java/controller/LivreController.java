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

import javax.inject.Inject;
import javax.inject.Named;
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
     @Inject
     CategorieDao catDao;
    private HashMap params;
    
    private List<Livre> livres ;

    public LivreController() {
        this.params = new HashMap<String,String>();
         // SqlParam sp = new SqlParam("titre=>livre","date=>21/13/14","date=>21/13/14");
    }

    @PostConstruct
    public void  init(){
        String titre = "Mon titre";
     livres =  livreDao.selectWhere(new SqlParam( 
                "titre =>harry"
              
        ));
       
     
    }

    public List<Livre> getLivres(){     
     
        return livres;
    }
    
    public Livre getLivreByTitle(String title){
        params.put("titre", title);
        return (Livre) livreDao.execNamedQuery("Livre.findByTitre",params ).get(0);
    }
    
    
}
