package switch2021.project.model;

public class ProjectStatus {

    /**
     * Classe  ProjectStatus -
     * Atributos da Classe
     **/
    private String description;

    public ProjectStatus setDescription(String description) {
        this.description = description;
        return null;
    }

    /**
     * Constutor da Classe  ProjectStatus (Paulo - US005)
     **/

    public ProjectStatus(String description) {
        this.description = description;
    }


    /** Metodos Override  para comparar objectos diferentes **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectStatus that = (ProjectStatus) o;

        return (this.description.equals(that.description));
    }
}