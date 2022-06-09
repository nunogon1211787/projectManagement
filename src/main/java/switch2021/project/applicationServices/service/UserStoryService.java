package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IUserStoryRepo;
import switch2021.project.dtoModel.dto.OutputUserStoryDTO;
import switch2021.project.dtoModel.dto.UpdateUserStoryDTO;
import switch2021.project.dtoModel.dto.UserStoryDTO;
import switch2021.project.entities.factories.factoryInterfaces.IUserStoryFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUsHourFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUsPriorityFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserStoryIDFactory;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.dtoModel.mapper.UserStoryMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

import java.util.*;

@Service
public class UserStoryService {

    /**
     * Attributes
     **/
    @Autowired
    private IUserStoryRepo iUserStoryRepo;
    @Autowired
    private IProjectRepo iProjectRepo;
    @Autowired
    private UserStoryMapper userStoryMapper;
    @Autowired
    private IUserStoryFactory iUserStoryFactory;
    @Autowired
    private IUserStoryIDFactory usIdFactory;
    @Autowired
    private IUsHourFactory usHourFactory;
    @Autowired
    private IUsPriorityFactory priorityFactory;

    /**
     * Create and save a User Story (US009)
     */
    public OutputUserStoryDTO createAndSaveUserStory(UserStoryDTO inDto) throws Exception {
        ProjectID projID = new ProjectID(inDto.projectID);
        Optional<Project> project = iProjectRepo.findById(projID);

        if (project.isEmpty()) {
            throw new Exception("Project does not exist");
        }
        UserStory newUserStory = iUserStoryFactory.createUserStory(inDto);
        Optional<UserStory> usSaved = iUserStoryRepo.save(newUserStory);
        OutputUserStoryDTO usDto;

        if (usSaved.isPresent()) {
            usDto = userStoryMapper.toDto(usSaved.get());
        } else {
            throw new Exception("User story already exist.");
        }
        return usDto;
    }


    public OutputUserStoryDTO showAUserStory(String id) throws Exception {
        UserStoryID usId = createUserStoryIdByStringInputFromController(id);
        Optional<UserStory> foundUs = iUserStoryRepo.findByUserStoryId(usId);

        if (foundUs.isEmpty()) {
            throw new Exception("User story does not exist");
        }
        return userStoryMapper.toDto(foundUs.get());
    }

    public CollectionModel<OutputUserStoryDTO> showAllUserStories() {
        List<UserStory> allUserStories = iUserStoryRepo.findAll();
        return userStoryMapper.toCollectionDto(allUserStories);
    }


    /**
     * Consult a Product Backlog of a Project (US018)
     */
    public CollectionModel<OutputUserStoryDTO> consultProductBacklog(String projectId) throws Exception {
        ProjectID projID = new ProjectID(projectId);

        if (!iProjectRepo.existsById(projID)) {
            throw new Exception("Project does not exist");
        }

        List<UserStory> productBacklog = iUserStoryRepo.findProductBacklog(projectId);
        return userStoryMapper.toCollectionDto(productBacklog);
    }


    /**
     * Update data of a User Story (US019 and US021)
     */
    public OutputUserStoryDTO updateUSData(String id, UpdateUserStoryDTO updateDTO) throws Exception {
        Optional<UserStory> opUs = iUserStoryRepo.findByUserStoryId(createUserStoryIdByStringInputFromController(id));
        UserStory userStory;
        OutputUserStoryDTO updatedDto = null;

        if (opUs.isPresent()) {
            userStory = opUs.get();

            if (updateDTO.priority != 0) {
                userStory.updatePriority(priorityFactory.create(updateDTO.getPriority()));
            }
            if (updateDTO.timeEstimate != 0) {
                userStory.updateTimeEstimate(usHourFactory.create(updateDTO.getTimeEstimate()));
            }
        } else {
            throw new Exception("User story does not exist");
        }

        Optional<UserStory> updated = iUserStoryRepo.update(userStory);

        if (updated.isPresent()) {
            updatedDto = userStoryMapper.toDto(updated.get());
        }
        return updatedDto;
    }

    public OutputUserStoryDTO startUserStory(String id) throws Exception {
        Optional<UserStory> opUs = iUserStoryRepo.findByUserStoryId(createUserStoryIdByStringInputFromController(id));
        UserStory userStory;
        OutputUserStoryDTO updatedDto = null;

        if (opUs.isPresent()) {
            userStory = opUs.get();
            if(userStory.getUsEndDate() != null) {
                throw new IllegalArgumentException("This User Story is already closed!");
            } else
                if(userStory.getUsStartDate() != null) {
                    throw new IllegalArgumentException("This User Story is already started!");
            } else {
                userStory.startUserStory();
            }
        } else {
                throw new Exception("User story does not exist");
            }
            Optional<UserStory> updated = iUserStoryRepo.update(userStory);

            if (updated.isPresent()) {
                updatedDto = userStoryMapper.toDto(updated.get());
            }
            return updatedDto;
    }

    public OutputUserStoryDTO cancelUserStory(String id) throws Exception {
        Optional<UserStory> opUs = iUserStoryRepo.findByUserStoryId(createUserStoryIdByStringInputFromController(id));
        UserStory userStory;
        OutputUserStoryDTO updatedDto = null;

        if (opUs.isPresent()) {
            userStory = opUs.get();
            if(userStory.getUsEndDate() != null) {
                throw new IllegalArgumentException("This User Story is already closed!");
            } else {
                userStory.cancelUserStory();
            }
        } else {
            throw new Exception("User story does not exist");
        }

        Optional<UserStory> updated = iUserStoryRepo.update(userStory);

        if (updated.isPresent()) {
            updatedDto = userStoryMapper.toDto(updated.get());
        }
        return updatedDto;
    }

    public OutputUserStoryDTO finishUserStory(String id) throws Exception {
        Optional<UserStory> opUs = iUserStoryRepo.findByUserStoryId(createUserStoryIdByStringInputFromController(id));
        UserStory userStory;
        OutputUserStoryDTO updatedDto = null;

        if (opUs.isPresent()) {
            userStory = opUs.get();
            if(userStory.getUsEndDate() != null) {
                throw new IllegalArgumentException("This User Story is already closed!");
            } else {
                userStory.finishUserStory();
            }
        } else {
            throw new IllegalArgumentException("User story does not exist");
        }

        Optional<UserStory> updated = iUserStoryRepo.update(userStory);

        if (updated.isPresent()) {
            updatedDto = userStoryMapper.toDto(updated.get());
        }
        return updatedDto;
    }


    /**
     * Refine a board user story of the Product Backlog (US020)
     */
    public CollectionModel<OutputUserStoryDTO> refineUserStory(String id, UserStoryDTO inDto) throws Exception {
        Optional<UserStory> opUs = iUserStoryRepo.findByUserStoryId(createUserStoryIdByStringInputFromController(id));
        List<UserStory> userStories = new ArrayList<>();
        CollectionModel<OutputUserStoryDTO> savedDto;

        if (opUs.isPresent()) {
            UserStory us = opUs.get();

            UserStory refinedUs = iUserStoryFactory.createUserStory(inDto);
            refinedUs.assignParentUserStory(us);
            Optional<UserStory> saved = iUserStoryRepo.save(refinedUs);

            if (saved.isPresent()) {
                userStories.add(saved.get());

                us.refinedUs();
                Optional<UserStory> updated = iUserStoryRepo.update(us);

                updated.ifPresent(userStories::add);
            } else {
                throw new IllegalArgumentException("This User Story could not be refined!");
            }
            savedDto = userStoryMapper.toCollectionDto(userStories);
        } else {
            throw new Exception("User story does not exist");
        }
        return savedDto;
    }


    /**
     * Delete User Story
     */
    public void deleteAUserStory(String id) throws Exception {
        UserStoryID usId = createUserStoryIdByStringInputFromController(id);

        if (!iUserStoryRepo.deleteByUserStoryId(usId)) {
            throw new Exception("User Story does not exist");
        }
    }


    /**
     * Create User Story ID method
     */
    public UserStoryID createUserStoryIdByStringInputFromController(String id) {
        String[] x = id.split("&");
        String pId = x[0];
        String uTitle = x[1];
        return usIdFactory.create(pId, uTitle);
    }
}
