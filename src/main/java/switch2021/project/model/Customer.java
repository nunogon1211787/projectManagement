package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.immutable.Nif;

@Getter
@Setter
public class Customer {

    /**
     * Customer Class
     * Attributes - The customer is composed of an automatically generated ID, an email address and a customer name.
     **/
    private int customerId;
    private String customerName;
    private String customerEmail;
    private Nif nipc;

    /**
     * Customer Constructor
     * Creates a new Customer instance.
     **/

    public Customer(String customerName, String customerEmail, long nif) {
        this.customerEmail = customerEmail;
        this.customerName = customerName;
        this.nipc = new Nif(nif);
    }
    
}
