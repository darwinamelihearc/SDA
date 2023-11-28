package ch.heg.ig.sda.app;


import ch.heg.ig.sda.datastructure.LinkedList;
import ch.heg.ig.sda.datastructure.List;

/**
 *
 * @author MJM
 */
public class Serie5 {


    /**
     * @param args the command line arguments
     * Lanceur des méthodes contenant le code pour les questions de la série d'exercice.
     */
    public static void main(String[] args) {
       
        Part1();
        
        Part2a();

        Part2b();

    }  
    
    public static void Part1(){
        List<String> alphabet = new LinkedList<>();

        System.out.println("\nAdd first & remove last");
        ((LinkedList)alphabet).addFirst("A");
        ((LinkedList)alphabet).removeLast();
        System.out.println(alphabet.toString());
        
        System.out.println("\nAdd first");
        ((LinkedList)alphabet).addFirst("C");
        ((LinkedList)alphabet).addFirst("B");
        ((LinkedList)alphabet).addFirst("A");
        System.out.println(alphabet.toString());
        
        System.out.println("\nAdd last");
        ((LinkedList)alphabet).addLast("D");
        ((LinkedList)alphabet).addLast("E");
        System.out.println(alphabet.toString());
        
        System.out.println("\nGet first");
        String first = (String) ((LinkedList)alphabet).first();
        System.out.println("First element : " + first);
        
        System.out.println("\nGet last");
        String last = (String) ((LinkedList)alphabet).last();
        System.out.println("Last element : " + last);
        
        System.out.println("\nRemove first");
        first = (String) ((LinkedList)alphabet).removeFirst();
        System.out.println("First removed element : " + first);
        System.out.println(alphabet.toString());
        
        System.out.println("\nRemove last");
        last = (String) ((LinkedList)alphabet).removeLast();
        System.out.println("Last removed element : " + last);
        System.out.println(alphabet.toString());

    }

    public static void Part2a(){
        System.out.println("\nPART 2a");

        List<String> alphabet = new LinkedList<>();

        alphabet.add("A");
        System.out.println(alphabet.toString());

        alphabet.add("B");
        System.out.println(alphabet.toString());

        alphabet.add("D");
        System.out.println(alphabet.toString());

        System.out.println("Set:");
        String oldElement = alphabet.set(2, "C");
        System.out.println("Old element: " + oldElement);
        System.out.println(alphabet.toString());

    }

    public static void Part2b(){
        System.out.println("\nPART 2b");

        List<String> alphabet = new LinkedList<>();

        alphabet.add(0, "A");


    }
    
}
