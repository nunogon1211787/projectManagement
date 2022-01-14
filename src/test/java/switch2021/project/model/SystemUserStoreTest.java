package switch2021.project.model;

import org.junit.jupiter.api.Test;

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
        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo);

        String userNameExpected = "manueloliveira";
        String emailExpected = "manueloliveira@beaver.com";
        String passwordExpected = "ÊËÌ";
        String functionExpected = "tester";
        String photoExpected = "photo";
        UserProfile assignedProfileExpected = new UserProfile("Visitor");
        //Act
        String userNameResult = newUser.getUserName();
        String emailResult = newUser.getEmail();
        String passwordResult = newUser.getPassword();
        String functionResult = newUser.getFunction();
        String photoResult = newUser.getPhoto();
        boolean activateUserResult = newUser.getActivateUserStatus();
        UserProfile assignedProfileResult = company.getUserProfileStore().getProfileByName("Visitor");
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
        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo);
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
        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo);
        company.getSystemUserStore().saveSystemUser(newUser);

        String userName2 = "maneloliveira";
        SystemUser newUser2 = company.getSystemUserStore().createSystemUser(userName2, email, function, password, passwordConfirmation, photo);
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
            company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo);
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
        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password, passwordConfirmation, photo);

        String userName2 = "manueloliveira";
        String email2 = "maneloliveira@beaver.com";
        String password2 = "ghi";
        String passwordConfirmation2 = "ghi";
        String function2 = "tester";
        String photo2 = "photo";
        SystemUser newUser2 = company.getSystemUserStore().createSystemUser(userName2, email2, function2, password2, passwordConfirmation2, photo2);
        SystemUserStore systemUserStore = new SystemUserStore();

        systemUserStore.saveSystemUser(newUser);
        systemUserStore.saveSystemUser(newUser2);
        List<SystemUser> list = systemUserStore.getSystemUserList();
        list.remove(0);

        assertEquals(2, systemUserStore.getSystemUserList().size());
    }
}
