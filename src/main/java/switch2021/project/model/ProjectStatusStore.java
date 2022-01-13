package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectStatusStore {

    /**
     * Project Status Store Atributes
     * Contains a Project Status list
     **/

    private List<ProjectStatus> projectStatusList;

    /**
     * Project Status Constructor
     **/

    public ProjectStatusStore() {
        this.projectStatusList = new ArrayList<>();
    }

    /**
     * Project Status Populator
     * Populates the Project Status List with pre-set objects
     **/

    public void populateProjectStatusList() {
        this.projectStatusList.add(new ProjectStatus("Planned"));
        this.projectStatusList.add(new ProjectStatus("Inception"));
        this.projectStatusList.add(new ProjectStatus("Elaboration"));
        this.projectStatusList.add(new ProjectStatus("Construction"));
        this.projectStatusList.add(new ProjectStatus("Transition"));
        this.projectStatusList.add(new ProjectStatus("Warranty"));
        this.projectStatusList.add(new ProjectStatus("Closed"));
    }

    /**
     * Create Project Status
     * Creates a new Project Status object
     **/

    public ProjectStatus createProjectStatus(String descritpion) {

        return new ProjectStatus(descritpion);
    }

    /**
     * Add Project Status Method
     * Adds a new Project Status object to the Project Status List
     **/

    public boolean add(ProjectStatus projStat) {
        this.projectStatusList.add(projStat);
        return true;
    }

    /**
     * Métodos Getter e Setter
     **/

    public List<ProjectStatus> getprojectStatusList() {

        return this.projectStatusList;
    }

    public ProjectStatus getProjectStatusByDescription(String name) {
        ProjectStatus status = null;

        for (ProjectStatus i : this.projectStatusList) {
            if (i.getDescription().equals(name)) {
                 status = i;
                break;
            }
        }
        return status;
    }


}
