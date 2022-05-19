package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.*;
import switch2021.project.service.CreateResourceInAProjectService;
import switch2021.project.service.ResourceService;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    /**
     * Attributes
     **/
    @Autowired private CreateResourceInAProjectService createResourceService;

    @Autowired private ResourceService srv;

//    @Autowired private ShowAllCurrentProjectsByUserService showAllCurrentProjectsByUserService;


    @GetMapping
    public ResponseEntity<Object> showCurrentProjectTeam(@RequestParam("project") IdDTO dto, @RequestParam("date") DateDTO dateDto){

        List<OutputResourceDTO> resourcesDto = srv.showCurrentProjectTeam(dto, dateDto);

        return new ResponseEntity<>(resourcesDto, HttpStatus.OK);

    }

//    @GetMapping
//    public ResponseEntity<Object> showCurrentProjectsByUser(@RequestParam("user") IdDTO id, @RequestParam("date") DateDTO dateDto){
//
//        List<OutputProjectDTO> projectsDto = showAllCurrentProjectsByUserService.showCurrentProjectsByUser(id, dateDto);
//
//        return new ResponseEntity<>(projectsDto, HttpStatus.OK);
//    }


    /**
     * Create a Resource
     */

    @PostMapping
    public ResponseEntity<Object> createResource(@RequestBody CreateResourceDTO dto) {
        ErrorMessage message = new ErrorMessage();
        OutputResourceDTO newResource = createResourceService.createAndSaveResource(dto);

        try {
            newResource = createResourceService.createAndSaveResource(dto);

//            newResource.add(linkTo(methodOn(ResourceController.class).showResourceRequested(1)).withSelfRel());

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(newResource, HttpStatus.CREATED);
    }


}
