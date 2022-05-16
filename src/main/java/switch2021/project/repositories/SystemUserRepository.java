package switch2021.project.repositories;

import org.springframework.stereotype.Repository;
import switch2021.project.interfaces.ISystemUserRepo;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.UserProfileID;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SystemUserRepository implements ISystemUserRepo {

    /**
     * Class Attributes
     */
    private final List<SystemUser> systemUserList;

    /**
     * Constructor
     */
    public SystemUserRepository() {
        this.systemUserList = new ArrayList<>();
    }

    @Override
    public SystemUser findSystemUserByEmail(String email) {
        SystemUser user = null;

        for (SystemUser i : this.systemUserList) {
            if (i.isYourEmail(email)) {
                user = i;
                break;
            }
        }
        return user;
    }

    @Override
    public List<SystemUser> findAllSystemUsers() {
        return new ArrayList<>(this.systemUserList);
    }

    /**
     * Save Method
     */
    @Override
    public boolean saveSystemUser(SystemUser user) {
        if (user == null || existsByEmail(user.getSystemUserId().getEmail().getEmailText())) {
            return false;
        } else {
            return this.systemUserList.add(user);
        }
    }

    @Override
    public boolean existsByEmail(String newUserEmail) {
        boolean result = false;

        for (SystemUser newUser : this.systemUserList) {
            if (newUser.getSystemUserId().getEmail().getEmailText().trim().equalsIgnoreCase(newUserEmail.trim())) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public List<SystemUser> findAllBySystemUserIdContains(String id) {
        return null;
    }

    @Override
    public List<SystemUser> findAllByNameContains(String name) {
        return null;
    }

    @Override
    public List<SystemUser> findAllByFunctionContains(String function) {
        return null;
    }

    @Override
    public List<SystemUser> findAllByUserProfileId(UserProfileID profile) {
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