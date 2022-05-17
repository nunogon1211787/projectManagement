package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.NewSprintDTO;
import switch2021.project.dto.OutPutSprintDTO;
import switch2021.project.factoryInterface.ISprintFactory;
import switch2021.project.interfaces.ISprintRepo;
import switch2021.project.mapper.SprintMapper;
import switch2021.project.model.Sprint.Sprint;

@Service
public class CreateSprintService {

    /** Attributes */
    @Autowired
    private ISprintRepo iSprintRepo;
    @Autowired
    private SprintMapper sprintMapper;
    @Autowired
    private ISprintFactory iSprintFactory;


    /** Create and Save a New Sprint */
    public OutPutSprintDTO createAndSaveSprint(NewSprintDTO dto) {
        Sprint newSprint = iSprintFactory.createSprint(dto);
        if (!iSprintRepo.save(newSprint)){
            throw  new IllegalArgumentException("Sprint already exists!");
        }
        return sprintMapper.toDTO(newSprint);
    }

}
