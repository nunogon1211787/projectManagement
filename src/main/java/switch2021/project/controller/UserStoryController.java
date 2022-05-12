package switch2021.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import switch2021.project.dto.ErrorMessage;
import switch2021.project.dto.OutPutUsDTO;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.service.CreateUserStoryService;

@Controller
@RestController
@RequestMapping("/userStories")
public class UserStoryController {

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
        ErrorMessage message = new ErrorMessage();
        if (userStoryDTO.projectID == null || userStoryDTO.title == null || userStoryDTO.projectID.isEmpty() ||
                userStoryDTO.title.isEmpty()) {
            message.errorMessage = "Fields title and project ID cannot be empty";
            return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
        }
        OutPutUsDTO newUserStory;
        try {
            newUserStory = createUserStoryService.createAndSaveUserStory(userStoryDTO);
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newUserStory, HttpStatus.CREATED);
    }
}
