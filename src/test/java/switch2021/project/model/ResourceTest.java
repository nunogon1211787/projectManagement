package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.factory.ResourceFactory;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResourceTest {

    @Test
    @DisplayName("Test to Check Resource Creation")
    public void Resource(){
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "as", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        input.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act
        SystemUser newUser2 = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "as", userProfile);
        LocalDate startDate2 = LocalDate.of(2021, 12, 31);
        LocalDate endDate2 = LocalDate.of(2022, 1, 5);
        Resource expected = new Resource(newUser2, startDate2, endDate2, 100, .5);
        expected.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Assert
        assertEquals(input, expected);
        assertEquals(newUser, input.getUser());
        assertEquals(startDate, input.getStartDate());
        assertEquals(endDate, input.getEndDate());
        assertEquals(100, input.getCostPerHour());
        assertEquals(.5, input.getPercentageOfAllocation());
    }

    @Test
    @DisplayName("Test to Check Resource Creation - Cost Per Hour 0")
    public void ResourceCostPerHour0(){
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "as", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, 0, .5);
        input.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act
        SystemUser newUser2 = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "as", userProfile);
        LocalDate startDate2 = LocalDate.of(2021, 12, 31);
        LocalDate endDate2 = LocalDate.of(2022, 1, 5);
        Resource expected = new Resource(newUser2, startDate2, endDate2, 0, .5);
        expected.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Assert
        assertEquals(input, expected);
    }


    @Test
    @DisplayName("Validate if copy constructor is working")
    public void copyConstructor() {
        //Arrange
        // user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        //Act
        Resource expected = new Resource(input);
        //Assert
        assertEquals(expected, input);
    }

    @Test
    @DisplayName("Test to Check If Resource is Available in a Period of Time (Available date is before global start date)")
    public void checkIfResourceIsAvailableSuccess() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1","", userProfile);
        LocalDate startDate = LocalDate.of(2022,1,1);
        LocalDate endDate = LocalDate.of(2022, 1,20);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        //Act
        LocalDate date = LocalDate.of(2022, 1, 10);
        //Assert
        assertTrue(input.isAvailableToSprint(date,1));
    }

    @Test
    @DisplayName("Test to Check If Resource is Available in a Period of Time (Available date is equal global start date)")
    public void checkIfResourceIsAvailableEqualDate() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1","", userProfile);
        LocalDate startDate = LocalDate.of(2022,1,1);
        LocalDate endDate = LocalDate.of(2022, 1,15);
        Resource input = new Resource(newUser, startDate, endDate, 100, 1);
        //Act
        LocalDate date = LocalDate.of(2022, 1, 2);
        //Assert
        assertTrue(input.isAvailableToSprint(date,1));
    }

    @Test
    @DisplayName("Test to Check If Resource is Available in a Period of Time (Available date is equal global end date)")
    public void checkIfResourceIsAvailableEqualDate2() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1","", userProfile);
        LocalDate startDate = LocalDate.of(2022,1,1);
        LocalDate endDate = LocalDate.of(2022, 1,20);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        //Act
        LocalDate date = LocalDate.of(2022, 1, 14);
        //Assert
        assertTrue(input.isAvailableToSprint(date,1));
    }

    @Test
    @DisplayName("Test to Check If Resource is Unavailable in a Period of Time")
    public void checkIfResourceIsUnavailableFail() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1","", userProfile);
        LocalDate startDate = LocalDate.of(2022,1,1);
        LocalDate endDate = LocalDate.of(2022, 1,20);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        //Act
        LocalDate date = LocalDate.of(2022, 1, 22);
        //Assert
        assertFalse(input.isAvailableToSprint(date,1));
    }

    @Test
    @DisplayName("Validate Resource Attributes")
    public void isYourUserTrueTest() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        LocalDate startDate = LocalDate.now().minusWeeks(1);
        LocalDate endDate = LocalDate.now().plusWeeks(1);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        input.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act and Assert
        assertTrue(input.isYourEmail("fase"));
        assertTrue(input.isYourEmail(newUser));
        assertTrue(input.isYourEmail(company.getProjectRoleStore().getProjectRole("Team Member")));
        assertTrue(input.isYourName("xyz"));
        assertTrue(input.isCurrent());
    }

    @Test
    @DisplayName("Validate Resource Attributes")
    public void isYourUserTrueTest2() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusWeeks(1);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        input.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act and Assert
        assertTrue(input.isYourEmail("fase"));
        assertTrue(input.isYourEmail(newUser));
        assertTrue(input.isYourEmail(company.getProjectRoleStore().getProjectRole("Team Member")));
        assertTrue(input.isYourName("xyz"));
        assertTrue(input.isCurrent());
    }

    @Test
    @DisplayName("Validate Resource Attributes")
    public void isYourUserTrueTest3() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        LocalDate startDate = LocalDate.now().minusWeeks(1);
        LocalDate endDate = LocalDate.now();
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        input.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act and Assert
        assertTrue(input.isYourEmail("fase"));
        assertTrue(input.isYourEmail(newUser));
        assertTrue(input.isYourEmail(company.getProjectRoleStore().getProjectRole("Team Member")));
        assertTrue(input.isYourName("xyz"));
        assertTrue(input.isCurrent());
    }

    @Test
    @DisplayName("Validate Resource Attributes - Fail")
    public void isYourUserFalseTest() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        SystemUser testUser = new SystemUser("xyz", "fase1@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        LocalDate startDate = LocalDate.now().minusWeeks(1);
        LocalDate endDate = LocalDate.now().plusWeeks(1);
        Resource test = new Resource(testUser, LocalDate.now().plusWeeks(1), LocalDate.now().plusWeeks(3), 100, .5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        input.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act and Assert
        assertFalse(test.isYourEmail("fase@beaver.com"));
        assertFalse(test.isYourEmail(newUser));
        //assertTrue(test.isYour(company.getProjectRoleStore().getProjectRole("Team Member")));
        assertFalse(test.isYourName("ert"));
        assertFalse(test.isCurrent());
    }

    @Test
    @DisplayName("Validate Resource Attributes - Fail")
    public void isYourUserFalseTest2() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        SystemUser testUser = new SystemUser("xyz", "fas1e@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusWeeks(1);
        Resource test = new Resource(testUser, LocalDate.now().plusWeeks(1), LocalDate.now().plusWeeks(3), 100, .5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        input.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act and Assert
        assertFalse(test.isYourEmail("fase"));
        assertFalse(test.isYourEmail(newUser));
        //assertTrue(test.isYour(company.getProjectRoleStore().getProjectRole("Team Member")));
        assertFalse(test.isYourName("ert"));
        assertFalse(test.isCurrent());
    }

    @Test
    public void checkIfResourceIsActiveAndCurrentTrue() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        SystemUser testUser = new SystemUser("xyz", "fase1@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        LocalDate startDate = LocalDate.now().minusWeeks(1);
        LocalDate endDate = LocalDate.now().plusWeeks(10);
        Resource test = new Resource(testUser, LocalDate.now().plusWeeks(1), LocalDate.now().plusWeeks(3), 100, .5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        input.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act and Assert
        assertTrue(input.isAvailableToSprint(LocalDate.now(),2));
    }
    @Test
    public void checkIfResourceIsActiveAndCurrentTrue2() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        SystemUser testUser = new SystemUser("xyz", "fase1@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        LocalDate startDate = LocalDate.now().minusWeeks(1);
        LocalDate endDate = LocalDate.now().plusWeeks(10);
        Resource test = new Resource(testUser, LocalDate.now().plusWeeks(1), LocalDate.now().plusWeeks(3), 100, .5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        input.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act and Assert
        assertTrue(input.isAvailableToSprint(LocalDate.now().plusWeeks(1),2));
    }

    @Test
    public void checkIfResourceIsActiveAndCurrentFalse() {
        //Arrange
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        SystemUser testUser = new SystemUser("xyz", "fas1e@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        LocalDate startDate = LocalDate.now().minusWeeks(1);
        LocalDate endDate = LocalDate.now().plusWeeks(1);
        Resource test = new Resource(testUser, LocalDate.now().plusWeeks(1), LocalDate.now().plusWeeks(3), 100, .5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        input.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act and Assert
        assertFalse(input.isAvailableToSprint(LocalDate.now(),3));
    }


    @Test
    @DisplayName("Validate if duplicate resource and set data is correct")
    public void duplicateResourceData() {
        //Arrange
        Company company = new Company();
            //Original
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1","", company.getUserProfileStore().getUserProfile("Visitor"));
        LocalDate startDate = LocalDate.of(2021,12,31);
        LocalDate endDate = LocalDate.of(2022, 4,5);
        Resource original = new Resource(newUser, startDate, endDate, 100, .5);
        original.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
            //Set Copy
        SystemUser newUserCopy = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1","", company.getUserProfileStore().getUserProfile("Visitor"));
        LocalDate startDateCopy = LocalDate.of(2021,1,15);
        LocalDate endDateCopy = LocalDate.of(2022, 4,5);
        Resource copySet = new Resource(newUserCopy, startDateCopy, endDateCopy, 100, .5);
        copySet.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
            //Set Original
        SystemUser newUserSet = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1","", company.getUserProfileStore().getUserProfile("Visitor"));
        LocalDate startDateSet = LocalDate.of(2021,12,31);
        LocalDate endDateSet = LocalDate.of(2022, 1,14);
        Resource originalSet = new Resource(newUserSet, startDateSet, endDateSet, 100, .5);
        originalSet.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act
        Resource copy = new Resource(original);
        copy.setStartDate(LocalDate.of(2021,1,15));
        copy.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
        original.setEndDate(LocalDate.of(2021,1,14));
        //Assert
//        assertEquals(original,originalSet); **** REVER ****
        assertEquals(copy,copySet);
    }

    @Test
    @DisplayName("Validate duplicate resource - Success")
    public void duplicateResourceSuccess() {
        //Arrange
        //Original
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1","", userProfile);
        LocalDate startDate = LocalDate.of(2021,12,31);
        LocalDate endDate = LocalDate.of(2022, 4,5);
        Resource original = new Resource(joana, startDate, endDate, 100, .5);
        original.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Copy
        SystemUser joana1 = new SystemUser("xyz", "fase1@beaver.com", "des", "Qwerty_1", "Qwerty_1","", userProfile);
        LocalDate startDateSet = LocalDate.of(2021,12,31);
        LocalDate endDateSet = LocalDate.of(2022, 4,5);
        Resource copy = new Resource(joana, startDateSet, endDateSet, 100, .5);
        copy.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Assert
        assertNotSame(original,copy);
        assertEquals(original,copy);
        assertEquals(company.getProjectRoleStore().getProjectRole("Team Member"), copy.getRole());
        assertEquals(startDate, copy.getStartDate());
        assertEquals(endDate, copy.getEndDate());
        assertEquals(original.getCostPerHour(),copy.getCostPerHour());
        assertEquals(original.getPercentageOfAllocation(), copy.getPercentageOfAllocation());
    }

    @Test
    @DisplayName("Validate duplicate resource - Fail")
    public void duplicateResourceFail() {
        //Arrange
        //Original
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana = new SystemUser("jose", "fase@beaver.com", "abc", "Qwerty_1", "Qwerty_1","456", userProfile);
        LocalDate startDate = LocalDate.of(2021,12,31);
        LocalDate endDate = LocalDate.of(2022, 4,5);
        Resource original = new Resource(joana, startDate, endDate, 100, .5);
        original.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Copy
        SystemUser joana1 = new SystemUser("jose um", "fase1@beaver.com", "123", "Qwerty_1", "Qwerty_1","img", userProfile);
        LocalDate startDateSet = LocalDate.of(2022,1,1);
        LocalDate endDateSet = LocalDate.of(2022, 1,15);
        Resource copy = new Resource(joana1, startDateSet, endDateSet, 10, .2);
        copy.setRole(company.getProjectRoleStore().getProjectRole("Developer"));
        //Assert
        assertNotEquals(original,copy);
        assertNotEquals(company.getProjectRoleStore().getProjectRole("Team Member"), copy.getRole());
        assertNotEquals(startDate, copy.getStartDate());
        assertNotEquals(endDate, copy.getEndDate());
        assertNotEquals(original.getCostPerHour(),copy.getCostPerHour());
        assertNotEquals(original.getPercentageOfAllocation(), copy.getPercentageOfAllocation());

    }

    @Test
    @DisplayName("Test to Check Allocation Period - False - SDtoAllocate is before and EDtoAllocate is before Allocated dates")
    public void checkAllocationPeriodFalseBothBefore() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 1);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 2);

        // user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to Check Allocation Period - False - SDtoAllocate is after and EDtoAllocate is after Allocated dates")
    public void checkAllocationPeriodFalseBothAfter() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2022, 1, 1);
        LocalDate endDateToAllocate = LocalDate.of(2022, 1, 2);

        //user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to Check Allocation Period - True - SDtoAllocate is equal and EDtoAllocate is equal Allocated dates")
    public void checkAllocationPeriodTrueBothEqual() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 12);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 24);

        // user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to Check Allocation Period - True - SDtoAllocate is equal to ED allocated")
    public void checkAllocationPeriodTrueSDEqualED() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 24);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 24);

        // user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to Check Allocation Period - True - EDtoAllocate is equal and SD allocated")
    public void checkAllocationPeriodTrueEDisEqualSD() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 1);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 12);

        // user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to Check Allocation Period - True - SDtoAllocate is after and EDtoAllocate is before Allocated dates")
    public void checkAllocationPeriodTrueSDisAfterEDisBefore() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);

        // user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to Check Allocation Period - True - SDtoAllocate is before and EDtoAllocate is before Allocated dates")
    public void checkAllocationPeriodTrueSDisBeforeEDisBefore() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 1);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 20);

        //user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to Check Allocation Period - True - SDtoAllocate is after and EDtoAllocate is after Allocated dates")
    public void checkAllocationPeriodTrueSDisAfterEDisAfter() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 14);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 25);

        //user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to Check Allocation Period - True - SDtoAllocate is before SDAllocated and EDtoAllocate is after SD allocated")
    public void checkAllocationPeriodTrueSDisBeforeSDandEDisAfterSD() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 10);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);

        //user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.checkAllocationPeriod(startDateToAllocate, endDateToAllocate);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to Check User - True")
    public void checkUserTrue() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        //user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        Resource resAllo = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        //Act
        boolean result = resAllo.isYourEmail(newUser);

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
            SystemUser newUser = new SystemUser("xyz", "fase", "des", "Switch_22", "Switch_22", "", userProfile);
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
            SystemUser newUser = new SystemUser("xyz", "fase", "des", "Switch_22", "Switch_22", "", userProfile);
            LocalDate startDate = LocalDate.of(2021, 12, 31);
            LocalDate endDate = LocalDate.of(2022, 1, 5);
            new Resource(newUser, startDate, endDate, -1, .5);
        });
    }

    @Test
    @DisplayName("Test setStartDate New role")
    public void setStartDate() {
        //Arrange
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate newStartDateAllocated = LocalDate.of(2021, 12, 13);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);


        //user
        Company company = new Company();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "", userProfile);
        Resource res = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);
        Resource resExpected = new Resource(newUser, newStartDateAllocated, endDateAllocated, 100, .5);

        //Act
        res.setStartDate(newStartDateAllocated);

        //Assert
        assertEquals(resExpected,res);
        assertEquals(newStartDateAllocated,res.getStartDate());
    }

    /**
     * Create a new Resource Test
     */
    @Test  //TODO Passou de ProjectTeamTest para aqui, é preciso rever se vai continuar aqui
    public void createResourceTestSuccess() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com",
                "tester", "Qwerty_1", "Qwerty_1", "photo", profile);
        Resource manuelbras = new Resource(user1, LocalDate.now(), LocalDate.now().plusWeeks(4), 100, .5);
        ResourceFactory resFac = mock(ResourceFactory.class);
        when(resFac.createResource(user1, LocalDate.now(), LocalDate.now().plusWeeks(4), 100, .5)).thenReturn(manuelbras);
        //Act

        Resource test = resFac.createResource(user1, LocalDate.now(), LocalDate.now().plusWeeks(4), 100, .5);
        //Assert
        assertEquals(test, manuelbras);
    }

    @Test  //TODO Passou de ProjectTeamTest para aqui, é preciso rever se vai continuar aqui
    public void createResourceTestFail() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            Company company = new Company();
            Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
            Customer customer = company.getCustomerStore().getCustomerByName("isep");
            BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            //Project 1
            Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                    typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
            proj1.setEndDate(LocalDate.now().plusYears(1));
            company.getProjectStore().saveNewProject(proj1);
            //Act
            proj1.getProjectTeam().createResource(null, LocalDate.now(), LocalDate.now().plusWeeks(4), 100, .5);
        });
    }

}