package switch2021.project.stores;

import switch2021.project.factoryInterface.ProjectStatusFactoryInterface;
import switch2021.project.model.ProjectStatus;
import java.util.ArrayList;
import java.util.List;

public class ProjectStatusStore {

    /**
     * Project Status Store Attributes
     **/
    private final List<ProjectStatus> projectStatusList;
    private final ProjectStatusFactoryInterface projectStatusFactory;

    /**
     * Project Status Constructor
     **/
    public ProjectStatusStore(ProjectStatusFactoryInterface projStatusFactory) {
        this.projectStatusList = new ArrayList<>();
        this.projectStatusFactory = projStatusFactory;
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
     * Create and Add Project Status (Creates a new Project Status object and add it to the list)
     **/
    public boolean createAndSaveProjectStatus(String description) {
        if (getProjectStatusByDescription(description) != null) {
            return false;
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

        for (ProjectStatus projectStatus : this.projectStatusList) {
            if (projectStatus.getDescription().getText().trim().equalsIgnoreCase(name.trim())) {
                status = projectStatus;
                break;
            }
        }
        return status;
    }
}
