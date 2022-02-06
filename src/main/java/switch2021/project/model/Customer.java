package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

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

    /**
     * Override Methods
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;

        return ((this.customerEmail.equals(that.customerEmail)) && (this.customerId ==that.customerId)  &&
                (this.customerName.equals(that.customerName)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerEmail,customerId,customerName);
    }
}
