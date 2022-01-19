package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.UserProfile;
import switch2021.project.model.SystemUser;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchUsersControllerTest {


    ////      REVER Metodo



    /*@Test
    void searchUsers() {
        //Input
        US004Controller test = new US004Controller();
        Company co = test.getCompany();
        //Input
        UserProfile p1 = new UserProfile("111", "222");
        UserProfile p2 = new UserProfile("333", "444");
        UserProfile p3 = new UserProfile("555", "666");
        UserProfile p4 = new UserProfile("777", "888");
        UserProfile p5 = new UserProfile("999", "000");
        SystemUser usr1 = new SystemUser("aaai", "brbb", "ckcc", "lddd", "lddd", "", p1);
        SystemUser usr2 = new SystemUser("qeee", "ffnf", "gggo", "phhh","phhh","", p2);
        SystemUser usr3 = new SystemUser("iiii", "jjjb", "kksk", "ltll","ltll","", p3);
        SystemUser usr4 = new SystemUser("mmam", "fnnn", "oxoo", "ppdp","ppdp", "", p4);
        SystemUser usr5 = new SystemUser("qmqe", "rjrr", "gsss", "ttth","ttth","", p5);
        co.getSystemUserStore().addSystemUser(usr1);
        co.getSystemUserStore().addSystemUser(usr2);
        co.getSystemUserStore().addSystemUser(usr3);
        co.getSystemUserStore().addSystemUser(usr4);
        co.getSystemUserStore().addSystemUser(usr5);
        //Expected
        int[] list = {};
        List<SystemUser> resultList = test.searchUsers("a", "", "", -1, list);
        List<SystemUser> expectedList = Arrays.asList(usr1, usr4);
        //Result
        assertEquals(expectedList, resultList);
    }*/
}