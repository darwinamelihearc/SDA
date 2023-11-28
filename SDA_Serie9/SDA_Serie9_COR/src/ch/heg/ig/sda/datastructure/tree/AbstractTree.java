package ch.heg.ig.sda.datastructure.tree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Une classe de base abstraite implémentant les fonctionnalités de base de l'interface Tree,
 * quelque soit l'implémentation qui découle de cette classe abstraite.
 * @param <E> Element générique
 */
public abstract class AbstractTree<E> implements Tree<E> {

    public boolean isInternal(Position<E> position) {
        return numChildren(position) > 0;
    }

    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }

    public boolean isRoot(Position<E> position) {
        return position == root();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /** Renvoie le nombre de niveaux (profondeur) séparant la Position position de la racine.
     *  La profondeur de position est le nombre d'ancêtres de position, autre que position lui-même.
     *  */
    public int depth(Position<E> position) {
        if (isRoot(position))
            return 0; // La profondeur de la racine de l'arbre est de 0
        else
            return 1 + depth(parent(position));
    }

    /** Retourne la hauteur d'un sous arbre enraciné à la position en paramètre.
     * La hauteur d'un arbre vide est égale à 0.
     * La hauteur de position est supérieure d'une unité à la hauteur maximale des hauteurs des enfants de p.
     */
    public int height(Position<E> position) {
        int height = 0; // height=0 si position est externe (aucun enfant)
        for (Position<E> child : children(position))
            height = Math.max(height, 1 + height(child));
        return height;
    }

    public String spaces(int indent){
        String spaces = "";

        for (int i=0; i<indent; i++)
            spaces = spaces + " ";

        return spaces;
    }

    // Note : Les parcours préordre et postordre sont les mêmes dans les arbres binaires et les abres (généraux)

    /**
     * Ajoute les positions de la sous-arborescence (snapshot) enracinée à la position position à l'instantané donné.
     */
    private void preOrderSubtree(Position<E> position, List<Position<E>> snapshot) {
        snapshot.add(position); // Ajouter la position avant d'explorer les sous-arbres
        for (Position<E> c : children(position))
            preOrderSubtree(c, snapshot);
    }

    /**
     * Retourne une collection de positions de l'arbre avec un parcours préordre.
     * @return
     */
    public Collection<Position<E>> preOrder() {
        List<Position<E>> snapshot = new LinkedList<>();

        if (!isEmpty())
            preOrderSubtree(root(), snapshot); // Rempli le snapshot de manière récursive

        return snapshot;
    }

    private void preorderIndent(Position<E> position, int depth, StringBuilder snapshot) {
        snapshot.append(spaces(2*depth) + position.getElement());
        snapshot.append("\n");
        for (Position<E> c : children(position))
            preorderIndent(c,depth+1, snapshot);
    }

    /**
     * Renvoie une chaine de caractère de l'arbre entier en parcours préordre.
     * @return
     */
    public String toStringPreorder() {
        StringBuilder stringBuilder = new StringBuilder();

        if (!isEmpty())
            preorderIndent(root(), 0, stringBuilder);

        return stringBuilder.toString();
    }


    /**
     * Ajoute les positions de la sous-arborescence (snapshot) enracinée à la position position à l'instantané donné.
     */
    private void postOrderSubtree(Position<E> p, List<Position<E>> snapshot) {
        for (Position<E> c : children(p))
            postOrderSubtree(c, snapshot);

        snapshot.add(p); // En postordre, les positions sont ajoutées après la visite du sous-arbre
    }

    //Returns an iterable collection of positions of the tree, reported in postorder. ∗/
    public Collection<Position<E>> postOrder() {
        List<Position<E>> snapshot = new LinkedList<>();

        if (!isEmpty())
            postOrderSubtree(root(), snapshot);

        return snapshot;
    }

}
