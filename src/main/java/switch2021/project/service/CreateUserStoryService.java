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
    private IRepoUserStory IRepoUserStory;
    @Autowired
    private UserStoryMapper userStoryMapper;
    @Autowired
    private IUserStoryFactory IUserStoryFactory;


    /**
     * Constructor
     **/

    public CreateUserStoryService() {

    }


    /**
     * Create and save a User Story
     *
     * @param dto
     */
    public OutputUsDTO createAndSaveUserStory(UserStoryDTO dto) {

        UserStory newUserStory = IUserStoryFactory.createUserStory(dto.getProjectId(), dto.getUserStoryId(),
                dto.getTitle(), dto.getPriority(), dto.getDescription(), dto.getTimeEstimate());

        IRepoUserStory.save(newUserStory);

        return userStoryMapper.toDto(newUserStory);
    }

    /**
     * Finds a user story using given ID.
     *
     * @param userStoryID id
     * @return userStoryDTO if found, else {@code null}
     */

    //TODO -----> Manter método aqui?
    public OutputUsDTO findUserStoryById(String userStoryID) {
        UserStory userStoryById = this.IRepoUserStory.findUserStoryById(userStoryID);
        return this.userStoryMapper.toDto(userStoryById);
    }

}
