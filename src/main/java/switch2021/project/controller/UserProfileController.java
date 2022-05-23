package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.ErrorMessage;
import switch2021.project.dto.UserProfileDTO;
import switch2021.project.service.UserProfileService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/profiles")
public class UserProfileController {

    /**
     * Attributes
     **/
    @Autowired
    private UserProfileService createUserProfileService;


    /**
     * Find all profiles
     */
    @GetMapping
    public ResponseEntity<Object> showAllProfiles(){
        ErrorMessage message = new ErrorMessage();
        List<UserProfileDTO> outPutDTO;

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
         ErrorMessage message = new ErrorMessage();
         UserProfileDTO outPutDTO;

         try {

             outPutDTO = createUserProfileService.showUserProfileRequested(id);

             outPutDTO.add(linkTo(methodOn(UserProfileController.class).showUserProfileRequested(id)).withSelfRel());
             outPutDTO.add(linkTo(methodOn(UserProfileController.class).deleteAUserProfile(id)).withRel("Delete"));
             outPutDTO.add(linkTo(methodOn(UserProfileController.class).editAUserProfile(id, outPutDTO)).withRel("Edit"));
             outPutDTO.add(linkTo(methodOn(UserProfileController.class).showAllProfiles()).withRel("Collection"));

         } catch (Exception exception) {
             message.errorMessage = exception.getMessage();
             return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
         }
         return new ResponseEntity<>(outPutDTO, HttpStatus.OK);
     }



    /**
     * Create a User Profile
     */
    @PostMapping
    public ResponseEntity<Object> createUserProfile(@RequestBody UserProfileDTO dto) {
        ErrorMessage message = new ErrorMessage();
        if(dto.description == null || dto.description.isEmpty()){
            message.errorMessage = "User Profile Name cannot be empty.";
            return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
        }
        UserProfileDTO outPutDTO;
        try {

            outPutDTO = createUserProfileService.createAndSaveUserProfile(dto);

            outPutDTO.add(linkTo(methodOn(UserProfileController.class).showUserProfileRequested(outPutDTO.description)).withSelfRel());
            outPutDTO.add(linkTo(methodOn(UserProfileController.class).deleteAUserProfile(outPutDTO.description)).withRel("Delete"));
            outPutDTO.add(linkTo(methodOn(UserProfileController.class).editAUserProfile(outPutDTO.description, outPutDTO)).withRel("Edit"));
            outPutDTO.add(linkTo(methodOn(UserProfileController.class).showAllProfiles()).withRel("Collection"));

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outPutDTO, HttpStatus.CREATED);
    }

    /**
     * Edit a requested profile
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> editAUserProfile(@PathVariable("id") String id, @RequestBody UserProfileDTO dto){
        ErrorMessage message = new ErrorMessage();
        UserProfileDTO outPutDTO;

        try {

            outPutDTO = createUserProfileService.editARequestedUserProfile(id, dto);

            outPutDTO.add(linkTo(methodOn(UserProfileController.class).showUserProfileRequested(outPutDTO.description)).withSelfRel());
            outPutDTO.add(linkTo(methodOn(UserProfileController.class).deleteAUserProfile(outPutDTO.description)).withRel("Delete"));
            outPutDTO.add(linkTo(methodOn(UserProfileController.class).editAUserProfile(outPutDTO.description, outPutDTO)).withRel("Edit"));
            outPutDTO.add(linkTo(methodOn(UserProfileController.class).showAllProfiles()).withRel("Collection"));

        } catch (Exception exception) {
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
        ErrorMessage message = new ErrorMessage();

        try {

            createUserProfileService.deleteARequestedUserProfile(id);
            message.errorMessage = "User profile successfully deleted";

            message.add(linkTo(methodOn(UserProfileController.class).showAllProfiles()).withRel("Collection"));

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

}


