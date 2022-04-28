package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.factoryInterface.ProjectFactoryInterface;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.repositories.ProjectStoreReeng;

@Service
public class ProjectService {

    @Autowired
    private  ProjectFactoryInterface projectFactoryInterface;
    @Autowired
    private ProjectMapper projectsMapper;
    @Autowired
    ProjectStoreReeng projectRepositoryReeng;

    public ProjectService() {
    }

    public OutputProjectDTO createAndSaveProject(ProjectDTO projDTO) {
        ProjectReeng newProject = projectFactoryInterface.createProject(

                projDTO.projectName,
                projDTO.description,
                projDTO.businessSector,
                projDTO.startDate,
                projDTO.numberOfSprints,
                projDTO.budget);

        projectRepositoryReeng.save(newProject);

        return projectsMapper.model2Dto(newProject);
    }

}
