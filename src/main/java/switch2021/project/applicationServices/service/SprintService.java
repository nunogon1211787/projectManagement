package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.applicationServices.iRepositories.ISprintRepo;
import switch2021.project.applicationServices.iRepositories.IUserStoryOfSprintRepo;
import switch2021.project.applicationServices.iRepositories.IUserStoryRepo;
import switch2021.project.dataModel.JPA.assembler.UserStoryOfSprintJpaAssembler;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.dtoModel.mapper.SprintMapper;
import switch2021.project.dtoModel.mapper.UserStoryOfSprintMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.entities.factories.factoryInterfaces.ISprintFactory;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.UserStoryOfSprintStatus;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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
    private IProjectRepo projRepo;
    @Autowired
    private IUserStoryOfSprintRepo userStoryOfSprintRepo;
    @Autowired
    private UserStoryOfSprintMapper userStoryOfSprintMapper;
    @Autowired
    private UserStoryOfSprintJpaAssembler userStoryOfSprintJpaAssembler;

    /**
     * Create and Save a New Sprint
     */

    public OutputSprintDTO createAndSaveSprint(NewSprintDTO inDTO) throws Exception {
        Sprint sprint = sprintFactory.createSprint(inDTO);

        if (!Objects.equals(inDTO.startDate, "")) {
            String sprintId = inDTO.projectID + "_" + inDTO.name;
            validateSprintStartDate(sprintId, inDTO.startDate); //TODO separar este método
            sprint.setStartDate(LocalDate.parse(inDTO.startDate));
        }

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
     * Find All Sprints
     */

    public CollectionModel<OutputSprintDTO> showAllSprints() {
        List<Sprint> allSprints = sprintRepo.findAllSprints();
        return sprintMapper.toCollectionDto(allSprints);
    }

    public void deleteSprint(SprintID id) throws Exception {
        if (!sprintRepo.deleteSprint(id)) {
            throw new Exception("Project does not exist");
        }
    }

    public CollectionModel<OutputSprintDTO> showSprintsOfAProject(String projId) throws Exception {

        if (projRepo.existsById(new ProjectID(projId))) {

            List<Sprint> allSprintsOfAProject = sprintRepo.findAllByProjectID(new ProjectID(projId));

            return sprintMapper.toCollectionDto(allSprintsOfAProject);

        } else {

            throw new Exception("Project does not exist");
        }
    }

    /**
     * Start a Sprint
     *
     * @param id  - Sprint Id
     * @param dto - Sprint DTO
     * @return - Returns an output dto of the sprint, or a null object in case it fails to save
     * @throws Exception - The start date validation can throw an exception in case the criteria inst met.
     */
    public OutputSprintDTO startSprint(String id, StartSprintDTO dto) throws Exception {

        long sprintDuration = validateSprintStartDate(id, dto.getStartDate());

        SprintID sprintID = new SprintID(id);

        Optional<Sprint> opSprint = sprintRepo.findBySprintID(sprintID);

        if (opSprint.isPresent()) {

            opSprint.get().setStartDate(LocalDate.parse(dto.startDate));
            opSprint.get().setEndDate(LocalDate.parse(dto.startDate).plusDays(sprintDuration));

            sprintRepo.deleteSprint(opSprint.get().getSprintID());

            Optional<Sprint> savedSprint = sprintRepo.save(opSprint.get());

            if (savedSprint.isPresent()) {
                return sprintMapper.toDTO(savedSprint.get());
            } else {
                throw new Exception("Sprint doesnt exist");
            }
        }

        return null;
    }

    /**
     * Adds User Story to a specific sprintBacklog(aka ScrumBoard)
     *
     * @param id             Sprint id
     * @param userStoryIdDTO User Story DTO
     * @return Output DTO of Sprint
     * @throws Exception in case of missing parameter return
     */

    @Transactional
    public UserStoryOfSprintDTO addUserStoryToSprintBacklog(String id, UserStoryIdDTO userStoryIdDTO) throws Exception {
        UserStoryOfSprintDTO userStoryOfSprintDTO = new UserStoryOfSprintDTO();

        SprintID sprintID = new SprintID(id);

        Optional<Sprint> sprint = sprintRepo.findBySprintID(sprintID);

        UserStoryID userStoryID = new UserStoryID(new ProjectID(userStoryIdDTO.projectID),
                                                  new UsTitle(userStoryIdDTO.title));

        Optional<UserStory> foundUs = usRepo.findByUserStoryId(userStoryID);

        UserStory userStory = foundUs.flatMap(us -> foundUs).orElse(null);

        if (userStory == null) {
            throw new NullPointerException("User story does not exist");
        }

        if (sprint.isPresent()) {
            Optional<UserStoryOfSprint> savedSprint =
                    userStoryOfSprintRepo.save(new UserStoryOfSprint(userStory.getUserStoryID(),
                                                                     UserStoryOfSprintStatus.Todo,
                                                                     sprint.get().getSprintID().getSprintName().getText()));

            if (savedSprint.isPresent()) {
                userStoryOfSprintDTO = userStoryOfSprintMapper.toDTO(savedSprint.get());
            }
        } else {
            throw new Exception("Sprint or User Story doesn't exist!");
        }

        return userStoryOfSprintDTO;
    }

    public CollectionModel<UserStoryOfSprintDTO> showScrumBoard(String id) throws Exception {
        CollectionModel<UserStoryOfSprintDTO> dtoList;
        String[] values = id.split("_");
        ProjectID projectID = new ProjectID(values[0] + "_" + values[1] + "_" + values[2]);
        Description sprintName = new Description((values[3]));

        SprintID sprintID = new SprintID(projectID, sprintName);

        List<UserStoryOfSprint> userStoryOfSprintList = userStoryOfSprintRepo.findAllUserStoriesBySprintID(sprintID);

        dtoList = userStoryOfSprintMapper.toCollectionDTO(userStoryOfSprintList);

        return dtoList;
    }

    public UserStoryOfSprintDTO changeStatusScrumBoard(String id, UserStoryOfSprintDTO userStoryDTO) throws Exception {
        UserStoryOfSprintDTO userStoryOfSprintDTO = new UserStoryOfSprintDTO();
        String[] values = id.split("_");
        ProjectID projectID = new ProjectID(values[0] + "_" + values[1] + "_" + values[2]);
        Description sprintName = new Description((values[3]));

        SprintID sprintID = new SprintID(projectID, sprintName);

        List<UserStoryOfSprint> userStoryOfSprintList = userStoryOfSprintRepo.findAllUserStoriesBySprintID(sprintID);

        for (UserStoryOfSprint userStoryOfSprint : userStoryOfSprintList) {
            if (Objects.equals(userStoryOfSprint.getUserStoryId().getProjectID().getCode(),
                               userStoryDTO.projectId)
                    && Objects.equals(userStoryOfSprint.getUserStoryId().getUsTitle().getTitleUs(),
                                      userStoryDTO.usTitle)
                    && Objects.equals(userStoryOfSprint.getSprintName(),
                                      sprintID.getSprintName().getText())) {

                /*userStoryOfSprintRepo.deleteUserStoryOfSprint(new UserStoryID(new ProjectID(userStoryDTO.projectId),
                                                                              new UsTitle(userStoryDTO.usTitle)));*/

                userStoryOfSprint.setUserStoryOfSprintStatus(UserStoryOfSprintStatus.valueOf(userStoryDTO.getStatus()));

                Optional<UserStoryOfSprint> savedSprint = userStoryOfSprintRepo.save(userStoryOfSprint);

                if (savedSprint.isPresent()) {
                    userStoryOfSprintDTO = userStoryOfSprintMapper.toDTO(savedSprint.get());
                }

                return userStoryOfSprintDTO;
            }
        }

        throw new Exception("User Story not found");
    }

    private long validateSprintStartDate(String id, String date) throws Exception {
        String[] values = id.split("_");
        ProjectID projectID = new ProjectID(values[0] + "_" + values[1] + "_" + values[2]);

        Optional<Project> foundProject = projRepo.findById(projectID);

        if (foundProject.isEmpty()) {
            throw new Exception("Project not found");
        }

        long sprintDuration = foundProject.get().getSprintDuration().getSprintDurationDays();

        if (foundProject.get().getStartDate().isAfter(LocalDate.parse(date))
                ||
                foundProject.get().getEndDate() != null
                ||
                foundProject.get().getEndDate().isBefore(LocalDate.parse(date))) {

            return sprintDuration;

        } else {
            throw new Exception("Start date outside of the project duration dates");
        }
    }
}