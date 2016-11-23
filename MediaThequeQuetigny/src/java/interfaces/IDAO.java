/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Tarik
 */
public interface IDAO<T> {
    
    public void create(T entityObj);
    
    
    public void update(T entityObj);
    
       
    public void delete(T entityObj);
    
  
    public T find(Integer id);
    
 
    public EntityManager getEm();    
 
     public List execNamedQuery(String namedQueryString,Map<String,String> params);
     
}
