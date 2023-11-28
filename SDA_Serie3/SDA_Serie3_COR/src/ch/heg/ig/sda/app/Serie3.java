/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heg.ig.sda.app;

import ch.heg.ig.sda.business.Customer;
import ch.heg.ig.sda.business.Person;
import ch.heg.ig.sda.datastructure.ArrayList;
import ch.heg.ig.sda.datastructure.List;

/**
 *
 * @author MJM
 */
public class Serie3 {



    /**
     * @param args the command line arguments
     * Lanceur des méthodes contenant le code pour les questions de la série d'exercice.
     */
    public static void main(String[] args) {
        point3();
        point4Variante1();
    }

    public static void point3() {
        System.out.println("Serie 3. Question 3");

        Customer customer0 = new Customer(0, "Elon", "Musk");
        Customer customer1 = new Customer(1, "Larry", "Page");
        Customer customer2 = new Customer(2, "Mark", "Zuckerberg");
        Customer customer3 = new Customer(3, "Bill", "Gates");

        List list = new ArrayList(5);
        list.add(customer0);
        list.add(customer1);
        list.add(customer2);
        list.add(0, customer3);

        Customer customer;
        for(int i = 0; i < list.size(); i++){
            customer = (Customer) list.get(i);
            System.out.println(customer.toString());
        }

    }

    public static void point4Variante1() {
        System.out.println("Serie 3. Question 4, variante 1");

        List list = new ArrayList(5);
        try {
            list.add(1, new Customer(1, "Max", "Jeanneret"));
        }catch (IndexOutOfBoundsException ex){
            System.out.println(ex.toString());
        }
    }

    public static void point4Variante2(){

        System.out.println("Serie 3. Question 4, variante 2");

        Customer customer0 = new Customer(0, "Customer", "0");
        Customer customer1 = new Customer(1, "Customer", "1");
        Customer customer2 = new Customer(2, "Customer", "2");

        ArrayList customers = new ArrayList();
        customers.add(0, customer0);
        customers.add(1, customer1);

        System.out.println(customers.get(0).toString());

        // NullPointerException lorsque checkIndex() n'est pas implémentée
        System.out.println(customers.get(2).toString());

    }
    
}
