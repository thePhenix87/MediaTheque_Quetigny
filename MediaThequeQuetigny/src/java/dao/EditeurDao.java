/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import model.Categorie;
import model.Editeur;

/**
 *
 * @author 21011-46-09
 */
@Stateless
public class EditeurDao extends DAO_IMPL<Editeur>{
  
    public EditeurDao () {
        super(Editeur.class);
    }
} 
