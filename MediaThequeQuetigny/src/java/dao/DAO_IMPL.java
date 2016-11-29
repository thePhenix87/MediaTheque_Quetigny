/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.IDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utilitaires.SqlParam;

/**
 * @author Tarik Moussaoui
 */
public class DAO_IMPL<T> implements Serializable {

    @PersistenceContext(unitName = "MediaThequeQuetignyPU")

    private EntityManager em;               // EntityManager pour gerer les models
    private Class<T> entityClass;           // stocker le type de chaque entity qui va herité du DAO_IMPL

    // constructeur sans parametres     
    public DAO_IMPL() {
    }

    /**
     * Constructeur de DAO avec entité en parametre
     *
     * @param entityClass permet de creer un dao tout en identifiant le type de
     * l'entité qui sera untilisé par la suite dans le CRUD
     */
    public DAO_IMPL(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Creer dans la base de donnée l'enrigstrement en cours
     *
     * @param entityObj enregistrement de doonnées dont le type est defini
     * pedant la creation du DAO_IMPL
     *
     */
    public void create(T entityObj) {
        em.persist(entityObj);
    }

    /**
     * Metre à Jours dans la base de donnée l'enrigstrement en cours
     *
     * @param entityObj enregistrement de doonnées dont le type est defini
     * pedant la creation du DAO_IMPL
     *
     */
    public void update(T entityObj) {
        em.merge(entityObj);
    }

    /**
     * Supprimer dans la base de donnée l'enrigstrement en cours
     *
     * @param entityObj enregistrement de doonnées dont le type est defini
     * pedant la creation du DAO_IMPL
     *
     */
    public void delete(T entityObj) {
        entityObj=em.merge(entityObj);
        em.remove(entityObj);
    }

    /**
     * Metre à Jours dans la base de donnée l'enrigstrement en cours
     *
     * @param id Clé primaire d'un enregistrement 
     *
     */
    public T find(Integer id) {

        return id != null ? em.find(entityClass, id) : null;

    }

    // Recuperer le EntityManager encas de besoin 
    public EntityManager getEm() {
        return this.em;
    }

    /**
     * Executé un Named Query avec un map de Parametre ou bien null si y'en a
     * pas
     *
     * @param namedQueryString
     * @param params
     * @return
     */
    public List execNamedQuery(String namedQueryString, Map<String, String> params) {
        Query q = em.createNamedQuery(namedQueryString);
        if (params != null && params.size() > 0) {
            for (String paramName : params.keySet()) {
                q.setParameter(paramName, params.get(paramName));
            }
        }

        return q.getResultList();

    }

    /**
     * Permet de Recuperer tout les enregistrement qlq soit le DAO appelant
     *
     * @return
     */
    public List getAll() {
        String entityName = entityClass.getSimpleName();
        String queryStr = "SELECT a FROM " + entityName + " a ";
        Query q = em.createQuery(queryStr);
        return q.getResultList();
    }

    /**
     * Placer des ordres de selection en passant un Mapage de parametre =>value
     *
     * @param params
     * @return
     */
    public List selectWhere(SqlParam sqlParam) {
        String entityName = entityClass.getSimpleName();
        String sqlStr = "SELECT a FROM " + entityName + " a ";
        if (sqlParam.getParamMap() != null && sqlParam.getParamMap().size() > 0) {
            sqlStr += " WHERE ";
            for (String paramName : sqlParam.getParamMap().keySet()) {
                sqlStr +="a."+paramName + " = :" + paramName + " AND ";
            }
        }
        if(sqlStr.endsWith("AND ")){           
         sqlStr = sqlStr.substring(0,sqlStr.length()-5);            
        }
        System.out.println(sqlStr);
        
        TypedQuery q = em.createQuery(sqlStr,entityClass);
       for (String paramName : sqlParam.getParamMap().keySet()) {         
           try {            
                q.setParameter(paramName, Integer.parseInt(sqlParam.getParamMap().get(paramName)));
           } catch (Exception e) {
               q.setParameter(paramName, sqlParam.getParamMap().get(paramName));
           }
           
           
        };
        
        return q.getResultList();       
        
    }
}
