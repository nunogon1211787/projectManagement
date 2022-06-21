package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.*;
import switch2021.project.dtoModel.dto.EditProjectInfoDTO;
import switch2021.project.dtoModel.dto.OutputProjectDTO;
import switch2021.project.dtoModel.dto.ProjectDTO;
import switch2021.project.dtoModel.mapper.ProjectMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Resource.ManagementResourcesService;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.factories.factoryInterfaces.IProjectFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class ProjectService {

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
    private IUserIDFactory userIDFactory;
    @Autowired
    private IResourceRepo resRepo;
    @Autowired
    private ManagementResourcesService resService;
    @Autowired
    private IProjectWebRepository iProjectWebRepository;


    public ProjectService() {
    }

    public OutputProjectDTO createAndSaveProject(ProjectDTO projDTO) throws Exception {

        Project newProject;

        if (iTypologyRepo.existsByTypologyId(new TypologyID(new Description(projDTO.typology/*.toLowerCase(Locale
        .ROOT)*/)))) {
            newProject = iProjectFactory.createProject(projDTO);
            ProjectID projID =
                    new ProjectID("Project_" + LocalDate.now().getYear() + "_" + (projRepo.findAll().size() + 1));

            newProject.setProjectCode(projID);
        } else {
            throw new Exception("Typology does not exist");
        }
        if (!projRepo.existsById(newProject.getProjectCode())) {
            Project savedProject = projRepo.save(newProject);
            return projMapper.model2Dto(savedProject);
        } else {
            throw new Exception("Project already exist.");
        }
    }

    /**
    * US008
     */

    public OutputProjectDTO updateProjectPartially(String id, EditProjectInfoDTO editProjectInfoDTO) {
        ProjectID projID = new ProjectID(id);

        Optional<Project> opProject = projRepo.findById(projID);
        Project proj;

        if (opProject.isPresent()) {
            proj = opProject.get();
            proj.setProjectName(new Description(editProjectInfoDTO.projectName));
            proj.setDescription(new Description(editProjectInfoDTO.description));
            proj.setStartDate(LocalDate.parse(editProjectInfoDTO.startDate));
            proj.setNumberOfSprints(new NumberOfSprints(Integer.parseInt(editProjectInfoDTO.numberOfSprints)));
            proj.setBudget(new Budget(Integer.parseInt(editProjectInfoDTO.budget)));
            proj.setSprintDuration(new SprintDuration(Integer.parseInt(editProjectInfoDTO.sprintDuration)));

            proj.setProjectStatus(ProjectStatusEnum.valueOf(editProjectInfoDTO.projectStatus.toUpperCase()));
            proj.setCustomer(new Customer(editProjectInfoDTO.customer));
            proj.setEndDate(LocalDate.parse(editProjectInfoDTO.endDate));

            if (iTypologyRepo.existsByTypologyId(new TypologyID(new Description(editProjectInfoDTO.typology)))) {
                proj.setTypologyId(new TypologyID(new Description(editProjectInfoDTO.typology)));
            }

            Project savedProject = projRepo.save(proj);

            return projMapper.model2Dto(savedProject);
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

    public CollectionModel<OutputProjectDTO> getAllProjects() {

        List<Project> projects = projRepo.findAll();
        List<Project> projectsWeb = iProjectWebRepository.findAll();

        CollectionModel<OutputProjectDTO> outputProjectDTOS = projMapper.toCollectionDto(projectsWeb);
        CollectionModel<OutputProjectDTO> outputProjectDTOS2= projMapper.toCollectionDto(projects);

        List<OutputProjectDTO> newList = Stream.concat(outputProjectDTOS.getContent().stream(), outputProjectDTOS2.getContent().stream())
                .collect(Collectors.toList());

        return CollectionModel.of(newList);
    }

    public OutputProjectDTO showProject(String id) throws Exception {
//        String[] x = id.split("_");
        ProjectID projID = new ProjectID(id);

        Optional<Project> foundProject = projRepo.findById(projID);

        if (foundProject.isEmpty()) {
            throw new Exception("Project does not exist");
        }

        return projMapper.model2Dto(foundProject.get());
    }

    public List<OutputProjectDTO> showCurrentProjectsByUser(String UserId) {
        List<OutputProjectDTO> projectsDto = new ArrayList<>();
        UserID userID = userIDFactory.createUserID(UserId);

        if (userRepo.existsById(userID)) {
            List<Resource> userResources = resRepo.findAllByUser(userID);

            List<Resource> currentUserResources = resService.currentResourcesByDate(userResources);

            List<ProjectID> resourceProjects = resService.listProjectsOfResources(currentUserResources);

            List<Project> projects = new ArrayList<>();

            for (ProjectID projId : resourceProjects) {

                Project proj = projRepo.findById(projId).get();

                projects.add(proj);

            }

            for (Project proj : projects) {

                OutputProjectDTO projDto = projMapper.model2Dto(proj);

                projectsDto.add(projDto);

            }
        }
        return projectsDto;
    }

    public void deleteProjectRequest(ProjectID id) throws Exception {
        if (!projRepo.deleteByProjectID(id)) {
            throw new Exception("Project does not exist");
        }
    }
}
