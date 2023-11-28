package ch.heg.ig.sda.datastructure;

import ch.heg.ig.sda.business.Person;

import javax.swing.*;

public class ArrayListDynamic implements List {

    private Person[] elements; // Tableau pour stocker des Person
    private int size = 0; // Nombre d'éléments

    public ArrayListDynamic(int size) {
        elements = new Person[size];
    }

    @Override
    public int size() { return size; }

    /**
     * Retourne un booléen qui indique si la liste est vide.
     *
     * @return True si la liste est vide.
     */
    @Override
    public boolean isEmpty() { return size == 0; }

    /**
     * Retourne l'élément à l'indice i.
     *
     * @param i Indice
     * @return Instance de Person à l'indice i
     */
    @Override
    public Person get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return elements[i];
    }

    /**
     * Remplace l'élément à l'indice i par une instance de Person et renvoie l'élément remplacé.
     *
     * @param i      Indice
     * @param person Instance de Person.
     * @return
     */
    @Override
    public Person set(int i, Person person) {
        checkIndex(i, size);
        Person temp = elements[i];
        elements[i] = person;
        return temp;
    }

    /**
     * Insère un élément de type Person à l'indice i, en décalant les éléments suivants d'un indice.
     *
     * @param i      Indice ou la personne est ajoutée.
     * @param person Instance de Person.
     */
    @Override
    public void add(int i, Person person) {
        this.checkIndex(i, this.size + 1);

        this.checkCapacity();

        for (int j = this.size - 1; j >= i; j--) {
            this.elements[j+1] = this.elements[j];
        }

        this.elements[i] = person;

        this.size++;
    }

    @Override
    public void add(Person person) {
        this.add(size, person);
    }

    /**
     * Supprime et renvoie l'élément à l'indice i, en décalant les éléments suivants.
     *
     * @param i indice
     * @return Instance de personne supprimée
     */
    @Override
    public Person remove(int i) {
        checkIndex(i, size);

        Person temp = elements[i];

        for (int j = 1; j < size - 1; j++) {
            elements[j] = elements[j + 1];
        }

        elements[this.size - 1] = null;
        this.size--;

        return temp;
    }

    @Override
    public Person removeLast() {
        return this.remove(this.size - 1);
    }

    /** Vérifie si l'indice donné est dans la plage [0, n].
     IndexOutOfBoundsException pour signaler un indice non valide en argument. */
    protected void checkIndex(int i, int n) {
        if (i < 0 || i >= n)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
    }

    /** Vérifie si le tableau à de la place disponible.
     IllegalStateException signale qu'une méthode a été invoquée à un moment illégal ou inapproprié.*/
    protected void checkCapacity() {
        if (this.size() == elements.length) {
            growCapacity();
        }
    }

    /**
     * Implementation de la liste dynamique
     */
    protected void growCapacity() {
        Person[] elementsTemp = elements;
        elements = new Person[this.size() * 2];

        int i = 0;
        for (Person p : elementsTemp) {
            elements[i++] = p;
        }
    }
}
