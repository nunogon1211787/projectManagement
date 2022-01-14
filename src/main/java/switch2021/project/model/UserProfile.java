package switch2021.project.model;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class UserProfile {

    /**
     * Classe Profile Attributes
     **/
    private int id_Profile;
    private String name;


    /**
     * Profile Constructors
     */
    public UserProfile(String name) {
        checkAttributeRules(name);
        this.id_Profile = ID_PROFILE_GENERATOR.getAndIncrement();
        this.name = name;
    }

    /**
     * Copy Constructor
     */
    public UserProfile(UserProfile originalProfile) {
        this.id_Profile = originalProfile.id_Profile;
        this.name = originalProfile.name;
    }


    //Create ID automatically
    private static AtomicInteger ID_PROFILE_GENERATOR = new AtomicInteger();

    /**
     * Getters e Setters
     */

    public String getName() {
        return this.name;
    }

    public int getId() {
        return id_Profile;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Method to verify if a given ID matches with object ID
     */

    public boolean isValidId(int x) {
        return (x == this.id_Profile);
    }



    /**
     * Method to validate entered data (name) by Administrator
     */

    private void checkAttributeRules(String name) {
        //Check empty field on name
        if (name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be blank.");


    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return (this.getName().equals(that.getName()));
    }
    //Este override foi feito expecíficamente para os teste... uma vez que os IDs da classe
    // vão sempre seguir uma sequência! Aceito sugestões para melhorar isto...

}
