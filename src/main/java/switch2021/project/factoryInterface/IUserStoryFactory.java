package switch2021.project.factoryInterface;

import switch2021.project.dto.UserStoryDTO;
import switch2021.project.model.UserStory.UserStory;


public interface IUserStoryFactory {

    UserStory createUserStory(UserStoryDTO userStoryDTO);

}
