package switch2021.project.datamodel.assembler;

import org.springframework.stereotype.Component;
import switch2021.project.datamodel.UserJpa;
import switch2021.project.model.SystemUser.User;
import switch2021.project.model.valueObject.*;
import java.util.List;

@Component
public class UserJpaAssembler {

    public UserJpa toData(User user) {

        String email = user.getSystemUserId().getEmail().getEmailText();
        String userName = user.getUserName().getText();
        String photo = user.getPhoto().getExtension();
        String function = user.getFunction().getText();
        String password = user.getEncryptedPassword().getText();
        String isActive = user.isActive() ? "True" : "False";
        List<UserProfileID> userProfileID = user.getAssignedProfiles();
        List<Request> requestList = user.getRequestedProfiles();


        return new UserJpa(email, userName, function, photo, password, isActive, userProfileID, requestList);
    }

    public User toDomain (UserJpa userJpa) {

        SystemUserID systemUserID = new SystemUserID(new Email(userJpa.getEmail()));
        Name userName = new Name(userJpa.getUserName());
        Photo photo = new Photo(userJpa.getPhoto());
        Function function = new Function(userJpa.getFunction());
        Description password = new Description(userJpa.getPassword());
        List<UserProfileID> userProfileID = userJpa.getAssignedIDProfiles();
        List<Request> requestList = userJpa.getRequestList();
        boolean isActive = userJpa.getIsActive().contains("True");

        return new User(systemUserID, userName, photo, password, function, isActive, userProfileID, requestList);

    }

}


