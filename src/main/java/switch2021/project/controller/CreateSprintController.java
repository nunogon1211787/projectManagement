package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import switch2021.project.dto.SprintDTO;
import switch2021.project.service.CreateSprintService;

@RestController
@RequestMapping("/sprints")
public class CreateSprintController {

    /**
     * Attributes
     **/

    @Autowired
    private CreateSprintService createSprintService;


    /**
     * Methods
     **/

    @PostMapping("")
    public ResponseEntity<Object> createAndSaveSprint(@RequestBody SprintDTO dto) {

        SprintDTO newSprint = createSprintService.createAndSaveSprint(dto);

        return new ResponseEntity<>(newSprint, HttpStatus.CREATED);
    }
}


