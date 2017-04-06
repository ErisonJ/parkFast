/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author yan_t
 */
public class Utilidades {
    public void Utilidades(){        
    }

    /**
     *
     * @param cadena
     * @return
     */
    public static boolean isNumeric(String cadena){
    	try {
            Integer.parseInt(cadena);
            return true;
    	} catch (NumberFormatException e){
            return false;
    	}
    }
    
}
