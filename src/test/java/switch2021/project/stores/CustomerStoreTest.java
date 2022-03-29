package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.immutable.Description;
import switch2021.project.immutable.Email;
import switch2021.project.immutable.Nif;
import switch2021.project.model.Project.Customer;

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
        String name_value = customer.getCustomerName().getText();
        customer2.setNipc(null);
        Nif nipc = customer.getNipc();

        assertEquals(0,id_value);
        assertEquals("teste", name_value);
        assertTrue(customer.getCustomerEmail().hasEmail("teste@teste.com"));
        assertNull(customer2.getNipc());
        assertNotNull(customer.getNipc());
        assertNull(customer2.getNipc());
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
        Email change = new Email("setemail@teste.com");
        Description changeDesc = new Description("setname");
        store.saveNewCustomer(customer);

        store.getCustomerList().get(0).setCustomerEmail(change);
        store.getCustomerList().get(0).setCustomerName(changeDesc);

        assertEquals("setname",store.getCustomerList().get(0).getCustomerName().getText());
        assertTrue(store.getCustomerList().get(0).getCustomerEmail().hasEmail("setemail@teste.com"));
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