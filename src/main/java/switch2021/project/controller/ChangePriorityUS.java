package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.stores.ProjectStore;

import java.util.List;

public class ChangePriorityUS {

    private Company company;
    private ProjectStore projectStore;
    private Project project;
    List<Project> arrayProject;

    public List<Project> getProjectListByMemberAssociated (String email){
        this.projectStore = this.company.getProjectStore();
        this.arrayProject = this.projectStore.getProjectListByMemberAssociated(email);
        return arrayProject;
    }





}
