package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.*;
import switch2021.project.repositories.jpa.UserJpaRepository;
import switch2021.project.datamodel.UserJpa;
import switch2021.project.service.CreateProjectService;
import switch2021.project.service.UserService;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     *Attributes
     */

    @Autowired
    private UserService userService;
    @Autowired
    private CreateProjectService projectService;
    @Autowired
    private UserJpaRepository sURepository;


    /**
     * Register an User (US001)
     */

    @PostMapping("/jpa")
    public ResponseEntity<Object> registerUser(@RequestBody NewUserInfoDTO newUserInfoDTO) {
        ErrorMessage message = new ErrorMessage();
        OutputUserDTO outputUserDTO;
        try {
            outputUserDTO = userService.createAndSaveUserJPA(newUserInfoDTO);

            outputUserDTO.add(linkTo(methodOn(UserController.class).getUser(outputUserDTO.email)).withSelfRel());

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputUserDTO, HttpStatus.CREATED);

    }

    /**
     * Get User, by ID
     */

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") String id){

        ErrorMessage message = new ErrorMessage();

        OutputUserDTO user;

        try {
            user = userService.findSystemUserByEmail(id);

            user.add(linkTo(methodOn(UserController.class).getUser(user.email)).withSelfRel());

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Update User Data (UserName, Function, Photo) (US010)
     * Change Password (US011)
     */


    @PatchMapping("/{id}")
    public ResponseEntity<Object> updatePersonalData(@PathVariable("id") IdDTO idDTO,
                                                     @RequestBody UpdateDataDTO updateDataDTO) {

        OutputUserDTO outputUserDTO;
        try {
            outputUserDTO = userService.updatePersonalData(idDTO, updateDataDTO);
            return new ResponseEntity<>(outputUserDTO, HttpStatus.OK);
        }
        catch (Exception error) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = error.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("users")
    public UserJpa addUser(@RequestBody UserJpa a) {
        return this.sURepository.save(a);
    }

    @GetMapping("users")
    public List<UserJpa> getUsers() {
        return this.sURepository.findAll();
    }


    //@GetMapping
    public ResponseEntity<Object> searchUsersByTypedParams(@RequestParam SearchUserDTO inDto){

        try {

            List<OutputUserDTO> usersFoundedDto = userService.searchUsersByParams(inDto);

            return new ResponseEntity<>(usersFoundedDto, HttpStatus.OK);

        } catch (Exception e){
            ErrorMessage error = new ErrorMessage();
            error.errorMessage = e.getMessage();

            return new ResponseEntity<>(error, HttpStatus.OK);
        }
    }


    @GetMapping("/{id}/resources")
    public ResponseEntity<Object> showCurrentProjectsByUser(@PathVariable IdDTO id,
                                                            @RequestParam("date") DateDTO dateDto) {

        List<OutputProjectDTO> projectsDto = projectService.showCurrentProjectsByUser(id, dateDto);

        return new ResponseEntity<>(projectsDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> showAllUsers() {
        List<OutputUserDTO> allUsersDto;

        try {
            allUsersDto = userService.findAllUsers();
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allUsersDto, HttpStatus.OK);
    }
//~
//
//    Método Nuno -> DUPLICADO!!!

//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getUserById(@PathVariable ("id") String idDTO) {
//
//        Optional<OutputUserDTO> opOutUser = userService.getUserById(idDTO);
//
//        if(opOutUser.isPresent()) {
//            OutputUserDTO outDTO = opOutUser.get();
//            return new ResponseEntity<>(outDTO, HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }
//
//
//  Método Antigo, sem "Postman" e HATEOAS
//    //@PostMapping
//    public ResponseEntity<Object> registerUser(@RequestBody NewUserInfoDTO infoDTO) {
//        OutputUserDTO outDTO;
//        try {
//            outDTO = userService.createAndSaveUser(infoDTO);
//            return new ResponseEntity<>(outDTO, HttpStatus.CREATED);
//        } catch (Exception e) {
//            ErrorMessage message = new ErrorMessage();
//            message.errorMessage = e.getMessage();
//            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//        }
//    }




}
