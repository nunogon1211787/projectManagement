package switch2021.project.interfaceAdapters.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.dtoModel.dto.LoginDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RootControllerTest {

    @Autowired
    RootController ctrl;

    @Test
    void test(){
        LoginDto dto = new LoginDto();

        dto.email = "jsz@mymail.com";
        dto.password = "Qwerty1!";

        ctrl.authentication(dto);
    }

}