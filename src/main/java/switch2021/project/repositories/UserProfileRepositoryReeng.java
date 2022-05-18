package switch2021.project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.datamodel.UserProfileJpa;
import switch2021.project.datamodel.assembler.UserProfileDomainDataAssembler;
import switch2021.project.interfaces.IUserProfileRepo;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.UserProfileID;
import switch2021.project.repositories.jpa.UserProfileJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserProfileRepositoryReeng implements IUserProfileRepo {

    @Autowired
    UserProfileJpaRepository userProfileJpaRepository;

    @Autowired
    UserProfileDomainDataAssembler assembler;


    public UserProfile findByUserProfileID (String userProfileName) {
        UserProfileID userProfileID = new UserProfileID(new Description(userProfileName));
        Optional <UserProfileJpa> userProfileJpa = userProfileJpaRepository.findById(userProfileID);
        UserProfile userProfile = null;

        if (userProfileJpa.isPresent()) {
            userProfile = assembler.toDomain(userProfileJpa.get());
        }
        return userProfile;
    }


    public List<UserProfile> findAllUserProfiles() {
        List<UserProfileJpa> userProfileJpaList = userProfileJpaRepository.findAll();
        List<UserProfile> userProfileList = new ArrayList<>();

        for (UserProfileJpa userProfileJpa: userProfileJpaList) {
            userProfileList.add(assembler.toDomain(userProfileJpa));
        }
        return userProfileList;
    }


    public boolean save (UserProfile profile) {
        UserProfileJpa userProfileJpa = assembler.toData(profile);
        UserProfileJpa saved = userProfileJpaRepository.save(userProfileJpa);
        if (saved != null) {
            return true;
        }
        return false;
    }


    public boolean existsByDescription(String userProfileName) {
        UserProfileID userProfileID = new UserProfileID(new Description(userProfileName));
        return userProfileJpaRepository.existsById(userProfileID);
    }


    @Override
    public boolean existsByUserProfileId(UserProfileID userProfileID) {
        return userProfileJpaRepository.existsById(userProfileID);
    }

    public void deleteById (String userProfileName) {
        UserProfileID userProfileID = new UserProfileID(new Description(userProfileName));
        userProfileJpaRepository.deleteById(userProfileID);
    }
}


