package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IDescriptionFactory;
import switch2021.project.factoryInterface.IUserProfileIDFactory;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.UserProfileID;

@Component
public class UserProfileIDFactory implements IUserProfileIDFactory {


    @Autowired
    private IDescriptionFactory descriptionFactory;


    @Override
    public UserProfileID createUserProfileID(String userProfileName) {
        Description description = descriptionFactory.createDescription(userProfileName);
        return new UserProfileID(description);
    }
}
