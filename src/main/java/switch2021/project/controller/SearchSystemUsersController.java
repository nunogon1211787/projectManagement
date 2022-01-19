package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.model.UserProfile;
import switch2021.project.model.UserProfileStore;
import switch2021.project.utils.App;

import java.util.ArrayList;
import java.util.List;

public class SearchSystemUsersController {

    private Company company;
    private List<UserProfile> userProfileList;

    /**
     * Construtor da Classe
     */
    public SearchSystemUsersController() { this.company = App.getInstance().getCompany();}

    /**
     * Método Get.
     */
    public List<UserProfile> getUserProfileList(){

        this.userProfileList = company.getUserProfileStore().getUserProfileList();

        return this.userProfileList;

    }

    /**
     * Método para procurar um ou mais Users no domínio.
     */
    public List<SystemUser> searchUsers (String name, String email, String function, int state, String[] profileChoosenNameList){

        List<UserProfile> profileChoosenList = new ArrayList<>();

        for (int i = 0; i < profileChoosenNameList.length; i++) {
            profileChoosenList.add(this.company.getUserProfileStore().getProfileByName(profileChoosenNameList[i]));
        }


        return company.getSystemUserStore().searchUsers(name, email, function, state, profileChoosenList);

    }



}
