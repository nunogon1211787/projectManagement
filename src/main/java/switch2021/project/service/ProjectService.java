package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.dto.*;
import switch2021.project.factoryInterface.IProjectFactory;
import switch2021.project.factoryInterface.IProjectIDFactory;
import switch2021.project.interfaces.IProjectRepo;
import switch2021.project.interfaces.IResourceRepo;
import switch2021.project.interfaces.ITypologyRepo;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Resource.ManageResourcesService;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProjectService {

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


    public ProjectService() {
    }

    public OutputProjectDTO createAndSaveProject(ProjectDTO projDTO) throws Exception {

        Project newProject = iProjectFactory.createProject(projDTO);

        Optional<Project> savedProject = projRepo.save(newProject);

        OutputProjectDTO projectDTO;

        if (savedProject.isPresent()) {
            projectDTO = projMapper.model2Dto(savedProject.get());
        } else {
            throw new Exception("Project already exist.");
        }
        return projectDTO;
    }

    public OutputProjectDTO updateProjectPartially(String id, EditProjectInfoDTO editProjectInfoDTO) {

        Optional<Project> opProject = projRepo.findById(id);
        Project proj;

        if (opProject.isPresent()) {
            proj = opProject.get();
            proj.setProjectName(new Description(editProjectInfoDTO.projectName));
            proj.setDescription(new Description(editProjectInfoDTO.description));
            proj.setStartDate(LocalDate.parse(editProjectInfoDTO.startDate));
            proj.setNumberOfSprints(new NumberOfSprints(Integer.parseInt(editProjectInfoDTO.numberOfSprints)));
            proj.setBudget(new Budget(Integer.parseInt(editProjectInfoDTO.budget)));
            proj.setSprintDuration(new SprintDuration(Integer.parseInt(editProjectInfoDTO.sprintDuration)));

            /*proj.setProjectStatus(ProjectStatusEnum.valueOf(editProjectInfoDTO.projectStatus));
            proj.setCustomer(new Customer(editProjectInfoDTO.customer, "email@email.pt", 123456789));
            proj.setEndDate(LocalDate.parse(editProjectInfoDTO.endDate));
            proj.setTypology(new Typology(new TypologyID(new Description(editProjectInfoDTO.description))));*/

            Optional<Project> savedProject = projRepo.save(proj);

            return savedProject.map(projectReeng -> projMapper.model2Dto(projectReeng)).orElse(null);
        }

        return null;
    }


    private List<OutputProjectDTO> createProjectDTOList(List<Project> projects) {

        List<OutputProjectDTO> allProjectsDto = new ArrayList<>();

        for (Project proj : projects) {

            OutputProjectDTO projDto = projMapper.model2Dto(proj);

            allProjectsDto.add(projDto);

        }

        return allProjectsDto;

    }

    public CollectionModel<OutputProjectDTO> showAllProjects() {

        List<Project> projects = projRepo.findAll();

        return projMapper.toCollectionDto(projects);
    }

    public OutputProjectDTO showProject(String id) throws Exception {

        Optional<Project> foundProject = projRepo.findById(id);

        if (foundProject.isEmpty()) {
            throw new Exception("Project does not exist");
        }

        return projMapper.model2Dto(foundProject.get());
    }

    public List<OutputProjectDTO> showCurrentProjectsByUser(IdDTO dto, DateDTO dateDto) {

        List<OutputProjectDTO> projectsDto = new ArrayList<>();
        SystemUserID systemUserID = new SystemUserID(new Email(dto.id));
        if (userRepo.existsById(systemUserID)) {

            SystemUserID userId = new SystemUserID(new Email(dto.id));
            List<ResourceReeng> userResources = resRepo.findAllByUser(userId);

            List<ResourceReeng> currentUserResources = resService.currentResourcesByDate(userResources,
                                                                                         LocalDate.parse(dateDto.date));

            List<ProjectID> resourceProjects = resService.listProjectsOfResources(currentUserResources);

            List<Project> projects = new ArrayList<>();

            for (ProjectID projId : resourceProjects) {

                Project proj = projRepo.findById(projId.getCode()).get();

                projects.add(proj);

            }

            for (Project proj : projects) {

                OutputProjectDTO projDto = projMapper.model2Dto(proj);

                projectsDto.add(projDto);

            }
        }
        return projectsDto;
    }

    public void deleteProjectRequest(String id) throws Exception {
        if (!projRepo.deleteByProjectID(id)) {
            throw new Exception("User Story does not exist");
        }
    }
}
