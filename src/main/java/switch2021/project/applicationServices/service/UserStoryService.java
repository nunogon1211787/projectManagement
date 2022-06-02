package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IUserStoryRepo;
import switch2021.project.dtoModel.dto.OutputUserStoryDTO;
import switch2021.project.dtoModel.dto.CreateUserStoryDTO;
import switch2021.project.entities.factories.factoryInterfaces.IUserStoryFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserStoryIDFactory;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.dtoModel.mapper.UserStoryMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.UserStoryID;

import java.util.List;
import java.util.Optional;

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
    private IUserStoryIDFactory factoryId;

    /**
     * Create and save a User Story (US009)
     */
    public OutputUserStoryDTO createAndSaveUserStory(CreateUserStoryDTO inDto) throws Exception {
        ProjectID projID = new ProjectID(inDto.projectID);
//        String[] x = inDto.projectID.split("_");
//        ProjectID projID = new ProjectID(x[2]);
        Optional<Project> project = iProjectRepo.findById(projID);

        if(project.isEmpty()) {
            throw new Exception("Project does not exist");
        }
        UserStory newUserStory = iUserStoryFactory.createUserStory(inDto);
        Optional<UserStory> usSaved = iUserStoryRepo.save(newUserStory);
        OutputUserStoryDTO usDto;

        if(usSaved.isPresent()) {
            usDto = userStoryMapper.toDto(usSaved.get());
        } else {
            throw new Exception("User story already exist.");
        }
        return usDto;
    }

    public OutputUserStoryDTO showAUserStory(String id) throws Exception {
        UserStoryID usId = createUserStoryIdByStringInputFromController(id);
        Optional<UserStory> foundUs = iUserStoryRepo.findByUserStoryId(usId);

        if(foundUs.isEmpty()){
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

        if(!iProjectRepo.existsById(projID)) {
            throw new Exception("Project does not exist");
        }

        List<UserStory> productBacklog = iUserStoryRepo.findProductBacklog(projectId);
        return userStoryMapper.toCollectionDto(productBacklog);
    }


    /**
     * Delete User Story
     */
    public void deleteAUserStory(String id) throws Exception {
        UserStoryID usId = createUserStoryIdByStringInputFromController(id);

        if(!iUserStoryRepo.deleteByUserStoryId(usId)){
            throw new Exception("User Story does not exist");
        }
    }


    /**
     * Create User Story ID method
     */
    private UserStoryID createUserStoryIdByStringInputFromController(String id){
        String[] x = id.split("&");
        String pId = x[0];
        String uTitle = x[1].replaceAll("%20", " ");
        return factoryId.create(pId, uTitle);
    }
}