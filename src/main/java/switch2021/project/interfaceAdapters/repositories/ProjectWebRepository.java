package switch2021.project.interfaceAdapters.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import switch2021.project.applicationServices.iRepositories.IProjectWebRepository;
import switch2021.project.dataModel.REST.assemblers.ProjectDomainDataRestAssembler;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.interfaceAdapters.repositories.REST.ProjectRestRepository;
import java.util.List;

public class ProjectWebRepository implements IProjectWebRepository {

    @Autowired
    ProjectRestRepository projectRestRepository;

    @Autowired
    ProjectDomainDataRestAssembler projectDomainDataRestAssembler;

    @Override
    public List<Project> findAll() {
//        List<ProjectRestDTO> projectRestDTO = projectRestRepository.findAll();
//        List<Project> projectList = new ArrayList<>();
//
//        for (ProjectRestDTO projectRestDTO1 : projectRestDTO) {
//            projectList.add(projectDomainDataRestAssembler.toDomain(projectRestDTO1));
//        }
//        List<Project> projectList =  projectDomainDataRestAssembler.toDomain(projectRestDTO);
//
        return projectDomainDataRestAssembler.toDomain(projectRestRepository.findAll());

    }

}
