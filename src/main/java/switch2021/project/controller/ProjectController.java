package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.interfaces.IProjectRepo;
import switch2021.project.repositories.ProjectRepository;
import switch2021.project.service.CreateProjectService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    CreateProjectService service;
    @Autowired
    ProjectRepository projStore;


    @GetMapping
    public ResponseEntity<Object> showAllProjects(){

        List<OutputProjectDTO> allProjectsDto = service.showAllProjects();

        return new ResponseEntity<>(allProjectsDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity <?> createProject (@Valid @RequestBody ProjectDTO projectDTO) {

/*        if (projStore.existsByName(projectDTO.projectName)) {
            return ResponseEntity
                    .badRequest()
                    .body("Project name already exists"); //TODO criar uma Response Class para mensagens
        }*/

        return new ResponseEntity<>(service.createAndSaveProject(projectDTO), HttpStatus.CREATED);
    }

/*    @PostMapping("/edit")
    public ResponseEntity <?> editProject (@Valid @RequestBody ProjectDTO projectDTO) {
        if (projStore.existByName(projectDTO.projectName)) {
            return ResponseEntity
                    .badRequest()
                    .body("Project name already exists"); //TODO criar uma Response Class para mensagens
        }

        return new ResponseEntity<>(service.editProject(projectDTO), HttpStatus.OK);
    }*/
}
