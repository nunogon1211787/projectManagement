package switch2021.project.datamodel.assembler;

import org.springframework.stereotype.Component;
import switch2021.project.datamodel.RequestJpa;
import switch2021.project.datamodel.UserJpa;
import switch2021.project.model.SystemUser.User;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;
import java.util.List;

@Component
public class UserJpaAssembler {

    public UserJpa toData(User user) {

        SystemUserID systemUserId = user.getSystemUserId();
        String userName = user.getUserName().getText();
        String photo = user.getPhoto().getExtension();
        String function = user.getFunction().getText();
        String password = user.getEncryptedPassword().getText();
        String isActive = user.isActive() ? "True" : "False";
        List<UserProfileID> userProfileIDList = user.getAssignedProfiles();

        UserJpa userJpa =  new UserJpa(systemUserId, userName, function, photo, password, isActive, userProfileIDList);

        List<Request> requestList = user.getRequestedProfiles();
        for (Request request : requestList) {
            String requestDate = null;
            if (!(request.getRequestDate() == null)) {
                requestDate = request.getRequestDate().toString();
            }
            RequestJpa requestJpa = new RequestJpa(requestDate, request.getProfileIdRequested(), userJpa);
            userJpa.getRequests().add(requestJpa);
        }
        return userJpa;
    }

    public User toDomain (UserJpa userJpa) {

        SystemUserID systemUserID = userJpa.getEmail();
        Name userName = new Name(userJpa.getUserName());
        Photo photo = new Photo(userJpa.getPhoto());
        Function function = new Function(userJpa.getFunction());
        Description password = new Description(userJpa.getPassword());
        boolean isActive = userJpa.getIsActive().contains("True");
        List<UserProfileID> userProfileIDList = userJpa.getAssignedIDProfiles();

        User user =  new User(systemUserID, userName, photo, password, function, isActive, userProfileIDList);

        List<RequestJpa> requestJpaList = userJpa.getRequests();
        for (RequestJpa requestJpa : requestJpaList) {
            LocalDate requestDate = null;
            if (!(requestJpa.getRequestDate() == null)) {
                requestDate = LocalDate.parse(requestJpa.getRequestDate());
            }
            Request request = new Request(requestDate, requestJpa.getProfileIdRequested());
            user.getRequestedProfiles().add(request);
        }
        return user;
    }
}


