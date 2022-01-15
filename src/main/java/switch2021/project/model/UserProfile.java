package switch2021.project.model;

public class UserProfile {

    /** Classe Profile Attributes **/
    private int id_Profile;
    private String userProfileName;


    /** Profile Constructors */
    public UserProfile(String userProfileName) {
        checkAttributeRules(userProfileName);
        this.userProfileName = userProfileName;
    }


    /** Copy Constructor */
    public UserProfile(UserProfile originalProfile) {
        this.id_Profile = originalProfile.id_Profile;
        this.userProfileName = originalProfile.userProfileName;
    }


    /** Getters e Setters **/
    public int getId_UserProfile() {
        return id_Profile;
    }

    public String getUserProfileName() {
        return this.userProfileName;
    }

    public void setUserProfileName(String newName) {
        this.userProfileName = newName;
    }

    public void setId_UserProfile(int id) {this.id_Profile = id;}


    /** Method to validate entered data (name) by Administrator */
    private void checkAttributeRules(String name) {
        //Check empty field on name
        if (name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be blank.");
    }


    /** Override Methods **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return (this.getUserProfileName().equals(that.getUserProfileName()));
    }
    //Este override foi feito expecíficamente para os teste... uma vez que os IDs da classe
    // vão sempre seguir uma sequência! Aceito sugestões para melhorar isto...
}
