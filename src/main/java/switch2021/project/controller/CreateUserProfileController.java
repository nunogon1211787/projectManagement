package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import switch2021.project.dto.UserProfileDTO;
import switch2021.project.service.CreateUserProfileService;


@RestController
@RequestMapping("/profiles")
public class CreateUserProfileController {
    /**
     * Attributes
     **/
    @Autowired
    private CreateUserProfileService createUserProfileService;

    /**
     * Constructor
     **/
    public CreateUserProfileController() {

    }

    /**
     * Create a User Profile
     *
     * @param dto
     */
    @PostMapping("")
    public ResponseEntity<Object> createUserProfile(@RequestBody UserProfileDTO dto) {

        UserProfileDTO newUserProfile = createUserProfileService.createAndSaveUserProfile(dto);

        return new ResponseEntity<>(newUserProfile, HttpStatus.CREATED);
    }
}


