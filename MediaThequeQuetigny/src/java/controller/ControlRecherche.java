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
import org.primefaces.context.RequestContext;

/**
 *
 * @author aka
 */

@Named
@SessionScoped
public class ControlRecherche implements Serializable {
    @Inject
    private LivreDao livreDao;
    
    @Inject
    private CategorieDao categorieDao;
    
    private String termeRecherche;
    
    private String critereRecherche; //titre, catégorie ou auteur
    
    private String categorieRecherche;
    
    private List listeNomCategorie;
    
    //un set pour éviter les doublons
    private Set setResultatsRecherche = new LinkedHashSet();
    
    //une liste car <p:autoComplete> veut une liste
    private List listeResultatsRecherche = new ArrayList();
    
    @PostConstruct
    public void init(){
        System.out.println("loooooooooooooooooooooooool");
        listeNomCategorie=new ArrayList();
        //pour récupérer le nom des catégories dans une liste
        for(Object o : categorieDao.getAll()){
            Categorie c=(Categorie)o;
            listeNomCategorie.add(c.getIntitule());
            System.out.println(c.getIntitule());
        }
        
    }
    
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
        if(recherche!=null){
            setResultatsRecherche.clear();
            listeResultatsRecherche.clear();
            if(critereRecherche=="auteur"){
                setResultatsRecherche.addAll(this.livreDao.listerLivresAuteurDebutantPar(recherche));
                setResultatsRecherche.addAll(this.livreDao.listerLivresAuteurContenant(recherche));
            }else if(critereRecherche=="type"){
            }
            else{
                setResultatsRecherche.addAll(this.livreDao.listerLivresTitreDebutantPar(recherche));
                setResultatsRecherche.addAll(this.livreDao.listerLivresTitreContenant(recherche));
            }
            listeResultatsRecherche.addAll(setResultatsRecherche);
        }
        return listeResultatsRecherche;
        
    }
    
    public String rechercherEtRedirigerVersResultats(){
        this.rechercher();
        RequestContext context = RequestContext.getCurrentInstance(); 
        //update panel 
        context.update("resultatsRechercher");  
        return "recherche.xhtml";
    }
    
    public List completeText(String query){
        List<String> listeLivresDescriptionCourte=new ArrayList();
        List<Livre> listeLivre=recupererListeLivresSelonRecherche(query);
        for(int i=0;i<listeLivre.size();i++){
            listeLivresDescriptionCourte.add(listeLivre.get(i).getDescriptionCourte());
        }
        return listeLivresDescriptionCourte;
    }

    public String getCritereRecherche() {
        return critereRecherche;
    }

    public void setCritereRecherche(String critereRecherche) {
        this.critereRecherche = critereRecherche;
    }

    public List getListeNomCategorie() {
        return listeNomCategorie;
    }

    public void setListeNomCategorie(List listeNomCategorie) {
        this.listeNomCategorie = listeNomCategorie;
    }

    public String getCategorieRecherche() {
        return categorieRecherche;
    }

    public void setCategorieRecherche(String categorieRecherche) {
        this.categorieRecherche = categorieRecherche;
    }
    
    
    
}
