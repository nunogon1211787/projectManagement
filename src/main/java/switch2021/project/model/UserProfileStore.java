package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserProfileStore {

    List<UserProfile> userProfileList;

    public UserProfileStore () {
        userProfileList = new ArrayList<>();
    }

    public void populateDefault() {
        userProfileList.add(new UserProfile("Visitor", "System Profile"));
        userProfileList.add(new UserProfile("Administrator", "System Profile"));
        userProfileList.add(new UserProfile("Director", "System Profile"));
    }

    public void populateSystemUser(UserProfile profile) {
        userProfileList.add(profile);
    }

    /**
     * Create Method
     **/

    public UserProfile createProfile(String name, String type) {
        return new UserProfile(name, type);
    }

    /**
     * Add Method
     **/

    public boolean addProfile(UserProfile profile) {

        if (!validateProfile(profile)) {
            return false;
        }
        userProfileList.add(profile);
        return true;
    }

    /**
     * Getter Methods
     **/

    public List<UserProfile> getArrayProfile() {
        return this.userProfileList;
    }

    public List<UserProfile> getArrayProfileWithType(String type) {

        List<UserProfile> foundList = new ArrayList<>();

        for (int i = 0; i < this.userProfileList.size(); i++) {

            if (this.userProfileList.get(i).hasType(type)) {
                foundList.add(this.userProfileList.get(i));
            }

        }

        return foundList;
    }

    ////Talvez mudar para não buscar por index
    public UserProfile getProfile(int index) {
        return new UserProfile(userProfileList.get(index));
    }

    public UserProfile getProfile(String name) {
        UserProfile pro = null;
        for (int i = 0; i < userProfileList.size(); i++) {
            if (Objects.equals(getProfile(i).getName(), name)) {
                pro = getProfile(i);
                break;
            }
        }
        return pro;
    }

    /**
     * Validation Method
     **/

    private boolean validateProfile(UserProfile profile) {
        //Check empty fields on name and type
        if (profile.getName().trim().isEmpty() || profile.getType().trim().isEmpty()) {
            return false;
        }

        //Check if the profile type is valid
        if (!profile.getType().equalsIgnoreCase("System Profile") && !profile.getType().equalsIgnoreCase("Special Profile")) {
            return false;
        }

        //Check if profile already exist
        for (UserProfile up : userProfileList) {
            if (up.equals(profile)) {
                return false;
            }
        }
        return true;
    }



}
