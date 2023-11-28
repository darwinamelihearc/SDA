package ch.heg.ig.sda.app;


import ch.heg.ig.sda.datastructure.LinkedList;

/**
 * @author MJM
 */
public class Serie5 {


    /**
     * @param args the command line arguments
     *             Lanceur des méthodes contenant le code pour les questions de la série d'exercice.
     */
    public static void main(String[] args) {
        Part1();
    }

    public static void Part1() {
        LinkedList<String> alphabet = new LinkedList<>();

        System.out.println("\nAdd first & remove last");
        alphabet.addFirst("A");
        alphabet.removeLast();
        System.out.println(alphabet.toString());

        System.out.println("\nAdd first");
        alphabet.addFirst("C");
        alphabet.addFirst("B");
        alphabet.addFirst("A");
        System.out.println(alphabet.toString());

        System.out.println("\nAdd last");
        alphabet.addLast("D");
        alphabet.addLast("E");
        System.out.println(alphabet.toString());

        System.out.println("\nGet first");
        String first = alphabet.first();
        System.out.println("First element : " + first);

        System.out.println("\nGet last");
        String last = alphabet.last();
        System.out.println("Last element : " + last);

        System.out.println("\nRemove first");
        first = alphabet.removeFirst();
        System.out.println("First removed element : " + first);
        System.out.println(alphabet.toString());

        System.out.println("\nRemove last");
        last = alphabet.removeLast();
        System.out.println("Last removed element : " + last);
        System.out.println(alphabet.toString());

    }

}
