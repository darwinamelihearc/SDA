/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heg.ig.sda.app;

import ch.heg.ig.sda.business.Customer;
import ch.heg.ig.sda.business.Employee;
import ch.heg.ig.sda.business.Person;
import ch.heg.ig.sda.datastructure.ArrayList;
import ch.heg.ig.sda.datastructure.List;

/**
 *
 * @author MJM
 */
class Serie4 {
    /**
     * @param args the command line arguments
     * Lanceur des méthodes contenant le code
     * pour les questions de la série d'exercice.
     */
    public static void main(final String[] args) {
        Person p1 = new Customer();
        Person p2 = new Employee();
        Person p3 = new Customer();

        List al = new ArrayList(2);
        // al.remove(3); // Throws IndexOutOfBoundsException
        al.add(p1);
        al.add(p2);
        al.add(p3); // Should not throw IllegalStateException or IndexOutOfBoundsException

        System.out.println("Avant suppression du dernier element : " + al.size());
        al.removeLast();
        System.out.println("Après suppression du dernier element : " + al.size());
    }
}
