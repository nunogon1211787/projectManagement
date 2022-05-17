package switch2021.project.stores;

public class SystemUserStoreTest {
/*    @Test
    public void saveSystemUserSuccess() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "tester";
        String photo = "photo.png";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile.getUserProfileId());
        int initialSize = company.getSystemUserStore().findAllSystemUsers().size();

        company.getSystemUserStore().saveSystemUser(newUser);
        int expected = initialSize + 1;
        //Act
        int result = company.getSystemUserStore().findAllSystemUsers().size();
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
        String photo = "photo.png";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile.getUserProfileId());
        company.getSystemUserStore().saveSystemUser(newUser);

        String userName2 = "maneloliveira";
        SystemUser newUser2 = new SystemUser(userName2, email, function, password, passwordConfirmation, photo, profile.getUserProfileId());
        //Assert
        assertFalse(company.getSystemUserStore().saveSystemUser(newUser2));
    }

    @Test
    public void getSystemUserListEncapsulationSuccess() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "tester";
        String photo = "photo.png";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile.getUserProfileId());

        String email2 = "maneloliveira@beaver.com";
        SystemUser newUser2 = new SystemUser(userName, email2, function, password, passwordConfirmation, photo, profile.getUserProfileId());
        SystemUserRepository systemUserStore = new SystemUserRepository();

        systemUserStore.saveSystemUser(newUser);
        systemUserStore.saveSystemUser(newUser2);
        List<SystemUser> list = systemUserStore.findAllSystemUsers();
        list.remove(0);
        //Assert
        assertEquals(2, systemUserStore.findAllSystemUsers().size());
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
        SystemUser findUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile.getUserProfileId());
        company.getSystemUserStore().saveSystemUser(findUser);
        SystemUser user_verification = company.getSystemUserStore().findSystemUserByEmail("anaguedes@beaver.com");
        assertEquals(user_verification, findUser);
    }

    @Test
    public void IsYourEmailSuccess() {
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser systemUser = new SystemUser("anaguedes", "anaguedes@beaver.com",
                "PO", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        //Assert
        assertTrue(systemUser.isYourEmail("anaguedes@beaver.com"));
    }

    @Test
    public void IsYourEmailFail() {
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser systemUser = new SystemUser("anaguedes","anaguedes@beaver.com",
                "PO", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
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
        String photo = "photo.png";
        String email2 = "maneloliveira@beaver.com";
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //newUser/list1 and newUser2/list2 are equals
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile.getUserProfileId());
        SystemUserRepository list1 = new SystemUserRepository();
        list1.saveSystemUser(newUser);

        SystemUser newUser2 = new SystemUser(userName, email, function, password, passwordConfirmation, photo, profile.getUserProfileId());
        SystemUserRepository list2 = new SystemUserRepository();
        list2.saveSystemUser(newUser2);
        list1.saveSystemUser(newUser2);
        //newUser3/list3 is different (different email)
        SystemUser newUser3 = new SystemUser(userName, email2, function, password, passwordConfirmation, photo, profile.getUserProfileId());
        SystemUserRepository list3 = new SystemUserRepository();
        list3.saveSystemUser(newUser3);
        //Assert
        assertNotSame(list1, list2);
        assertEquals(list2.getSystemUserList().size(), list3.getSystemUserList().size());
        assertEquals(newUser, newUser2);
        assertEquals(list1.getSystemUserList().hashCode(), list1.getSystemUserList().hashCode());
        assertNotEquals(list1, list3);
        assertNotEquals(list1.hashCode(), list3.hashCode());
    }*/
}
