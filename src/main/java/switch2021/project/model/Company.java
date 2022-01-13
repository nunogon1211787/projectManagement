package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company {

    /**
     * Atributos da Classe
     **/
    private TypologyStore typologyStore;
    SystemUserStore systemUserStore;
    UserProfileStore userProfileStore;
    ProjectStore projectStore;
    // falta colocar a chamada para o request quando se criar a página;
    List<Request> requestList;

    public  Company(){
        this.systemUserStore = new SystemUserStore();
        this.userProfileStore = new UserProfileStore();
        this.projectStore = new ProjectStore();
        this.typologyStore = new TypologyStore();

        this.userProfileStore.populateDefault();
        this.typologyStore.populateTypologyList();
    }


    //Project
    public ProjectStore getProjectStore() {
        return this.projectStore;
    }

    public SystemUserStore getSystemUserStore() {
        return this.systemUserStore;
    }

    //Profile
    public UserProfileStore getUserProfileStore() {
            return this.userProfileStore;
    }

    //Typology
    public TypologyStore getTypologyStore() {
        return this.typologyStore;
    }


    // Profile

    /**
     * Create Method
     **/

    public UserProfile createProfile(String name, String type) {
        return new UserProfile(name, type);
    }

    /**
     * Add Method
     **/

    public boolean addUserProfile(UserProfile profile) {

        if (!validateProfile(profile)) {
            return false;
        }
        userProfileStore.userProfileList.add(profile);
        return true;
    }

    /**
     * Getter Methods
     **/

    public List<UserProfile> getUserProfileList() {
        return this.getUserProfileList();
    }

    public List<UserProfile> getUserProfileListWithType(String type) {

        List<UserProfile> foundList = new ArrayList<>();

        for (int i = 0; i < this.userProfileStore.userProfileList.size(); i++) {

            if (this.userProfileStore.userProfileList.get(i).hasType(type)) {
                foundList.add(this.userProfileStore.userProfileList.get(i));
            }

        }

        return foundList;
    }

    ////Talvez mudar para não buscar por index
    public UserProfile getUserProfile(int index) {
        return new UserProfile(userProfileStore.userProfileList.get(index));
    }

    public UserProfile getUserProfile(String name) {
        UserProfile pro = null;
        for (int i = 0; i < userProfileStore.userProfileList.size(); i++) {
            if (Objects.equals(getUserProfile(i).getName(), name)) {
                pro = getUserProfile(i);
                break;
            }
        }
        return pro;
    }

    /**
     * Validation Method
     *
     * @param profile*/

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
        for (UserProfile up : userProfileStore.userProfileList) {
            if (up.equals(profile)) {
                return false;
            }
        }
        return true;
    }

    //Request

    /**
     * Add Method
     */

    public boolean addRequest(Request request) {
        this.requestList.add(request);
        return true;

    }

    /**
     * Validation Method
     */

    private boolean validateRequest(Request newRequest) {

        //Check if request already exist
        for (Request up : requestList) {
            if (up.equals(newRequest)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Save Method
     */

    public boolean saveRequest(Request newRequest) {

        boolean result = false;

        if (validateRequest(newRequest)) {
            addRequest(newRequest);
            result = true;
        }

        return result;

    }


}