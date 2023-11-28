package ch.heg.ig.sda.datastructure.tree;

/**
 * Fournit une abstraction générale pour l'emplacement d'un élément dans une structure.
 * @param <E>
 */
public interface Position<E> {

    /** Retourne l'élément stocké à cette position.
     * @return L'élément stocké
     * @throws IllegalStateException si la position est invalide.
     */
    E getElement() throws IllegalStateException;

}
