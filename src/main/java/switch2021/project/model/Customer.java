package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    /**
     * Customer Class
     * Attributes - The customer is composed of an automatically generated ID, an email address and a customer name.
     **/
    private long customerId;
    private String customerName;
    private String customerEmail;

    /**
     * Customer Constructor
     * Creates a new Customer instance.
     **/

    public Customer(String customerName, String customerEmail) {
        this.customerId = -1;
        this.customerEmail = customerEmail;
        this.customerName = customerName;
    }
    
}
