package switch2021.project.interfaceAdapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dtoModel.dto.ErrorMessage;
import switch2021.project.dtoModel.dto.NewSprintDTO;
import switch2021.project.dtoModel.dto.OutputSprintDTO;
import switch2021.project.applicationServices.service.SprintService;

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
     * Methods
     **/

    @PostMapping
    public ResponseEntity<Object> createAndSaveSprint(@RequestBody NewSprintDTO dto) {
        ErrorMessage message = new ErrorMessage();
        if(dto.name == null || dto.name.isEmpty() || dto.projectID == null
                || dto.projectID.isEmpty()) {
            message.errorMessage = "Needs to provide Sprint Name or Project ID.";
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
}


