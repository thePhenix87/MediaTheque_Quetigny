/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import model.Commentaire;
import model.Livre;

/**
 *
 * @author Samuel
 */
@Stateless
public class CommentaireDao extends DAO_IMPL<Commentaire>{
    
    public CommentaireDao()
    {
        super(Commentaire.class);
    }
    
    public double getNoteMoyenneLivre(Livre l)
    {
        System.out.println(getEm().createNativeQuery("SELECT AVG(note) FROM commentaire WHERE codeLivre="+l.getCodeLivre()).getSingleResult());
        return ((BigDecimal) getEm().createNativeQuery("SELECT AVG(note) FROM commentaire WHERE codeLivre="+l.getCodeLivre()).getSingleResult()).doubleValue();
    }
        public int stars(Integer codelivre) {
        List star = this.getEm().createNativeQuery("select (AVG(note)) from commentaire as c where c.codeLivre=" + codelivre).getResultList();
        if (star.size()>0&&star.get(0)!=null) {
            //System.out.println(Integer.valueOf(star.get(0).toString()));
            return Math.round(Float.valueOf(star.get(0).toString()));
        }
        return 0;
    }
        
     public List<Commentaire> comvalides(int codelivre){
      List<Commentaire>comvalides= this.getEm().createNativeQuery("Select * from commentaire as c where c.affiche=1 And c.codeLivre="+codelivre,Commentaire.class).getResultList();
         return  comvalides;
     }   
}
