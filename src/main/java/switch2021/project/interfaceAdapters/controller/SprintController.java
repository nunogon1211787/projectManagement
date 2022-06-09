package switch2021.project.interfaceAdapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.applicationServices.service.SprintService;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.SprintID;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/sprints")
public class SprintController {

    /**
     * Attributes
     **/

    @Autowired
    private SprintService sprintService;

    /**
     * Create Sprint
     **/

    @PostMapping
    public ResponseEntity<Object> createAndSaveSprint(@RequestBody NewSprintDTO dto) {
        ErrorMessage message = new ErrorMessage();
        if(dto.name == null || dto.name.isEmpty() || dto.projectID == null
                || dto.projectID.isEmpty()) {
            message.errorMessage = "Must provide Sprint Name or Project ID.";
            return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);}

        OutputSprintDTO outPutSprintDTO;
        try {
            outPutSprintDTO = sprintService.createAndSaveSprint(dto);
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outPutSprintDTO, HttpStatus.CREATED);
    }


    @PostMapping("/{id}")
    public ResponseEntity<Object> addUserStoryToSprintBacklog(@PathVariable("id") String id,
                                                              @RequestBody UserStoryIdDTO UsIdDto) {
        ErrorMessage message = new ErrorMessage();
        OutputSprintDTO sprintDTO;

        try {
            sprintService.addUserStoryToSprintBacklog(id, UsIdDto);
            message.errorMessage = "User story added to sprintbacklog";
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    /**
     * Find All Sprints
     */

    @GetMapping
    public ResponseEntity<Object> showSprints(){

        CollectionModel<OutputSprintDTO> result;

        try {
            result = CollectionModel.of(sprintService.showAllSprints());

            if(result.getContent().isEmpty()) {
                ErrorMessage message = new ErrorMessage();
                message.errorMessage = "Was not created any sprint yet!";
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
     * Find Sprint By ID
     **/

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSprintByID (@PathVariable("id") String id) {

        OutputSprintDTO outputSprintDTO;

        try {
            outputSprintDTO = sprintService.findSprintByID(id);
        } catch (Exception exception) {
            ErrorMessage msg = new ErrorMessage();
            msg.errorMessage = exception.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(outputSprintDTO, HttpStatus.OK);
    }


    /**
     * Delete Sprint
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSprint (@PathVariable String id) {
        ResponseMessage responseMessage = new ResponseMessage();

        try {
            sprintService.deleteSprint(id);
            responseMessage.responseMessage = "Sprint Was delete successfully";
        } catch (Exception exception) {
            ErrorMessage msg = new ErrorMessage();
            msg.errorMessage = exception.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }


//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteProjectRequest(@PathVariable String id) {
//        ErrorMessage message = new ErrorMessage();
//
//        String [] values = id.split("_");
//
//        SprintID sprintID = new SprintID(new ProjectID(values[0] + "_" + values[1] + "_" + values[2])  ,
//                                         new Description(values[3]));
//
//        try {
//            sprintService.deleteSprint(sprintID);
//            message.errorMessage = "Sprint was deleted successfully";
//
//        } catch (Exception exception) {
//            message.errorMessage = exception.getMessage();
//            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(message, HttpStatus.OK);
//    }



/*    @GetMapping("/{id}")
    public  ResponseEntity<Object> showScrumBoard(@PathVariable("id") String id) {

        ErrorMessage message = new ErrorMessage();
        OutputSprintDTO sprintDTO;

        try {
            sprintDTO = sprintService.;

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(sprintDTO, HttpStatus.OK);
    }*/
}


