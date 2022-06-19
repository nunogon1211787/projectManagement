package switch2021.project.dataModel.REST.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dataModel.REST.UserProfileRestDTO;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.valueObjects.voFactories.voFactories.UserProfileIDFactory;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserProfileDomainDataRestAssembler {

    @Autowired
    UserProfileIDFactory userProfileIDFactory;


    public UserProfile toDomain(UserProfileRestDTO userProfileRestDTO) {

        UserProfileID userProfileID = userProfileIDFactory.createUserProfileID(userProfileRestDTO.getDescription());

        return new UserProfile(userProfileID);
    }

//    public UserProfileRestDTO toData(UserProfile userProfile) {
//        return new UserProfileRestDTO(userProfile.getUserProfileId().getUserProfileName().getText());
//    }
//
//    public CollectionModel<UserProfileRestDTO> toCollections(List<UserProfile> userProfiles) {
//
//        CollectionModel<UserProfileRestDTO> result = CollectionModel.of(userProfiles.stream()
//                .map(this::toData)
//                .collect(Collectors.toList()));
//
//        return result;
//    }

    public List<UserProfile> toCollections(List<UserProfileRestDTO> userProfileRestDTO) {

        List<UserProfile> newList = new ArrayList<>();

        userProfileRestDTO.forEach(uProfile -> newList.add(toDomain(uProfile)));

        return newList;
    }

}
