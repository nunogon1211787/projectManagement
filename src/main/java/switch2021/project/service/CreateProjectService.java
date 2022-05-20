package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.datamodel.assembler.ProjectJpaAssembler;
import switch2021.project.dto.*;
import switch2021.project.factoryInterface.IProjectFactory;
import switch2021.project.factoryInterface.IProjectIDFactory;
import switch2021.project.interfaces.IProjectRepo;
import switch2021.project.factoryInterface.IProjectIDFactory;
import switch2021.project.interfaces.IProjectRepo;
import switch2021.project.interfaces.IResourceRepo;
import switch2021.project.interfaces.ITypologyRepo;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.Project.ProjectStatusEnum;
import switch2021.project.model.Resource.ManageResourcesService;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.valueObject.*;
import switch2021.project.repositories.ProjectRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CreateProjectService {

    @Autowired
    private IProjectRepo projRepo;
    @Autowired
    private ITypologyRepo iTypologyRepo;
    @Autowired
    private IProjectFactory iProjectFactory;
    @Autowired
    private IProjectIDFactory projIDFactory;
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

    public OutputProjectDTO updateProjectPartially(IdDTO idDTO, EditProjectInfoDTO editProjectInfoDTO) {
        ProjectID projectID = projIDFactory.create(idDTO.id);
        Optional<ProjectReeng> opProject = projRepo.findById(projectID);
        if (opProject.isPresent()) {
            ProjectReeng proj = opProject.get();

            if (!editProjectInfoDTO.projectName.isEmpty()) {
                proj.setProjectName(new Description(editProjectInfoDTO.projectName));
            }
            if (!editProjectInfoDTO.description.isEmpty()) {
                proj.setDescription(new Description(editProjectInfoDTO.description));
            }
            if (!editProjectInfoDTO.startDate.isEmpty()) {
                proj.setStartDate(LocalDate.parse(editProjectInfoDTO.startDate));
            }
            if (!editProjectInfoDTO.endDate.isEmpty()) {
                proj.setStartDate(LocalDate.parse(editProjectInfoDTO.endDate));
            }
            if (!editProjectInfoDTO.numberOfSprints.isEmpty()) {
                proj.setNumberOfSprints(new NumberOfSprints(Integer.parseInt(editProjectInfoDTO.numberOfSprints)));
            }
            if (!editProjectInfoDTO.budget.isEmpty()) {
                proj.setBudget(new Budget(Integer.parseInt(editProjectInfoDTO.budget)));
            }
            if (!editProjectInfoDTO.projectStatus.isEmpty()) {
                proj.setProjectStatus(ProjectStatusEnum.valueOf(editProjectInfoDTO.projectStatus));
            }
            if (!editProjectInfoDTO.sprintDuration.isEmpty()) {
                proj.setSprintDuration(new SprintDuration(Integer.parseInt(editProjectInfoDTO.sprintDuration)));
            }
            return projMapper.model2Dto(proj);
        } else
            return null;
    }

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
