package switch2021.project.interfaceAdapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.applicationServices.service.ResourceService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/resources")
public class ResourceController {

    /**
     * Attributes
     **/
    @Autowired
    private ResourceService service;


    /**
     * Create a Resource (US007)
     */
    @PostMapping
    public ResponseEntity<Object> createResource(@RequestBody CreateResourceDTO dto) {
        OutputResourceDTO newResource;

        try {
            newResource = service.createAndSaveResource(dto);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newResource, HttpStatus.CREATED);
    }


    /**
     * Find by id
     *
     * @return resource
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> showResourceRequested(@PathVariable("id") String id) {
        OutputResourceDTO newResource;

        try {
            newResource = service.showResourceRequested(id);

            if (newResource == null) {
                ErrorMessage message = new ErrorMessage();
                message.errorMessage = "Resource does not exist!";
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newResource, HttpStatus.OK);
    }


    /**
     * Find all Resources
     */
    @GetMapping
    public ResponseEntity<Object> showAllResources() {
        CollectionModel<OutputResourceDTO> result;

        try {
            result = CollectionModel.of(service.showAllResources());

            if (result.getContent().isEmpty()) {
                ErrorMessage message = new ErrorMessage();
                message.errorMessage = "There are no resources yet!";
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/currentProjectTeam/{id}")
    public ResponseEntity<Object> showCurrentProjectTeam(@PathVariable("id") String projectId) {
        CollectionModel<OutputResourceDTO> resourcesFound;

        try {
            resourcesFound = service.showCurrentProjectTeam(projectId);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resourcesFound, HttpStatus.OK);
    }


    /**
     * Consult a Project Team of a Project (US028)
     */
    @GetMapping("/registerOfResources/{id}")
    public ResponseEntity<Object> showRegisterOfResourcesInAProject(@PathVariable("id") String idProject) {
        List<OutputResourceDTO> result;

        try {
            result = service.showProjectTeam(idProject);

            if (result.isEmpty()) {
                ErrorMessage message = new ErrorMessage();
                message.errorMessage = "There are no resources in this project";
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


//    @GetMapping
//    public ResponseEntity<Object> showCurrentProjectsByUser(@RequestParam("user") IdDTO id, @RequestParam("date")
//    DateDTO dateDto){
//
//        List<OutputProjectDTO> projectsDto = showAllCurrentProjectsByUserService.showCurrentProjectsByUser(id,
//        dateDto);
//
//        return new ResponseEntity<>(projectsDto, HttpStatus.OK);

//    }


    /**
     * Define the requested project role of a resource (US014 and US027)
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Object> defineProjectRoleOfAResource(@PathVariable String id,
                                                               @RequestBody DefineRoleOfResourceDTO dto) {
        OutputResourceDTO response;

        try {
            response = service.defineProjectRole(id, dto);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * Delete a Resource
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAResource(@PathVariable String id) {
        ResponseMessage response = new ResponseMessage();
        try {
            service.deleteResourceRequest(id);
            response.responseMessage = "Resource was deleted successfully!";
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

