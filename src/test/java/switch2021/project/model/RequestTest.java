package switch2021.project.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestTest {

    @Test
    public void createRequestTest () {

        //arrange

        Company companyTest = new Company();
        SystemUser systemUserTest = new SystemUser("Roger","test@test.pt","devop","pass",companyTest.arrayProfile.get(0));
        Request requestTest = new Request(LocalDate.of(2022,1,9),LocalDate.now(),companyTest.arrayProfile.get(0),systemUserTest);

    //act

        LocalDate testDate = requestTest.getRequestDate();
        LocalDate testTime = requestTest.getRequestTime();

     //compare

        assertEquals(requestTest.getRequestDate(),testDate);
        assertEquals(requestTest.getRequestTime(),testTime);
    }

    @Test
    public void createRequesEqualProfile (){

     //arrange

        Company companyTest = new Company();
        SystemUser systemUserTest = new SystemUser("Roger","test@test.pt","devop","pass",companyTest.arrayProfile.get(1));
        Request requestTest = new Request(LocalDate.of(2022,1,9),LocalDate.now(),companyTest.arrayProfile.get(7),systemUserTest);

    //act

        Profile profileTest = requestTest.getProfile();

    //compare

        assertEquals(requestTest.getProfile(),profileTest);

    }





}
