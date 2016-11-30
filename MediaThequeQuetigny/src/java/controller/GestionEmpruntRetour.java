/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmpruntDao;
import dao.ExemplaireDao;
import dao.LivreDao;
import dao.UtilisateurDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.Emprunt;
import model.Exemplaire;
import model.Livre;
import model.Utilisateur;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Gabriel
 */
@Named
@RequestScoped

public class GestionEmpruntRetour {

    @Inject
    EmpruntDao edao;
    @Inject
    LivreDao ldao;
    @Inject
    ExemplaireDao exdao;
    @Inject
    UtilisateurDao udao;
    @Inject
    MailSend mailSend;

    private int livre, user;
    private List<Emprunt> listE;
    private boolean us = true, ex = true;

    public GestionEmpruntRetour() {
        List<Emprunt> listE = new ArrayList<Emprunt>();
        Utilisateur user1= new Utilisateur();
    }

    public void emprunt() {

        Date d = new Date();
        Utilisateur u = new Utilisateur();
        u.setIdUtilisateur(user);
        u = udao.find(u.getIdUtilisateur());
        Exemplaire ex = exdao.find(livre);
        List<Emprunt> l = edao.getListSansDateRetourSemaineGlissante(u);

        // verifies que le exemplaire est  disponible
        if (u == null) {
            this.affichermsg(8);
        } else if (ex == null) {
            this.affichermsg(9);
        } else if (!ex.getStatut()) {
            this.affichermsg(6);
        } else if (l == null || this.verifierposibilite(l)) {// teste l'egibilite de emprunt d'usager

            this.stockerEmprunt(u, ex, new Date());
        }

    }

    //verifie si le utilisateur es capable de emprunter plusiers livres
    public boolean verifierposibilite(List<Emprunt> l) {

        Calendar c = Calendar.getInstance();// date acutal
        Calendar cm7 = Calendar.getInstance();
        Calendar cm21 = Calendar.getInstance();
        cm7.add(Calendar.DATE, -7);//date mois sept pour la semaine glisante

        int ctrsg = 0;//compteur de semaine glissante
        System.out.println(l.size() + " sise");

        // pour compter le livres empruntes pendant cette semaine glissante
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getDateEmprunt().after(cm7.getTime())) {
                ctrsg++;
            }
            cm21.setTime(l.get(i).getDateEmprunt());
            cm21.add(Calendar.DATE, 21);

            if (cm21.before(c) && l.get(i).getDateRetour() == null) {// pour tester si le utilisateur a des emprunts avec les tempts de retour depase

                this.affichermsg(1);
                return false;
            }
        }
        if (ctrsg > 2) {// plus de trois exemplaires en une semaine glissante

            this.affichermsg(2);
            return false;
        }

        return true;
    }

    // Methode pour retourne le Exemplaire
    public void retour() {

        Exemplaire ex = exdao.find(livre);
        Emprunt e = this.edao.geEmprunParEx(ex);

        if (e == null) { // aucun emprunt trouve
            this.affichermsg(4);
        } else {// gestion de retour
            e.setDateRetour(new Date());
            this.edao.update(e);
            e.getIdExemplaire().setStatut(true);
            this.exdao.update(e.getIdExemplaire());
            this.affichermsg(5);
        }

    }

    //  recherces la list d'emprunts d'utilisateur
    public void searchListEmpruntsParUser() {
        Utilisateur u =  udao.find(user);
        ex = true;
        us = false;
        this.listE = this.edao.getList(u);
    }

    // recherches la list par Exemplaire
    public void searchListEmpruntsParExemplaire() {
        Exemplaire e = exdao.find(livre);
        ex = false;
        us = true;
        this.listE = this.edao.getListparEx(e);
    }

    public void searchListEmpruntsParLivre() {
        Livre l = ldao.find(livre);
        ex = true;
        us = true;
        this.listE = this.edao.getListparLivre(l);
    }

    // Gestion du Messages dan la interface
    public void affichermsg(int i) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;

        switch (i) {

            case 1:
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Un Exemplaire pas rendu en temps ", "Date Limite de livre est depase");
                break;
            case 2:
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Limite de Exemplaires est atteinte", "Limite de Livres par semaine depase");
                break;
            case 3:
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Exmpleaire Emprunte", "Exmpleaire Emprunte");
                break;
            case 4:
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Emprunt pas Trouve", "Acun information liee aux donnees");
                break;
            case 5:
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Rendre effectue", "Le Retour a ete registre");
                break;
            case 6:
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Exemplaire pas disponible", "Exemplaire pas disponible");
                break;
            case 7:
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Exemplaire pas disponible", "Exemplaire pas disponible");
                break;
            case 8:
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Utilisateur pas trouve");
                break;
            case 9:
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Exemplaire pas trouve");
                break;
            case 10:
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Error de Saisie");

                break;

        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // envoies un mail a tous les utilisateur avec la date depase
    public void envoyerMail() {
      
        
        List<Emprunt> l = this.edao.getListDepaseDate();
        
        for (int i = 0; i < l.size(); i++) {
              
            mailSend.sendMailNOtification(l.get(i));
        }
        this.affichermsg(7);

    }

    public void stockerEmprunt(Utilisateur u, Exemplaire ex, Date d) {

        Emprunt e = new Emprunt();
        e.setIdUtilisateur(u);
        e.setIdExemplaire(ex);
        e.setDateEmprunt(d);
        e.setDateRetour(null);
        edao.create(e);
        ex.setStatut(false);
        exdao.update(ex);
        this.affichermsg(3);
    }

    public EmpruntDao getEdao() {
        return edao;
    }

    public void setEdao(EmpruntDao edao) {
        this.edao = edao;
    }

    public LivreDao getLdao() {
        return ldao;
    }

    public void setLdao(LivreDao ldao) {
        this.ldao = ldao;
    }

    public ExemplaireDao getExdao() {
        return exdao;
    }

    public void setExdao(ExemplaireDao exdao) {
        this.exdao = exdao;
    }

    public UtilisateurDao getUdao() {
        return udao;
    }

    public void setUdao(UtilisateurDao udao) {
        this.udao = udao;
    }

    public List<Emprunt> getListE() {
        return listE;
    }

    public void setListE(List<Emprunt> listE) {
        this.listE = listE;
    }

    public int getLivre() {
        return livre;
    }

    public void setLivre(int livre) {
        this.livre = livre;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public boolean isUs() {
        return us;
    }

    public void setUs(boolean us) {
        this.us = us;
    }

    public boolean isEx() {
        return ex;
    }

    public void setEx(boolean ex) {
        this.ex = ex;
    }

}
