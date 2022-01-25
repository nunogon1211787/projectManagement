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

        return new Customer(name, email);
    }

    /**
     * Add Customer Method
     * Adds a Customer object to the customer list
     **/

    public boolean saveNewCustomer(Customer customer) {
        if (!validateCustomer(customer)) {
            customer.setCustomerId(this.customerList.size() + 1);
            this.customerList.add(customer);
        }

        return true;
    }

    public boolean validateCustomer(Customer customer) {
        boolean status = false;
        for (Customer i : customerList) {
            if (i.getCustomerName().equals(customer.getCustomerName())) {
                status = true;
                break;
            }
        }
        return status;
    }

    /**
     * Métodos Getter e Setter
     **/

    public List<Customer> getCustomerList() {
        return new ArrayList<>(customerList);
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
