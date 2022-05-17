package switch2021.project.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.ErrorMessage;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.service.TypologyService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/typologies")
public class TypologyController {

    /**
     * Attributes
     **/
    @Autowired
    private TypologyService service;

    /**
     * Methods
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

    @GetMapping("/{id}")
    public ResponseEntity<Object> findTypologyByDescription(@PathVariable IdDTO idDTO) {
        ErrorMessage message = new ErrorMessage();
        if(idDTO.getId() == null || idDTO.getId().isEmpty()) {
            message.errorMessage = "Needs to provide an acceptable argument";
            return new ResponseEntity<>(message ,HttpStatus.NOT_ACCEPTABLE);}

        TypologyDTO outputDto;
        try {
            outputDto = service.findTypologyByDescription(idDTO);
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(outputDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> findTypologyList() {
        List<TypologyDTO> typologyDTOList;
        try {
            typologyDTOList = service.findAllTypologies();
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(typologyDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTypology(@PathVariable IdDTO idDTO) {
        ErrorMessage message = new ErrorMessage();
        if(idDTO.getId() == null || idDTO.getId().isEmpty()) {
            message.errorMessage = "Needs to provide an acceptable argument";
            return new ResponseEntity<>(message ,HttpStatus.NOT_ACCEPTABLE);}

        TypologyDTO outputDto;
        try {
            service.deleteTypology(idDTO);
            outputDto = new TypologyDTO(idDTO.getId());
            outputDto.description = "Deleted successfully";
        } catch (Exception exception) {
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(outputDto, HttpStatus.ACCEPTED);
    }
}
