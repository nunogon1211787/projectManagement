package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {

    private Company company;
    private UserProfile userProfile;

    @BeforeEach
    public void init() {
        company = App.getInstance().getCompany(); // sempre a mesma instancia
        userProfile = company.createProfile("Cris");
        company.getUserProfileStore().addProfile(userProfile);
    }


    @Test
    @DisplayName("Teste de criação de Resource")
    public void Resource(){
        //Input
        /** user **/
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
        LocalDate startDate = LocalDate.of(2021,12,31);
        LocalDate endDate = LocalDate.of(2022, 1,5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        //Expected
        SystemUser newUser2 = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
        LocalDate startDate2 = LocalDate.of(2021,12,31);
        LocalDate endDate2 = LocalDate.of(2022,1,5);
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

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 1);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 2);

        /** user **/
        UserProfile pro = new UserProfile( "mku");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", pro);
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

        LocalDate startDateToAllocate = LocalDate.of(2022, 1, 1);
        LocalDate endDateToAllocate = LocalDate.of(2022, 1, 2);

        /** user **/
        UserProfile pro = new UserProfile("mku");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", pro);
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
        UserProfile pro = new UserProfile("mku");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", pro);
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
        UserProfile pro = new UserProfile("mku");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", pro);
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

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 1);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 20);

        /** user **/
        UserProfile pro = new UserProfile("mku");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", pro);
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
        UserProfile pro = new UserProfile("mku");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", pro);
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
        UserProfile pro = new UserProfile("mku");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", pro);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.isYourUser(newUser);

        //Assert
        assertTrue(result);
    }

    @Test
    public void checkStartDateEndDateFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange

            /** user **/
            SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth", "", userProfile);
            LocalDate startDate = LocalDate.of(2021, 12, 31);
            LocalDate endDate = LocalDate.of(2021, 1, 5);
            Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        });
    }

        @Test
        public void checkCostPerHourFail() {
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                //Arrange

                /** user **/
                SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
                LocalDate startDate = LocalDate.of(2021,12,31);
                LocalDate endDate = LocalDate.of(2022, 1,5);
                Resource input = new Resource(newUser, startDate, endDate, -1, .5);
            });

}
}