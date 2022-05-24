package switch2021.project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

    /**
     * Class Attributes
     */
    private List<User> userList;

    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserJpaAssembler userJpaAssembler;

    /**
     * Constructor
     */
    public UserRepository() {
        this.userList = new ArrayList<>();
    }


    @Override
    public Optional<User> findUserById(SystemUserID systemUserID) {

        Optional<UserJpa> userJpa = userJpaRepository.findById(systemUserID);

        Optional<User> user = Optional.empty();

        if(userJpa.isPresent()){
            user = Optional.of(userJpaAssembler.toDomain(userJpa.get()));
        }
        return user;
    }

    @Override
    public List<User> findAllSystemUsers() {

        List<UserJpa> userJpaList = userJpaRepository.findAll();
        List<User> userList = new ArrayList<>();

        userJpaList.forEach(userJpa -> userList.add(userJpaAssembler.toDomain(userJpa)));

        return userList;
    }

    /**
     * Save Method
     */
    @Override
    public boolean save(User user) {
        if (user == null || existsByEmail(user.getSystemUserId().getEmail().getEmailText())) {
            return false;
        } else {
            return this.userList.add(user);
        }
    }

    public Optional<User> saveReeng (User newUser) {

        UserJpa userJpa = userJpaAssembler.toData(newUser);
        Optional<User> user = Optional.empty();

        if(!userJpaRepository.existsById(userJpa.getEmail())){
            UserJpa userSaved = userJpaRepository.save(userJpa);
            user = Optional.of(userJpaAssembler.toDomain(userSaved));
        }
        return  user;
    }

    @Override
    public boolean existsByEmail(String newUserEmail) {
        boolean result = false;

        for (User newUser : this.userList) {
            if (newUser.getSystemUserId().getEmail().getEmailText().trim().equalsIgnoreCase(newUserEmail.trim())) {
                result = true;
                break;
            }
        }
        return result;
    }


    @Override
    public boolean deleteUser (SystemUserID systemUserID) {

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