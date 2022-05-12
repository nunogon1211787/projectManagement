package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.model.Project.ProjectReeng;

@Component
public class ProjectMapper {

    public OutputProjectDTO model2Dto(ProjectReeng newProject) {
        String code = newProject.getProjectCode().getCode();
        String projectName = newProject.getProjectName().getText();
        String description = newProject.getDescription().getText();
        String businessSector = newProject.getBusinessSector().getDescription().getText();
        String numberOfSprints = newProject.getNumberOfSprints().toString();
        String budget = newProject.getBudget().toString();
        String status = newProject.getProjectStatus().toString();
       String startDate = newProject.getStartDate().toString();
        OutputProjectDTO projDto = new OutputProjectDTO(code,projectName,description,businessSector,startDate, numberOfSprints,budget,status);

        if (newProject.getTypology() != null) {
            projDto.typo = newProject.getTypology().getId_description().getDescription().getText();
        }

        if (newProject.getCustomer() != null) {
            projDto.customer = newProject.getCustomer().getCustomerName().getText();
        }

        return projDto;
    }
}
