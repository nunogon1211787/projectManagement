package switch2021.project.model.valueObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    /**
     * Customer Class
     * Attributes - The customer is composed of an automatically generated ID, an email address and a customer name.
     **/
    private Description customerName;
    private Email customerEmail;
    private Nif nipc;


    /**
     * Customer Constructor
     * Creates a new Customer instance.
     **/
    public Customer(String customerName, String customerEmail, long nif) {
        this.customerEmail = new Email(customerEmail);
        this.customerName = new Description(customerName);
        this.nipc = new Nif(nif);
    }
}
