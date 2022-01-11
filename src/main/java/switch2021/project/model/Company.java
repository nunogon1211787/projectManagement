package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

public class Company {

    /**
     * Atributos da Classe
     **/
    private TypologyStore typologyStore = new TypologyStore();
    SystemUserStore userStore;
    UserProfileStore userProfileStore;
    ProjectStore userProjectStore;
    // falta colocar a chamada para o request quando se criar a página;
    List<Request> arrayRequest;

    public  Company(){
        this.userStore = getSystemUserStore();
        this.userProfileStore = getUserProfileStore();
        this.userProjectStore = getProjectStore();

        this.userProfileStore.populateDefault();
    }


    //Project
    public ProjectStore getProjectStore() {
        return new ProjectStore();
    }

    public SystemUserStore getSystemUserStore() {
        return new SystemUserStore();
    }

    //Profile
    public UserProfileStore getUserProfileStore() {
            return new UserProfileStore();
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

    public boolean addProfile(Profile profile) {

        if (!validateProfile(profile)) {
            return false;
        }
        arrayProfile.add(profile);
        return true;
    }

    /**
     * Getter Methods
     **/

    public List<Profile> getArrayProfile() {
        return this.arrayProfile;
    }

    public List<Profile> getArrayProfileWithType(String type) {

        List<Profile> foundList = new ArrayList<>();

        for (int i = 0; i < this.arrayProfile.size(); i++) {

            if (this.arrayProfile.get(i).hasType(type)) {
                foundList.add(this.arrayProfile.get(i));
            }

        }

        return foundList;
    }

    ////Talvez mudar para não buscar por index
    public Profile getProfile(int index) {
        return new Profile(arrayProfile.get(index));
    }

    public Profile getProfile(String name) {
        Profile pro = null;
        for (int i = 0; i < arrayProfile.size(); i++) {
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

    private boolean validateProfile(Profile profile) {
        //Check empty fields on name and type
        if (profile.getName().trim().isEmpty() || profile.getType().trim().isEmpty()) {
            return false;
        }

        //Check if the profile type is valid
        if (!profile.getType().equalsIgnoreCase("System Profile") && !profile.getType().equalsIgnoreCase("Special Profile")) {
            return false;
        }

        //Check if profile already exist
        for (Profile up : arrayProfile) {
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
        this.arrayRequest.add(request);
        return true;

    }

    /**
     * Validation Method
     */

    private boolean validateRequest(Request newRequest) {

        //Check if request already exist
        for (Request up : arrayRequest) {
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