package switch2021.project.mapper;

import switch2021.project.dto.ProjectDTO;
import switch2021.project.model.Project;
import java.util.ArrayList;
import java.util.List;

public class ProjectsMapper {

    /**
     * Method to change data in to a Project DTO
     **/
    public ProjectDTO toDTO(Project project) {
        String startDate = project.getStartDate().getYear()
                + "/" + project.getStartDate().getMonthValue() + "/" + project.getStartDate().getDayOfMonth();
        String endDate = project.getEndDate().getYear()
                + "/" + project.getEndDate().getMonthValue() + "/" + project.getEndDate().getDayOfMonth();
        return new ProjectDTO(project.getCode(), project.getProjectName(), project.getDescription(),
                startDate, endDate, project.getNumberOfSprints(), project.getBudget(), project.getSprintDuration());
    }


    /**
     * Method to change data in to a Project DTO List
     **/
    public List<ProjectDTO> toDTO(List<Project> projectList) {
        List<ProjectDTO> projectDTOList = new ArrayList<>();

        for(Project project : projectList) {
            ProjectDTO projectDTO = toDTO(project);
            projectDTOList.add(projectDTO);
        }
        return projectDTOList;
    }
}
