package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Task.Task;
import switch2021.project.repositories.ProjectStore;

import java.util.ArrayList;
import java.util.List;

public class ViewStatusOfActivitiesInAProjectController {

    /**
     * Attributes
     **/
    private final Company company;
    private Project project;
    List<Task> listOfTasks;  //Review, it isn't used


    /**
     * Constructor to test (without SINGLETON)
     **/
    public ViewStatusOfActivitiesInAProjectController(Company company) {
        this.company = company;
    }


    /**
     * Method do get list of projects where the user is associated
     **/
    public List<Project> getProjectListByUser(String email) {
        ProjectStore projectStore = this.company.getProjectStore();
        return projectStore.getProjectsByUserEmail(email);
    }


    /**
     * Method to get project by code
     **/
    public Project getProjectByCode(String code) {
        ProjectStore projectStore = this.company.getProjectStore();
        this.project = projectStore.getProjectByCode(code);
        return this.project;
    }


    /**
     * Method to get list of project activities
     **/
    public List<Task> getListOfProjectActivities() {
        return new ArrayList<>(project.getSprintList().getListOfAllAActivitiesOfAProject());
    }
}
