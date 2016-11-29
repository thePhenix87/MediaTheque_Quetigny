/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UtilisateurDao;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Utilisateur;

/**
 *
 * @author onesh
 */
@Named
@SessionScoped
public class controllerAdministrateur implements Serializable {
    
    
    private Utilisateur util,utilselected;
    @Inject
    private UtilisateurDao utildao;
    private List<Utilisateur> listUtil;
    private HashMap param;
    
    public controllerAdministrateur(){
       util = new Utilisateur();
        
    }

     public void rechercherUtil(){
        utildao.find(Integer.SIZE);
    }
    
     public void supprimerUtil(Utilisateur u){
         System.out.println("rentre dans supprimer util");
         System.err.println(u);
         utildao.delete(u);
     }
    
     public void ajouterUtil(Utilisateur utilselected){
         System.out.println("rentre dans ajouter util");
         utildao.create(utilselected);
     }
     
     public void modifierUtil(Utilisateur utilselected){
         System.out.println("rentre dans modifier util");
         utildao.update(utilselected);
     }
     
    /**
     * @return the util
     */
    public Utilisateur getUtil() {
        return util;
    }

    /**
     * @param util the util to set
     */
    public void setUtil(Utilisateur util) {
        this.util = util;
    }

    /**
     * @return the utildao
     */
    public UtilisateurDao getUtildao() {
        return utildao;
    }

    /**
     * @param utildao the utildao to set
     */
    public void setUtildao(UtilisateurDao utildao) {
        this.utildao = utildao;
    }

    /**
     * @return the listUtil
     */
    public List<Utilisateur> getListUtil() {
        return utildao.getAll();
    }

    /**
     * @param listUtil the listUtil to set
     */
    public void setListUtil(List<Utilisateur> listUtil) {
        this.listUtil = listUtil;
    }

    /**
     * @return the utilselected
     */
    public Utilisateur getUtilselected() {
        return utilselected;
    }

    /**
     * @param utilselected the utilselected to set
     */
    public void setUtilselected(Utilisateur utilselected) {
        this.utilselected = utilselected;
    }
    
    
    
    
    
}
