package switch2021.project.interfaceAdapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    private ResourceService srv;


    /**
     * Create a Resource
     */

    @PostMapping
    public ResponseEntity<Object> createResource(@RequestBody CreateResourceDTO dto) {
        ErrorMessage message = new ErrorMessage();
        OutputResourceDTO newResource;
        try {
            newResource = srv.createAndSaveResource(dto);
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newResource, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<Object> showCurrentProjectTeam(@RequestParam("project") IdDTO dto, @RequestParam("date") DateDTO dateDto){

        List<OutputResourceDTO> resourcesDto = srv.showCurrentProjectTeam(dto, dateDto);

        return new ResponseEntity<>(resourcesDto, HttpStatus.OK);

    }

    /**
     * Find by id
     *
     * @return resource
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> showResourceRequested(@PathVariable("id") String id) {
        ErrorMessage message = new ErrorMessage();
        OutputResourceDTO newResource;

        try {
            newResource = srv.showResource(id);

            if(newResource == null){
                message.errorMessage = "Resource does not exist!";
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(newResource, HttpStatus.OK);
    }


//    @GetMapping
//    public ResponseEntity<Object> showCurrentProjectsByUser(@RequestParam("user") IdDTO id, @RequestParam("date") DateDTO dateDto){
//
//        List<OutputProjectDTO> projectsDto = showAllCurrentProjectsByUserService.showCurrentProjectsByUser(id, dateDto);
//
//        return new ResponseEntity<>(projectsDto, HttpStatus.OK);

//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAResource(@PathVariable String id){
        ResponseMessage response = new ResponseMessage();
        try {
            srv.deleteResourceRequest(id);
            response.responseMessage = "Resource was deleted successfully!";
        } catch (Exception exception){
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

