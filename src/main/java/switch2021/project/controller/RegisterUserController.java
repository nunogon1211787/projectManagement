package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.*;
import switch2021.project.service.RegisterUserService;
import switch2021.project.service.ShowAllCurrentProjectsByUserService;


import java.util.List;

@RestController
@RequestMapping("/users")
public class RegisterUserController {

    @Autowired
    private RegisterUserService registerUserService;
    @Autowired
    private ShowAllCurrentProjectsByUserService showAllCurrentProjectsByUserService;

    public RegisterUserController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    @GetMapping("/{id}/resources")
    public ResponseEntity<Object> showCurrentProjectsByUser(@PathVariable IdDTO id, @RequestParam("date") DateDTO dateDto){

        List<OutputProjectDTO> projectsDto = showAllCurrentProjectsByUserService.showCurrentProjectsByUser(id, dateDto);

        return new ResponseEntity<>(projectsDto, HttpStatus.OK);

    }



    @PostMapping
    public ResponseEntity<Object> registerUser(@RequestBody NewUserInfoDTO infoDTO) {

        OutputUserDTO outDTO = registerUserService.createAndSaveSystemUser(infoDTO);

        return new ResponseEntity<>(outDTO, HttpStatus.CREATED);
    }
}
