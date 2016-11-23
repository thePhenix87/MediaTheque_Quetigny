/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaires;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Tarik
 */
public class SqlParam {

    HashMap<String, String> paramMap = new HashMap<>();

    public SqlParam() {
    }

    public SqlParam(String... statements) {       
        for (String statement : statements) {            
            paramMap.put(statement.split("=>")[0].trim() != null ? statement.split("=>")[0].trim() : statement,
                    statement.split("=>")[1].trim() != null ? statement.split("=>")[1].trim() : null);

        }

    }

    public HashMap<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(HashMap<String, String> paramMap) {
        this.paramMap = paramMap;
    }

}
