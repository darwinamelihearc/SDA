package ch.heg.ig.sda.datastructure;

import ch.heg.ig.sda.business.Person;

/**
 *
 * @author maximili.jeannere
 */
public interface List {
    
    /** Retourne le nombre d'éléments stockés dans la liste.
     * @return Nombre d'éléments dans la liste. */
    int size( ); // (public abstract facultatifs)
    
    /** Retourne un booléen qui indique si la liste est vide.
     * @return True si la liste est vide. */
    boolean isEmpty( );
    
    /** Retourne l'élément à l'indice i.
     * @param i Indice
     * @return Instance de Person à l'indice i */
    Person get(int i) throws IndexOutOfBoundsException;

    /** Remplace l'élément à l'indice i par une instance de Person et renvoie l'élément remplacé.
     * @param i Indice
     * @param person Instance de Person. 
     * @return  */
    Person set(int i, Person person);
    
    /** Insère un élément de type Person à l'indice i, en décalant les éléments suivants d'un indice.
     * @param i Indice ou la personne est ajoutée.
     * @param person Instance de Person. */
    void add(int i, Person person);

    void add(Person person);
    
    /** Supprime et renvoie l'élément à l'indice i, en décalant les éléments suivants.
     * @param i indice
     * @return Instance de personne supprimée*/
    Person remove(int i);
    
}
