package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.utils.App;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {

    /** Objetos estão iguais, mas no assertEquals não identifica */
    @Test
    @DisplayName("Teste de criação de Resource")
    public void Resource(){
        //Arrange
        //user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
        LocalDate startDate = LocalDate.of(2021,12,31);
        LocalDate endDate = LocalDate.of(2022, 1,5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        //Act
        SystemUser newUser2 = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
        LocalDate startDate2 = LocalDate.of(2021,12,31);
        LocalDate endDate2 = LocalDate.of(2022,1,5);
        Resource expected = new Resource(newUser2, startDate2, endDate2, 100, .5);
        //Assert
        assertEquals(input, expected);
    }

    /** Objetos estão iguais, mas no assertEquals não identifica */
    @Test
    @DisplayName("Validate if copy constructor is working")
    public void copyConstructor() {
        //Arrange
        // user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
        LocalDate startDate = LocalDate.of(2021,12,31);
        LocalDate endDate = LocalDate.of(2022, 1,5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        //Act
        Resource expected = new Resource(input);
        //Assert
        assertEquals(expected,input);
    }

   /* @Test
    @DisplayName("Validate Resource Attributes")
    public void isYourUserTrueTest() {
        //Arrenge
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
        LocalDate startDate = LocalDate.of(2021,12,31);
        LocalDate endDate = LocalDate.of(2022, 1,5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        input.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act and Assert
        assertTrue(input.isYour("fase"));
        assertTrue(input.isYour(newUser));
        assertTrue(input.isYour(company.getProjectRoleStore().getProjectRole("Team Member")));
        //assertTrue(input.isCurrent());
    }

    @Test
    @DisplayName("Validate Resource Attributes")
    public void isYourUserFalseTest() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
        SystemUser testUser = new SystemUser("xyz", "test", "des", "gth", "gth","", userProfile);
        LocalDate startDate = LocalDate.of(2021,12,31);
        LocalDate endDate = LocalDate.of(2022, 1,5);
        Resource test = new Resource(testUser, LocalDate.now().plusWeeks(1), LocalDate.now().plusWeeks(3), 100, .5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        input.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act and Assert
        assertFalse(test.isYour("fase"));
        assertFalse(test.isYour(newUser));
        assertFalse(test.isYour(company.getProjectRoleStore().getProjectRole("Product Owner")));
        //assertFalse(test.isCurrent());
    }*/

    /** Objetos estão iguais, mas no assertEquals não identifica */
//    @Test
//    @DisplayName("Validate if duplicate resource and set data is correct")
//    public void duplicateResourceData() {
//        //Arrange
//            //Original
//        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
//        LocalDate startDate = LocalDate.of(2021,12,31);
//        LocalDate endDate = LocalDate.of(2022, 4,5);
//        Resource original = new Resource(newUser, startDate, endDate, 100, .5);
//        original.setRole(App.getInstance().getCompany().getProjectRoleStore().getProjectRole("Team Member"));
//            //Set Copy
//        SystemUser newUserCopy = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
//        LocalDate startDateCopy = LocalDate.of(2021,1,15);
//        LocalDate endDateCopy = LocalDate.of(2022, 4,5);
//        Resource copySet = new Resource(newUserCopy, startDateCopy, endDateCopy, 100, .5);
//        copySet.setRole(App.getInstance().getCompany().getProjectRoleStore().getProjectRole("Scrum Master"));
//            //Set Original
//        SystemUser newUserSet = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
//        LocalDate startDateSet = LocalDate.of(2021,12,31);
//        LocalDate endDateSet = LocalDate.of(2022, 1,14);
//        Resource originalSet = new Resource(newUserSet, startDateSet, endDateSet, 100, .5);
//        originalSet.setRole(App.getInstance().getCompany().getProjectRoleStore().getProjectRole("Team Member"));
//        //Act
//        Resource copy = new Resource(original);
//        copy.setStartDate(LocalDate.of(2021,1,15));
//        copy.setRole(App.getInstance().getCompany().getProjectRoleStore().getProjectRole("Scrum Master"));
//        original.setEndDate(LocalDate.of(2021,1,14));
//        //Assert
//        assertEquals(original,originalSet);
//        assertEquals(copy,copySet);
//    }

    @Test
    @DisplayName("Teste para validar check Allocation Period - False - SDtoAllocate is before and EDtoAllocate is before Allocated dates")
    public void checkAllocationPeriodFalseBothBefore() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 1);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 2);

        // user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
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

        //user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
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

        // user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
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

        // user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
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

        //user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
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

        //user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
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

        //user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.isYour(newUser);

        //Assert
        assertTrue(result);
    }

    @Test
    public void checkStartDateEndDateFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            //user
            Company company = new Company();
            UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth", "", userProfile);
            LocalDate startDate = LocalDate.of(2021, 12, 31);
            LocalDate endDate = LocalDate.of(2021, 1, 5);
            new Resource(newUser, startDate, endDate, 100, .5);
        });
    }

        @Test
        public void checkCostPerHourFail() {
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                //Arrange
                //user
                Company company = new Company();
                UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
                SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth","", userProfile);
                LocalDate startDate = LocalDate.of(2021,12,31);
                LocalDate endDate = LocalDate.of(2022, 1,5);
                new Resource(newUser, startDate, endDate, -1, .5);
            });


}
}