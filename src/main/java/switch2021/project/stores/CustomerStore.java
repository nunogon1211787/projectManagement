package switch2021.project.stores;

import switch2021.project.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerStore {

    /**
     * Customer Store Atributes
     * Contains a Customer list
     **/

    private final List<Customer> customerList;

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
        long id = this.customerList.size()+1;
        return new Customer(id, name,email);
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
        List<Customer> copyList;
        copyList = this.customerList;
        return copyList;
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
