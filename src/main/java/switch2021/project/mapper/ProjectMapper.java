package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Project.ProjectReeng;

@Component
public class ProjectMapper {

    public OutputProjectDTO model2Dto(Project project) {

        OutputProjectDTO projDto = new OutputProjectDTO();

        projDto.code = project.getProjectCode().getCode();
        projDto.name = project.getProjectName().getText();
        projDto.desc = project.getDescription().getText();
        projDto.customer = project.getCustomer().getCustomerName().getText();
        projDto.sector = project.getBusinessSector().getDescription().getText();
        projDto.typo = project.getTypology().getId_description().getDescription().getText();
        projDto.budget = project.getBudget().toString();
        projDto.status = project.getProjectStatus().toString();
        projDto.startDate = project.getStartDate().toString();

        return projDto;
    }

    public OutputProjectDTO model2Dto(ProjectReeng project) {

        OutputProjectDTO projDto = new OutputProjectDTO();

        projDto.code = project.getProjectCode().getCode();
        projDto.name = project.getProjectName().getText();
        projDto.desc = project.getDescription().getText();
        projDto.customer = project.getCustomer().getCustomerName().getText();
        projDto.sector = project.getBusinessSector().getDescription().getText();
        projDto.typo = project.getTypology().getId_description().getDescription().getText();
        projDto.budget = project.getBudget().toString();
        projDto.status = project.getProjectStatus().toString();
        projDto.startDate = project.getStartDate().toString();

        return projDto;
    }
}
