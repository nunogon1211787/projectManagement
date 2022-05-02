package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Project.ProjectReeng;

@Component
public class ProjectMapper {

    public OutputProjectDTO model2Dto(ProjectReeng project) {

        OutputProjectDTO projDto = new OutputProjectDTO();

        projDto.code = project.getProjectCode().getCode();
        projDto.name = project.getProjectName().getText();
        projDto.desc = project.getDescription().getText();
        projDto.sector = project.getBusinessSector().getDescription().getText();
        projDto.numberOfSprints = project.getNumberOfSprints().toString();
        projDto.budget = project.getBudget().toString();
        projDto.status = project.getProjectStatus().toString();
        projDto.startDate = project.getStartDate().toString();

        if (project.getTypology() != null) {
            projDto.typo = project.getTypology().getId_description().getDescription().getText();
        }

        if (project.getCustomer() != null) {
            projDto.customer = project.getCustomer().getCustomerName().getText();
        }

        return projDto;
    }
}
