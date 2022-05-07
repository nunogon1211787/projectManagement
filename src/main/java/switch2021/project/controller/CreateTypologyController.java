package switch2021.project.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import switch2021.project.dto.ErrorMessage;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.service.CreateTypologyService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/typologies")
public class CreateTypologyController {

    /**
     * Attributes
     **/
    @Autowired
    private CreateTypologyService service;

    /**
     * Methods
     */
    @PostMapping("/")
    public ResponseEntity<Object> createTypology(@RequestBody TypologyDTO inputDto) {
        if(inputDto.description == null || inputDto.description.isEmpty()) {
            String responseMessege = "Needs to provide an acceptable argument";
            return new ResponseEntity<>(responseMessege ,HttpStatus.NOT_ACCEPTABLE);}

        TypologyDTO outputDto;
        try {
            outputDto = service.createAndSaveTypology(inputDto);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputDto, HttpStatus.CREATED);
    }

    @GetMapping("/id")
    public ResponseEntity<Object> findTypologyByDescription(@RequestBody TypologyDTO inputDto) {
        if(inputDto.description == null || inputDto.description.isEmpty()) {
            String responseMessege = "Needs to provide an acceptable argument";
            return new ResponseEntity<>(responseMessege ,HttpStatus.NOT_ACCEPTABLE);}

        TypologyDTO outputDto;
        try {
            outputDto = service.findTypologyByDescription(inputDto);
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(outputDto, HttpStatus.OK);
    }

    @GetMapping("/")
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

    @DeleteMapping("/id")
    public ResponseEntity<Object> deleteTypology(@RequestBody TypologyDTO inputDto) {
        if(inputDto.description == null || inputDto.description.isEmpty()) {
            String responseMessege = "Needs to provide an acceptable argument";
            return new ResponseEntity<>(responseMessege ,HttpStatus.NOT_ACCEPTABLE);}

        TypologyDTO outputDto;
        try {
            service.deleteTypology(inputDto);
            outputDto = new TypologyDTO();
            outputDto.description = "Deleted successfully";
        } catch (Exception exception) {
            ErrorMessage message = new ErrorMessage();
            message.errorMessage = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(outputDto, HttpStatus.ACCEPTED);
    }
}
