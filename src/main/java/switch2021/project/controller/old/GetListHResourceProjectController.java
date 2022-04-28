package switch2021.project.controller.old;

import switch2021.project.dto.old.ResourceDTO;
import switch2021.project.mapper.old.ProjectTeamMapper;
import switch2021.project.model.Company;
import switch2021.project.model.Resource.old.Resource;

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
        List<Resource> projectTeamList = company.getProjectStore().findById(projectCode).getProjectTeam().getProjectTeamList();
        return this.projectTeamMapper.toDto(projectTeamList);
    }
}
