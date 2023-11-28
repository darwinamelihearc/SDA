package ch.heg.ig.sda.datastructure.tree;

/**
 * Un arbre binaire est un arbre ordonné avec les propriétés suivantes :
 *      1. Chaque noeud d'un arbre binaire a zéro ou deux enfants.
 *      2. Chaque nœud enfant est soit de gauche (left child), soit de droite (right child).
 *      3. Un enfant de gauche précède un enfant de droite.
 * @param <E>
 */
public interface BinaryTree<E> extends Tree<E> {

    /**
     * Retourne la position de l'enfant de gauche de position en paramètre (ou null si aucun enfant n'existe).
     * @param position
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> left(Position<E> position) throws IllegalArgumentException;

    /**
     * Retourne la position de l'enfant de droite de position en paramètre (ou null si aucun enfant n'existe).
     * @param position
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> right(Position<E> position) throws IllegalArgumentException;

    /**
     * Retourne la position du frère/sœur de la position en paramètre.
     * @param position
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> sibling(Position<E> position) throws IllegalArgumentException;

}
