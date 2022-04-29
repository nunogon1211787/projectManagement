package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.service.RegisterUserService;

@RestController
@RequestMapping("/users")
public class RegisterUserController {

    @Autowired
    private RegisterUserService registerUserService;

    public RegisterUserController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    @PostMapping
    public ResponseEntity<Object> registerUser(@RequestBody NewUserInfoDTO infoDTO) {

        OutputUserDTO outDTO = registerUserService.createAndSaveSystemUser(infoDTO);

        return new ResponseEntity<>(outDTO, HttpStatus.CREATED);
    }
}
