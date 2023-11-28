package ch.heg.ig.sda.view;

import ch.heg.ig.sda.business.Customer;
import ch.heg.ig.sda.business.Person;

import java.time.LocalDate;

public class App {

    /**
     * @param args the command line arguments
     * Lanceur des méthodes contenant le code pour les questions de la série d'exercice.
     */
    public static void main(String[] args) {

        point4();

        point5();

        point8();

        point10();

    }

    private static void point4(){
        System.out.println("Serie 1. Question 4");

        // 1. Déclaration d'une variable de type Person
        Person person1;

        // 2. instanciation par le mot clé new et le constructeur vide
        person1 = new Person();
        System.out.println(person1); // Console : ch.heg.ig.sda.business.Person@6d06d69c
        System.out.println("Hash code : " + person1.hashCode());

        // 3. instanciation avec un constructeur spécifié
        person1 = new Person("Alan", "Kay") ;
        System.out.println(person1); // Console : ch.heg.ig.sda.business.Person@7852e922
        System.out.println("Hash code : " + person1.hashCode());

        Person person2;
        // System.out.println("Hash code : " + person2.hashCode()); // Impossible, objet non instancié, pas de référence sur l'objet

        person2 = person1;
        System.out.println(person2); // Console : ch.heg.ig.sda.business.Person@7852e922
        System.out.println("Hash code : " + person2.hashCode());

        // Comparaisons
        System.out.println("\nTests d'égalité");
        System.out.println("Comparaison avec == : " + (person1 == person2));
        System.out.println("Comparaison avec equals() : " + person1.equals(person2) );
        System.out.println("Comparaison avec equals() version 2 : " + (person1.hashCode() == person2.hashCode()));
        System.out.println("--------------------------------------");

    }

    private static void point5(){
        System.out.println("Serie 1. Question 5");
        Person person1 = new Person("Alan", "Kay", LocalDate.of(1940, 5, 17)) ;
        System.out.println(person1);
        System.out.println("Hash code : " + person1.hashCode());

        Person person2 = person1.clone();
        System.out.println(person2);
        System.out.println("Hash code : " + person2.hashCode());
        System.out.println("--------------------------------------");
    }


    private static void point8(){
        System.out.println("Serie 1. Question 8");
        Person person1 = new Person("Alan", "Kay", LocalDate.of(1940, 5, 17)) ;
        System.out.println(person1.toString());
        System.out.println("--------------------------------------");
    }

    /**
     * Voir les classes Person et Customer pour plus de détail.
     * @see Person
     * @see Customer
     */
    private static void point10(){
        System.out.println("Serie 1. Question 10");

        Object object = new Object();
        Person person = new Person("Alan", "Kay");
        Customer customer = new Customer(1, "Bjorn", "Stroustrup");

        // Affectations polymorphes
        object = person; // person est un Object
        person = customer; // Customer est une Person

        // Le type à l'exécution est pris en compte et non celui à la compilation
        System.out.println(object.toString()); // Console: I'm a person
        System.out.println(person.toString()); // Console: I'm a customer
        System.out.println("--------------------------------------");

    }
}
