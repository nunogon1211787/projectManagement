package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.valueObject.Customer;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomerStore {

    /**
     * Customer Store Attributes (Contains a Customer list)
     **/
    private final List<Customer> customerList;


    /**
     * Customer Store Constructor
     **/
    public CustomerStore() {
        this.customerList = new ArrayList<>();
    }


    /**
     * Create Customer (Creates a new Customer object)
     **/
    public void createAndAddCustomer(String name, String email, long nif) {
        if (getCustomerByName(name) != null) {
            throw new IllegalArgumentException("Customer already exist!");
        }
        customerList.add(new Customer(name, email, nif));
    }

    /**
     * Getter Method
     **/
    public Customer getCustomerByName(String name) {
        Customer cust = null;
        for (Customer i : this.customerList)
            if (i.getCustomerName().getText().equals(name)) {
                cust = i;
                break;
            }
        return cust;
    }
}
