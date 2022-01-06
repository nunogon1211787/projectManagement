package switch2021.project.controller;

import switch2021.project.model.*;

import java.util.List;

public class US008Controller {

    private Company company;
    private Project project;
    private SystemUser user;
    private Resource resource;
    private Profile profile;

    public List<Project> getProjectList() {
        return company.getArrayProj();
    }


//    public getProjectRelatedInfo() {
//
//   }



}



