package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.stores.CustomerStore;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {


    @Test
    void testHashCode() {
        Customer customer = new Customer("ZeManel", "zemanel@beaver.com");
        Customer customer2 = new Customer("ZePaulo", "zepaulo@beaver.com");
        Customer x = new Customer("ZeManel", "zemanel@beaver.com");


        assertNotEquals(customer.hashCode(),customer2.hashCode());
        assertEquals(customer.hashCode(),x.hashCode());
        assertNotEquals(null, customer);
        assertEquals(x.getCustomerName(), customer.getCustomerName());
        assertEquals(x.getCustomerEmail(), customer.getCustomerEmail());
        assertEquals(x.getCustomerId(), customer.getCustomerId());
        assertNotEquals(customer2.getCustomerName(), customer.getCustomerName());
        assertNotEquals(customer2.getCustomerEmail(), customer.getCustomerEmail());
    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        CustomerStore list1 = new CustomerStore();
        Customer customer = list1.createCustomer("manel", "manel@beaver.com");
        list1.saveNewCustomer(customer);
        CustomerStore list2 = new CustomerStore();
        list2.saveNewCustomer(customer);
        CustomerStore list3 = new CustomerStore();
        list3.saveNewCustomer(list3.createCustomer("jose", "jose@beaver.com"));

          //Assert
        assertNotSame(list1, list2);
        assertEquals(list1, list2);
        assertEquals(list1.hashCode(), list2.hashCode());
        assertNotEquals(list1, list3);
        assertNotEquals(list1.hashCode(), list3.hashCode());
    }
}