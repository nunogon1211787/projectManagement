package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.factoryInterface.*;
import switch2021.project.model.UserStory.*;
import switch2021.project.model.valueObject.Description;

@Component
public class UserStoryFactory implements IUserStoryFactory {


//    @Qualifier("IUserStoryIDFactory")
@Autowired
    private IUserStoryIDFactory userStoryID;
    @Autowired
    private IUsPriorityFactory priority;
    @Autowired
    private IDescriptionFactory description;
    @Autowired
    private IUsHourFactory timeEstimate;


    public UserStoryFactory(IUserStoryIDFactory userStoryID, IUsPriorityFactory priority,
                            IDescriptionFactory description, IUsHourFactory timeEstimate) {
        this.userStoryID = userStoryID;
        this.priority = priority;
        this.description = description;
        this.timeEstimate = timeEstimate;
    }

    @Override
    public UserStory createUserStory(UserStoryDTO userStoryDTO) {
        return new UserStory(userStoryID.create(userStoryDTO.projectID, userStoryDTO.title),
                priority.create(userStoryDTO.priority),
                description.createDescription(userStoryDTO.description),
                timeEstimate.create(userStoryDTO.timeEstimate));
    }

}