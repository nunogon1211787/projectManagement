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
     * Método para verificar se atributo "type" do objecto é igual a "type" do parâmetro.
     *
     */

    public boolean hasEqualType (String type) {return this.type.equals(type);}




    public Profile(String name, String type) {
        this.id_Profile = ID_PROFILE_GENERATOR.getAndIncrement();
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
     * Profle Constructors
     */
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
        try {
            if (x == this.id_Profile) {
                return true;
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isValidName(String name) {
        if(!name.toUpperCase(Locale.ROOT).equals(this.name.toUpperCase())) {
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
