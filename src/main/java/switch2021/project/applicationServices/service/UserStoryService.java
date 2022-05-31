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
     * Create and save a User Story
     */
    public OutputUserStoryDTO createAndSaveUserStory(CreateUserStoryDTO inDto) throws Exception {

//        ProjectID projectID = new ProjectID(inDto.projectID);

        Optional<Project> project = iProjectRepo.findById(inDto.projectID);

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

    public void deleteAUserStory(String id) throws Exception {

        UserStoryID usId = createUserStoryIdByStringInputFromController(id);

        if(!iUserStoryRepo.deleteByUserStoryId(usId)){
            throw new Exception("User Story does not exist");
        }

    }

    private UserStoryID createUserStoryIdByStringInputFromController(String id){

        String[] x = id.split("&");

        String pId = x[0];

        String uTitle = x[1].replaceAll("%20", " ");

        return factoryId.create(pId, uTitle);
    }
}
