package switch2021.project.factoryInterface;

import switch2021.project.dto.CreateUserStoryDTO;
import switch2021.project.model.UserStory.UserStory;


public interface IUserStoryFactory {

    UserStory createUserStory(CreateUserStoryDTO createUserStoryDTO);

}
