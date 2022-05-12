package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutPutUsDTO;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.factoryInterface.IUserStoryFactory;
import switch2021.project.interfaces.IUserStoryRepo;
import switch2021.project.mapper.UserStoryMapper;
import switch2021.project.model.UserStory.UserStory;

import java.util.Optional;

@Service
public class CreateUserStoryService {

    /**
     * Attributes
     **/
    @Autowired
    private IUserStoryRepo iUserStoryRepo;
    @Autowired
    private UserStoryMapper userStoryMapper;
    @Autowired
    private IUserStoryFactory iUserStoryFactory;

    /**
     * Create and save a User Story
     */
    public OutPutUsDTO createAndSaveUserStory(UserStoryDTO userStoryDTO) {
        UserStory newUserStory = iUserStoryFactory.createUserStory(userStoryDTO);
        if(!iUserStoryRepo.save(newUserStory)){
            throw new IllegalArgumentException("User Story already exists");
        }
        return userStoryMapper.toDto(newUserStory);
    }

}
