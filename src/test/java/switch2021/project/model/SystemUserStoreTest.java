package switch2021.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SystemUserStoreTest {
    @Test
    public void createSystemUserWithPhotoSuccess() {
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
        //List<UserProfile> assignedProfileExpected = new ArrayList<>();
        //assignedProfileExpected.add(pro);
        //Act
        String userNameResult = newUser.getUserName();
        String emailResult = newUser.getEmail();
        String passwordResult = newUser.getPassword();
        String functionResult = newUser.getFunction();
        String photoResult = newUser.getPhoto();
        boolean activateUserResult = newUser.getUserActivated();
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
    public void saveSystemUserWithPhotoSuccess() {
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
    public void saveSystemUserWithPhotoFailEmailAlreadyExists() {
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
}
