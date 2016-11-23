/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Tarik
 */
@Named
@RequestScoped
public class Router implements Serializable  {
    
    private  Map<String, String> params;
    String page;
    String fromPage;
  

    public Router() {             
        params =FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        page = params.get("page");  
        if(page==null){
            page="home";
        }
        System.out.println(page+" From router");
     
    }

    public String getFromPage() {
        return fromPage;
    }

    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
    }

    
   
   
    
    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

   
    
    
    
}
