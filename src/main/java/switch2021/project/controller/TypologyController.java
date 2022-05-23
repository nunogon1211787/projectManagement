package switch2021.project.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.ErrorMessage;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.service.TypologyService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/typologies")
public class TypologyController {

    /**
     * Attributes
     **/
    @Autowired
    private TypologyService service;

    /**
     * Create a new Typology
     */
    @PostMapping
    public ResponseEntity<Object> createTypology(@RequestBody TypologyDTO inputDto) {
        ErrorMessage message = new ErrorMessage();
        if(inputDto.getDescription() == null || inputDto.getDescription().isEmpty()) {
            message.errorMessage = "Needs to provide an acceptable argument";
            return new ResponseEntity<>(message ,HttpStatus.NOT_ACCEPTABLE);}

        TypologyDTO outputDto;
        try {

            outputDto = service.createAndSaveTypology(inputDto);

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputDto, HttpStatus.CREATED);
    }

    /**
     * Find a requested Typology
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findTypologyRequested(@PathVariable("id") String id) {
        ErrorMessage message = new ErrorMessage();
        if(id == null || id.isEmpty()) {
            message.errorMessage = "Needs to provide an acceptable argument";
            return new ResponseEntity<>(message ,HttpStatus.NOT_ACCEPTABLE);}

        TypologyDTO outputDto;
        try {

            outputDto = service.findTypologyRequested(id);

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(outputDto, HttpStatus.OK);
    }

    /**
     * Find all Typologies
     */
    @GetMapping
    public ResponseEntity<Object> findTypologyList() {
        CollectionModel<TypologyDTO> typologyDTOList;
        try {

            typologyDTOList = service.findAllTypologies();

        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(typologyDTOList, HttpStatus.OK);
    }

    /**
     * Delete a requested Typology
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTypology(@PathVariable("id") String id) {
        ErrorMessage message = new ErrorMessage();
        if(id == null || id.isEmpty()) {
            message.errorMessage = "Needs to provide an acceptable argument";
            return new ResponseEntity<>(message ,HttpStatus.NOT_ACCEPTABLE);}
        try {

            service.deleteTypology(id);
            message.errorMessage = "Typology successfully deleted.";

            message.add(linkTo(methodOn(TypologyController.class).findTypologyList()).withRel("Collection"));

        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }
}
