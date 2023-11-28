import ch.hearc.ig.sda.business.Customer;
import ch.hearc.ig.sda.business.Gender;
import ch.hearc.ig.sda.business.Person;

import java.time.LocalDate;
import java.util.concurrent.AbstractExecutorService;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class App {
    public static void main(String[] args) {

        Customer c1 = new Customer();

        c1.setFirstName("Darwin");
        c1.setLastName("Ameli");
        c1.setGender(Gender.M);
        c1.setDateOfBirth(LocalDate.of(2000, 1, 6));
        c1.setCode("0001");

        System.out.println(c1);

        Person p1 = new Customer();

        p1.setFirstName("Lorenzo");
        p1.setLastName("Dignoti");
        p1.setGender(Gender.F);
        p1.setDateOfBirth(LocalDate.of(1999, 10, 5));

        System.out.println(p1);

        Person c2 = c1.clone();

        c2.toString();
    }
}