/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import model.Utilisateur;

/**
 *
 * @author Samuel
 */
@Stateless
public class UtilisateurDao extends DAO_IMPL<Utilisateur>{
    
    public UtilisateurDao()
    {
        super(Utilisateur.class);
    } 
    
}
