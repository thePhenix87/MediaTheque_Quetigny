/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UtilisateurDao;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Utilisateur;
import static utilitaires.Sha256.SHA;
import utilitaires.SqlParam;

/**
 *
 * @author Samuel
 */
@Named
@SessionScoped
public class UtilisateurController implements Serializable{
    
    @Inject
    private UtilisateurDao utilisateurDao;
    private Utilisateur utilisateur;    
    private Utilisateur nouvelutilisateur;
    
    public UtilisateurController()
    {
        nouvelutilisateur=new Utilisateur();
    }
    
    public void creerUtilisateur()
    {

        if (utilisateurDao.selectWhere(new SqlParam("login=>"+nouvelutilisateur.getLogin()))!=null)
            System.out.println("Pseudo déja utilisé");
        else
        {
            try {
                nouvelutilisateur.setMdp(SHA(nouvelutilisateur.getMdp()));
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Logger.getLogger(UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
            }
            utilisateurDao.create(nouvelutilisateur);
            System.out.println("Utilisateur crée");
        }
    }

    public UtilisateurDao getUtilisateurDao() {
        return utilisateurDao;
    }

    public void setUtilisateurDao(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getNouvelutilisateur() {
        return nouvelutilisateur;
    }

    public void setNouvelutilisateur(Utilisateur nouvelutilisateur) {
        this.nouvelutilisateur = nouvelutilisateur;
    }
    
}
