package switch2021.project.entities.factories.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.CreateUserStoryDTO;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IDescriptionFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUsHourFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUsPriorityFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserStoryIDFactory;
import switch2021.project.entities.factories.factoryInterfaces.*;
import switch2021.project.entities.aggregates.UserStory.*;

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