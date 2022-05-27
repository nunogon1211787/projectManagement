package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.NewSprintDTO;
import switch2021.project.dto.OutputSprintDTO;
import switch2021.project.factoryInterface.ISprintFactory;
import switch2021.project.interfaces.ISprintRepo;
import switch2021.project.mapper.SprintMapper;
import switch2021.project.model.Sprint.Sprint;

import java.util.Optional;

@Service
public class SprintService {

    /**
     * Attributes
     */
    @Autowired
    private ISprintRepo sprintRepo;
    @Autowired
    private SprintMapper sprintMapper;
    @Autowired
    private ISprintFactory sprintFactory;

    /**
     * Create and Save a New Sprint
     */
    /*public OutputSprintDTO createAndSaveSprint(NewSprintDTO dto) {
        Sprint newSprint = iSprintFactory.createSprint(dto);
        if (!iSprintRepo.save(newSprint)){
            throw  new IllegalArgumentException("Sprint already exists!");
        }
        return sprintMapper.toDTO(newSprint);
    }

     */
    public OutputSprintDTO createAndSaveSprint(NewSprintDTO inDTO) throws Exception {
        Sprint sprint = sprintFactory.createSprint(inDTO);
        Optional<Sprint> sprintSaved = sprintRepo.save(sprint);

        OutputSprintDTO outputSprintDTO;

        if (sprintSaved.isPresent()) {
            outputSprintDTO = sprintMapper.toDTO(sprintSaved.get());
        } else {
            throw new Exception("Sprint already exists!");
        }
        return outputSprintDTO;
    }
}
