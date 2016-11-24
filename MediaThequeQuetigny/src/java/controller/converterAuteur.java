/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import model.Auteur;
import model.Categorie;
import model.Editeur;

/**
 *
 * @author 21011-46-09
 */
@Named
@RequestScoped
public class converterAuteur implements Serializable, Converter {
    @Inject
    private AuteurController ctrlAuteur;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        
        for(Auteur auteur : ctrlAuteur.getListAuteur()){
           if (auteur.getNom().equals(value)) { //comparaison des nom d'auteur;              
                return (Auteur)auteur;
           }     
          }
        return null;
}

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) { 
        
               if(object != null) {
            return String.valueOf(((Auteur) object).getNom());
        }
        else {
                   System.out.println(((Auteur)object).getNom());
            return null;
        } 
    }
}
