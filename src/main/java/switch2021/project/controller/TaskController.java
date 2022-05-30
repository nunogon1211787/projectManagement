package switch2021.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.ErrorMessage;
import switch2021.project.dto.OutputTaskDTO;
import switch2021.project.dto.TaskDTO;
import switch2021.project.service.TaskService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    /*** Attributes **/
    @Autowired
    TaskService createTaskService;

    /*** Methods **/
    @PostMapping
    public ResponseEntity<Object> createAndSaveTask(@RequestBody TaskDTO dto) {
        OutputTaskDTO newTask;
        try {
            newTask = createTaskService.createAndSaveTask(dto);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }
}
