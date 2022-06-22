package switch2021.project.applicationServices.service;

import org.apache.commons.codec.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.*;
import switch2021.project.dtoModel.dto.EditProjectInfoDTO;
import switch2021.project.dtoModel.dto.OutputProjectDTO;
import switch2021.project.dtoModel.dto.PartialProjectDTO;
import switch2021.project.dtoModel.dto.ProjectDTO;
import switch2021.project.dtoModel.mapper.ProjectMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Resource.ManagementResourcesService;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.factories.factoryInterfaces.IProjectFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.*;
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
    @Autowired
    private ITypologyIDFactory typologyIDFactory;
    @Autowired
    private IProjectIDFactory projectIDFactory;
    @Autowired
    private IDescriptionFactory descriptionFactory;
    @Autowired
    private INumberOfSprintsFactory numberOfSprintsFactory;
    @Autowired
    private IBudgetFactory budgetFactory;
    @Autowired
    private ISprintDurationFactory sprintDurationFactory;


    /**
     * Methods to execute controller requests to Project Aggregate
     */


    public OutputProjectDTO createAndSaveProject(ProjectDTO projDTO) throws Exception {

        Project newProject;

        if (iTypologyRepo.existsByTypologyId(typologyIDFactory.createIdWithString(projDTO.getTypology()))) {
            newProject = iProjectFactory.createProject(projDTO);
            ProjectID projID = generatedProjectId();

            newProject.setProjectCode(projID);
        } else {
            throw new IllegalArgumentException("Typology does not exist");
        }
        if (!projRepo.existsById(newProject.getProjectCode())) {
            Project savedProject = projRepo.save(newProject);
            return projMapper.model2Dto(savedProject);
        } else {
            throw new IllegalArgumentException("Project already exist.");
        }

    }

    private ProjectID generatedProjectId(){
        String format = "Project_" + LocalDate.now().getYear() + "_";
        int sequenceNumber = projRepo.findAll().size() + 1;
        String id = format + sequenceNumber;
        return projectIDFactory.create(id);
    }

    /**
     * US008
     */

    public OutputProjectDTO updateProjectPartially(String id, EditProjectInfoDTO editProjectInfoDTO) {

        ProjectID projID = projectIDFactory.create(id);

        Optional<Project> opProject = projRepo.findById(projID);

        if (opProject.isPresent()) {
            Project proj = opProject.get();
            proj.setProjectName(descriptionFactory.createDescription(editProjectInfoDTO.getProjectName()));
            proj.setDescription(descriptionFactory.createDescription(editProjectInfoDTO.getDescription()));
            proj.setStartDate(LocalDate.parse(editProjectInfoDTO.getStartDate()));
            proj.setNumberOfSprints(numberOfSprintsFactory.create(Integer.parseInt(editProjectInfoDTO.getNumberOfSprints())));
            proj.setBudget(budgetFactory.create(Integer.parseInt(editProjectInfoDTO.getBudget())));
            proj.setSprintDuration(sprintDurationFactory.create(Integer.parseInt(editProjectInfoDTO.getSprintDuration())));

            proj.setProjectStatus(ProjectStatusEnum.valueOf(editProjectInfoDTO.getProjectStatus().toUpperCase()));
            proj.setCustomer(Customer.create(editProjectInfoDTO.getCustomer()));
            proj.setEndDate(LocalDate.parse(editProjectInfoDTO.getEndDate()));

            TypologyID typoId = typologyIDFactory.createIdWithString(editProjectInfoDTO.getTypology());

            if (iTypologyRepo.existsByTypologyId(typoId)) {
                proj.setTypologyId(typoId);
            }

            Project savedProject = projRepo.save(proj);

            return projMapper.model2Dto(savedProject);
        }

        throw new IllegalArgumentException("Project does not exist.");
    }

    public Map<String, CollectionModel<PartialProjectDTO>>  getAllProjects() {

        List<Project> projects = projRepo.findAll();
        List<Project> projectsWeb = iProjectWebRepository.findAll();

        CollectionModel<PartialProjectDTO> outputProjectDTOS = projMapper.toCollectionDto2(projectsWeb, true);
        CollectionModel<PartialProjectDTO> outputProjectDTOS2 = projMapper.toCollectionDto2(projects, false);

        Map<String, CollectionModel<PartialProjectDTO>> mapProjects = new HashMap<>();
        mapProjects.put("internalProjects", outputProjectDTOS2);
        mapProjects.put("externalProjects", outputProjectDTOS);

        return mapProjects;
    }
    
    public OutputProjectDTO showProject(String id) throws Exception {

        ProjectID projID = projectIDFactory.create(id);

        Optional<Project> foundProject = projRepo.findById(projID);

        if (foundProject.isEmpty()) {
            throw new IllegalArgumentException("Project does not exist");
        }

        return projMapper.model2Dto(foundProject.get());
    }

    public CollectionModel<OutputProjectDTO> showCurrentProjectsByUser(String UserId) {

        UserID userID = userIDFactory.createUserID(UserId);

        if (userRepo.existsById(userID)) {

            List<Resource> userResources = resRepo.findAllByUser(userID);

            List<Resource> currentUserResources = resService.currentResourcesByDate(userResources);

            List<ProjectID> resourceProjects = resService.listProjectsOfResources(currentUserResources);

            List<Project> projects = resourceProjects.stream().map(projectID ->
                    projRepo.findById(projectID).get()
            ).collect(Collectors.toList());

            List<OutputProjectDTO> projectsDto = projects.stream().map(project ->
                    projMapper.model2Dto(project)
            ).collect(Collectors.toList());

            return CollectionModel.of(projectsDto);

            }

        throw new IllegalArgumentException("User dos not exist");
    }

    public boolean deleteProjectRequest(String id) {

        ProjectID projectId = projectIDFactory.create(id);

        if (!projRepo.delete(projectId)) {
            throw new IllegalArgumentException("Project does not exist");
        }

        return true;
    }
}
