/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heg.ig.sda.app;


import ch.heg.ig.sda.datastructure.tree.LinkedBinaryTree;
import ch.heg.ig.sda.datastructure.tree.LinkedGeneralTree;
import ch.heg.ig.sda.datastructure.tree.Position;

/**
 *
 * @author MJM
 */
public class Serie9 {


    /**
     * @param args the command line arguments
     * Lanceur des méthodes contenant le code pour les questions de la série d'exercice.
     */
    public static void main(String[] args) {

        testLinkedBinaryTree();
        testLinkedTree();
    }

    private static void testLinkedBinaryTree(){
        System.out.println("\nTEST LinkedBinaryTree");

        LinkedBinaryTree<Integer> numbersTree = new LinkedBinaryTree<>();

        Position<Integer> zero = numbersTree.addRoot(0);
        Position<Integer> one = numbersTree.addLeft(zero, 1);
        Position<Integer> two = numbersTree.addRight(zero, 2);

        Position<Integer> three = numbersTree.addLeft(one, 3);
        Position<Integer> four = numbersTree.addRight(one, 4);

        Position<Integer> five = numbersTree.addLeft(two, 5);
        Position<Integer> six = numbersTree.addRight(two, 6);

        System.out.println("\nNumbersTree (preorder): ");
        System.out.println(numbersTree.toStringPreorder());

        System.out.print("\nNumbersTree (preorder): ");
        int i = 0;
        for(Position<Integer> position : numbersTree.preOrder()) {
            System.out.print(position.getElement());
            if(i < numbersTree.size()-1)
                System.out.print(", ");
            i++;
        }

        System.out.print("\nNumbersTree (postorder): ");
        int j = 0;
        for(Position<Integer> position : numbersTree.postOrder()) {
            System.out.print(position.getElement());
            if(j < numbersTree.size()-1)
                System.out.print(", ");
            j++;
        }

        System.out.print("\nNumbersTree (inorder): ");
        int k = 0;
        for(Position<Integer> position : numbersTree.inorder()) {
            System.out.print(position.getElement());
            if(k < numbersTree.size()-1)
                System.out.print(", ");
            k++;
        }

        System.out.print("\n");

    }

    private static void testLinkedTree(){
        System.out.println("\nTEST LinkedGeneralTree");

        LinkedGeneralTree<Integer> numbersTree = new LinkedGeneralTree<>();

        Position<Integer> zero = numbersTree.addRoot(0);
        Position<Integer> one = numbersTree.addChild(zero, 1);
        Position<Integer> two = numbersTree.addChild(zero, 2);
        Position<Integer> three = numbersTree.addChild(zero, 3);

        Position<Integer> four = numbersTree.addChild(two, 4);
        Position<Integer> five = numbersTree.addChild(two, 5);

        Position<Integer> six = numbersTree.addChild(five, 6);

        System.out.println("\nNumbersTree (preorder): ");
        System.out.println(numbersTree.toStringPreorder());

        numbersTree.remove(two);

        System.out.println("\nNumbersTree (preorder) after removal: ");
        System.out.println(numbersTree.toStringPreorder());

    }

}
