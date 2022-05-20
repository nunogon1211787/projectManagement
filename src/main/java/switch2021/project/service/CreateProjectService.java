package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.datamodel.assembler.ProjectJpaAssembler;
import switch2021.project.dto.DateDTO;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.factoryInterface.IProjectFactory;
import switch2021.project.interfaces.IProjectRepo;
import switch2021.project.interfaces.IResourceRepo;
import switch2021.project.interfaces.ITypologyRepo;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.model.Resource.ManageResourcesService;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.repositories.ProjectRepository;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.ProjectReeng;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class CreateProjectService {



    @Autowired
    private IProjectRepo projRepo;
    @Autowired
    private ITypologyRepo iTypologyRepo;
    @Autowired
    private IProjectFactory iProjectFactory;
    @Autowired
    private ProjectMapper projMapper;
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private IResourceRepo resRepo;
    @Autowired
    private ManageResourcesService resService;
    @Autowired
    private ProjectJpaAssembler assembler;

    public CreateProjectService() {
    }

    public OutputProjectDTO createAndSaveProject(ProjectDTO projDTO) {

        ProjectReeng newProject = iProjectFactory.createProject(projDTO);

        ProjectReeng savedProject = projRepo.save(newProject);

        return projMapper.model2Dto(savedProject);
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

    public List<OutputProjectDTO> showAllProjects() {

        List<ProjectReeng> projects = projRepo.findAll();

        return createProjectDTOList(projects);
    }

    private List<OutputProjectDTO> createProjectDTOList(List<ProjectReeng> projects) {

        List<OutputProjectDTO> allProjectsDto = new ArrayList<>();

        for (ProjectReeng proj : projects) {

            OutputProjectDTO projDto = projMapper.model2Dto(proj);

            allProjectsDto.add(projDto);

        }

        return allProjectsDto;

    }

    public List<OutputProjectDTO> showCurrentProjectsByUser(IdDTO dto, DateDTO dateDto){

        List<OutputProjectDTO> projectsDto = new ArrayList<>();

        if(userRepo.existsByEmail(dto.id)){

            List<ResourceReeng> userResources = resRepo.findAllByUser(dto.id);

            List<ResourceReeng> currentUserResources = resService.currentResourcesByDate(userResources, LocalDate.parse(dateDto.date));

            List<ProjectID> resourceProjects = resService.listProjectsOfResources(currentUserResources);

            List<ProjectReeng> projects = new ArrayList<>();

            for(ProjectID projId : resourceProjects){

                ProjectReeng proj = projRepo.findById(projId.toString()).get();

                projects.add(proj);

            }

            for(ProjectReeng proj : projects){

                OutputProjectDTO projDto = projMapper.model2Dto(proj);

                projectsDto.add(projDto);

            }
        }
        return projectsDto;
    }

}
