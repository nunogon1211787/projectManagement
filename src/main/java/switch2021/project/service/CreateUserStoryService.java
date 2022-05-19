package switch2021.project.service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.datamodel.UserStoryJpa;
import switch2021.project.dto.OutputUserStoryDTO;
import switch2021.project.dto.CreateUserStoryDTO;
import switch2021.project.factoryInterface.IUserStoryFactory;
import switch2021.project.interfaces.IUserStoryRepo;
import switch2021.project.mapper.UserStoryMapper;
import switch2021.project.model.SystemUser.User;
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
    public OutputUserStoryDTO createAndSaveUserStory(CreateUserStoryDTO inDto) {
        UserStory newUserStory = iUserStoryFactory.createUserStory(inDto);


        //Must be checked which return the save method will have when the user story already exist.

        if(!iUserStoryRepo.save(newUserStory)){
            throw new IllegalArgumentException("User Story already exists");
        }

        return userStoryMapper.toDto(newUserStory);
    }

    public OutputUserStoryDTO createAndSaveUserStoryJPA(CreateUserStoryDTO inDto) throws Exception {
        UserStory newUserStory = iUserStoryFactory.createUserStory(inDto);


        //Must be checked which return the save method will have when the user story already exist.

//        if(!iUserStoryRepo.save(newUserStory)){
//            throw new IllegalArgumentException("User Story already exists");
//        }

        Optional<UserStory> usSaved = iUserStoryRepo.saveReeng(newUserStory);

        OutputUserStoryDTO usDto;

        if(usSaved.isPresent()) {
            usDto = userStoryMapper.toDto(usSaved.get());
        } else {
            throw new Exception("User story already exist.");
        }

        return usDto;
    }

}
