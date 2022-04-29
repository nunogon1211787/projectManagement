package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputUsDTO;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.factoryInterface.IUserStoryFactory;
import switch2021.project.interfaces.IRepoUserStory;
import switch2021.project.mapper.UserStoryMapper;
import switch2021.project.model.UserStory.UserStory;


@Service
public class CreateUserStoryService {

    /**
     * Attributes
     **/
    @Autowired
    private IRepoUserStory iRepoUserStory;
    @Autowired
    private UserStoryMapper userStoryMapper;
    @Autowired
    private IUserStoryFactory iUserStoryFactory;


    /**
     * Create and save a User Story
     *
     */
    public OutputUsDTO createAndSaveUserStory(UserStoryDTO dto) {

        UserStory newUserStory = iUserStoryFactory.createUserStory(dto.projectID, dto.userStoryID,
                dto.title, dto.priority, dto.description, dto.timeEstimate);

        iRepoUserStory.save(newUserStory);

        return userStoryMapper.toDto(newUserStory);
    }

}
