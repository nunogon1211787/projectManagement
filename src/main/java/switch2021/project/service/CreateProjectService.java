package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.factoryInterface.IProjectFactory;
import switch2021.project.interfaces.ITypologyRepo;
import switch2021.project.repositories.ProjectRepository;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.ProjectReeng;


@Service
public class CreateProjectService {

    @Autowired
    private ProjectRepository projRepo;
    @Autowired
    private ITypologyRepo iTypologyRepo;
    @Autowired
    private IProjectFactory iProjectFactory;
    @Autowired
    private ProjectMapper projectsMapper;

    public CreateProjectService() {

    }

    public OutputProjectDTO createAndSaveProject(ProjectDTO projDTO) {

        ProjectReeng newProject = iProjectFactory.createProject(projDTO);

        ProjectReeng savedProject = projRepo.save(newProject);

        return projectsMapper.model2Dto(savedProject);
    }

/*    public OutputProjectDTO editProject(ProjectDTO projectDTO) {

        ProjectReeng proj = projRepo.findById(projectDTO.code);

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
            proj.setTypology(iTypologyRepo.findTypologyById(projectDTO.typology));
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
        if (!projectDTO.sprintDuration.isEmpty()) {
            proj.setSprintDuration(new SprintDuration(Integer.parseInt(projectDTO.sprintDuration)));
        }

        return projectsMapper.model2Dto(proj);
    }*/

}
