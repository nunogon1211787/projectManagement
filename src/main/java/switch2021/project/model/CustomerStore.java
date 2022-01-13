package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerStore {

    /**
     * <p>
     * Atributes
     **/

    private List<Customer> customerList;

    /**
     * >>>>>CUSTOMER´S METHODS<<<<<
     * <p>
     * Create Customer
     **/
    public CustomerStore() {
        this.customerList = new ArrayList<>();
    }

    public Customer createCustomer(int id, String name, String email) {
        return new Customer(id, name,email);
    }

    /**
     * Metodo adicionar Customer
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
            if (i.getName().equals(name)) {
                cust = i;
                break;
            }

        return cust;
    }

}
