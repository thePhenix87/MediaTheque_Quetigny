/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaires;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Samuel
 */
public class Sha256 {
    
    public static String SHA(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException 
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes("UTF-8")); 
        byte[] digest = md.digest();
        return String.format("%064x", new java.math.BigInteger(1, digest));
    }
}
