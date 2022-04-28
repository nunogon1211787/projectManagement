package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputSprintDTO;
import switch2021.project.dto.SprintDTO;
import switch2021.project.factoryInterface.ISprintFactory;
import switch2021.project.interfaces.SprintRepositoryInterface;
import switch2021.project.mapper.SprintMapper;
import switch2021.project.model.Sprint.Sprint;

@Service
public class CreateSprintService {


    /** Attributes */
    @Autowired
    private SprintRepositoryInterface sprintRepositoryInterface;
    @Autowired
    private SprintMapper sprintMapper;
    @Autowired
    private ISprintFactory iSprintFactory;


    /** Constructor */
    public CreateSprintService() {
    }

    /** Create and Save a New Sprint */
    public OutputSprintDTO createAndSaveSprint(SprintDTO dto) {
        Sprint newSprint = iSprintFactory.createSprint(dto.projectID, dto.sprintID, dto.name);
        sprintRepositoryInterface.saveSprint(newSprint);
        return sprintMapper.toDTO(newSprint);
    }

}
