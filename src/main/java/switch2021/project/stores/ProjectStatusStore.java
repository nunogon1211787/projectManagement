package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.factory.ProjectStatusFactory;
import switch2021.project.model.ProjectStatus;

import java.util.ArrayList;
import java.util.List;

@Getter

public class ProjectStatusStore {

    /**
     * Project Status Store Attributes (Contains a Project Status list)
     **/
    private final List<ProjectStatus> projectStatusList;
    private ProjectStatusFactory projectStatusFactory;

    /**
     * Project Status Constructor
     **/
    public ProjectStatusStore(ProjectStatusFactory projStatusFactory) {
        this.projectStatusList = new ArrayList<>();
        this.projectStatusFactory = projStatusFactory;
    }

    public ProjectStatusStore() {
        this.projectStatusList = new ArrayList<>();
    }

    /**
     * Project Status Populator (Populates the Project Status List with pre-set objects)
     **/
    public void populateDefault() {
        createAndSaveProjectStatus("Planned");
        createAndSaveProjectStatus("Inception");
        createAndSaveProjectStatus("Elaboration");
        createAndSaveProjectStatus("Construction");
        createAndSaveProjectStatus("Transition");
        createAndSaveProjectStatus("Warranty");
        createAndSaveProjectStatus("Closed");
    }

    /**
     * Create Project Status (Creates a new Project Status object)
     **/
    public boolean createAndSaveProjectStatus(String description) {
        if (getProjectStatusByDescription(description) != null) {
            //return false;
            throw new IllegalArgumentException("Project status already exists.");
        }
        ProjectStatus newProjectStatus = new ProjectStatus(description);

        return this.projectStatusList.add(newProjectStatus);
    }

    public boolean createAndSaveProjectStatusWithFactory(String description) {
        if (getProjectStatusByDescription(description) != null) {
            return false;
            //throw new IllegalArgumentException("Project status already exists.");
        }
        ProjectStatus newProjectStatus = this.projectStatusFactory.createProjectStatus(description);

        return this.projectStatusList.add(newProjectStatus);
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
            if (i.getDescription().getText().trim().equalsIgnoreCase(name.trim())) {
                status = i;
                break;
            }
        }
        return status;
    }
}
