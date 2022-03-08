package switch2021.project.utils;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    void isFibonacciTestSuccess() {
        //Arrange
        int n = 2;
        //Act and Assert
        assertTrue(Utils.isFibonacci(n));
    }

    @Test
    void isFibonacciTestSuccess2() {
        //Arrange
        int n = 3;
        //Act and Assert
        assertTrue(Utils.isFibonacci(n));
    }

    @Test
    void isFibonacciTestSuccess3() {
        //Arrange
        int n = 5;
        //Act and Assert
        assertTrue(Utils.isFibonacci(n));
    }

    @Test
    void isFibonacciTestSuccess4() {
        //Arrange
        int n = 1;
        //Act and Assert
        assertTrue(Utils.isFibonacci(n));
    }

    @Test
    void isFibonacciTestFail() {
        //Arrange
        int n = 4;
        //Act and Assert
        assertFalse(Utils.isFibonacci(n));
    }

    @Test
    void isFibonacciTestFail2() {
        //Arrange
        int n = 6;
        //Act and Assert
        assertFalse(Utils.isFibonacci(n));
    }

    @Test
    void isFibonacciTestFail3() {
        //Arrange
        int n = 7;
        //Act and Assert
        assertFalse(Utils.isFibonacci(n));
    }

    @Test
    void instanceNull() {
        //Arrange
        Company company = null;
        company = App.getInstance().getCompany();
        //Act and Assert
        assertNotNull(company);
    }

    @Test
    void instance() {
        //Arrange
        Company company = new Company();
        company = App.getInstance().getCompany();
        //Act and Assert
        assertNotNull(company);
    }
}