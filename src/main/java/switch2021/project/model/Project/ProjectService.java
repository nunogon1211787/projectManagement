package switch2021.project.model.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.factoryInterface.ProjectFactoryInterface;
import switch2021.project.mapper.ProjectsMapper;

@Service
public class ProjectService {

    private  ProjectFactoryInterface projectFactoryInterface;
    private ProjectsMapper projectsMapper;

    @Autowired
    public ProjectService() {
    }

    public ProjectDTO createAndSaveProject(ProjectDTO projDTO) {
        Project newProject = projectFactoryInterface.createProject(

                projDTO.projectName,
                projDTO.description,
                projDTO.startDate,
                projDTO.numberOfSprints,
                projDTO.budget);

        return projectsMapper.toDTO(newProject);
    }

}
