/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Livre;
import javax.ejb.Stateless;

/**
 *
 * @author Tarik
 */
@Stateless
public class LivreDao extends DAO_IMPL<Livre> {

    public LivreDao() {
       //construir le livreDao avec le constructeur parent qui prend un type de class en parametre
       // maintenant le LivreDao sait que la variable entityClass = Livre.class
      super(Livre.class);
    }
    
   public List listerLivresTitreDebutantPar(String termeRecherche){
       return this.getEm().createNativeQuery("SELECT * FROM livre WHERE titre LIKE \""+termeRecherche+"%\"", Livre.class).getResultList();
   }
    
   public List listerLivresTitreContenant(String termeRecherche){
       return this.getEm().createNativeQuery("SELECT * FROM livre WHERE titre LIKE \"%"+termeRecherche+"%\"", Livre.class).getResultList();
   }
    
}
