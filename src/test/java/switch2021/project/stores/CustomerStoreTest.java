package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.Immutables.Nif;
import switch2021.project.model.Customer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerStoreTest {

    @Test
    void createCustomer() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com", 123456789);
        Customer customer2 = store.createCustomer("teste","teste@teste.com", 123456789);

        long id_value= customer.getCustomerId();
        String email_value = customer.getCustomerEmail();
        String name_value = customer.getCustomerName();
        customer2.setNipc(null);
        Nif nipc = customer.getNipc();

        assertEquals(0,id_value);
        assertEquals("teste", name_value);
        assertEquals("teste@teste.com", email_value);
        assertNull(customer2.getNipc());
        assertNotNull(customer.getNipc());
        assertEquals(null,customer2.getNipc());
        assertEquals(nipc,customer.getNipc());
    }

    @Test
    void add() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com", 123456789);
        store.saveNewCustomer(customer);

        assertEquals(customer, store.getCustomerList().get(0));
    }

    @Test
    void getArrayCustomer() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com", 123456789);
        store.saveNewCustomer(customer);

        List<Customer> list = new ArrayList<>();
        list.add(customer);

        assertEquals(list,store.getCustomerList());
    }

    @Test
    void getCustomerByName() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com", 123456789);
        store.saveNewCustomer(customer);

        assertEquals(customer,store.getCustomerByName("teste"));
    }

    @Test
    void getCustomerByName_null() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com", 123456789);
        store.saveNewCustomer(customer);

        assertNull(store.getCustomerByName("null"));
    }

    @Test
    void setAttributes() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com", 123456789);
        store.saveNewCustomer(customer);

        store.getCustomerList().get(0).setCustomerEmail("setemail");
        store.getCustomerList().get(0).setCustomerName("setname");

        assertEquals("setname",store.getCustomerList().get(0).getCustomerName());
        assertEquals("setemail",store.getCustomerList().get(0).getCustomerEmail());
    }

    @Test
    void getHash() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com", 123456789);
        store.saveNewCustomer(customer);

        assertEquals(customer.hashCode(),store.getCustomerList().get(0).hashCode());
    }

    @Test
    void encapsulationTest() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com", 123456789);
        store.saveNewCustomer(customer);

        List<Customer> customerTest = store.getCustomerList();

        assertNotEquals(customerTest.hashCode(),customer.hashCode());
    }

    @Test
    void validateCustomerTrue() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com", 123456789);
        store.saveNewCustomer(customer);

        Customer customerTest = store.createCustomer("teste","teste@teste.com", 123456789);

        assertTrue(store.validateCustomer(customerTest));
    }

    @Test
    void validateCustomerFalse() {
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste","teste@teste.com", 123456789);
        store.saveNewCustomer(customer);

        Customer customerTest = store.createCustomer("teste2","teste2@teste.com", 123456789);

        assertFalse(store.validateCustomer(customerTest));
    }

    @Test
    void saveNewCustomerAndCheckID(){
        //Arrange
        CustomerStore store = new CustomerStore();
        Customer customer = store.createCustomer("teste", "teste@teste.com", 123456789);
        //Act
        store.saveNewCustomer(customer);
        //Assert
        assertEquals(1, customer.getCustomerId());
    }

    @Test
    void saveTwoNewCustomerAndCheckSecondID(){
        //Arrange
        CustomerStore store = new CustomerStore();
        Customer customer1 = store.createCustomer("teste", "teste@teste.com", 123456789);
        Customer customer2 = store.createCustomer("teste2", "teste2@teste.com", 123456789);
        //Act
        store.saveNewCustomer(customer1);
        store.saveNewCustomer(customer2);
        //Assert
        assertEquals(2, customer2.getCustomerId());
    }

}