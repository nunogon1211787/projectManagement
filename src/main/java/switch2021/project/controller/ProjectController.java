package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.*;
import switch2021.project.repositories.ProjectRepository;
import switch2021.project.service.CreateProjectService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateProjectPartially(@PathVariable("id") IdDTO idDTO,
                                                         @RequestBody EditProjectInfoDTO editProjectInfoDTO) {
        OutputProjectDTO outputProjectDTO;
        try {
            outputProjectDTO = service.updateProjectPartially(idDTO, editProjectInfoDTO);
            return new ResponseEntity<>(outputProjectDTO, HttpStatus.OK);
        } catch (Exception error) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = error.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
