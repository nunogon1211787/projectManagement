package switch2021.project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.datamodel.ResourceIDJpa;
import switch2021.project.datamodel.ResourceJpa;
import switch2021.project.datamodel.UserJpa;
import switch2021.project.datamodel.assembler.UserJpaAssembler;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.model.SystemUser.User;
import switch2021.project.model.valueObject.SystemUserID;
import switch2021.project.model.valueObject.UserProfileID;
import switch2021.project.repositories.jpa.UserJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepo {

    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserJpaAssembler userJpaAssembler;

    @Override
    public Optional<User> findByUserId(SystemUserID systemUserID) {

        Optional<UserJpa> userJpa = userJpaRepository.findById(systemUserID);

        if(userJpa.isPresent()){
            UserJpa userJpa1 = userJpa.get();
            User user = userJpaAssembler.toDomain(userJpa1);
            return Optional.of(user);
        } else
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {

        List<UserJpa> userJpaList = userJpaRepository.findAll();
        List<User> userList = new ArrayList<>();

        userJpaList.forEach(userJpa -> userList.add(userJpaAssembler.toDomain(userJpa)));

        return userList;
    }

    @Override
    public boolean existsById(SystemUserID id) {
        return userJpaRepository.existsById(id);
    }

    @Override
    public Optional<User> save (User newUser) {

        UserJpa userJpa = userJpaAssembler.toData(newUser);
        Optional<User> user = Optional.empty();

        if(!userJpaRepository.existsById(userJpa.getEmail())){
            UserJpa save = userJpaRepository.save(userJpa);
            user = Optional.of(userJpaAssembler.toDomain(save));
        }
        return user;
    }

    @Override
    public boolean delete(SystemUserID systemUserID) {

        if(userJpaRepository.existsById(systemUserID)) {
            userJpaRepository.deleteById(systemUserID);
            return true;
        }
        return  false;
    }


    @Override
    public List<User> findAllBySystemUserIdContains(String id) {
        return null;
    }

    @Override
    public List<User> findAllByNameContains(String name) {
        return null;
    }

    @Override
    public List<User> findAllByFunctionContains(String function) {
        return null;
    }

    @Override
    public List<User> findAllByUserProfileId(UserProfileID profile) {
        return null;
    }

/*
    ///// ----->>>>>>  Rever Método
    public List<SystemUser> searchUsers(String name, String email, String function, int state, List<UserProfile> profileChoosenList) {
        int listSize = this.systemUserList.size();
        List<SystemUser> foundUsersList = new ArrayList<>();

        if (listSize != 0) {
            for (SystemUser systemUser : this.systemUserList)
                if (systemUser.hasThisData(name, email, function, state, profileChoosenList)) {
                    foundUsersList.add(systemUser);
                }
        }
        return foundUsersList;
    }

 */
}