package ch.hearc.ig.sda.business;

import java.time.LocalDate;
import java.time.Period;

public class Person implements Cloneable {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;

    public Person() { }
    public Person(String firstName, String lastName, LocalDate dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public int getAge(){
        int period = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
        return period;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Je suis une personne");
        sb.append("First name : ").append(this.firstName);
        sb.append("Last name : ").append(this.lastName);
        sb.append("Age : ").append(this.getAge());
        return sb.toString();
    }

    @Override
    public Person clone() {
        try {
            Person clone = (Person) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
