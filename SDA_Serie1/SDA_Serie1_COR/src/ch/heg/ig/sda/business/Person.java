package ch.heg.ig.sda.business;

import java.time.LocalDate;
import java.time.Period;



/**
 *
 * @author MJM
 */
public class Person implements Cloneable {
 
    private String firstName;
    private String lastName;
    /**
     * Date of birth of the person.
     * LocalDate is an immutable date-time object that represents a date, often viewed as year-month-day. 
     * Other date fields, such as day-of-year, day-of-week and week-of-year, can also be accessed. 
     * For example, the value "2nd October 2007" can be stored in a LocalDate.
     * https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
     * https://www.oracle.com/technetwork/articles/java/jf14-date-time-2125367.html
     */
    private LocalDate dateOfBirth;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, LocalDate dateOfBirth) {
        this(firstName, lastName); // Appel du constructeur Person(String firstName, String lastName) qui possède deux paramètres
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Clone une instance de Person.
     * @return Nouvelle objet de type Person ayant les valeurs de l'objet courant (this)
     */
    @Override
    public Person clone() {

        Person person = null;

        try {
            person = (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return person;
    }

    /**
     * Copy factory.
     * Item 13: Override clone judiciously
     * @param person Instance de Person à copier
     * @return Copie de la Person .
     */
    public static Person newInstance(Person person){
        Person copy = new Person();

        if(person.firstName != null)
            copy.firstName = person.getFirstName();

        if(person.lastName != null)
            copy.lastName = person.getLastName();

        if(person.dateOfBirth != null)
            copy.dateOfBirth = person.getDateOfBirth();

        return person;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public int getAge(){
        LocalDate now = LocalDate.now();
        int period = Period.between(this.dateOfBirth, now).getYears();
        return period;
    }

    // Redéfinition de la méthode toString();
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nI'm a person");
        
        sb.append("\nFirst name : ").append(this.firstName);
        sb.append("\nLast name : ").append(this.lastName);

        sb.append("\nAge : ");

        if(this.dateOfBirth != null){
            sb.append(this.getAge());
        }else{
            sb.append(" - ");
        }
        
        sb.append("\nClass: ");
        sb.append(this.getClass());
            
        return sb.toString();    
    }
           
}
