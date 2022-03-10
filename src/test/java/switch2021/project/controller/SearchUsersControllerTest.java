package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchUsersControllerTest {

    @Test
    void searchUsersSuccess() {
        //Input
        Company company = new Company();
        SearchSystemUsersController test = new SearchSystemUsersController(company);
       // company.getUserProfileStore().populateDefault();
        UserProfile p1 = company.getUserProfileStore().getUserProfile("visitor");
        //UserProfile p2 = company.getUserProfileStore().getUserProfile("director");
       // UserProfile p3 = company.getUserProfileStore().getUserProfile("administrator");
        SystemUser usr1 = new SystemUser("Romulo", "rom@rom.pt", "any", "qwerty", "qwerty", "", p1);
        SystemUser usr2 = new SystemUser("Maria", "ma@ma.pt", "any", "qwerty", "qwerty", "", p1);
        SystemUser usr3 = new SystemUser("Joao", "jo@jo.pt", "any", "qwerty", "qwerty", "", p1);
        company.getSystemUserStore().saveSystemUser(usr1);
        company.getSystemUserStore().saveSystemUser(usr2);
        company.getSystemUserStore().saveSystemUser(usr3);
        //Expected
        String[] list = {};
        List<SystemUser> resultList = test.searchUsers("a", "", "", -1, list);
        List<SystemUser> expectedList = Arrays.asList(usr2, usr3);
        //Result
        assertEquals(expectedList, resultList);
    }

    @Test
    void searchUsersSuccess2() {
        //Input
        Company company = new Company();
        SearchSystemUsersController test = new SearchSystemUsersController(company);
        // company.getUserProfileStore().populateDefault();
        UserProfile p1 = company.getUserProfileStore().getUserProfile("visitor");
        //UserProfile p2 = company.getUserProfileStore().getUserProfile("director");
        // UserProfile p3 = company.getUserProfileStore().getUserProfile("administrator");
        SystemUser usr1 = new SystemUser("Romulo", "rom@rom.pt", "any", "qwerty", "qwerty", "", p1);
        SystemUser usr2 = new SystemUser("Maria", "ma@ma.pt", "any", "qwerty", "qwerty", "", p1);
        SystemUser usr3 = new SystemUser("Joao", "jo@jo.pt", "any", "qwerty", "qwerty", "", p1);
        company.getSystemUserStore().saveSystemUser(usr1);
        company.getSystemUserStore().saveSystemUser(usr2);
        company.getSystemUserStore().saveSystemUser(usr3);
        //Expected
        String[] list = {};
        List<SystemUser> resultList = test.searchUsers("a", "", "", -1, list);
        List<SystemUser> expectedList = Arrays.asList(usr2);
        //Result
        assertNotEquals(expectedList, resultList);
    }

    @Test
    void getProfileListSuccess(){
        //Input
        Company co = new Company();
        SearchSystemUsersController test = new SearchSystemUsersController(co);
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

    @Test
    void getProfileListSuccessValidateInfo(){
        //Input
        Company co = new Company();
        SearchSystemUsersController test = new SearchSystemUsersController(co);
        //Expected
        UserProfile p1 = co.getUserProfileStore().getUserProfile("visitor");
        UserProfile p2 = co.getUserProfileStore().getUserProfile("administrator");
        UserProfile p3 = co.getUserProfileStore().getUserProfile("director");
        UserProfile p4 = co.getUserProfileStore().getUserProfile("user");
        List<UserProfile> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        //Result
        assertEquals(p1.getUserProfileName(), list.get(0).getUserProfileName());
        assertEquals(p2.getUserProfileName(), list.get(1).getUserProfileName());
        assertEquals(p3.getUserProfileName(), list.get(2).getUserProfileName());
        assertEquals(p4.getUserProfileName(), list.get(3).getUserProfileName());
        assertEquals(p4.getIdProfile(), list.get(3).getIdProfile());
    }
}