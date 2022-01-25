package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Customer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerStoreTest {

    @Test
    void createCustomer() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com");

        long id_value= customer.getCustomerId();
        String email_value = customer.getCustomerEmail();
        String name_value = customer.getCustomerName();

        assertEquals(-1,id_value);
        assertEquals("teste", name_value);
        assertEquals("teste@teste.com", email_value);
    }

    @Test
    void add() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com");
        store.saveNewCustomer(customer);

        assertEquals(customer, store.getArrayCustomer().get(0));
    }

    @Test
    void getArrayCustomer() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com");
        store.saveNewCustomer(customer);

        List<Customer> list = new ArrayList<>();
        list.add(customer);

        assertEquals(list,store.getArrayCustomer());
    }

    @Test
    void getCustomerByName() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com");
        store.saveNewCustomer(customer);

        assertEquals(customer,store.getCustomerByName("teste"));
    }

    @Test
    void getCustomerByName_null() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com");
        store.saveNewCustomer(customer);

        assertNull(store.getCustomerByName("null"));
    }

    @Test
    void setAtributes() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com");
        store.saveNewCustomer(customer);

        store.getArrayCustomer().get(0).setCustomerEmail("setemail");
        store.getArrayCustomer().get(0).setCustomerName("setname");

        assertEquals("setname",store.getArrayCustomer().get(0).getCustomerName());
        assertEquals("setemail",store.getArrayCustomer().get(0).getCustomerEmail());
    }

    @Test
    void getHash() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com");
        store.saveNewCustomer(customer);

        assertEquals(customer.hashCode(),store.getArrayCustomer().get(0).hashCode());
    }

    @Test
    void override() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com");
        store.saveNewCustomer(customer);

        Customer customerTest = new Customer("teste","teste@teste.com");
        customerTest.setCustomerId(1);
        assertEquals(customerTest,customer);
    }
}