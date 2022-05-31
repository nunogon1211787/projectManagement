package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
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
