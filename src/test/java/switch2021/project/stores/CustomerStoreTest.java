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

        assertEquals(customer, store.getCustomerList().get(0));
    }

    @Test
    void getArrayCustomer() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com");
        store.saveNewCustomer(customer);

        List<Customer> list = new ArrayList<>();
        list.add(customer);

        assertEquals(list,store.getCustomerList());
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
    void setAttributes() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com");
        store.saveNewCustomer(customer);

        store.getCustomerList().get(0).setCustomerEmail("setemail");
        store.getCustomerList().get(0).setCustomerName("setname");

        assertEquals("setname",store.getCustomerList().get(0).getCustomerName());
        assertEquals("setemail",store.getCustomerList().get(0).getCustomerEmail());
    }

    @Test
    void getHash() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com");
        store.saveNewCustomer(customer);

        assertEquals(customer.hashCode(),store.getCustomerList().get(0).hashCode());
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

    @Test
    void encapsulationTest() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com");
        store.saveNewCustomer(customer);

        List<Customer> customerTest = store.getCustomerList();

        assertNotEquals(customerTest.hashCode(),customer.hashCode());
    }

    @Test
    void validateCustomerTrue() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com");
        store.saveNewCustomer(customer);

        Customer customerTest = store.createCustomer("teste","teste@teste.com");

        assertTrue(store.validateCustomer(customerTest));
    }

    @Test
    void validateCustomerFalse() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com");
        store.saveNewCustomer(customer);

        Customer customerTest = store.createCustomer("teste2","teste2@teste.com");

        assertFalse(store.validateCustomer(customerTest));
    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        CustomerStore list1 = new CustomerStore();
        list1.saveNewCustomer(list1.createCustomer("new","email@isep.pt"));
        CustomerStore list2 = new CustomerStore();
        list2.saveNewCustomer(list1.createCustomer("new","email@isep.pt"));
        CustomerStore list3 = new CustomerStore();
        list3.saveNewCustomer(list3.createCustomer("not new", "email@isep.pt"));
        //Assert
        assertNotSame(list1, list2);
        assertEquals(list1, list2);
        assertEquals(list1.hashCode(), list2.hashCode());
        assertNotEquals(list1, list3);
        assertNotEquals(list1.hashCode(), list3.hashCode());
    }

    @Test
    public void overrideNull() {
        //Arrange
        Customer customer = null;
        Customer customerTest = new Customer("teste","teste@teste.com");

        assertNotEquals(customerTest,customer);
    }
}