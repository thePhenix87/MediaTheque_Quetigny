/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
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
        System.out.println(em.createNativeQuery("SELECT AVG(note) FROM commentaire WHERE codeLivre="+l.getCodeLivre()).getSingleResult());
        return ((BigDecimal) em.createNativeQuery("SELECT AVG(note) FROM commentaire WHERE codeLivre="+l.getCodeLivre()).getSingleResult()).doubleValue();
    }
}
