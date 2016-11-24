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
import model.Categorie;
import model.Editeur;

/**
 *
 * @author 21011-46-09
 */
@Named
@RequestScoped
public class converterEditeur implements Serializable, Converter {
    @Inject
    private EditeurController ctrlEditeur;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        
        for(Editeur edit : ctrlEditeur.getListEditeur()){
           if (edit.getNom().equals(value)) { //comparaison des nom d'éditeur;              
                return (Editeur)edit;
           }     
          }
        return null;
}

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) { 
        
               if(object != null) {
            return String.valueOf(((Editeur) object).getNom());
        }
        else {
                   System.out.println(((Editeur)object).getNom());
            return null;
        } 
    }
}
