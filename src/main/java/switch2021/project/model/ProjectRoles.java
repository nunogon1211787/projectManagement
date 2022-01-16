package switch2021.project.model;

import java.util.Locale;

public class ProjectRoles {

    /**
     * Classe ProjectRoles Atributes
     * Attributes
     **/
    private int id_Role;
    private String name;

    public ProjectRoles(int idRole, String name) {
        this.id_Role = idRole;
        this.name = name;
    }

    /**
     * Copy Constructor
     */

    public ProjectRoles(ProjectRoles originalProfile) {
        this.id_Role = originalProfile.id_Role;
        this.name = originalProfile.name;
    }

    /**
     * Getters e Setters
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_Role() {
        return id_Role;
    }

    public void setId_Role(int id_Role) {
        this.id_Role = id_Role;
    }

    /**
     * Method to verify if a given name to Project Role is valid
     */

    public boolean isValidName(String name) {
        if(!name.toUpperCase(Locale.ROOT).equals(this.name.toUpperCase())) {
            return false;
        }
        return true;
    }

    /**
     * Override Equals
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectRoles that = (ProjectRoles) o;
        return (this.getName().equals(that.getName()));
    }

}
