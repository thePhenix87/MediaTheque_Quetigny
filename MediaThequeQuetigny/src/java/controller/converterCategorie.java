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



/**
 *
 * @author 21011-46-09
 */

@Named
@RequestScoped
public class converterCategorie implements Serializable, Converter {
    @Inject
    private CategorieController ctrlCategorie;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        
        for(Categorie cat : ctrlCategorie.getListCategorie()){
           if (cat.getIntitule().equals(value)) { //comparaison des intitulé;              
                return (Categorie)cat;
           }     
          }
        return null;
}

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) { 
        
               if(object != null) {
            return String.valueOf(((Categorie) object).getIntitule());
        }
        else {
                   System.out.println(((Categorie)object).getIntitule());
            return null;
        } 
    }
}
