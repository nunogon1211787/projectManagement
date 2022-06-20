package switch2021.project.interfaceAdapters.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.applicationServices.iRepositories.IUserProfileWebRepository;
import switch2021.project.dataModel.REST.UserProfileRestDTO;
import switch2021.project.dataModel.REST.assemblers.UserProfileDomainDataRestAssembler;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.interfaceAdapters.repositories.REST.UserProfileRestRepository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserProfileWebRepository implements IUserProfileWebRepository {

    @Autowired
    UserProfileRestRepository userProfileRestRepository;

    @Autowired
    UserProfileDomainDataRestAssembler userProfileDomainDataRestAssembler;

    public List<UserProfile> findAll() {

        List<UserProfileRestDTO> userProfileRestDTO = userProfileRestRepository.findAll();

        if(userProfileRestDTO.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<UserProfile> userProfiles = userProfileDomainDataRestAssembler.toCollections(userProfileRestDTO);
            return userProfiles;
        }
    }
}
