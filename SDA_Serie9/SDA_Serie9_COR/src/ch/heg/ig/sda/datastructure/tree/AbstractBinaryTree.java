package ch.heg.ig.sda.datastructure.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * En abre binaire abstrait est une spécialisation d'arbre abstrait
 * prenant en charge les positions gauche et droite.
 * @param <E>
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E>
                                            implements BinaryTree<E> {

    public Position<E> sibling(Position<E> position) {
        Position<E> parent = parent(position);

        if (parent == null) return null; // position est la racine, aucun frère/soeur

        if (position == left(parent)) // position est l'enfant à gauche
            return right(parent); // retour de l'enfant de droite (peut être null)
        else // position est l'enfant à droite
            return left(parent); // retour de l'enfant de gauche (peut être null)
    }

    /** Retourne le nombre d'enfants de position en paramètre. */
    public int numChildren(Position<E> position) {

        int count=0;

        if (left(position) != null)
            count++;

        if (right(position) != null)
            count++;

        return count;

    }

    /** Renvoie une collection des positions enfants de position. */
    public Collection<Position<E>> children(Position<E> position) {

        List<Position<E>> children = new ArrayList<>(2); // 2 car deux enfants au maximum
        if (left(position) != null)
            children.add(left(position));

        if (right(position) != null)
            children.add(right(position));

        return children;

    }

}
