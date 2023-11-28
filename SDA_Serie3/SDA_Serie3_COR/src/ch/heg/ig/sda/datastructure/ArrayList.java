package ch.heg.ig.sda.datastructure;

import ch.heg.ig.sda.business.Person;

/**
 *
 * @author maximili.jeannere
 * ArrayList contenant des intances de type Person de taille fixe. 
 */
public class ArrayList implements List {

    private static final int DEFAULT_CAPACITY=10;

    private Person[] elements; // Tableau pour stocker des Person
    private int size = 0; // Nombre d'éléments
    
    // Constructeurs
    
    public ArrayList( ) {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructeur de capacité donnée.
     * @param capacity Capactité du tableau
     */
    public ArrayList(int capacity) {
        elements = new Person[capacity];
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
    public Person get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return elements[i];
    }

    @Override
    public Person set(int i, Person person) {
        checkIndex(i, size);
        Person temp = elements[i];
        elements[i] = person;
        return temp;
    }

    @Override
    public void add(int i, Person person) {

        // Vérifie la validité de l'index
        // Une arraylist stocke des éléments contigus, cette méthode permet d'éliminer une insertion à un index trop lointain
        this.checkIndex(i, this.size + 1);
        
        // Vérifie si le tableau à encore de la place
        this.checkCapacity();
        
        for(int j = this.size-1; j >= i; j--){ // Déplacement des éléments vers la droite, le plus à droite en 1er.
            this.elements[j+1] = this.elements[j];
        }
        
        this.elements[i] = person; // Place libre pour le nouvel élément
        
        this.size++;
        
    }

    @Override
    public void add(Person person) {
        this.add(size, person);
    }

    @Override
    public Person remove(int i) {
        checkIndex(i, size);
        
        Person temp = elements[i];
        
        for(int j=i; j < size-1;j++){ // Décale les éléments vers la gauche pour remplir le trou
            elements[j] = elements[j+1];
        }
        
        elements[this.size-1] = null; // candidat au garbage collector
        this.size--;
        
        return temp;
    }

    // Utilitaires
    
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
