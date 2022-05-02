package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import switch2021.project.dto.OutputResourceDTO;
import switch2021.project.dto.ResourceDTOReeng;
import switch2021.project.service.CreateResourceInAProjectService;

@RestController
@RequestMapping("/resources")
public class CreateResourceInAProjectController {

    /**
     * Attributes
     **/
    @Autowired
    private CreateResourceInAProjectService createResourceService;

    /**
     * Create a Resource
     */

//    @PostMapping("");
    public ResponseEntity<Object> createResource(@RequestBody ResourceDTOReeng dto) {

        OutputResourceDTO newResource = createResourceService.createAndSaveResource(dto);

        return new ResponseEntity<>(newResource, HttpStatus.CREATED);
    }
}
