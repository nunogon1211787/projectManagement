package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserProfileStore {

    /**
     * Atributes
     **/

    List<UserProfile> userProfileList;

    /**
     * Constructor
     **/

    public UserProfileStore() {
        userProfileList = new ArrayList<>();
    }

    public void populateDefault() {
        userProfileList.add(new UserProfile("Visitor"));
        userProfileList.add(new UserProfile("Administrator"));
        userProfileList.add(new UserProfile("Director"));
    }

    /**
     * Create Method
     **/

    public UserProfile createProfile(String name) {
        return new UserProfile(name);
    }

    /**
     * Add Method
     **/

    public void addUserProfile(UserProfile profile) {

        if (!validateProfile(profile)) {
            throw new IllegalArgumentException("Repeated user profile name inserted.");
        }
        userProfileList.add(profile);
    }

    /**
     * save Method
     **/

    public void saveUserProfile(UserProfile profile) {

        if (!validateProfile(profile)) {
            throw new IllegalArgumentException("Repeated user profile name inserted.");
        }
        userProfileList.add(profile);
    }

    /**
     * Getter Methods
     **/

    public List<UserProfile> getUserProfileList() {
        return this.userProfileList;
    }

    /*public List<UserProfile> getUserProfileListWithType(String type) {

        List<UserProfile> foundList = new ArrayList<>();

        for (UserProfile userProfile : this.userProfileList) {

            if (userProfile.hasType(type)) {
                foundList.add(userProfile);
            }

        }

        return foundList;
    }*/

    ////Talvez mudar para não buscar por index
    /*public UserProfile getProfileByName(int index) {
        return new UserProfile(userProfileList.get(index));
    }

    public UserProfile getProfileByName(String name) {
        UserProfile pro = null;
        for (int i = 0; i < userProfileList.size(); i++) {
            if (Objects.equals(getProfileByName(i).getName(), name)) {
                pro = getProfileByName(i);
                break;
            }
        }
        return pro;
    }*/
    public UserProfile getProfileByName(String profileName) {
        int result = -1;

        for (int i = 0; i < userProfileList.size(); i++) {
            if (userProfileList.get(i).getName().equals(profileName)) {
                result = i;
            }
        }
        return userProfileList.get(result);
    }

    /**
     * Validation Method
     **/


    private boolean validateProfile(UserProfile profile) {
        //Check empty fields on name
        if (profile.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }

        //Check if profile already exist
        for (UserProfile up : userProfileList) {
            if (up.equals(profile)) {
                throw new IllegalArgumentException("Repeated user profile name inserted.");
            }
        }
        return true;
    }


    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserProfileStore)) return false;
        UserProfileStore that = (UserProfileStore) obj;
        return
                (this.userProfileList.equals(that.getUserProfileList()));
    }
}
