package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.DateDTO;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.OutputResourceDTO;
import switch2021.project.dto.ResourceDTOReeng;
import switch2021.project.service.CreateResourceInAProjectService;
import switch2021.project.service.ShowCurrentProjectTeamService;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourcesController {

    /**
     * Attributes
     **/
    @Autowired private CreateResourceInAProjectService createResourceService;

    @Autowired private ShowCurrentProjectTeamService srv;


    @GetMapping
    public ResponseEntity<Object> showCurrentProjectTeam(@RequestParam("project") IdDTO dto, @RequestParam("date") DateDTO dateDto){

        List<OutputResourceDTO> resourcesDto = srv.showCurrentProjectTeam(dto, dateDto);

        return new ResponseEntity<>(resourcesDto, HttpStatus.OK);

    }


    /**
     * Create a Resource
     */

//    @PostMapping("");
    public ResponseEntity<Object> createResource(@RequestBody ResourceDTOReeng dto) {

        OutputResourceDTO newResource = createResourceService.createAndSaveResource(dto);

        return new ResponseEntity<>(newResource, HttpStatus.CREATED);
    }
}
