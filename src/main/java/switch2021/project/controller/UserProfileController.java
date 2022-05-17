package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import switch2021.project.dto.ErrorMessage;
import switch2021.project.dto.UserProfileDTO;
import switch2021.project.service.CreateUserProfileService;


@RestController
@RequestMapping("/profiles")
public class UserProfileController {

    /**
     * Attributes
     **/
    @Autowired
    private CreateUserProfileService createUserProfileService;


    /**
     * Create a User Profile
     */
    @PostMapping
    public ResponseEntity<Object> createUserProfile(@RequestBody UserProfileDTO dto) {
        ErrorMessage message = new ErrorMessage();
        if(dto.userProfileName == null || dto.userProfileName.isEmpty()){
            message.errorMessage = "User Profile Name cannot be empty.";
            return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
        }
        UserProfileDTO outPutDTO;
        try {
            outPutDTO = createUserProfileService.createAndSaveUserProfile(dto);
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outPutDTO, HttpStatus.CREATED);
    }
}


