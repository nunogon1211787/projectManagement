package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.*;
import switch2021.project.service.RegisterUserService;
import switch2021.project.service.SearchUsersByParamsService;
import switch2021.project.service.ShowAllCurrentProjectsByUserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RegisterUserService registerUserService;
    @Autowired
    private ShowAllCurrentProjectsByUserService showAllCurrentProjectsByUserService;
    @Autowired
    private SearchUsersByParamsService searchUsersByParamsService;

    @GetMapping
    public ResponseEntity<Object> searchUsersByTypedParams(@RequestParam SearchUserDTO inDto){

        try {

            List<OutputUserDTO> usersFoundedDto = searchUsersByParamsService.searchUsersByParams(inDto);

            return new ResponseEntity<>(usersFoundedDto, HttpStatus.OK);

        } catch (Exception e){
            ErrorMessage error = new ErrorMessage();
            error.errorMessage = e.getMessage();

            return new ResponseEntity<>(error, HttpStatus.OK);
        }
    }


    @GetMapping("/{id}/resources")
    public ResponseEntity<Object> showCurrentProjectsByUser(@PathVariable IdDTO id, @RequestParam("date") DateDTO dateDto){

        List<OutputProjectDTO> projectsDto = showAllCurrentProjectsByUserService.showCurrentProjectsByUser(id, dateDto);

        return new ResponseEntity<>(projectsDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registerUser(@RequestBody NewUserInfoDTO infoDTO) {
        OutputUserDTO outDTO;
        try {
            outDTO = registerUserService.createAndSaveUser(infoDTO);
            return new ResponseEntity<>(outDTO, HttpStatus.CREATED);
        }
        catch (Exception e){
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
