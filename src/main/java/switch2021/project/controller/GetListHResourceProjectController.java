package switch2021.project.controller;

import switch2021.project.dto.ResourceDTO;
import switch2021.project.mapper.ProjectTeamMapper;
import switch2021.project.model.Company;
import switch2021.project.model.Resource.Resource;

import java.util.List;

public class GetListHResourceProjectController {

    /**
     * Attributes
     **/
    private final Company company;
    private final ProjectTeamMapper projectTeamMapper;


    /**
     * Constructor to test (without SINGLETON)
     */
    public GetListHResourceProjectController(Company company, ProjectTeamMapper projectTeamMapper) {
        this.company = company;
        this.projectTeamMapper = projectTeamMapper;
    }


    /**
     * Method
     **/
    public List<ResourceDTO> getProjectTeam(String projectCode) {
        List<Resource> projectTeamList = company.getProjectStore().getProjectByCode(projectCode).getProjectTeam().getProjectTeamList();
        return this.projectTeamMapper.toDto(projectTeamList);
    }
}
