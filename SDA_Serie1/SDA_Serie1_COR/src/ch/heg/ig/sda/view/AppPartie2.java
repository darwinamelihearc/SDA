package ch.heg.ig.sda.view;

import ch.heg.ig.sda.business.Person;

import java.time.LocalDate;

public class AppPartie2 {

    static private Person[] persons;

    /**
     * @param args the command line arguments
     * Lanceur des méthodes contenant le code pour les questions de la série d'exercice.
     */
    public static void main(String[] args) {
        point2();

        try {

            point2e();

        } catch (NullPointerException e) {
            System.out.println("NULL : " + e.getMessage());

        } catch (IllegalStateException e) {
            System.out.println("ILLEGAL : " + e.getMessage());
        }

    }

    private static void point2(){
        System.out.println("Serie 2. Question 2a-d");

        persons = new Person[3];

        Person p1 = new Person("Steve", "Dawson", LocalDate.of(1982,1,1));
        persons[0] = p1;

        Person p2 = new Person("Paulette", "Charpie", LocalDate.of(1996,1,1));
        persons[1] = p2;

        Person p3 = new Person("Benoît", "Charron", LocalDate.of(2000,1,1));
        persons[2] = p3;

        float averageAge = getAverageAge(persons);
        System.out.printf("Average age: %f \n", averageAge);

        Person younger = getYounger(persons);
        System.out.println("\nPersonne la plus jeune : " + younger.toString());

        Person older = getOlder(persons);
        System.out.println("\nPersonne la plus âgée : " + older.toString());

        //System.out.printf("\nPrenom : %s\nAge : %d\nTaille : %.2f\nMarrié : %b\nSexe : %s\n", firstname, age, size, married, sexe);

    }

    /**
     * Un NullPointerException est déclenché. Un tableau de 10 cases est parcourus alors que seuls 3 éléments y sont compris.
     * Au parcours de la case 4 (en position 3), l'erreur est déclenchée.
     */
    private static void point2e(){
        System.out.println("Serie 2. Question 2e");

        persons = new Person[3];

        Person p1 = new Person("Steve", "Dawson", LocalDate.of(1982,1,1));
        persons[0] = p1;

        Person p2 = new Person("Paulette", "Charpie", LocalDate.of(1996,1,1));
        persons[1] = p2;

        Person p3 = new Person("Benoît", "Charron", LocalDate.of(2000,1,1));
        persons[2] = p3;

        float averageAge = getAverageAge(persons);
        System.out.printf("Average age: %f \n", averageAge);

    }

    /**
     * Calcul l'âge moyen d'un tableau de personnes
     * @param persons Tableau contenant des éléments de type Person
     * @return age moyen
     */
    private static float getAverageAge(Person[] persons) throws NullPointerException, IllegalStateException {
        float ageMoyen = 0;
        int ageTotal = 0;

        for(int i = 0; i < persons.length; i++){
            ageTotal += persons[i].getAge();
        }

        ageMoyen = (ageTotal / persons.length);

        if(ageMoyen < 0)
            throw new IllegalStateException("Un age moyen négatif est impossible");

        return ageMoyen;
    }

    /**
     * Calcul l'âge moyen d'un tableau de personnes
     * @param persons Tableau contenant des éléments de type Person
     * @return age moyen
     */
    private float getAverageAgeForEach(Person[] persons){
        float ageMoyen = 0;
        int ageTotal = 0;

        // Boucle for each: évite de devoir employer les positions
        for (Person person : persons) {
            ageTotal += person.getAge();
        }

        ageMoyen = ageTotal / persons.length;

        return ageMoyen;
    }

    /**
     * Retourne la personne la plus jeune
     * @param persons Tableau contenant des éléments de type Person
     * @return  Personne la plus jeune
     */
    private static Person getYounger(Person[] persons){
        int ageMin = Integer.MAX_VALUE; // Utilisation de la classe Integer
        int position = 0;
        int youngerPersonPosition = -1; // enregistre la position du tableau ou la personne est stockée. Par défaut -1 pour éviter de retourner la première personne du tableau

        while(position < persons.length){
            if(persons[position].getAge() < ageMin){
                ageMin = persons[position].getAge();
                youngerPersonPosition = position;
            }

            position++;
        }

        return persons[youngerPersonPosition];
    }

    /**
     * Retourne la personne la plus agée
     * @param persons Tableau contenant des éléments de type Person
     * @return Personne la plus agée du tableau
     */
    private static Person getOlder(Person[] persons){
        int ageMax = Integer.MIN_VALUE; // Utilisation de la classe Integer
        int position = 0;
        int olderPersonPosition = -1; // enregistre la position du tableau ou la personne est stockée. Par défaut -1 pour éviter de retourner la première personne du tableau

        do{
            if(persons[position].getAge() > ageMax){
                ageMax = persons[position].getAge();
                olderPersonPosition = position;
            }

            position++;
        }while(position < persons.length);

        return persons[olderPersonPosition];
    }

}
