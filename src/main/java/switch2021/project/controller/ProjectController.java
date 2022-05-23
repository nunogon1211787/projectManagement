package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.ErrorMessage;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.dto.*;
import switch2021.project.repositories.ProjectRepository;
import switch2021.project.service.CreateProjectService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    CreateProjectService service;
    @Autowired
    ProjectRepository projRepo;


//    @GetMapping
//    public ResponseEntity<Object> showAllProjects(){
//
//        List<OutputProjectDTO> allProjectsDto = service.showAllProjects();
//
//        return new ResponseEntity<>(allProjectsDto, HttpStatus.OK);
//    }


    /**
     * Find all projects
     *
     * @return List Projects
     */

    @GetMapping
    public ResponseEntity<Object> showAllProjects() {
        ErrorMessage message = new ErrorMessage();
        List<OutputProjectDTO> allProjectsDto;

        try {
            allProjectsDto = service.showAllProjects();

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

            newProject.add(linkTo(methodOn(ProjectController.class).showProjectRequested(newProject.code)).withSelfRel());
            newProject.add(linkTo(methodOn(ProjectController.class).showAllProjects()).withSelfRel());
       //     newProject.add(linkTo(methodOn(ProjectController.class).updateProjectPartially(newProject.code )).withSelfRel());


        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(newProject, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Object> createProject( @RequestBody ProjectDTO projectDTO) {
        ErrorMessage message = new ErrorMessage();
        OutputProjectDTO newProject;

        try {
            newProject = service.createAndSaveProject(projectDTO);

            newProject.add(linkTo(methodOn(ProjectController.class).showProjectRequested(newProject.code)).withSelfRel());
            newProject.add(linkTo(methodOn(ProjectController.class).showAllProjects()).withSelfRel());

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }


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


//    @PostMapping("/create")
//    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectDTO projectDTO) {
//
//        try {
//
//            return new ResponseEntity<>(service.createAndSaveProject(projectDTO), HttpStatus.CREATED);
//
//        } catch (Exception e) {
//            ErrorMessage error = new ErrorMessage();
//            error.errorMessage = e.getMessage();
//
//            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//        }
//
//    }
}
