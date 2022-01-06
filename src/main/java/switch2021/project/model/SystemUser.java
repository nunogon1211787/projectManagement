package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

public class SystemUser {

    /**
     * Attributes of systemUser´s class
     **/
    private String userName;
    private String email;
    private String photo;
    private String password;
    private String function;
    private boolean activateUser;
    private List<Profile> assignedProfileList;

    /**
     * Contructor without photo
     **/

    public SystemUser(String userName, String email, String function, String password,Profile profile) {
        this.userName = userName;
        this.email = email;
        this.photo = "";
        this.function = function;
        this.password = encryptPassword(password);
        this.activateUser = false;
        this.assignedProfileList = new ArrayList<>();
        assignedProfileList.add(profile);
    }

    /**
     * Contructor with photo
     **/
    public SystemUser(String userName, String email, String function, String photo, String password, Profile profile) {
        this.userName = userName;
        this.email = email;
        this.photo = photo;
        this.function = function;
        this.password = encryptPassword(password);
        this.activateUser = false;
        this.assignedProfileList = new ArrayList<>();
        this.assignedProfileList.add(profile);
    }

    /**
     * Copy Constructor. Para criar um novo objeto, igual ao parâmetro, mas sem levar adiante as referências do objeto original.
     */
    public SystemUser(SystemUser originalUser) {
        this.userName = originalUser.userName;
        this.email = originalUser.email;
        this.photo = originalUser.photo;
        this.function = originalUser.function;
        this.password = originalUser.password;
        this.activateUser = originalUser.activateUser;
        this.assignedProfileList = deepCopyListProfile(originalUser.assignedProfileList);

    }
    //para encriptar (Nuno)
    public String encryptPassword(String password) {
        int codigoASCII;
        String result = "";

        for (int i = 0; i < password.length(); i++) {
            codigoASCII = password.charAt(i) + 99;
            result += (char) codigoASCII;
        }

        return result;
    }
    //para desencriptar (Nuno)
    public String decryptPassword(String password) {
        int codigoASCII;
        String result = "";

        for (int i = 0; i < password.length(); i++) {
            codigoASCII = password.charAt(i) - 99;
            result += (char) codigoASCII;
        }

        return result;
    }

    /**
     * Getting and Setting Methods
     **/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFunction() {
        return this.function;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }


    public String getActivateUser() { //alguém está a usar??? (ver isUserActivated())

        String status = "inativo";

        if (this.activateUser) {
            status = "ativo";
        }

        return status;
    }

    public boolean isUserActivated() {
        return this.activateUser;
    }

    public List<Profile> getAssignedProfileList() {
        return this.assignedProfileList;
    }


    public boolean activateUser() {
        return true;
    }

    public String getAssignedProfile(int idx) {
        return assignedProfileList.get(idx).getName();
    }

    /**
     * Método para validar se o email (ou parte dele) é deste objeto.
     */
    public boolean isYourEmail(String email) {

        boolean result = false;
        int idxString = this.email.indexOf(email.toLowerCase());

        if (idxString != -1) {
            result = true;
        }

        return result;

    }

    /**
     * Método para verificar se o profile recebido (by ID) está associado (tem na lista) ao objeto.
     */
    public boolean isYourProfile(int id) {

        boolean valid = false;

        for (int i = 0; i < this.assignedProfileList.size(); i++) {

            if (this.assignedProfileList.get(i).isValidId(id)) {
                valid = true;
                break;
            }

        }

        return valid;
    }

    /**
     * Método para verificar se os parâmetros recebidos são do objeto.
     */

    public boolean hasThisData(String userName, String email, String function, int isActive, int [] profilesId){

        boolean result = true;

        if(!userName.isEmpty()){
            int idxString = this.userName.toLowerCase().indexOf(userName.toLowerCase());
            if(idxString == -1){ result = false;}
        }

        if(!email.isEmpty()){
            int idxString = this.email.toLowerCase().indexOf(email.toLowerCase());
            if(idxString == -1){ result = false;}
        }

        if(!function.isEmpty()){
            int idxString = this.function.toLowerCase().indexOf(function.toLowerCase());
            if(idxString == -1){ result = false;}
        }

        if(isActive != -1){
            if(isActive == 0){
                if(this.activateUser) {result = false;}
            } else if (isActive == 1) {
                if(!this.activateUser) {result = false;}
            }
        }

        if(profilesId.length != 0){

            if(this.assignedProfileList.size() == 0) { result = false; } else {

                int count = 0;

                for (int k : profilesId) {
                    for (Profile profile : this.assignedProfileList) {
                        if (profile.isValidId(k)) {
                            count++;
                            break;
                        }
                    }
                }

                if(count != profilesId.length) { result = false;}

            }
        }

        return result;
    }

    /**
     * Método para criar uma Deep Copy da lista de profiles do user.
     */

    private List<Profile> deepCopyListProfile(List<Profile> originalList) {

        List<Profile> deepCopyList = new ArrayList<>();

        for (int i = 0; i < originalList.size(); i++) {

            deepCopyList.add(new Profile(originalList.get(i)));

        }

        return deepCopyList;
    }

    /**
     * Method to update old password with the new password
     */

    public boolean updatePassword (String oldpasswordUI, String newpassword) {


        if (validateOldPassword(oldpasswordUI) == true) {
            setNewPassword(newpassword);
        }
        return true;

    }

    /**
     * Method to compare the oldpassword from de UI (oldpasswordUI) and the oldpassword saved in System User (oldpasswordSU)
     */

    private boolean validateOldPassword(String oldpasswordUI) {

        String oldpasswordSU = getPassword();

        if (oldpasswordUI.equals(oldpasswordSU)) {
            return true;
        }
        return false;
    }

    /**
     * Method to set the new password
     */

    private void setNewPassword(String newpassword) {
        this.password = newpassword;
    }

    /*
    /**
     * Method to update systemuser data in a systemuser


    public boolean

     */



}
