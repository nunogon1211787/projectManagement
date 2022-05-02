package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.factoryInterface.ProjectFactoryInterface;
import switch2021.project.interfaces.IRepoTypology;
import switch2021.project.interfaces.ProjectRepositoryInterface;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.Project.ProjectStatusEnum;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;

@Service
public class ProjectService {

    @Autowired
    private IRepoTypology iRepoTypology;
    @Autowired
    ProjectRepositoryInterface projectRepositoryInterface;
    @Autowired
    private ProjectFactoryInterface projectFactoryInterface;
    @Autowired
    private ProjectMapper projectsMapper;

    public ProjectService() {
    }

    public OutputProjectDTO createAndSaveProject(ProjectDTO projDTO) {

        int nextId = projectRepositoryInterface.findAll().size();

        ProjectReeng newProject = projectFactoryInterface.createProject(projDTO, nextId);

        projectRepositoryInterface.save(newProject);

        return projectsMapper.model2Dto(newProject);
    }

    public OutputProjectDTO editProject(ProjectDTO projectDTO) {

        ProjectReeng proj = projectRepositoryInterface.findById(projectDTO.code);

        if (!projectDTO.projectName.isEmpty()) {
            proj.setProjectName(new Description(projectDTO.projectName));
        }
        if (!projectDTO.description.isEmpty()) {
            proj.setDescription(new Description(projectDTO.description));
        }
        if (!projectDTO.businessSector.isEmpty()) {
            proj.setBusinessSector(new BusinessSector(projectDTO.businessSector));
        }
        if (!projectDTO.typology.isEmpty()) {
            proj.setTypology(iRepoTypology.findTypologyByDescription(projectDTO.typology)); //It's a entity, should be search at the repository!
            //This alteration must be checked!
        }
        if (!projectDTO.numberOfSprints.isEmpty()) {
            proj.setNumberOfSprints(new NumberOfSprints(Integer.parseInt(projectDTO.numberOfSprints)));
        }
        if (!projectDTO.budget.isEmpty()) {
            proj.setBudget(new Budget(Integer.parseInt(projectDTO.budget)));
        }
        if (!projectDTO.startDate.isEmpty()) {
            proj.setStartDate(LocalDate.parse(projectDTO.startDate));
        }
        if (!projectDTO.projectStatus.isEmpty()) {
            proj.setProjectStatus(ProjectStatusEnum.valueOf(projectDTO.projectStatus));
        }

        return projectsMapper.model2Dto(proj);
    }

}
