package switch2021.project.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Profile {

    /**
     * Attributes
     **/
    private int id;
    private String name;
    private String type;


    /**
     * Constructors
     */
    //Create ID automatically (Nuno)
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(001);

    //sem parâmetros = visitor profile (Nuno)
    public Profile() {
        this.id = ID_GENERATOR.getAndIncrement();
        this.type = "system_user";
        this.name = "visitor";
    }

    public Profile(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    /**
     * Copy Constructor.
     */

    public Profile(Profile originalProfile) {
        this.id = originalProfile.id;
        this.name = originalProfile.name;
        this.type = originalProfile.type;
    }

    /**
     * Getters e Setters.
     */
    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Método para verificar se o ID recebido corresponde ao ID do objeto.
     */
    public boolean isValidId(int x) {
        return (x == this.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;
        Profile profile = (Profile) o;
        return Objects.equals(getName(), profile.getName()) && Objects.equals(getType(), profile.getType());
    }

}
