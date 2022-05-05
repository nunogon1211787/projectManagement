package switch2021.project.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.service.CreateTypologyService;

@Controller
@RestController
@RequestMapping("/typologies")
public class CreateTypologyController {

    /**
     * Attributes
     **/
    @Autowired
    private CreateTypologyService service;

    public CreateTypologyController(CreateTypologyService service) {
        this.service = service;
    }

    /**
     * Methods
     * @param inputDto
     */
    @PostMapping("")
    public ResponseEntity<Object> createTypology(@RequestParam TypologyDTO inputDto) {
        TypologyDTO typologyDto = service.createAndSaveTypology(inputDto);
        return new ResponseEntity<>(typologyDto, HttpStatus.CREATED);
    }
}
