package switch2021.project.interfaceAdapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dtoModel.dto.ErrorMessage;
import switch2021.project.dtoModel.dto.ResponseMessage;
import switch2021.project.dtoModel.dto.UserProfileDTO;
import switch2021.project.applicationServices.service.UserProfileService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/profiles")
public class UserProfileController {

    /**
     * Attributes
     **/
    @Autowired
    private UserProfileService createUserProfileService;


    /**
     * Create a User Profile (US013)
     */
    @PostMapping
    public ResponseEntity<Object> createUserProfile(@RequestBody UserProfileDTO dto) {
        UserProfileDTO outPutDTO;

        if(dto.description == null || dto.description.isEmpty()){
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = "User Profile Name cannot be empty.";
            return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            outPutDTO = createUserProfileService.createAndSaveUserProfile(dto);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outPutDTO, HttpStatus.CREATED);
    }


    /**
     * Find all profiles
     */
    @GetMapping
    public ResponseEntity<Object> showAllProfiles(){
        ErrorMessage message = new ErrorMessage();
        CollectionModel<UserProfileDTO> outPutDTO;

        try {
            outPutDTO = createUserProfileService.showAllProfiles();
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outPutDTO, HttpStatus.OK);
    }


    /**
     * Find a requested user profile
     */
     @GetMapping("/{id}")
     public ResponseEntity<Object> showUserProfileRequested(@PathVariable("id") String id){
         UserProfileDTO outPutDTO;

         try {
             outPutDTO = createUserProfileService.showUserProfileRequested(id);
         } catch (Exception exception) {
             ErrorMessage message = new ErrorMessage();
             message.errorMessage = exception.getMessage();
             return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
         }
         return new ResponseEntity<>(outPutDTO, HttpStatus.OK);
     }


    /**
     * Edit a requested profile
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> editAUserProfile(@PathVariable("id") String id, @RequestBody UserProfileDTO dto){
        UserProfileDTO outPutDTO;

        try {
            outPutDTO = createUserProfileService.editARequestedUserProfile(id, dto);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outPutDTO, HttpStatus.ACCEPTED);
    }


    /**
     * Delete a requested profile
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAUserProfile(@PathVariable("id") String id){
        ResponseMessage response = new ResponseMessage();

        try {
            createUserProfileService.deleteARequestedUserProfile(id);
            response.responseMessage = "User profile successfully deleted";
            response.add(linkTo(methodOn(UserProfileController.class).showAllProfiles()).withRel("Collection"));

        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}


