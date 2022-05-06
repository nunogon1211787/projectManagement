package switch2021.project.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutPutUsDTO;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.factoryInterface.IUserStoryFactory;
import switch2021.project.interfaces.IUserStoryRepo;
import switch2021.project.mapper.UserStoryMapper;
import switch2021.project.model.UserStory.UserStory;


@Service
@NoArgsConstructor
public class CreateUserStoryService {

    /**
     * Attributes
     **/
    //@Autowired
    private IUserStoryRepo iUserStoryRepo;
    @Autowired
    private UserStoryMapper userStoryMapper;
    @Autowired
    private IUserStoryFactory iUserStoryFactory;

    public CreateUserStoryService(IUserStoryRepo iUserStoryRepo, UserStoryMapper userStoryMapper,
                                  IUserStoryFactory iUserStoryFactory) {
        this.iUserStoryRepo = iUserStoryRepo;
        this.userStoryMapper = userStoryMapper;
        this.iUserStoryFactory = iUserStoryFactory;
    }

    /**
     * Create and save a User Story
     */
    public OutPutUsDTO createAndSaveUserStory(UserStoryDTO userStoryDTO) {

        UserStory newUserStory = iUserStoryFactory.createUserStory(userStoryDTO);

        iUserStoryRepo.save(newUserStory);

        return userStoryMapper.toDto(newUserStory);
    }

}
