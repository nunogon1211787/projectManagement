package switch2021.project.model;

import java.util.Locale;
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

    public void setType(String newType) { this.type = newType; }

    /**
     * Method to verify if a given ID matches with object ID
     */

    public boolean isValidId(int x) {
        return (x == this.id_Profile);
    }

    public boolean isValidName(String name) {
        if(name.toUpperCase(Locale.ROOT) != this.getName().toUpperCase()) {
            return false;
        }
        return true;
    }

    public boolean hasType(String type){ return this.type.equalsIgnoreCase(type);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile that = (Profile) o;
        return (this.getName().equals(that.getName())) && (this.getType().equals(getType()));
    }
    //Este override foi feito expecíficamente para os teste... uma vez que os IDs da classe
    // vão sempre seguir uma sequência! Aceito sugestões para melhorar isto...

}
