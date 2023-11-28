package ch.heg.ig.sda.datastructure.tree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/** Implémentation concrète d'un arbre
 *  utilisant une structure liée s'appuyant sur des nœuds. */
public class LinkedGeneralTree<E> extends AbstractTree<E> {

    /**
     * Concept de classe interne/imbriquée (nested class).
     * Cette classe est static afin de bloquer l'accès direct aux méthodes d'AbstractBinaryTree.
     * @param <E>
     */
    protected static class Node<E> implements Position<E> {

        private E element; // Elément stocké dans ce nœud
        private Node<E> parent; // Référence au nœud parent (si elle existe)
        private List<Node<E>> children; // Référence sur une liste de nœuds enfants

        private int index;

        public Node(E e, Node<E> above) {
            element = e;
            parent = above;
            this.children = new LinkedList<>();
        }

        /**
         *
         * @param e
         * @param above
         * @param children Varargs https://docs.oracle.com/javase/tutorial/java/javaOO/arguments.html
         */
        public Node(E e, Node<E> above, Node<E>... children) {
            element = e;
            parent = above;

            this.children = new LinkedList<>();
            for(int i=0; i < children.length; i++){
                this.addChild(children[i]);
            }
        }

        public E getElement() { return element; }
        public Node<E> getParent() { return parent; }
        public List<Node<E>> getChildren() { return children; }
        public int getIndex() { return this.index; }

        public void setElement(E e) { element = e; }
        public void setParent(Node<E> parentNode) { parent = parentNode; }
        public void setIndex(int index) { this.index = index; }

        public void addChild(Node<E> child) {
            this.addChild(children.size(), child);
        }

        public void addChild(int i, Node<E> child) {
            this.children.add(i, child);
            child.setIndex(i);
        }

        public void remove() {
            if (parent != null) {
                parent.removeChild(this);
            }
        }

        private void removeChild(Node<E> child) {
            if (children.contains(child))
                children.remove(child);
        }

        public Node<E> getChild(Node<E> child) {
            for(Node<E> node : this.children){
                if(child == node)
                    return node;
            }

            return null;
        }

    }

    protected Node<E> createNode(E e, Node<E> parent) {
        return new Node<E>(e, parent);
    }


    protected Node<E> root = null; // racine de l'abre binaire
    private int size = 0; // Nombre de noeuds de l'arbre

    public LinkedGeneralTree() { }

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

    @Override
    public Collection<Position<E>> children(Position<E> position) {
        Node<E> node = validate(position);
        return (Collection) node.getChildren();
    }

    @Override
    public int numChildren(Position<E> position) throws IllegalArgumentException {
        return 0;
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
        root = createNode(element, null);
        size = 1;
        return root;
    }

    /**
     * Crée un nouvel enfant de gauche à la position en paramètre.
     */
    public Position<E> addChild(Position<E> position, E element) throws IllegalArgumentException {
        Node<E> parent = validate(position);

        Node<E> child = createNode(element, parent);
        parent.addChild(child);

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
     *  Supprime le nœud à la position en paramètre
     *  et le remplace par son enfant (s'il existe).
     *  Cette façon de supprimer dénature l'abre le moins possible. Elle est peu commune.
     */
    public E remove(Position<E> position) throws IllegalArgumentException {

        Node<E> node = validate(position);

        Node<E> child = null;

        if(node.children.size() == 1)
            child = node.children.get(0);

        if (child != null)
            child.setParent(node.getParent()); // Le grand-parent de l'enfant devient son parent

        if (node == root)
            root = child; // l'enfant devient la racine
        else {
            // Les enfants du noeud à supprimer deviennent les enfants du parent
            Node<E> parent = node.getParent();

            // Permet de ne pas déplacer les enfants à la fin
            int j = node.getIndex();
            for(int i = node.children.size()-1; i >= 0 ; i--) {
                parent.addChild(j, node.children.get(i));
            }
        }

        size--;

        E temp = node.getElement();
        node.remove(); // Suppression du noeud

        return temp;
    }

}


