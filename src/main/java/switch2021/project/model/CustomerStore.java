package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerStore {

    /**
     * Customer Store Atributes
     * Contains a Customer list
     **/

    private List<Customer> customerList;

    /**
     * Customer Store Constructor
     **/

    public CustomerStore() {
        this.customerList = new ArrayList<>();
    }

    /**
     * Create Customer
     * Creates a new Customer object
     **/

    public Customer createCustomer(String name, String email) {
        return new Customer(name,email);
    }

    /**
     * Add Customer Method
     * Adds a Customer object to the customer list
     **/

    public boolean add(Customer customer) {
        this.customerList.add(customer);
        return true;
    }

    /**
     * Métodos Getter e Setter
     **/

    public List<Customer> getArrayCustomer() {

        return this.customerList;
    }

    public Customer getCustomerByName(String name) {
        Customer cust = null;
        for (Customer i : this.customerList)
            if (i.getCustomerName().equals(name)) {
                cust = i;
                break;
            }

        return cust;
    }

}
