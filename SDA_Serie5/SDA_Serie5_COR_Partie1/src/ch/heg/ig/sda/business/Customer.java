/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heg.ig.sda.business;

import java.time.LocalDate;

/**
 *
 * @author MJM
 */
public class Customer extends Person {
    
    private int code;

    public Customer(){
        
    }
    
    /**
     * Constructeur qui réutilise le constructeur de sa classe parente.
     * @param code
     * @param firstName
     * @param lastName 
     */
    public Customer(int code, String firstName, String lastName) {
        super(firstName, lastName);
        this.code = code;
    }  
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        
        sb.append("\nCode: ");
        sb.append(this.getCode());
        
        return sb.toString();
    }
    
}
