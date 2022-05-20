package switch2021.project.datamodel.assembler;

import org.springframework.stereotype.Service;
import switch2021.project.datamodel.UserProfileJpa;
import switch2021.project.model.UserProfile.UserProfile;

@Service
public class UserProfileJpaAssembler {

    public UserProfileJpa toData (UserProfile userProfile) {
        return new UserProfileJpa(userProfile.getUserProfileId());
    }

    public UserProfile toDomain (UserProfileJpa userProfileJPA) {
        return new UserProfile(userProfileJPA.getUserProfileID());
    }
}
