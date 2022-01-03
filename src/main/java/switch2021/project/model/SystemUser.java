package switch2021.project.model;

public class SystemUser {

    /**
     * Attributes of SystemUser´s Class
     **/
    private String userName;
    private String email;
    private String photo;
    private String password;
    private boolean activateUser;

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

    /**
     * Contructor with photo
     **/
    public SystemUser(String userName, String email, String photo, String password) {
        this.userName = userName;
        this.email = email;
        this.photo = photo;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean activateUser() {
        return true;
    }

    /**
     * Método para validar se o email é deste objeto.
     */
    public boolean checkEmail(String email){return this.email.equalsIgnoreCase(email);}
}
