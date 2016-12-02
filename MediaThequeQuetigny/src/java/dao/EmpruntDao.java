/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import model.Emprunt;
import model.Exemplaire;
import model.Livre;
import model.Utilisateur;

/**
 *
 * @author Gabriel
 */
@Stateless
public class EmpruntDao extends DAO_IMPL<Emprunt> {

    public EmpruntDao() {
        super(Emprunt.class);
    }

    public List<Emprunt> getListSansDateRetourSemaineGlissante(Utilisateur u) {
        System.out.println(u.getIdUtilisateur());
        Calendar cm7 = Calendar.getInstance();
        cm7.add(Calendar.DATE, -7);
        Query q = this.getEm().createQuery("SELECT e FROM Emprunt e where (e.idUtilisateur =:iduser and  e.dateRetour =null) OR (e.dateEmprunt>:date AND e.idUtilisateur =:iduser) order by e.dateEmprunt desc", Emprunt.class);
        q.setParameter("iduser", u);
        q.setParameter("date", cm7.getTime());
        List<Emprunt> l = q.getResultList();

        if (l.size() < 1) {
            return null;
        }

        return l;
    }

    public List<Emprunt> getList(Utilisateur u) {
        Query q = this.getEm().createQuery("SELECT e FROM Emprunt e where  e.idUtilisateur =:iduser order by e.dateEmprunt desc", Emprunt.class);
        q.setParameter("iduser", u);
        List<Emprunt> l = q.getResultList();

        if (l.size() < 1) {
            return null;
        }
        return l;
    }

    public List<Emprunt> getListparEx(Exemplaire ex) {
        Query q = this.getEm().createQuery("SELECT E FROM Emprunt E where E.idExemplaire  =:idex order by e.dateEmprunt desc", Emprunt.class);
        q.setParameter("idex", ex);
        List<Emprunt> l = q.getResultList();

        if (l.size() < 1) {
            return null;
        }
        return l;
    }
    
     public Emprunt geEmprunParEx(Exemplaire ex) {
        Query q = this.getEm().createQuery("SELECT E FROM Emprunt E where E.idExemplaire=:idex AND E.dateRetour=null order by e.dateEmprunt desc", Emprunt.class);
        q.setParameter("idex", ex);
        List<Emprunt> l = q.getResultList();

        if (l.size() < 1) {
            return null;
        }
        return l.get(0);
    }

    public List<Emprunt> getListparLivre(Livre lv) {
        Query q = this.getEm().createQuery("SELECT E FROM Emprunt E where E.idExemplaire.codeLivre  =:codel order by e.dateEmprunt desc", Emprunt.class);
        q.setParameter("codel", lv);
        List<Emprunt> l = q.getResultList();

        if (l.size() < 1) {
            return null;
        }
        return l;
    }

    public Emprunt getEmpruntUserExe(Utilisateur u, Exemplaire ex) {
        Query q = this.getEm().createQuery("SELECT e FROM Emprunt e where e.idUtilisateur =:iduser and e.dateRetour =null AND e.idExemplaire=:ex", Emprunt.class);
        q.setParameter("iduser", u);
        q.setParameter("ex", ex);

        try {
            Emprunt e = (Emprunt) q.getSingleResult();
            return e;
        } catch (NoResultException NRE) {
            return null;
        }

    }

    public List<Emprunt> getListDepaseDate() {
        Calendar cm21 = Calendar.getInstance();
        cm21.add(Calendar.DATE, -21);
        Query q = this.getEm().createQuery("SELECT e FROM Emprunt e where e.dateRetour =null and e.dateEmprunt>:date order by e.dateEmprunt desc", Emprunt.class);
        q.setParameter("date", cm21.getTime());
        List<Emprunt> l = q.getResultList();

        if (l.size() < 1) {
            return null;
        }

        return l;
    }
}
