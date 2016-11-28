/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import model.Emprunt;
import model.Utilisateur;

/**
 *
 * @author Gabriel
 */
@Stateless
public class EmpruntDao extends DAO_IMPL<Emprunt>{
    
    
    public EmpruntDao()
    {
        super(Emprunt.class);
    }
    
    public List<Emprunt> getlist(Date d, Utilisateur u)
    { 
        Query q = this.getEm().createQuery("SELECT e FROM Emprunt e where  e.idUtilisateur =:iduser and  e.dateEmprunt >:date order by e.dateEmprunt desc", Emprunt.class);
        q.setParameter("iduser", u);
        q.setParameter("date", d);        
        List<Emprunt> l = q.getResultList();
        
        if (l.size()<1)
         {
             return null ;
         }
        return l;
    }
    
}
