package switch2021.project.stores;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.model.ProjectStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class ProjectStatusStore {

    /**
     * Project Status Store Attributes (Contains a Project Status list)
     **/
    private final List<ProjectStatus> projectStatusList;

    /**
     * Project Status Constructor
     **/
    public ProjectStatusStore() {
        this.projectStatusList = new ArrayList<>();
    }

    /**
     * Project Status Populator (Populates the Project Status List with pre-set objects)
     **/
    public void populateDefault() {
        this.projectStatusList.add(new ProjectStatus("Planned"));
        this.projectStatusList.add(new ProjectStatus("Inception"));
        this.projectStatusList.add(new ProjectStatus("Elaboration"));
        this.projectStatusList.add(new ProjectStatus("Construction"));
        this.projectStatusList.add(new ProjectStatus("Transition"));
        this.projectStatusList.add(new ProjectStatus("Warranty"));
        this.projectStatusList.add(new ProjectStatus("Closed"));
    }

    /**
     * Create Project Status (Creates a new Project Status object)
     **/
    public ProjectStatus createProjectStatus(String description) {
        return new ProjectStatus(description);
    }

    /**
     * Add Project Status Method (Adds a new Project Status object to the Project Status List)
     **/
    public boolean add(ProjectStatus projStat) {
        if (this.projectStatusList.contains(projStat))
            return false;
        return this.projectStatusList.add(projStat);
    }

    /**
     * Getter Methods
     **/
    public List<ProjectStatus> getProjectStatusList() {
        return new ArrayList<>(projectStatusList);
    }

    public ProjectStatus getProjectStatusByDescription(String name) {
        ProjectStatus status = null;

        for (ProjectStatus i : this.projectStatusList) {
            if (i.getDescription().getText().equals(name)) {
                 status = i;
                break;
            }
        }
        return status;
    }

}
