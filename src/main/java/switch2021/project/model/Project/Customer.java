package switch2021.project.model.Project;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.immutable.Description;
import switch2021.project.immutable.Email;
import switch2021.project.immutable.Nif;

@Getter
@Setter
public class Customer {

    /**
     * Customer Class
     * Attributes - The customer is composed of an automatically generated ID, an email address and a customer name.
     **/
    private int customerId;
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
