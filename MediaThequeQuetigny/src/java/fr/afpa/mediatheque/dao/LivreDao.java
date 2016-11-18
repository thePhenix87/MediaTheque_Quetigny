/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afpa.mediatheque.dao;

import fr.afpa.mediatheque.model.Livre;
import javax.ejb.Stateless;

/**
 *
 * @author Tarik
 */
@Stateless
public class LivreDao extends DAO<Livre> {

    public LivreDao() {
       //construir le livreDao avec le constructeur parent qui prend un type de class en parametre
       // maintenant le LivreDao sait que la variable entityClass = Livre.class
      super(Livre.class);
    }
    
    
    
}
