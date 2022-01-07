package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.Profile;
import switch2021.project.model.SystemUser;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US004ControllerTest {

    @Test
    void searchUsers() {
        //Input
        US004Controller test = new US004Controller();
        Company co = test.getCompany();
        //Input
        Profile p1 = new Profile("111", "222");
        Profile p2 = new Profile("333", "444");
        Profile p3 = new Profile("555", "666");
        Profile p4 = new Profile("777", "888");
        Profile p5 = new Profile("999", "000");
        SystemUser usr1 = new SystemUser("aaai", "brbb", "ckcc", "lddd", p1);
        SystemUser usr2 = new SystemUser("qeee", "ffnf", "gggo", "phhh", p2);
        SystemUser usr3 = new SystemUser("iiii", "jjjb", "kksk", "ltll", p3);
        SystemUser usr4 = new SystemUser("mmam", "fnnn", "oxoo", "ppdp", p4);
        SystemUser usr5 = new SystemUser("qmqe", "rjrr", "gsss", "ttth", p5);
        co.addSystemUser(usr1);
        co.addSystemUser(usr2);
        co.addSystemUser(usr3);
        co.addSystemUser(usr4);
        co.addSystemUser(usr5);
        //Expected
        int[] list = {};
        List<SystemUser> resultList = test.searchUsers("a", "", "", -1, list);
        List<SystemUser> expectedList = Arrays.asList(usr1, usr4);
        //Result
        assertEquals(expectedList, resultList);
    }
}