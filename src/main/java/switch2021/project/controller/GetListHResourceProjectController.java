package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Resource;
import switch2021.project.utils.App;

import java.util.List;

public class GetListHResourceProjectController {

    private final Company company;
    private List<Resource> projectTeamList;

    /**
     * Constructor to UI (with SINGLETON).
     */
    public GetListHResourceProjectController(){
        this.company = App.getInstance().getCompany();
    }

    /**
     * Constructor to test (without SINGLETON).
     */
    public GetListHResourceProjectController(Company company){
        this.company = company;
    }

//    public List<ResourceDto> getProjectTeam(){
//
//    }
}
