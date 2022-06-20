package switch2021.project.interfaceAdapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dtoModel.dto.ErrorMessage;
import switch2021.project.dtoModel.dto.OutputProjectDTO;
import switch2021.project.dtoModel.dto.ProjectDTO;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.applicationServices.service.ProjectService;
import switch2021.project.entities.valueObjects.vos.ProjectID;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "http://localhost:3000")
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
            allProjectsDto = CollectionModel.of(service.getAllProjects());

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


    /**
     * Create Project - US005
     **/
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
     * Edit project - US008
     **/
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateProjectPartially(@PathVariable("id") String id,
                                                         @RequestBody EditProjectInfoDTO editProjectInfoDTO) {
        ErrorMessage message = new ErrorMessage();
        OutputProjectDTO outputProjectDTO;
        try {
            outputProjectDTO = service.updateProjectPartially(id, editProjectInfoDTO);
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputProjectDTO, HttpStatus.OK);
    }


    /**
     * Delete project
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProjectRequest(@PathVariable String id) {
        ErrorMessage message = new ErrorMessage();
//        String[] x = id.split("_");
        ProjectID projID = new ProjectID(id);

        try {
            service.deleteProjectRequest(projID);
            message.errorMessage = "Project was deleted successfully";
            message.add(linkTo(methodOn(ProjectController.class).showAllProjects()).withRel("Collection"));
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

//    @GetMapping("/{id}/projects") //TODO review method
//    public ResponseEntity<Object> showCurrentProjectsByUser(@PathVariable String id,
//                                                            @RequestParam("date") DateDTO dateDto) {
//        List<OutputProjectDTO> projectsDto = service.showCurrentProjectsByUser(id, dateDto);
//        return new ResponseEntity<>(projectsDto, HttpStatus.OK);
//    }
}
