/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heg.ig.sda.business;

import java.time.LocalDate;

/**
 *
 * @author maximili.jeannere
 */
public class Employee extends Person {
    
    public String title;
    public float salary;

    public Employee(){
        
    }

    public Employee(String title, float salary, String firstName, String lastName) {
        super(firstName, lastName);
        this.title = title;
        this.salary = salary;
    }
        

    public Employee(String title, float salary, String firstName, String lastName, LocalDate dateOfBirth, Gender gender) {
        super(firstName, lastName, dateOfBirth, gender);
        this.title = title;
        this.salary = salary;
    }      
    
    /**
     * Clone l'objet courant.
     * Lors de l'héritage d'une classe abtraite, il est possible de retourner un type hérité.
     * @return Customer
     */
    @Override
    public Employee clone() {
        Employee employee = new Employee();
        
        if(this.getFirstName() != null)
            employee.setFirstName(this.getFirstName());
        
        if(this.getLastName() != null)
            employee.setLastName(this.getLastName());
        
        if(this.getDateOfBirth() != null)
            employee.setDateOfBirth(this.getDateOfBirth());
        
        if(this.getGender() != null)
            employee.setGender(this.getGender());        
           
        if(this.getTitle() != null)
            employee.setTitle(this.getTitle());
        
        if(this.getSalary()!= 0)
            employee.setSalary(this.getSalary());
        
        return employee;
    }    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }    
        
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        
        sb.append("\nTitle: ");
        sb.append(this.getTitle());
        
        sb.append("\nSalary: ");
        sb.append(this.getSalary());
        
        return sb.toString();
    }
    
}
