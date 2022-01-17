package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.stores.SystemUserStore;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SystemUserStoreTest {
    @Test
    public void createSystemUserSuccess() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String passwordConfirmation = "ghi";
        String function = "tester";
        String photo = "photo";
        Company company = new Company();
        List<UserProfile> assignedProfileExpected = new ArrayList<>();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        assignedProfileExpected.add(profile);
        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo, profile);

        String userNameExpected = "manueloliveira";
        String emailExpected = "manueloliveira@beaver.com";
        String passwordExpected = "ÊËÌ";
        String functionExpected = "tester";
        String photoExpected = "photo";

        String userNameResult = newUser.getUserName();
        String emailResult = newUser.getEmail();
        String passwordResult = newUser.getPassword();
        String functionResult = newUser.getFunction();
        String photoResult = newUser.getPhoto();
        boolean activateUserResult = newUser.getActivateUserStatus();
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
        String password = "ghi";
        String passwordConfirmation = "ghi";
        String function = "tester";
        String photo = "photo";
        Company company = new Company();
        List<UserProfile> assignedProfileExpected = new ArrayList<>();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        assignedProfileExpected.add(profile);
        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo, profile);
        int initialSize = company.getSystemUserStore().getSystemUserList().size();

        company.getSystemUserStore().saveSystemUser(newUser);
        int expected = initialSize + 1;
        //Act
        int result = company.getSystemUserStore().getSystemUserList().size();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void saveSystemUserFailEmailAlreadyExists() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String passwordConfirmation = "ghi";
        String function = "tester";
        String photo = "photo";
        Company company = new Company();
        List<UserProfile> assignedProfileExpected = new ArrayList<>();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        assignedProfileExpected.add(profile);
        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo,profile);
        company.getSystemUserStore().saveSystemUser(newUser);

        String userName2 = "maneloliveira";
        SystemUser newUser2 = company.getSystemUserStore().createSystemUser(userName2, email, function, password, passwordConfirmation, photo,profile);
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
            String password = "ghi";
            String passwordConfirmation = "ghi";
            String function = "tester";
            String photo = "photo";
            Company company = new Company();
            List<UserProfile> assignedProfileExpected = new ArrayList<>();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            assignedProfileExpected.add(profile);
            company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo,profile);
        });
    }

    @Test
    public void getSystemUserListEncapsulationSuccess() {
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String passwordConfirmation = "ghi";
        String function = "tester";
        String photo = "photo";
        Company company = new Company();
        List<UserProfile> assignedProfileExpected = new ArrayList<>();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        assignedProfileExpected.add(profile);
        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo,profile);

        String email2 = "maneloliveira@beaver.com";
        SystemUser newUser2 = company.getSystemUserStore().createSystemUser(userName, email2, function, password, passwordConfirmation, photo,profile);
        SystemUserStore systemUserStore = new SystemUserStore();

        systemUserStore.saveSystemUser(newUser);
        systemUserStore.saveSystemUser(newUser2);
        List<SystemUser> list = systemUserStore.getSystemUserList();
        list.remove(0);

        assertEquals(2, systemUserStore.getSystemUserList().size());
    }

    @Test
    public void reachUserbyEmailSuccess () {
        Company company = new Company();
        String userName = "anaguedes";
        String email = "anaguedes@beaver.com";
        String password = "hello123";
        String passwordConfirmation = "hello123";
        String function = "PO";
        String photo = "photo.png";
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser findUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo,profile);
        company.getSystemUserStore().addSystemUser(findUser);// faltou adicionar à company
        SystemUser user_verification = company.getSystemUserStore().getUserByEmail("anaguedes@beaver.com"); // email do user que criei
        assertEquals(user_verification, findUser);
    }


    @Test
    public void IsYourEmailSuccess() {
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser systemUser = company.getSystemUserStore().createSystemUser("anaguedes", "anaguedes@beaver.com",
                "PO", "hello123", "hello123", "photo.png",profile);
        //Assert
        assertTrue(systemUser.isYourEmail("anaguedes@beaver.com"));
    }


}
