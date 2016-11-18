/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afpa.mediatheque.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/** 
 * @author Tarik Moussaoui
 */
public class DAO <T> implements Serializable {
    
    @PersistenceContext(unitName="MediaThequeQuetignyPU")
    
    private EntityManager em;               // EntityManager pour gerer les models
    private Class<T> entityClass;           // stocker le type de chaque entity qui va herité du DAO
    
    
    // constructeur sans parametres     
    public DAO() {}
    
    /**
     * Constructeur de DAO avec entité en parametre
     * @param entityClass 
     * permet de creer un dao tout en identifiant le  type de l'entité
     * qui sera untilisé par la suite dans le CRUD
     */
    
     public DAO(Class<T> entityClass) {
         this.entityClass = entityClass;
    }
    /**
     * Creer dans la base de donnée l'enrigstrement en cours 
     * @param entityObj  enregistrement de doonnées dont le type est 
     * defini pedant la creation du DAO
     **/
    public void create(T entityObj){
        em.persist(entityObj);
    }
    
    /**
     * Metre à Jours dans la base de donnée l'enrigstrement en cours 
     * @param entityObj  enregistrement de doonnées 
     * dont le type est defini pedant la creation du DAO
     **/
    public void update(T entityObj){      
        em.merge(entityObj);
    }
    
       /**
     * Supprimer dans la base de donnée l'enrigstrement en cours 
     * @param entityObj  enregistrement de doonnées 
     * dont le type est defini pedant la creation du DAO
     **/
    public void delete(T entityObj){
        em.remove(entityObj);
    }
    
    /**
     * Metre à Jours dans la base de donnée l'enrigstrement en cours 
     * @param id Clé primaire d'un enregistrement 
     **/
    public T find(Integer id) {
                
        return id!=null?em.find(entityClass, id):null;
     
    }
    
    // Recuperer le EntityManager encas de besoin 
    public EntityManager getEm(){
        return this.em;
    }
    
    /**
     * Executé un Named Query avec un map de Parametre ou bien null si y'en a pas
     * @param namedQueryString
     * @param params
     * @return 
     */
     public List execNamedQuery(String namedQueryString,Map<String,String> params){
        Query q = em.createNamedQuery(namedQueryString);
        if(params!=null &&params.size()> 0){
           for(String paramName:params.keySet()){
               q.setParameter(paramName, params.get(paramName));
           }          
        }
        
         return q.getResultList();
       
       
    }
    
}
