/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heg.ig.sda.datastructure;

/**
 *
 * @author maximili.jeannere
 */
public class LinkedList<E> implements List<E> {
    
    private static class Node<E> {

        private E element;        
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
        
        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
    
    private Node<E> head = null;
    private int size = 0; // Nombre d'éléments

    public LinkedList() {
    }    
    
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retourne l'élément à l'indice i.
     *
     * @param i Indice
     * @return Instance de Person à l'indice i
     */
    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return null; //node.getElement;
    }

    /**
     * Remplace l'élément à l'indice i par un element générique et renvoie l'élément remplacé.
     *
     * @param i       Indice
     * @param element Element générique.
     * @return ancien élément.
     */
    @Override
    public E set(int i, E element) {
        Node<E> node = head;

        for (int j = 0; j < i; j++) {
            node = node.getNext();
        }

        E removedElement = node.getElement();
        node.setElement(element);
        return removedElement;

    }

    /**
     * Insère un élément de type générique à l'indice i, en décalant les éléments suivants d'un indice.
     *
     * @param i       Indice ou la personne est ajoutée.
     * @param element Instance de Person.
     */
    @Override
    public void add(int i, E element) {

    }

    @Override
    public void add(E element) {
        addFirst(element);
    }

    /**
     * Supprime et renvoie l'élément à l'indice i, en décalant les éléments suivants.
     *
     * @param i indice
     * @return Instance de personne supprimée
     */
    @Override
    public E remove(int i) {
        if (isEmpty()) return null;

        Node<E> node = head;
        E removedElement = null;

        for (int j = 0; j < i; j++) {

        }

        return removedElement;
    }

    public void addFirst(E element){
        Node<E> newest = new Node<>(element, head);
        head = newest;
        // Version courte : head = new Node<>(element, head);
        size++;
    }
    
    public void addLast(E element){
        Node<E> newest = new Node<>(element, null);
        
        if(this.isEmpty()){
            head = newest;
            
        }else{
            
            Node<E> node = head;
            
            while(node.getNext() != null){
                node = node.getNext();
            }
            
            node.next = newest;
        }
        
        size++;
    }
    
    public E removeFirst(){
        if (isEmpty()) return null;
        
        E removedElement = head.getElement();
        head = head.getNext(); // null si la liste n'a qu'un seul noeud
        this.size--;
        
        return removedElement;
    }

    public E removeLast(){
        if (isEmpty()) return null;

        E removedElement = null;
        Node<E> beforeLast = null;
        Node<E> node = head;

        if(node.getNext() == null){
            removedElement = head.getElement();
            head = null;
        }else{
            while(node.getNext() != null){
                beforeLast = node;
                node = node.getNext();
            }

            removedElement = node.getElement();
            beforeLast.setNext(null);
        }

        size--;

        return removedElement;
    }

    /**
     * Comparaison de références.
     *
     * @param element
     * @return
     */
    @Override
    public boolean isEquals(E element) {
        return false;
    }

    /**
     * Retourne le premier élément. 
     * @return Premier élément
     */
    public E first() {
        if (isEmpty()) return null;
        return head.getElement();
    }
    
    /**
     * Retourne le dernier élément.
     * @return Dernier élément
     */
    public E last(){
        if (this.isEmpty()) return null;
        
        Node<E> node = head;

        for (int i = 0; i < this.size; i++) {

            if(node.getNext() != null){
                node = node.getNext();
            }
        }

        return node.getElement();
    }

    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        Node<E> node = head;
        
        sb.append("Linkedlist content : ");
        
        for(int i=0; i<size; i++){
            
            sb.append("[");
            sb.append(node.getElement().toString());
            sb.append(" | .]-> ");
            
            if(node.getNext() == null){
                sb.append("null");
            }
            
            node = node.getNext();
        }
        
        return sb.toString();
    }

    /** Vérifie si l'indice donné est dans la plage [0, n].
     IndexOutOfBoundsException pour signaler un indice non valide en argument. */
    protected void checkIndex(int i, int n) {
        if (i < 0 || i >= n)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
    }

}
