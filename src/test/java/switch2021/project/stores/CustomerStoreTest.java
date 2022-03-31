package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.valueObject.Customer;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.Email;
import switch2021.project.model.valueObject.Nif;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerStoreTest {

    @Test
    void createCustomer() {
        CustomerStore store = new CustomerStore();
        store.createAndAddCustomer("teste", "teste@teste.com", 123456789);
        store.createAndAddCustomer("teste2", "teste@teste.com", 123456789);

        Customer customer = store.getCustomerByName("teste");
        Customer customer2 = store.getCustomerByName("teste2");

        String name_value = customer.getCustomerName().getText();
        customer2.setNipc(null);
        Nif nipc = customer.getNipc();

        assertEquals("teste", name_value);
        assertTrue(customer.getCustomerEmail().hasEmail("teste@teste.com"));
        assertNull(customer2.getNipc());
        assertNotNull(customer.getNipc());
        assertNull(customer2.getNipc());
        assertEquals(nipc, customer.getNipc());
    }

    @Test
    void add() {
        CustomerStore store = new CustomerStore();
        store.createAndAddCustomer("teste", "teste@teste.com", 123456789);

        Customer customer = store.getCustomerByName("teste");

        assertEquals(customer, store.getCustomerList().get(0));
    }

    @Test
    void getArrayCustomer() {
        CustomerStore store = new CustomerStore();
        store.createAndAddCustomer("teste", "teste@teste.com", 123456789);

        assertEquals(1, store.getCustomerList().size());
    }

    @Test
    void getCustomerByName() {
        //Arrange
        CustomerStore store = new CustomerStore();
        store.createAndAddCustomer("test", "teste@teste.com", 123456789);
        //Act
        Customer customer = store.getCustomerByName("test");
        //Assert
        assertEquals("test", customer.getCustomerName().getText());
    }

    @Test
    void getCustomerByName_null() {
        CustomerStore store = new CustomerStore();
        store.createAndAddCustomer("teste", "teste@teste.com", 123456789);

        assertNull(store.getCustomerByName("null"));
    }

    @Test
    void setAttributes() {
        CustomerStore store = new CustomerStore();
        store.createAndAddCustomer("teste", "teste@teste.com", 123456789);
        Email change = new Email("setemail@teste.com");
        Description changeDesc = new Description("setname");

        store.getCustomerList().get(0).setCustomerEmail(change);
        store.getCustomerList().get(0).setCustomerName(changeDesc);

        assertEquals("setname", store.getCustomerList().get(0).getCustomerName().getText());
        assertTrue(store.getCustomerList().get(0).getCustomerEmail().hasEmail("setemail@teste.com"));
    }

    @Test
    void getHash() {
        //Arrange
        CustomerStore store = new CustomerStore();
        store.createAndAddCustomer("teste", "teste@teste.com", 123456789);
        //Act
        Customer customer = store.getCustomerByName("teste");
        assertEquals(customer.hashCode(), store.getCustomerList().get(0).hashCode());
    }

    @Test
    void encapsulationTest() {
        CustomerStore store = new CustomerStore();
        store.createAndAddCustomer("teste", "teste@teste.com", 123456789);

        List<Customer> customerTest = store.getCustomerList();
        Customer customer = store.getCustomerByName("teste");

        assertNotEquals(customerTest.hashCode(), customer.hashCode());
    }

}