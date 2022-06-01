package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import switch2021.project.interfaceAdapters.controller.ProjectController;
import switch2021.project.dtoModel.dto.EditProjectInfoDTO;
import switch2021.project.dtoModel.dto.OutputProjectDTO;
import switch2021.project.entities.aggregates.Project.Project;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjectMapper {

    public OutputProjectDTO model2Dto(Project newProject) {

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

        if (newProject.getEndDate() != null) {
            projDto.endDate = newProject.getEndDate().toString();
        }

        if (newProject.getTypology() != null) {
            projDto.typo = newProject.getTypology().getId_description().getDescription().getText();
        }

        if (newProject.getCustomer() != null) {
            projDto.customer = newProject.getCustomer().getCustomerName().getText();
        }

        //Show a project
        projDto.add(linkTo(methodOn(ProjectController.class).showProjectRequested(projDto.code))
                .withSelfRel());

        //Show all projects
        projDto.add(linkTo(methodOn(ProjectController.class).showAllProjects()).withRel("Collection"));

        //Delete
        projDto.add(linkTo(methodOn(ProjectController.class).deleteProjectRequest(projDto.code)).withRel("Delete"));

        //Edit a project

        projDto.add(linkTo(methodOn(ProjectController.class).updateProjectPartially(projDto.code,
                new EditProjectInfoDTO())).withRel("Edit"));


        return projDto;
    }

    public CollectionModel<OutputProjectDTO> toCollectionDto(List<Project> projects) {

        CollectionModel<OutputProjectDTO> result = CollectionModel.of(projects.stream()
                .map(this::model2Dto)
                .collect(Collectors.toList()));


        result.add(linkTo(methodOn(ProjectController.class).showAllProjects()).withSelfRel());

        return result;
    }
}
