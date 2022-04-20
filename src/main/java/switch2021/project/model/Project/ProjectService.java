package switch2021.project.model.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.factoryInterface.ProjectFactoryInterface;
import switch2021.project.interfaces.ProjectRepositoryInterface;
import switch2021.project.mapper.ProjectsMapper;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepositoryInterface projectRepositoryInterface;

    @Autowired
    private ProjectFactoryInterface projectFactoryInterface;

    @Autowired
    private ProjectsMapper projectsMapper;

    public ProjectService() {
    }

    public ProjectDTO createAndSaveProject(ProjectDTO projDTO) {
        Project newProject = projectFactoryInterface.createProject(
                projDTO.getProjectName(),
                projDTO.getDescription(),
                projDTO.getStartDate(),
                projDTO.getNumberOfSprints().getNumberOfSprintsVO(),
                projDTO.getBudget().getBudgetVO());


        if (projectRepositoryInterface.hasProjectId(newProject.getProjectCode().getCode())) {
            throw new IllegalArgumentException("Error: Project already exists!");
        }

        return projectsMapper.toDTO(newProject);
    }
}
