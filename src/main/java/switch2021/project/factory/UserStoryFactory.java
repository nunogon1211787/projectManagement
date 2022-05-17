package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dto.CreateUserStoryDTO;
import switch2021.project.factoryInterface.*;
import switch2021.project.model.UserStory.*;

@Component
public class UserStoryFactory implements IUserStoryFactory {


    @Autowired
    private IUserStoryIDFactory userStoryID;
    @Autowired
    private IUsPriorityFactory priority;
    @Autowired
    private IDescriptionFactory description;
    @Autowired
    private IUsHourFactory timeEstimate;



    @Override
    public UserStory createUserStory(CreateUserStoryDTO createUserStoryDTO) {
        return new UserStory(userStoryID.create(createUserStoryDTO.projectID, createUserStoryDTO.title),
                priority.create(createUserStoryDTO.priority),
                description.createDescription(createUserStoryDTO.description),
                timeEstimate.create(createUserStoryDTO.timeEstimate));
    }

}