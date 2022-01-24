package switch2021.project.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer {

    /**
     * Customer Class
     * Class Atributes - The customer is composed of an automaticly generated ID, an email adress and a customer name.
     **/
    private long customerId;
    private String customerName;
    private String customerEmail;


    /**
     * Customer Constructor
     * Creates a new Customer instance.
     **/

    public Customer(String customerEmail, String customerName) {
        this.customerId = ID_GENERATOR.getAndIncrement();
        this.customerEmail = customerEmail;
        this.customerName = customerName;
    }

    //Create ID automatically
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    /**
     * Getter and Setters
     **/

    public long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
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
