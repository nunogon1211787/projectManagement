package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.ISprintRepo;
import switch2021.project.dtoModel.dto.NewSprintDTO;
import switch2021.project.dtoModel.dto.OutputSprintDTO;
import switch2021.project.entities.factories.factoryInterfaces.ISprintFactory;
import switch2021.project.dtoModel.mapper.SprintMapper;
import switch2021.project.entities.aggregates.Sprint.Sprint;

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
