package switch2021.project.model;

public class ProjectStatus {

    /**
     * Project Status class
     * Project Status atributes are composed of a description of the Project Status.
     **/

    private String description;

    /**
     * Constructors of Project Status class
     * Creates a new Project Status instance.
     **/

    public ProjectStatus(String description) {
        this.description = description;
    }


    /**
     * Getters and Setters
     **/

    public String getDescription() {
        return description;
    }

    public ProjectStatus setDescription(String description) {
        this.description = description;
        return null;
    }

    /**
     * Override Methods
     **/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectStatus that = (ProjectStatus) o;

        return (this.description.equals(that.description));
    }
}