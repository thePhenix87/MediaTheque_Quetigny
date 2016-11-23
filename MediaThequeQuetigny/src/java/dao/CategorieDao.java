/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Categorie;
import javax.ejb.Stateless;

/**
 *
 * @author Tarik
 */
@Stateless
public class CategorieDao extends DAO_IMPL<Categorie>{

    public CategorieDao() {
        super(Categorie.class);
    }
    
    
}
