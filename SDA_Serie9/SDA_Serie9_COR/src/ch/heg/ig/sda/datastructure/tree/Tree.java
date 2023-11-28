package ch.heg.ig.sda.datastructure.tree;

import java.util.Collection;

public interface Tree<E> {

    /** Retourne la racine de l'arbre.
     * @return
     */
    Position<E> root();

    /**
     * Retourne le parent de l'élément en paramètre.
     * @param position
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> parent(Position<E> position) throws IllegalArgumentException;

    /**
     * Retourne un sous type de Position
     * https://docs.oracle.com/javase/tutorial/java/generics/subtyping.html
     * @param position
     * @return
     */
    Collection<Position<E>> children(Position<E> position);

    /**
     * Retourne le nombre d'enfants d'un élément de l'arbre.
     * @param position
     * @return
     * @throws IllegalArgumentException
     */
    int numChildren(Position<E> position) throws IllegalArgumentException;

    /**
     * Retourne vrai si l'élément est interne à l'arbre.
     * @param position
     * @return
     * @throws IllegalArgumentException
     */
    boolean isInternal(Position<E> position) throws IllegalArgumentException;

    /**
     * Retourne vrai si l'élément est externe à l'arbre (une feuille).
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    boolean isExternal(Position<E> p) throws IllegalArgumentException;

    /**
     * Retourne vrai si l'élément est la racine de l'arbre.
     * @param position
     * @return
     * @throws IllegalArgumentException
     */
    boolean isRoot(Position<E> position) throws IllegalArgumentException;

    /**
     * Retourne la taille de l'arbre.
     * @return
     */
    int size();

    /**
     * Retourne vrai si l'arbre est vide.
     * @return
     */
    boolean isEmpty();

}
