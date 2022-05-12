package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.interfaces.IProjectRepo;
import switch2021.project.service.CreateProjectService;

import javax.validation.Valid;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    CreateProjectService service;
    @Autowired
    IProjectRepo projStore;

    @PostMapping("/createProject")
    public ResponseEntity <?> createProject (@Valid @RequestBody ProjectDTO projectDTO) {

        if (projStore.existByName(projectDTO.projectName)) {
            return ResponseEntity
                    .badRequest()
                    .body("Project name already exists"); //TODO criar uma Response Class para mensagens
        }

        return new ResponseEntity<>(service.createAndSaveProject(projectDTO), HttpStatus.CREATED);
    }

    @PostMapping("/editProject")
    public ResponseEntity <?> editProject (@Valid @RequestBody ProjectDTO projectDTO) {
        if (projStore.existByName(projectDTO.projectName)) {
            return ResponseEntity
                    .badRequest()
                    .body("Project name already exists"); //TODO criar uma Response Class para mensagens
        }

        return new ResponseEntity<>(service.editProject(projectDTO), HttpStatus.OK);
    }
}
