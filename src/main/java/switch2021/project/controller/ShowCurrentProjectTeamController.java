package switch2021.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.OutputResourceDTO;
import switch2021.project.service.ShowCurrentProjectTeamService;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ShowCurrentProjectTeamController {

    @Autowired
    private ShowCurrentProjectTeamService srv;


    @GetMapping
    public ResponseEntity<Object> showCurrentProjectTeam(@PathVariable IdDTO dto){

        List<OutputResourceDTO> resourcesDto = srv.showCurrentProjectTeam(dto);

        return new ResponseEntity<>(resourcesDto, HttpStatus.OK);

    }


}
