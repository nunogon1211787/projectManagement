package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.factoryInterface.IUserStoryFactory;
import switch2021.project.factoryInterface.VOFactoryInterface;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.UserStory.*;
import switch2021.project.model.valueObject.Description;

@Component
public class UserStoryFactory implements IUserStoryFactory {


    @Autowired
    private VOFactoryInterface<UserStoryID> userStoryID;
    @Autowired
    private IValueObjectsFactory<UsPriority> priority;
    @Autowired
    private IValueObjectsFactory<Description> description;
    @Autowired
    private IValueObjectsFactory<UsHour> timeEstimate;


    public UserStoryFactory(VOFactoryInterface<UserStoryID> userStoryID, IValueObjectsFactory<UsPriority> priority,
                            IValueObjectsFactory<Description> description, IValueObjectsFactory<UsHour> timeEstimate) {
        this.userStoryID = userStoryID;
        this.priority = priority;
        this.description = description;
        this.timeEstimate = timeEstimate;
    }

    @Override
    public UserStory createUserStory(UserStoryDTO userStoryDTO) {
        UserStory newUserStory = new UserStory(userStoryID.create(userStoryDTO.projectID, userStoryDTO.title));

        newUserStory.setPriority(priority.create(userStoryDTO.priority));
        newUserStory.setDescription(description.create(userStoryDTO.description));
        newUserStory.setTimeEstimate(timeEstimate.create(timeEstimate));

        return newUserStory;
    }

}