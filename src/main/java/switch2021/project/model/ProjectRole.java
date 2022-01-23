package switch2021.project.model;

import java.util.Locale;

public class ProjectRole {


    /** Classe ProjectRoles Atributes **/
    private int id_Role;
    private String name;


    /** Constructor **/
    public ProjectRole(int idRole, String name) {
        this.id_Role = idRole;
        this.name = name;
    }


    /** Copy Constructor **/
    public ProjectRole(ProjectRole originalProfile) {
        this.id_Role = originalProfile.id_Role;
        this.name = originalProfile.name;
    }


    /** Getters e Setters **/
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


    /** Method to verify if a given name to Project Role is valid **/
    public boolean isValidName(String name) {
        return name.toUpperCase(Locale.ROOT).equals(this.name.toUpperCase());
    }


    /** Override Equals **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectRole that = (ProjectRole) o;
        return (this.name.equals(that.name));
    }
}
