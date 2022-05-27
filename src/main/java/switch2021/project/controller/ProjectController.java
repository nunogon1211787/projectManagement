package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.ErrorMessage;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.dto.*;
import switch2021.project.service.ProjectService;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService service;

    /**
     * Find all projects
     *
     * @return List Projects
     */

    @GetMapping
    public ResponseEntity<Object> showAllProjects() {
        ErrorMessage message = new ErrorMessage();
        CollectionModel<OutputProjectDTO> allProjectsDto;

        try {
            allProjectsDto = CollectionModel.of(service.showAllProjects());

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(allProjectsDto, HttpStatus.OK);
    }

    /**
     * Find by id
     *
     * @return project
     */

    @GetMapping("/{id}")
    public ResponseEntity<Object> showProjectRequested(@PathVariable("id") String id) {
        ErrorMessage message = new ErrorMessage();
        OutputProjectDTO newProject;

        try {
            newProject = service.showProject(id);

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(newProject, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Object> createProject(@RequestBody ProjectDTO projectDTO) {
        ErrorMessage message = new ErrorMessage();
        OutputProjectDTO newProject;

        try {
            newProject = service.createAndSaveProject(projectDTO);

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }

    /**
     * Edit project
     */

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateProjectPartially(@PathVariable("id") String id,
                                                         @RequestBody EditProjectInfoDTO editProjectInfoDTO) {
        OutputProjectDTO outputProjectDTO;
        try {
            outputProjectDTO = service.updateProjectPartially(id, editProjectInfoDTO);
            return new ResponseEntity<>(outputProjectDTO, HttpStatus.OK);
        } catch (Exception error) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = error.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete project
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProjectRequest(@PathVariable String id) {
        ErrorMessage message = new ErrorMessage();

        try {
            service.deleteProjectRequest(id);
            message.errorMessage = "Project was deleted successfully";

            message.add(linkTo(methodOn(ProjectController.class).showAllProjects()).withRel("Collection"));

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
