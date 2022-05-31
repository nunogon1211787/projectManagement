package switch2021.project.entities.factories.factoryInterfaces;

import switch2021.project.dtoModel.dto.CreateUserStoryDTO;
import switch2021.project.entities.aggregates.UserStory.UserStory;


public interface IUserStoryFactory {

    UserStory createUserStory(CreateUserStoryDTO createUserStoryDTO);

}
