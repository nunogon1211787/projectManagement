package switch2021.project.controller;

import lombok.Getter;
import switch2021.project.model.*;
import switch2021.project.stores.*;
import switch2021.project.utils.App;
import java.util.ArrayList;
import java.util.List;

@Getter
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
            profileChoosenList.add(this.company.getUserProfileStore().getUserProfile(profileChoosenNameList[i]));
        }


        return company.getSystemUserStore().searchUsers(name, email, function, state, profileChoosenList);

    }



}
