package switch2021.project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.datamodel.UserProfileJpa;
import switch2021.project.datamodel.assembler.UserProfileJpaAssembler;
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
    UserProfileJpaAssembler assembler;


    public Optional<UserProfile> findByUserProfileID (UserProfileID userProfileID) {

        Optional <UserProfileJpa> userProfileJpa = userProfileJpaRepository.findById(userProfileID);
        Optional<UserProfile> userProfile = Optional.empty();

        if (userProfileJpa.isPresent()) {
            userProfile = Optional.of(assembler.toDomain(userProfileJpa.get()));
        }
        return userProfile;
    }


    public List<UserProfile> findAll() {
        List<UserProfileJpa> userProfileJpaList = userProfileJpaRepository.findAll();
        List<UserProfile> userProfileList = new ArrayList<>();

        for (UserProfileJpa userProfileJpa: userProfileJpaList) {
            userProfileList.add(assembler.toDomain(userProfileJpa));
        }
        return userProfileList;
    }


    public Optional<UserProfile> save (UserProfile profile) {
        Optional<UserProfile> result = Optional.empty();

        if(!userProfileJpaRepository.existsById(profile.getUserProfileId())) {
            UserProfileJpa userProfileJpa = assembler.toData(profile);
            UserProfileJpa saved = userProfileJpaRepository.save(userProfileJpa);
            result = Optional.of(assembler.toDomain(saved));
        }

        return result;
    }


    public boolean existsByUserProfileId(UserProfileID userProfileID) {
        return userProfileJpaRepository.existsById(userProfileID);
    }

    public boolean deleteById (UserProfileID userProfileID) {

        if(userProfileJpaRepository.existsById(userProfileID)) {
            userProfileJpaRepository.deleteById(userProfileID);
            return true;
        }

        return false;
    }
}


