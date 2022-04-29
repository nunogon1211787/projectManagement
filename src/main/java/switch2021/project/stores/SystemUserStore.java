package switch2021.project.stores;

import org.springframework.stereotype.Repository;
import switch2021.project.interfaces.ISystemUserRepo;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.UserProfile.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class SystemUserStore implements ISystemUserRepo {

    /**
     * Class Attributes
     */
    private final List<SystemUser> systemUserList;

    /**
     * Constructor
     */
    public SystemUserStore() {
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
        for (SystemUser newUser : this.systemUserList) {
            if (newUser.getSystemUserId().getEmail().getEmailText().trim().equalsIgnoreCase(newUserEmail.trim())) {
                return true;
            }
        }
        return false;
    }

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

    /**
     * Override
     **/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SystemUserStore)) return false;
        SystemUserStore that = (SystemUserStore) obj;
        return (this.systemUserList.equals(that.systemUserList));
    }

    @Override
    public int hashCode() {
        return Objects.hash(systemUserList);
    }
}