package switch2021.project.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Profile {

    /**
     * Classe Profile Atributes
     * Attributes
     **/
    private int id_Profile;
    private String name;
    private String type;


    /**
     * Profle Constructors
     * Constructors
     */
    //Create ID automatically
    private static AtomicInteger ID_GENERATOR = new AtomicInteger();

    public Profile(String name, String type) {
        this.id_Profile = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.type = type;
    }

    /**
     * Copy Constructor
     */

    public Profile(Profile originalProfile) {
        this.id_Profile = originalProfile.id_Profile;
        this.name = originalProfile.name;
        this.type = originalProfile.type;
    }

    /**
     * Getters e Setters
     */

    public String getName() {
        return this.name;
    }

    public int getId() {
        return id_Profile;
    }

    public String getType() {
        return type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;
        Profile profile = (Profile) o;
        return Objects.equals(getName(), profile.getName()) && Objects.equals(getType(), profile.getType());
    }

}
