/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author aka
 */
import dao.UtilisateurDao;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import model.Utilisateur;

@RequestScoped
@Named
public class converterUtilisateur implements Converter{
    @Inject
    private UtilisateurDao utilisateurDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("aeaeaeae");
        return utilisateurDao.find(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("ppppopo");
        return String.valueOf(((Utilisateur)value).getIdUtilisateur());
    }


}
