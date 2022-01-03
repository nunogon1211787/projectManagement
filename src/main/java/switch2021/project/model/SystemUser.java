package switch2021.project.model;

import java.util.ArrayList;

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
    ArrayList<Profile> assignedProfileList;

    /**
     * Contructor without photo
     **/
    public SystemUser(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.photo = "";
        this.password = password;
        this.activateUser = false;
    }

    public SystemUser(String userName, String email, String function, String password) {
        this.userName = userName;
        this.email = email;
        this.photo = "";
        this.function = function;
        this.password = password;
        this.activateUser = false;
    }

    /**
     * Contructor with photo
     **/
    public SystemUser(String userName, String email, String function, String photo, String password) {
        this.userName = userName;
        this.email = email;
        this.photo = photo;
        this.function = function;
        this.password = password;
        this.activateUser = false;
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

    public String getActivateUser() {

        String status = "inativo";

        if(this.activateUser) {status = "ativo";}

        return status;
    }

    public boolean activateUser() {return true;}

    public String getAssignedProfile(int idx){return assignedProfileList.get(idx).getName();}

    /**
     * Método para validar se o email é deste objeto.
     */
    public boolean checkEmail(String email){return this.email.equalsIgnoreCase(email);}

    /**
     * Método para verificar se o profile recebido (by ID) está associado (tem na lista) ao objeto.
     */
    public boolean checkProfile(int id){

        boolean valid = false;

        for (int i = 0; i < this.assignedProfileList.size(); i++) {

            if(this.assignedProfileList.get(i).isValidId(id)){
                valid = true;
                break;
            }

        }

        return valid;
    }

    /**
     * Método para verificar quantos profiles estão associados (na lista) ao objeto.
     */

    public int countProfile(){return assignedProfileList.size();}


}
