package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.valueObject.Password;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.valueObject.UserProfile;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SystemUserStoreTest {
    @Test
    public void createSystemUserSuccess() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "tester";
        String photo = "photo";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo, profile);

        String userNameExpected = "manueloliveira";
        String emailExpected = "manueloliveira@beaver.com";
        Password passWordMock = mock(Password.class);
        when(passWordMock.encryptPassword("Qwerty_1")).thenReturn("´ÚÈÕ×ÜÂ\u0094");
        String passwordExpected = passWordMock.encryptPassword("Qwerty_1");
        String functionExpected = "tester";
        String photoExpected = "photo";
        List<UserProfile> assignedProfileExpected = new ArrayList<>();
        assignedProfileExpected.add(profile);
        //Act
        String userNameResult = newUser.getUserName().getNameF();
        String emailResult = newUser.getEmail().getEmail();
        String passwordResult = newUser.getPassword().getPwd();
        String functionResult = newUser.getFunction().getText();
        String photoResult = newUser.getPhoto();
        boolean activateUserResult = newUser.isActivateUser();
        List<UserProfile> assignedProfileResult = newUser.getAssignedProfileList();
        //Assert
        assertEquals(userNameExpected, userNameResult);
        assertEquals(emailExpected, emailResult);
        assertEquals(passwordExpected, passwordResult);
        assertEquals(functionExpected, functionResult);
        assertEquals(photoExpected, photoResult);
        assertFalse(activateUserResult);
        assertEquals(assignedProfileExpected, assignedProfileResult);
    }

    @Test
    public void saveSystemUserSuccess() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "tester";
        String photo = "photo";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
        int initialSize = company.getSystemUserStore().getSystemUsers().size();

        company.getSystemUserStore().saveSystemUser(newUser);
        int expected = initialSize + 1;
        //Act
        int result = company.getSystemUserStore().getSystemUsers().size();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void saveSystemUserFailEmailAlreadyExists() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "tester";
        String photo = "photo";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
        company.getSystemUserStore().saveSystemUser(newUser);

        String userName2 = "maneloliveira";
        SystemUser newUser2 = company.getSystemUserStore().createSystemUser(userName2, email, function, password, passwordConfirmation, photo, profile);
        //Assert
        assertFalse(company.getSystemUserStore().saveSystemUser(newUser2));
    }

    @Test
    public void saveSystemUserFailUserNameEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String userName = "";
            String email = "manueloliveira@beaver.com";
            String password = "Qwerty_1";
            String passwordConfirmation = "Qwerty_1";
            String function = "tester";
            String photo = "photo";
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
        });
    }

    @Test
    public void getSystemUserListEncapsulationSuccess() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "tester";
        String photo = "photo";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo, profile);

        String email2 = "maneloliveira@beaver.com";
        SystemUser newUser2 = company.getSystemUserStore().createSystemUser(userName, email2, function, password, passwordConfirmation, photo, profile);
        SystemUserStore systemUserStore = new SystemUserStore();

        systemUserStore.saveSystemUser(newUser);
        systemUserStore.saveSystemUser(newUser2);
        List<SystemUser> list = systemUserStore.getSystemUsers();
        list.remove(0);
        //Assert
        assertEquals(2, systemUserStore.getSystemUsers().size());
    }

    @Test
    public void reachUserByEmail() {
        Company company = new Company();
        String userName = "anaguedes";
        String email = "anaguedes@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "PO";
        String photo = "photo.png";
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser findUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
        company.getSystemUserStore().saveSystemUser(findUser);
        SystemUser user_verification = company.getSystemUserStore().getUserByEmail("anaguedes@beaver.com");
        assertEquals(user_verification, findUser);
    }

    @Test
    public void IsYourEmailSuccess() {
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser systemUser = company.getSystemUserStore().createSystemUser("anaguedes", "anaguedes@beaver.com",
                "PO", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        //Assert
        assertTrue(systemUser.isYourEmail("anaguedes@beaver.com"));
    }

    @Test
    public void IsYourEmailFail() {
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser systemUser = company.getSystemUserStore().createSystemUser("anaguedes", "anaguedes@beaver.com",
                "PO", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        //Assert
        assertFalse(systemUser.isYourEmail("guedesana@beaver.com"));
    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "tester";
        String photo = "photo";
        String email2 = "maneloliveira@beaver.com";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //newUser/list1 and newUser2/list2 are equals
        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo, profile);

        SystemUserStore list1 = new SystemUserStore();
        list1.saveSystemUser(newUser);

        SystemUser newUser2 = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
        SystemUserStore list2 = new SystemUserStore();
        list2.saveSystemUser(newUser2);
        //newUser3/list3 is different (different email)
        SystemUser newUser3 = company.getSystemUserStore().createSystemUser(userName, email2, function, password, passwordConfirmation, photo, profile);
        SystemUserStore list3 = new SystemUserStore();
        list3.saveSystemUser(newUser3);
        //Assert
        assertNotSame(list1, list2);
        assertEquals(list1.getSystemUserList(), list2.getSystemUserList());
        assertEquals(newUser, newUser2);
        assertEquals(list1.getSystemUserList().hashCode(), list2.getSystemUserList().hashCode());
        assertNotEquals(list1, list3);
        assertNotEquals(list1.hashCode(), list3.hashCode());
    }

    @Test
    public void validateSystemUser() {
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser systemUser = company.getSystemUserStore().createSystemUser("anaguedes", "anaguedes@beaver.com",
                "POAAAA", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        boolean expected = company.getSystemUserStore().validateSystemUser(systemUser);
        company.getSystemUserStore().saveSystemUser(systemUser);
        //Assert
        assertTrue(expected);
    }

    @Test
    public void validateSystemUser2() {
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser systemUser = company.getSystemUserStore().createSystemUser("anaguedes", "anaguedes@beaver.com",
                "PO", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        SystemUser systemUser2 = company.getSystemUserStore().createSystemUser("anaguedes", "anaguedes@beaver.com",
                "PO", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        SystemUserStore list1 = new SystemUserStore();
        list1.saveSystemUser(systemUser);
        list1.saveSystemUser(systemUser2);

        boolean expected = list1.validateSystemUser(systemUser);
        //Assert
        assertFalse(expected);
    }

    @Test
    public void validateSystemUser3() {
        Company company = new Company();
        SystemUser systemUser = null;
        boolean expected = company.getSystemUserStore().validateSystemUser(systemUser);
        company.getSystemUserStore().saveSystemUser(systemUser);
        //Assert
        assertFalse(expected);
    }

    @Test
    public void validateSystemUser4() {
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser systemUser = company.getSystemUserStore().createSystemUser("anaguedes", "anaguedes@beaver.com",
                "POAAAA", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        boolean expected = company.getSystemUserStore().validateSystemUser(systemUser);
        company.getSystemUserStore().saveSystemUser(systemUser);
        //Assert
        assertTrue(expected);
    }
}
