/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heg.ig.sda.business;

/**
 *
 * @author MJM
 */
public class Customer extends Person {
    
    private int code;

    /**
     * Constructeur qui réutilise le constructeur de sa classe parente.
     * @param code
     * @param firstName
     * @param lastName 
     */
    public Customer(int code, String firstName, String lastName) {
        super(firstName, lastName); // Appel du constructeur parent ayant cette signature
        this.code = code;
    }  
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    // Redéfinition de la méthode toString();
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();

        sb.append("\nI'm a customer");
        
        sb.append("\nFirst name : ").append(this.getFirstName());
        sb.append("\nLast name : ").append(this.getLastName());
        return sb.toString();    
    }
    
}
