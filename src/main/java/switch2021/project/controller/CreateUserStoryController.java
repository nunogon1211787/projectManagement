package switch2021.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.service.CreateUserStoryService;

@RestController
@RequestMapping("/userStories")
public class CreateUserStoryController {

    /**
     * Attributes
     **/
    @Autowired
    private CreateUserStoryService createUserStoryService;


    /**
     * Create a User Story
     */
    @PostMapping("")
    public ResponseEntity<Object> createUserStory(@RequestBody UserStoryDTO userStoryDTO) {

        UserStoryDTO newUserStory = createUserStoryService.createAndSaveUserStory(userStoryDTO);

        return new ResponseEntity<>(newUserStory, HttpStatus.CREATED);
    }
}
