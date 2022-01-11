package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {


    @Test
    @DisplayName("Teste de criação de Resource")
    public void Resource(){
        //Input
        /** user **/
        UserProfile pro = new UserProfile("mku","sss");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", pro);
        LocalDate startDate = LocalDate.of(2021,12,31);
        LocalDate endDate = LocalDate.of(2022,01,05);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        //Expected
        UserProfile iso = new UserProfile("mku","sss");
        SystemUser newUser2 = new SystemUser("xyz", "fase", "des", "gth", iso);
        LocalDate startDate2 = LocalDate.of(2021,12,31);
        LocalDate endDate2 = LocalDate.of(2022,01,05);
        Resource expected = new Resource(newUser2, startDate2, endDate2, 100, .5);
        //Result
        assertEquals(input, expected);
    }

    @Test
    @DisplayName("Teste para validar check Allocation Period - False - SDtoAllocate is before and EDtoAllocate is before Allocated dates")
    public void checkAllocationPeriodFalseBothBefore() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 01);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 02);

        /** user **/
        Profile pro = new Profile("mku", "sss");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", pro);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Teste para validar check Allocation Period - False - SDtoAllocate is after and EDtoAllocate is after Allocated dates")
    public void checkAllocationPeriodFalseBothAfter() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2022, 01, 01);
        LocalDate endDateToAllocate = LocalDate.of(2022, 01, 02);

        /** user **/
        Profile pro = new Profile("mku", "sss");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", pro);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Teste para validar check Allocation Period - True - SDtoAllocate is equal and EDtoAllocate is equal Allocated dates")
    public void checkAllocationPeriodTrueEqual() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 12);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 24);

        /** user **/
        Profile pro = new Profile("mku", "sss");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", pro);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Teste para validar check Allocation Period - True - SDtoAllocate is after and EDtoAllocate is before Allocated dates")
    public void checkAllocationPeriodTrueSDisAfterEDisBefore() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);

        /** user **/
        Profile pro = new Profile("mku", "sss");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", pro);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertTrue(result);
    }
    @Test
    @DisplayName("Teste para validar check Allocation Period - True - SDtoAllocate is before and EDtoAllocate is before Allocated dates")
    public void checkAllocationPeriodTrueSDisBeforeEDisBefore() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 01);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 20);

        /** user **/
        Profile pro = new Profile("mku", "sss");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", pro);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Teste para validar check Allocation Period - True - SDtoAllocate is after and EDtoAllocate is after Allocated dates")
    public void checkAllocationPeriodTrueSDisAfterEDisAfter() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 14);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 25);

        /** user **/
        Profile pro = new Profile("mku", "sss");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", pro);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Teste para validar check User - True")
    public void checkUserTrue() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        /** user **/
        Profile pro = new Profile("mku", "sss");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", pro);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.isYourUser(newUser);

        //Assert
        assertTrue(result);
    }
}