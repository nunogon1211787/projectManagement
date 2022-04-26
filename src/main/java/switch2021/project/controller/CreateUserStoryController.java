package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import switch2021.project.dto.OutputUsDTO;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.model.UserStory.UserStoryService;

@Controller
@RestController
@RequestMapping()
public class CreateUserStoryController {

    /**
     * Attributes
     **/
    @Autowired
    private final UserStoryService userStoryService;


    /**
     * Constructor
     **/
    public CreateUserStoryController(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    /**
     * Create a User Story
     *
     * @param dto
     */
    @PostMapping("")
    public ResponseEntity<Object> createUserStory(@RequestBody UserStoryDTO dto) {

        OutputUsDTO newUserStory = userStoryService.createAndSaveUserStory(dto);

        return new ResponseEntity<>(newUserStory, HttpStatus.CREATED);
    }
}
