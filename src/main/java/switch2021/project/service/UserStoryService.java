package switch2021.project.service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.datamodel.UserStoryJpa;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.OutputUserStoryDTO;
import switch2021.project.dto.CreateUserStoryDTO;
import switch2021.project.dto.UserStoryIdDTO;
import switch2021.project.factoryInterface.IProjectIDFactory;
import switch2021.project.factoryInterface.IUserStoryFactory;
import switch2021.project.factoryInterface.IUserStoryIDFactory;
import switch2021.project.interfaces.IUserStoryRepo;
import switch2021.project.mapper.UserStoryMapper;
import switch2021.project.model.SystemUser.User;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.UsTitle;
import switch2021.project.model.valueObject.UserStoryID;

import java.util.ArrayList;
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
    private UserStoryMapper userStoryMapper;
    @Autowired
    private IUserStoryFactory iUserStoryFactory;
    @Autowired
    private IUserStoryIDFactory factoryId;

    /**
     * Create and save a User Story
     */
    public OutputUserStoryDTO createAndSaveUserStory(CreateUserStoryDTO inDto) throws Exception {
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

        String pId = x[0].replaceAll("Project_2022_", "");

        String uTitle = x[1].replaceAll("%20", " ");

        return factoryId.create(pId, uTitle);
    }
}
