/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heg.ig.sda.business;

import java.time.LocalDate;
import java.time.Period;



/**
 *
 * @author MJM
 */
public abstract class Person {
    
    // Enumeration qui remplace le gender en char. Cette énumération est interne à la classe Person.
    public enum Gender {M, F}
 
    private String firstName;
    private String lastName;
    /**
     * Date of birth of the person.
     * LocalDate is an immutable date-time object that represents a date, often viewed as year-month-day. 
     * Other date fields, such as day-of-year, day-of-week and week-of-year, can also be accessed. 
     * For example, the value "2nd October 2007" can be stored in a LocalDate.
     * https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
     * https://www.oracle.com/technetwork/articles/java/jf14-date-time-2125367.html
     */
    private LocalDate dateOfBirth;
    private Gender gender;  

    public Person() {
    }

    protected Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    protected Person(String firstName, String lastName, LocalDate dateOfBirth, Gender gender) {
        this(firstName, lastName); // Appel du constructeur Person(String firstName, String lastName) qui possède deux paramètres
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public int getAge(){
        LocalDate now = LocalDate.now();
        int period = Period.between(this.dateOfBirth, now).getYears();
        return period;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    } 

    // Redéfinition de la méthode toString();
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\nClass: ");
        sb.append(this.getClass());
        
        sb.append("\nFirst name : ").append(this.firstName);
        sb.append("\nLast name : ").append(this.lastName);
        
        sb.append("\nGender : ");
        
        // Variante gender en enum
        if(this.gender != null){
            switch(this.gender){
                case M:
                    sb.append("Male");
                    break;
                case F:
                    sb.append("Female");
                    break;
                default:
                    sb.append(" - ");
                    break;
            }   
        }
        
        sb.append("\nAge : ");

        if(this.dateOfBirth != null){
            sb.append(this.getAge());
        }else{
            sb.append(" - ");
        }      
                    
        return sb.toString();
    }
           
}
