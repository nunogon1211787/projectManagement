package switch2021.project.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer {

    /**
     * Customer Class
     * Class Atributes
     **/
    private int id_Costumer;
    private String email;

    private static AtomicInteger ID_GENERATOR = new AtomicInteger();

    /**
     * Costumer Constuctor
     **/

    public Customer(String email) {
        this.id_Costumer = ID_GENERATOR.getAndIncrement();
        this.email = email;
    }
}
