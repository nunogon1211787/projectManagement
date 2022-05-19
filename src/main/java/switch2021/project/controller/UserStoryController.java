package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.ErrorMessage;
import switch2021.project.dto.OutputUserStoryDTO;
import switch2021.project.dto.CreateUserStoryDTO;
import switch2021.project.service.CreateUserStoryService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/userstories")
public class UserStoryController {

    /**
     * Attributes
     **/
    @Autowired
    private CreateUserStoryService createUserStoryService;


    @GetMapping("/{id}") //TODO must be implemented. Used to test HATEOAS.
    public ResponseEntity<Object> showUserStoryRequested(@PathVariable long id){
        ErrorMessage message = new ErrorMessage();

        message.errorMessage = "Must be implemented";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Create a User Story
     */
    @PostMapping
    public ResponseEntity<Object> createAndSaveUserStory(@RequestBody CreateUserStoryDTO inDto) {
        ErrorMessage message = new ErrorMessage();
        OutputUserStoryDTO newUserStory;
        try {
            newUserStory = createUserStoryService.createAndSaveUserStory(inDto);

            newUserStory.add(linkTo(methodOn(UserStoryController.class).showUserStoryRequested(1)).withSelfRel());

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newUserStory, HttpStatus.CREATED);
    }

    @PostMapping("/jpa")
    public ResponseEntity<Object> createAndSaveUserStoryJPA(@RequestBody CreateUserStoryDTO inDto) {
        ErrorMessage message = new ErrorMessage();
        OutputUserStoryDTO newUserStory;
        try {
            newUserStory = createUserStoryService.createAndSaveUserStoryJPA(inDto);

            newUserStory.add(linkTo(methodOn(UserStoryController.class).showUserStoryRequested(1)).withSelfRel());

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newUserStory, HttpStatus.CREATED);
    }
}
