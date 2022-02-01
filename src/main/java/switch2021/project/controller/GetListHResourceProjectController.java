package switch2021.project.controller;
import switch2021.project.dto.ResourceDto;
import switch2021.project.mapper.ProjectTeamMapper;
import switch2021.project.model.Company;
import switch2021.project.model.Resource;
import switch2021.project.utils.App;

import java.util.List;

public class GetListHResourceProjectController {

    private final Company company;
    private List<Resource> projectTeamList;
    private ProjectTeamMapper projectTeamMapper;


    /**
     * Constructor to UI (with SINGLETON).
     */
//    public GetListHResourceProjectController(){
//        this.company = App.getInstance().getCompany();
//    }

    /**
     * Constructor to test (without SINGLETON).
     */
    public GetListHResourceProjectController(Company company, ProjectTeamMapper projectTeamMapper){
        this.company = company;
        this.projectTeamMapper = projectTeamMapper;
    }

    public List<ResourceDto> getProjectTeam(String projectCode){
        this.projectTeamList = company.getProjectStore().getProjectByCode(projectCode).getProjectTeam().getProjectTeamList();
        return this.projectTeamMapper.toDto(projectTeamList);
    }
}
