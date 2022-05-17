package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.Project.ProjectStatusEnum;

@Component
public class ProjectMapper {

    public OutputProjectDTO model2Dto(ProjectReeng newProject) {

        String code = newProject.getProjectCode().getCode();
        String projectName = newProject.getProjectName().getText();
        String description = newProject.getDescription().getText();
        String businessSector = newProject.getBusinessSector().getDescription().getText();
        String numberOfSprints = Integer.toString(newProject.getNumberOfSprints().getNumberOfSprintsVO());
        String budget = Double.toString(newProject.getBudget().getBudgetVO());
        String status = newProject.getProjectStatus().name();
        String startDate = newProject.getStartDate().toString();
        String sprintDuration = Integer.toString(newProject.getSprintDuration().getSprintDurationDays());

        OutputProjectDTO projDto = new OutputProjectDTO(code, projectName, description, businessSector, startDate,
                numberOfSprints, budget, status, sprintDuration);

        if (newProject.getTypology() != null) {
            projDto.typo = newProject.getTypology().getId_description().getDescription().getText();
        }

        if (newProject.getCustomer() != null) {
            projDto.customer = newProject.getCustomer().getCustomerName().getText();
        }

        return projDto;
    }
}
