package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.model.Task;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

import java.util.ArrayList;
import java.util.List;

public class ViewStatusOfActivitiesInAProjectController {

    private Company company;
    private ProjectStore projectStore;
    private Project project;
    List<Project> arrayProject;
    List<Task> listOfTasks;

    /**
     * Constructor to UI (with SINGLETON).
     */
    public ViewStatusOfActivitiesInAProjectController(){ this.company = App.getInstance().getCompany(); }

    /**
     * Constructor to test (without SINGLETON).
     */
    public ViewStatusOfActivitiesInAProjectController(Company company){ this.company = company; }


    //Method do get list of projects where the user is associated

    public List<Project> getProjectListByUser(String email) {
        this.projectStore = this.company.getProjectStore();
        this.arrayProject = this.projectStore.getProjectListByUserEmail(email);
        return arrayProject;
    }
    //Method to get project by code

    public Project getProjectByCode(String code){
        this.projectStore = this.company.getProjectStore();
        this.project = this.projectStore.getProjectByCode(code);
        return this.project;
    }

    //Method to get list of project activities

    public List<Task> getListOfProjectActivities(){
        return new ArrayList<>(project.getSprintStore().getListOfAllAActivitiesOfAProject());
    }

}
