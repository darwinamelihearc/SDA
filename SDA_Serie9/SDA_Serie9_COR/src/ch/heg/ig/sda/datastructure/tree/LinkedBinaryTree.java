package ch.heg.ig.sda.datastructure.tree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/** Implémentation concrète d'une arborescence binaire
 *  utilisant une structure liée s'appuyant sur des nœuds. */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    /**
     * Concept de classe interne/imbriquée (nested class).
     * Cette classe est static afin de bloquer l'accès direct aux méthodes d'AbstractBinaryTree.
     * @param <E>
     */
    protected static class Node<E> implements Position<E> {

        private E element; // Elément stocké dans ce nœud
        private Node<E> parent; // Référence au nœud parent (si elle existe)
        private Node<E> left; // Référence du nœud enfant de gauche (si elle existe)
        private Node<E> right; // Référence du nœud enfant de droite (si elle existe)

        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        public E getElement() { return element; }
        public Node<E> getParent() { return parent; }
        public Node<E> getLeft() { return left; }
        public Node<E> getRight() { return right; }

        public void setElement(E e) { element = e; }
        public void setParent(Node<E> parentNode) { parent = parentNode; }
        public void setLeft(Node<E> leftChild) { left = leftChild; }
        public void setRight(Node<E> rightChild) { right = rightChild; }

    }

    /**
     * Factory method pour créer un nouveau noeud stockant un élément.
     */
    protected Node<E> createNode(E e, Node<E> parent,
                                 Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    protected Node<E> root = null; // racine de l'abre binaire
    private int size = 0; // Nombre de noeuds de l'arbre

    public LinkedBinaryTree() { }

    //

    /** Valide la position et la renvoie en tant que nœud.
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");

        Node<E> node = (Node<E>) p; // safe cast

        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");

        return node;
    }

    // Accesseur non implémentés dans la classe abstraite parente
    public int size() {
        return size;
    }

    public Position<E> root() {
        return root;
    }

    public Position<E> parent(Position<E> position) throws IllegalArgumentException {
        Node<E> node = validate(position);
        return node.getParent();
    }

    public Position<E> left(Position<E> position) throws IllegalArgumentException {
        Node<E> node = validate(position);
        return node.getLeft();
    }

    public Position<E> right(Position<E> position) throws IllegalArgumentException {
        Node<E> node = validate(position);
        return node.getRight();
    }


    // Méthode de modification de l'arbre supportées par cette classe


    /**
     * Place l'élément element à la racine d'un arbre vide et retourne sa nouvelle position.
     * @param element
     * @return
     * @throws IllegalStateException
     */
    public Position<E> addRoot(E element) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(element, null, null, null);
        size = 1;
        return root;
    }

    /**
     * Crée un nouvel enfant de gauche à la position en paramètre.
     */
    public Position<E> addLeft(Position<E> position, E element) throws IllegalArgumentException {
        Node<E> parent = validate(position);

        if (parent.getLeft() != null)
            throw new IllegalArgumentException("position already has a left child");

        Node<E> child = createNode(element, parent, null, null);
        parent.setLeft(child);

        size++;

        return child;
    }

    /**
     * Crée un nouvel enfant de droite à la position en paramètre.
     */
    public Position<E> addRight(Position<E> position, E element) throws IllegalArgumentException {
        Node<E> parent = validate(position);

        if (parent.getRight() != null)
            throw new IllegalArgumentException("position already has a right child");

        Node<E> child = createNode(element, parent, null, null);
        parent.setRight(child);

        size++;

        return child;
    }

    // Replaces the element at Position p with e and returns the replaced element.

    /**
     * Remplace l'élément à la position en paramètre.
     */
    public E set(Position<E> position, E element) throws IllegalArgumentException {
        Node<E> node = validate(position);

        E temp = node.getElement();
        node.setElement(element);

        return temp;
    }

    /**
     * Attache les arbres leftSubtree et rightSubtree comme sous-arbres gauche et droit d'une feuille (position externe).
     */
    public void attach(Position<E> position,
                       LinkedBinaryTree<E> leftSubtree,
                       LinkedBinaryTree<E> rightSubtree) throws IllegalArgumentException {

        Node<E> node = validate(position);

        if (isInternal(position)) throw new IllegalArgumentException("position must be a leaf");

        size += leftSubtree.size() + rightSubtree.size();

        if (!leftSubtree.isEmpty()) {
            leftSubtree.root.setParent(node);
            node.setLeft(leftSubtree.root);
            leftSubtree.root = null;
            leftSubtree.size = 0;
        }

        if (!rightSubtree.isEmpty()) {
            rightSubtree.root.setParent(node);
            node.setRight(rightSubtree.root);
            rightSubtree.root = null;
            rightSubtree.size = 0;
        }

    }


    /**
     *  Supprime le nœud à la position en paramètre
     *  et le remplace par son enfant (s'il existe).
     */
    public E remove(Position<E> position) throws IllegalArgumentException {

        Node<E> node = validate(position);

        if (numChildren(position) == 2)
            throw new IllegalArgumentException("position has two children");

        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight() );

        if (child != null)
            child.setParent(node.getParent()); // Le grand-parent de l'enfant devient son parent

        if (node == root)
            root = child; // l'enfant devient la racine
        else {

            Node<E> parent = node.getParent();
            if (node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }

        size--;

        E temp = node.getElement();

        node.setElement(null); // pour le garbage collector
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return temp;

    }

    /**
     * Ajoute les positions de la sous-arborescence (snapshot) enracinée à la
     * position en paramètre à l'instantané donné.
     * @param position
     * @param snapshot
     */
    private void inorderSubtree(Position<E> position, List<Position<E>> snapshot) {
        if (left(position) != null)
            inorderSubtree(left(position), snapshot);

        snapshot.add(position);

        if (right(position) != null)
            inorderSubtree(right(position), snapshot);
    }

    /**
     * Retourne une collection de positions de l'arbre avec un parcours inordre.
     * @return
     */
    public Collection<Position<E>> inorder() {

        List<Position<E>> snapshot = new LinkedList<>();

        if (!isEmpty())
            inorderSubtree(root(), snapshot);

        return snapshot;
    }

}


