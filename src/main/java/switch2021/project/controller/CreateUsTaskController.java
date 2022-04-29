package switch2021.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import switch2021.project.dto.OutputTaskDTO;
import switch2021.project.dto.TaskDTO;
import switch2021.project.service.CreateTaskService;

@Controller
    @RestController
    @RequestMapping("/tasks")
    public class CreateUsTaskController {

        /**
         * Attributes
         **/
        @Autowired
        CreateTaskService createTaskService;

        /**
         * Methods
         **/
        @PostMapping("")
        public ResponseEntity<Object> createAndSaveUsTask(@RequestBody TaskDTO dto) {

            OutputTaskDTO newTask = createTaskService.createAndSaveTask(dto);
            return new ResponseEntity<>(newTask, HttpStatus.CREATED);

    }
}
