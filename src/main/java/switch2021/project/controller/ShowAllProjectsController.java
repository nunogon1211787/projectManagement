package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.service.ShowAllProjectsService;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ShowAllProjectsController {

    @Autowired ShowAllProjectsService srv;

    @GetMapping
    public ResponseEntity<Object> showAllProjects(){

        List<OutputProjectDTO> allProjectsDto = srv.showAllProjects();

        return new ResponseEntity<>(allProjectsDto, HttpStatus.OK);
    }

}
