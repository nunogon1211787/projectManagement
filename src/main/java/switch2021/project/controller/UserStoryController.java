package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.*;
import switch2021.project.service.UserStoryService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/userstories")
public class UserStoryController {

    /**
     * Attributes
     **/
    @Autowired
    private UserStoryService createUserStoryService;


    /**
     * Find all user stories
     */
    @GetMapping
    public ResponseEntity<Object> showAllUserStories(){
        ErrorMessage message = new ErrorMessage();
        List<OutputUserStoryDTO> newUserStory;

        try {

            newUserStory = createUserStoryService.showAllUserStories();

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newUserStory, HttpStatus.OK);
    }


    /**
     * Find a requested user story
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> showUserStoryRequested(@PathVariable("id") String id){
        ErrorMessage message = new ErrorMessage();
        OutputUserStoryDTO newUserStory;

        try {
            newUserStory = createUserStoryService.showAUserStory(id);

            newUserStory.add(linkTo(methodOn(UserStoryController.class).showUserStoryRequested(newUserStory.id)).withSelfRel());
            newUserStory.add(linkTo(methodOn(UserStoryController.class).deleteAUserStory(newUserStory.id)).withRel("Delete"));
            newUserStory.add(linkTo(methodOn(UserStoryController.class).showAllUserStories()).withRel("Collection"));

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newUserStory, HttpStatus.OK);
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

            newUserStory.add(linkTo(methodOn(UserStoryController.class).showUserStoryRequested(newUserStory.id)).withSelfRel());
            newUserStory.add(linkTo(methodOn(UserStoryController.class).deleteAUserStory(newUserStory.id)).withRel("Delete"));
            newUserStory.add(linkTo(methodOn(UserStoryController.class).showAllUserStories()).withRel("Collection"));

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newUserStory, HttpStatus.CREATED);
    }

    /**
     * Delete a requested User Story
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAUserStory(@PathVariable String id){
        ErrorMessage message = new ErrorMessage();
        OutputUserStoryDTO newUserStory;
        try {
            createUserStoryService.deleteAUserStory(id);
            message.errorMessage = "User Story was deleted successfully";

            message.add(linkTo(methodOn(UserStoryController.class).showAllUserStories()).withRel("Collection"));

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
