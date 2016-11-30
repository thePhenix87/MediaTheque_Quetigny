/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ExemplaireDao;
import dao.LivreDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Exemplaire;
import model.Livre;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import utilitaires.SqlParam;

/**
 *
 * @author Tarik
 */
@Named
@SessionScoped
public class LivreController implements Serializable {

    @Inject
    LivreDao livreDao;
    private HashMap params;
    @Inject
    private ExemplaireDao exemplaireDao;
    @Inject
    private AuteurController auteurCtrl;
    @Inject
    private EditeurController editeurCtrl;
    @Inject
    private CategorieController categorieCtrl;

    private Exemplaire exemplaire;
    private boolean statutExemplaire;
    private Livre livre;
    private List<Livre> listLivres;
    private FacesContext context;
    private String anneeString;
    private int nbrExemplaire;
    private String statutLivre;
    UploadedFile file;
    String destination;
    String newFileName;

    public LivreController() {
        this.params = new HashMap<String, String>();
        // SqlParam sp = new SqlParam("titre=>livre","date=>21/13/14","date=>21/13/14");  
        livre = new Livre();
        exemplaire = new Exemplaire();
        statutExemplaire = true;
        anneeString = "";
        context = FacesContext.getCurrentInstance(); //gestion des messages   
        statutLivre = "Disponible";
        nbrExemplaire = 0;
        System.out.println("From Contructor livre");
    }

    @PostConstruct
    public void init() {
        String titre = "Mon titre";
        listLivres = livreDao.getAll();
        destination = FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("/") ;
        String[] parts = destination.split("build");
        parts[0] = parts[0].substring(0,parts[0].length()-1);
        System.out.println(parts[0]);
        System.out.println(parts[1]);
        destination=parts[0]+parts[1];
        destination+="resources"+File.separator+"img"+File.separator;
        System.out.println(destination);
        
    }

    public List<Livre> getLivres() {

        return listLivres;
    }

    public Livre getLivreByTitle(String title) {
        params.put("titre", title);
        return (Livre) livreDao.execNamedQuery("Livre.findByTitre", params).get(0);
    }

    /*TELECHARGER UNE IMAGE*/
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Le téléchargement", event.getFile().getFileName() + " est terminé.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        System.out.println("TELECHARGEMENT");
    }

    public void doUploadFile(FileUploadEvent event) throws IOException {
        file = event.getFile();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        newFileName = sdf.format(new Date()) + file.getFileName().substring(
                event.getFile().getFileName().lastIndexOf('.'));;
        try {
            FileInputStream in = (FileInputStream) file.getInputstream();
            FileOutputStream out = new FileOutputStream(destination + newFileName);
            byte[] buffer = new byte[(int) file.getSize()];
            int conteur = 0;
            while ((conteur = in.read(buffer)) != -1) {
                out.write(buffer, 0, conteur);
            }
            in.close();
            out.close();
            livre.setImage(newFileName);

            System.out.println(destination + file.getFileName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //AJOUTER UNE LIVRE

    public void ajouterLivre() {
        System.out.println(livre.getImage());
        livre.setIdAuteur(auteurCtrl.getAuteur());
        livre.setIdEditeur(editeurCtrl.getEditeur());
        livre.setIdCategorie(categorieCtrl.getCategorie());
        livre.setTitre(this.livre.getTitre());
        livre.setAnneeEdition(new Date());
        livre.setDescription(this.livre.getDescription());
        livre.setStatut(statutLivre);
        
        livreDao.debugLivre(livre);
        livreDao.create(livre);// Insérer un livre  
        
        /*Insérer le nombre d'exemplaire*/
        for (int i = 0; i < nbrExemplaire; i++) {
            exemplaire.setCodeLivre(this.livre);
            exemplaire.setStatut(statutExemplaire);
            exemplaireDao.create(exemplaire);
        }
        listLivres.add(livre);
        auteurCtrl.getListAuteur().add(auteurCtrl.getAuteur());
        editeurCtrl.getListEditeur().add(editeurCtrl.getEditeur());
        categorieCtrl.getListCategorie().add(categorieCtrl.getCategorie());
        FacesMessage msg = new FacesMessage("Le livre " + livre.getTitre() + " a bien été Ajouté");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        livreDao.update(livre);
    }

    /*MODIFIER FORMAT DATE pour l'année d'édition du livre*/
    public Date modifDate() {
        Calendar c = Calendar.getInstance();
        System.out.println("antes");
        c.set(Integer.parseInt(this.anneeString), 01, 01);
        System.out.println(c.getTime());
        ///this.livre.setAnneeEdition(c.getTime());
        return c.getTime();

    }

    //GETTERS & SETTERS
    public int getNbrExemplaire() {
        return nbrExemplaire;
    }

    public FacesContext getContext() {
        return context;
    }

    public void setContext(FacesContext context) {
        this.context = context;
    }

    public void setNbrExemplaire(int nbrExemplaire) {
        this.nbrExemplaire = nbrExemplaire;
    }

    public ExemplaireDao getExemplaireDao() {
        return exemplaireDao;
    }

    public void setExemplaireDao(ExemplaireDao exemplaireDao) {
        this.exemplaireDao = exemplaireDao;
    }

    public AuteurController getAuteurCtrl() {
        return auteurCtrl;
    }

    public void setAuteurCtrl(AuteurController auteurCtrl) {
        this.auteurCtrl = auteurCtrl;
    }

    public EditeurController getEditeurCtrl() {
        return editeurCtrl;
    }

    public void setEditeurCtrl(EditeurController editeurCtrl) {
        this.editeurCtrl = editeurCtrl;
    }

    public CategorieController getCategorieCtrl() {
        return categorieCtrl;
    }

    public void setCategorieCtrl(CategorieController categorieCtrl) {
        this.categorieCtrl = categorieCtrl;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public boolean isStatutExemplaire() {
        return statutExemplaire;
    }

    public void setStatutExemplaire(boolean statutExemplaire) {
        this.statutExemplaire = statutExemplaire;
    }

    public String getStatutLivre() {
        return statutLivre;
    }

    public void setStatutLivre(String statutLivre) {
        this.statutLivre = statutLivre;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public List<Livre> getListLivres() {
        return listLivres;
    }

    public void setListLivres(List<Livre> listLivres) {
        this.listLivres = listLivres;
    }

    public LivreDao getLivreDao() {
        return livreDao;
    }

    public void setLivreDao(LivreDao livreDao) {
        this.livreDao = livreDao;
    }

    public String getAnneeString() {
        return anneeString;
    }

    public void setAnneeString(String anneeString) {
        this.anneeString = anneeString;
    }

}
