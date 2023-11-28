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
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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

    @Override
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

    // List implementation
    
    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    /**
     * Modifie un élément à un indice donné.
     * @param index Position de l'élément à modifier.
     * @param element Nouvel élément.
     * @return Ancien élément.
     * @implNote Cette méthode ne garantie pas un bon fonctionnement dans tous les cas, p. ex. liste vide.
     */
    @Override
    public E set(int index, E element) {
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        E removedElement = node.getElement();
        node.setElement(element);
        return removedElement;
    }

    @Override
    public void add(int i, E element) {
        throw new UnsupportedOperationException("Not supported yet.");         
    }

    @Override
    public void add(E element) {
        this.addLast(element);
    }

    @Override
    public E remove(int i) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }    

    @Override
    public boolean isEquals(E element) {
        throw new UnsupportedOperationException("Not supported yet."); 
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
    
    
}
