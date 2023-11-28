/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heg.ig.sda.datastructure;

/**
 *
 * @author maximili.jeannere
 * ArrayList contenant des intances de type Person de taille fixe. 
 */
public class ArrayList<E> implements List<E> {

    public static final int DEFAULT_CAPACITY=10;
    private E[] elements; // Tableau pour stocker des Person
    private int size = 0; // Nombre d'éléments
    
    // Constructeurs
    
    public ArrayList( ) { 
        this(DEFAULT_CAPACITY); 
    }

    /**
     * Instancie une arraylist avec une capacité définie.
     * @param capacity Nombre d'éléments maximum à l'initialisation.
     * @implNote Cette implémentation s'appuie sur l'item 29 (Favor generic types) du livre effective Java.
     *  L'annotation @SuppressWarnings("unchecked") permet d'éviter un warning de l'IDE.
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        // elements = new E[capacity]; // Error: Generic array creation
        // Cast du tableau de type Object en un tableau de E
        // Ceci est suffisant pour assurer la sécurité des types, mais le type du tableau à l'exécution sera toujours Object[]
        this.elements = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return elements[i];
    }

    @Override
    public E set(int i, E element) {
        checkIndex(i, size);
        E temp = elements[i];
        elements[i] = element;
        return temp;
    }

    @Override
    public void add(int i, E element) {
        
        // Vérifie la validité de l'index
        // Une arraylist stocke des éléments contigus, cette méthode permet d'éliminer une insertion à un index trop lointain
        this.checkIndex(i, size + 1);
        
        // Vérifie si le tableau à encore de la place
        this.checkCapacity(); 
        
        for(int j = size-1; j >= i; j--){ // Déplacement des éléments vers la droite, le plus à droite en 1er.
            this.elements[j+1] = this.elements[j];
        }
        
        this.elements[i] = element; // Place libre pour le nouvel élément
        
        size++;
        
    }
    
    public void add(E element){
        this.add(size, element);
    }

    @Override
    public E remove(int i) {
        checkIndex(i, size);
        
        E temp = elements[i];
        
        for(int j=i; j < size-1;j++){ // Décale les éléments vers la gauche pour remplir le trou
            elements[j] = elements[j+1];
        }
        
        elements[this.size-1] = null; // candidat au garbage collector
        this.size--;
        
        return temp;
    }
    
    public E removeLast(){
        return this.remove(this.size()-1);
    }
    
    public boolean isEquals(E element){
       
        for(int i=0; i < this.size; i++){
            if(this.get(i)== element){
                return true;
            }
        }
        
        return false;
    }
    
    // Utilitaires
    
    public int capacity(){
        return elements.length;
    }
    
    /** Vérifie si l'indice donné est dans la plage [0, n]. 
        IndexOutOfBoundsException pour signaler un indice non valide en argument. */
    protected void checkIndex(int i, int n) { 
        if (i < 0 || i >= n)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
    }
    
    /** Vérifie si le tableau à de la place disponible. 
        IllegalStateException signale qu'une méthode a été invoquée à un moment illégal ou inapproprié.*/
    protected void checkCapacity(){
        if (this.size() == elements.length)
            throw new IllegalStateException("Array is full");
    }
    
}
