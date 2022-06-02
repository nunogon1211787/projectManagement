package switch2021.project.interfaceAdapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.applicationServices.service.UserStoryService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/userstories")
public class UserStoryController {

    /**
     * Attributes
     **/
    @Autowired
    private UserStoryService service;


    /**
     * Create a User Story (US009)
     */
    @PostMapping
    public ResponseEntity<Object> createAndSaveUserStory(@RequestBody CreateUserStoryDTO inDto) {
        ErrorMessage message = new ErrorMessage();
        OutputUserStoryDTO newUserStory;
        try {

            newUserStory = service.createAndSaveUserStory(inDto);

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newUserStory, HttpStatus.CREATED);
    }


    /**
     * Find all user stories
     */
    @GetMapping
    public ResponseEntity<Object> showAllUserStories(){
        CollectionModel<OutputUserStoryDTO> result;

        try {
            result = CollectionModel.of(service.showAllUserStories());

            if(result.getContent().isEmpty()) {
                ErrorMessage message = new ErrorMessage();
                message.errorMessage = "Was not created any user story yet!";
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * Consult a Product Backlog of a Project (US018)
     */
    @GetMapping("/productBacklog/{id}")
    public ResponseEntity<Object> consultProductBacklog(@PathVariable("id") String idProject) {
        CollectionModel<OutputUserStoryDTO> result;

        try {
            result = CollectionModel.of(service.consultProductBacklog(idProject));

            if(result.getContent().isEmpty()) {
                ErrorMessage message = new ErrorMessage();
                message.errorMessage = "Was not created any user story yet!";
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * Find a requested user story
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> showUserStoryRequested(@PathVariable("id") String id){
        ErrorMessage message = new ErrorMessage();
        OutputUserStoryDTO newUserStory;

        try {

            newUserStory = service.showAUserStory(id);

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newUserStory, HttpStatus.OK);
    }

    /**
     * Delete a requested User Story
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAUserStory(@PathVariable String id){
        ErrorMessage message = new ErrorMessage();

        try {
            service.deleteAUserStory(id);
            message.errorMessage = "User Story was deleted successfully";

            message.add(linkTo(methodOn(UserStoryController.class).showAllUserStories()).withRel("Collection"));

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
