package switch2021.project.mapper.old;

import lombok.Getter;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.model.Project.Project;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProjectsMapper {

    /**
     * Attributes
     */
    List<ProjectDTO> projectDTOList;

    /**
     * Method to change data in to a Project DTO
     **/
    public ProjectDTO toDTO(Project project) {
        String startDate = project.getStartDate().getYear()
                + "/" + project.getStartDate().getMonthValue() + "/" + project.getStartDate().getDayOfMonth();
        String endDate = null;
        if (project.getEndDate() != null) {
            endDate = project.getEndDate().getYear()
                    + "/" + project.getEndDate().getMonthValue() + "/" + project.getEndDate().getDayOfMonth();
        }
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.code = project.getProjectCode().getCode();
        projectDTO.projectName = project.getProjectName().getText();
        projectDTO.startDate = project.getStartDate().toString();
        projectDTO.endDate = project.getEndDate().toString();

        return projectDTO;
    }


    /**
     * Method to change data in to a Project DTO List
     **/
    public List<ProjectDTO> toDTO(List<Project> projectList) {
        this.projectDTOList = new ArrayList<>();

        for (Project project : projectList) {
            ProjectDTO projectDTO = toDTO(project);
            this.projectDTOList.add(projectDTO);
        }
        return this.projectDTOList;
    }

    /**
     * Method to change data in to a project DTO List
     **/

    public List<ProjectDTO> toDtoByUser(List<Project> projectListByUser) {
        List<ProjectDTO> projectListByUserDtoList = new ArrayList<>();
        for (Project project : projectListByUser) {
            ProjectDTO projectListByUserDto = new ProjectDTO();
            projectListByUserDto.code = project.getProjectCode().getCode();
            projectListByUserDto.projectName = project.getProjectName().getText();
            projectListByUserDto.description = project.getDescription().getText();
            projectListByUserDtoList.add(projectListByUserDto);
        }
        return projectListByUserDtoList;
    }

}