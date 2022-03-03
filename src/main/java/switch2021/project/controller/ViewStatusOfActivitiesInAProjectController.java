package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.model.Task;
import switch2021.project.stores.ProjectStore;

import java.util.ArrayList;
import java.util.List;

public class ViewStatusOfActivitiesInAProjectController {

    /**
     * Attributes
     **/

    private final Company company;
    private Project project;
    List<Task> listOfTasks;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public ViewStatusOfActivitiesInAProjectController(Company company){ this.company = company; }

    /**
     * Method do get list of projects where the user is associated
     **/

    public List<Project> getProjectListByUser(String email) {
        ProjectStore projectStore = this.company.getProjectStore();
        List<Project> arrayProject = projectStore.getProjectsByUserEmail(email);
        return arrayProject;
    }

    /**
     * Method to get project by code
     **/

    public Project getProjectByCode(String code){
        ProjectStore projectStore = this.company.getProjectStore();
        this.project = projectStore.getProjectByCode(code);
        return this.project;
    }

    /**
     * Method to get list of project activities
     **/

    public List<Task> getListOfProjectActivities(){
        return new ArrayList<>(project.getSprints().getListOfAllAActivitiesOfAProject());
    }

}
