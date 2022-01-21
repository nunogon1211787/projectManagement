package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchUsersControllerTest {

    @Test
    void searchUsersSuccess() {
        //Input
        SearchSystemUsersController test = new SearchSystemUsersController();
        Company co = test.getCompany();
        co.getUserProfileStore().populateDefault();
        UserProfile p1 = co.getUserProfileStore().getUserProfile("visitor");
        UserProfile p2 = co.getUserProfileStore().getUserProfile("director");
        UserProfile p3 = co.getUserProfileStore().getUserProfile("administrator");
        SystemUser usr1 = new SystemUser("Romulo", "rom@rom.pt", "any", "qwerty", "qwerty", "", p1);
        SystemUser usr2 = new SystemUser("Maria", "ma@ma.pt", "any", "qwerty", "qwerty", "", p2);
        SystemUser usr3 = new SystemUser("Joao", "jo@jo.pt", "any", "qwerty", "qwerty", "", p3);
        co.getSystemUserStore().addSystemUser(usr1);
        co.getSystemUserStore().addSystemUser(usr2);
        co.getSystemUserStore().addSystemUser(usr3);
        //Expected
        String[] list = {};
        List<SystemUser> resultList = test.searchUsers("a", "", "", -1, list);
        List<SystemUser> expectedList = Arrays.asList(usr2, usr3);
        //Result
        assertEquals(expectedList, resultList);
    }

    @Test
    void getProfileListSuccess(){
        //Input
        SearchSystemUsersController test = new SearchSystemUsersController();
        Company co = test.getCompany();
        List<UserProfile> profileList = test.getUserProfileList();
        //Expected
        List<UserProfile> expectedList = co.getUserProfileStore().getUserProfileList();
        UserProfile p1 = co.getUserProfileStore().getUserProfile("visitor");
        UserProfile p2 = co.getUserProfileStore().getUserProfile("administrator");
        UserProfile p3 = co.getUserProfileStore().getUserProfile("director");
        UserProfile p4 = co.getUserProfileStore().getUserProfile("user");
        List<UserProfile> nominalList = new ArrayList<>();
        nominalList.add(p1);
        nominalList.add(p2);
        nominalList.add(p3);
        nominalList.add(p4);
        //Result
        assertEquals(expectedList, profileList);
        assertEquals(nominalList, profileList);
    }
}