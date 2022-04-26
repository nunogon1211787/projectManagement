package switch2021.project.model.UserStory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputUsDTO;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.factoryInterface.IUserStoryFactory;
import switch2021.project.interfaces.UserStoryRepositoryInterface;
import switch2021.project.mapper.UserStoryMapper;


@Service
public class UserStoryService {

    /**
     * Attributes
     **/
    @Autowired
    private UserStoryRepositoryInterface userStoryRepositoryInterface;
    @Autowired
    private UserStoryMapper userStoryMapper;
    @Autowired
    private IUserStoryFactory IUserStoryFactory;


    /**
     * Constructor
     **/
    //TODO -----> Como colocar o construtor sem parametros? - no teste tenho a factory a null nao passa :S

    public UserStoryService(UserStoryRepositoryInterface userStoryRepositoryInterface,
                            UserStoryMapper userStoryMapper, IUserStoryFactory IUserStoryFactory) {
        this.userStoryRepositoryInterface = userStoryRepositoryInterface;
        this.userStoryMapper = userStoryMapper;
        this.IUserStoryFactory = IUserStoryFactory;
    }


    /**
     * Create and save a User Story
     *
     * @param dto
     */
    public OutputUsDTO createAndSaveUserStory(UserStoryDTO dto) {

        UserStory newUserStory = IUserStoryFactory.createUserStory(dto.getProjectId(), dto.getUserStoryId(),
                dto.getTitle(), dto.getPriority(), dto.getDescription(), dto.getTimeEstimate());

        userStoryRepositoryInterface.save(newUserStory);

        return userStoryMapper.toDto(newUserStory);
    }

    /**
     * Finds a user story using given ID.
     *
     * @param userStoryID id
     * @return userStoryDTO if found, else {@code null}
     */

    public OutputUsDTO findUserStoryById(String userStoryID) {
        UserStory userStoryById = this.userStoryRepositoryInterface.findUserStoryById(userStoryID);
        return this.userStoryMapper.toDto(userStoryById);
    }

}
