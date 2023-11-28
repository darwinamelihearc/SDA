package ch.hearc.ig.sda.business;

import java.time.LocalDate;

public class Customer extends Person {
    private String code;

    public Customer() { }
    public Customer(String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String code) {
        super(firstName, lastName, dateOfBirth, gender);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Je suis un client " +
                "code='" + code + '\'' +
                '}';
    }
}
