package switch2021.project.repositories;

import org.springframework.stereotype.Repository;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.model.SystemUser.User;
import switch2021.project.model.valueObject.UserProfileID;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IUserRepo {

    /**
     * Class Attributes
     */
    private final List<User> userList;

    /**
     * Constructor
     */
    public UserRepository() {
        this.userList = new ArrayList<>();
    }

    @Override
    public User findByUserID(String email) {
        User user = null;

        for (User i : this.userList) {
            if (i.isYourEmail(email)) {
                user = i;
                break;
            }
        }
        return user;
    }

    @Override
    public List<User> findAllSystemUsers() {
        return new ArrayList<>(this.userList);
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