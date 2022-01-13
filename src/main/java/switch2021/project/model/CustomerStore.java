package switch2021.project.model;

import java.util.List;
import java.util.Objects;

public class CustomerStore {

    private List<Customer> customerList;


    /**
     * >>>>>CUSTOMER´S METHODS<<<<<
     * <p>
     * Create Customer
     **/
    public Customer createCustomer(String name, String email) {

        return new Customer(name, email);

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
        List<Customer> copyCustomer = this.customerList;

        return copyCustomer;
    }

    public Customer getCustomerByName(String name) {
        Customer cust = null;
        for (Customer i : this.customerList)
            if (Objects.equals(i.getName(), name)) {
                cust = i;
                break;
            }

        return cust;
    }

}
