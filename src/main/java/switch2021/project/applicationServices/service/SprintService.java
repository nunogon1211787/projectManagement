package switch2021.project.applicationServices.service;

import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.ISprintRepo;
import switch2021.project.applicationServices.iRepositories.IUserStoryRepo;
import switch2021.project.dtoModel.dto.NewSprintDTO;
import switch2021.project.dtoModel.dto.OutputSprintDTO;
import switch2021.project.dtoModel.dto.UserStoryIdDTO;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.dtoModel.mapper.SprintMapper;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.entities.factories.factoryInterfaces.ISprintFactory;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.UserStoryOfSprintStatus;
import switch2021.project.interfaceAdapters.controller.SprintController;
import switch2021.project.interfaceAdapters.controller.UserController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
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
    @Autowired
    private IUserStoryRepo usRepo;
    @Autowired
    private ISprintIDFactory iSprintIDFactory;


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

    /**
     * Adds User Story to a specific sprintBacklog(aka ScrumBoard)
     *
     * @param id             Sprint id
     * @param userStoryIdDTO User Story DTO
     * @return Output DTO of Sprint
     * @throws Exception in case of missing parameter return
     */

    public OutputSprintDTO addUserStoryToSprintBacklog(String id, UserStoryIdDTO userStoryIdDTO) throws Exception {
        OutputSprintDTO outputSprintDTO = new OutputSprintDTO();

        SprintID sprintID = new SprintID(id);

        Optional<Sprint> sprint = sprintRepo.findBySprintID(sprintID);

        UserStoryID userStoryID = new UserStoryID(new ProjectID(userStoryIdDTO.projectID),
                                                  new UsTitle(userStoryIdDTO.title));

        UserStory userStory = usRepo.findByUserStoryId(userStoryID);

        if (sprint.isPresent()) {
            sprint.get().saveUsInScrumBoard(new UserStoryOfSprint(userStory.getUserStoryID(),
                                                                  UserStoryOfSprintStatus.Todo));

            sprintRepo.deleteSprint(sprint.get().getSprintID());

            Optional<Sprint> savedSprint = sprintRepo.save(sprint.get());

            if (savedSprint.isPresent()) {
                outputSprintDTO = sprintMapper.toDTO(savedSprint.get());
            }
        } else {
            throw new Exception("Sprint or User Story doesn't exist!");
        }

        return outputSprintDTO;
    }

    /**
     * Find All Sprints
     */
    public CollectionModel<OutputSprintDTO> showAllSprints() {
        List<Sprint> allSprints = sprintRepo.findAllSprints();
        return sprintMapper.toCollectionDto(allSprints);
    }


    public OutputSprintDTO showScrumBoard(String id) {

        String[] values = id.split("_");
        ProjectID projectID = new ProjectID(values[0] + values[1] + values[2]);
        Description sprintName = new Description((values[3]));

        SprintID sprintID = new SprintID(projectID, sprintName);

        Optional<Sprint> sprint = sprintRepo.findBySprintID(sprintID);

        return null;
    }

    public void deleteSprint(SprintID id) throws Exception {
        if (!sprintRepo.deleteSprint(id)) {
            throw new Exception("Project does not exist");
        }
    }



    /**
     * Find Sprint By ID
     */

    public OutputSprintDTO findSprintByID(String id) throws Exception{

        OutputSprintDTO outputSprintDTO = new OutputSprintDTO();

        SprintID sprintID = createSprintIDByStringInputFromController(id);

        Optional<Sprint> optionalSprint = sprintRepo.findBySprintID(sprintID);

        if (optionalSprint.isEmpty()) {
            throw new Exception("Sprint does not exist.");
        }

        return sprintMapper.toDTO(optionalSprint.get());
    }

    /**
     * Delete Sprint
     */

    public void deleteSprint (String id) throws Exception{

        SprintID sprintID = createSprintIDByStringInputFromController(id);

        if(!sprintRepo.deleteSprint(sprintID)) {
            throw new Exception("Sprint does not exist!");
        }
    }


    /**
     * Create Sprint ID method
     */
    private SprintID createSprintIDByStringInputFromController(String id) {
        String[] x = id.split("&");
        String projectID = x[0];
        String sprintName = x[1].replaceAll("%20", " ");
        return iSprintIDFactory.create(projectID, sprintName);
    }
}
