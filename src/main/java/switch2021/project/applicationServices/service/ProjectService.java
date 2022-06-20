package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.applicationServices.iRepositories.IResourceRepo;
import switch2021.project.applicationServices.iRepositories.ITypologyRepo;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.dto.EditProjectInfoDTO;
import switch2021.project.dtoModel.dto.OutputProjectDTO;
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

        if (iTypologyRepo.existsByTypologyId(typologyIDFactory.createIdWithString(projDTO.getTypology().toLowerCase()))) {
            newProject = iProjectFactory.createProject(projDTO);
            ProjectID projID = generatedProjectId();

            newProject.setProjectCode(projID);
        }else {
            throw new Exception("Typology does not exist");
        }
        if (!projRepo.existsById(newProject.getProjectCode())) {
            Project savedProject = projRepo.save(newProject);
            return projMapper.model2Dto(savedProject);
        } else {
            throw new Exception("Project already exist.");
        }

    }

    private ProjectID generatedProjectId(){
        String format = "Project_" + LocalDate.now().getYear() + "_";
        int sequenceNumber = projRepo.findAll().size() + 1;
        return projectIDFactory.create(format + sequenceNumber);
    }

    public OutputProjectDTO updateProjectPartially(String id, EditProjectInfoDTO editProjectInfoDTO) throws Exception {

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

            TypologyID typoId = typologyIDFactory.createIdWithString(editProjectInfoDTO.getTypology().toLowerCase());

            if (iTypologyRepo.existsByTypologyId(typoId)) {
                proj.setTypologyId(typoId);
            }

            Project savedProject = projRepo.save(proj);

            return projMapper.model2Dto(savedProject);
        }

        throw new Exception("Project does not exist.");
    }

    public CollectionModel<OutputProjectDTO> showAllProjects() {

        List<Project> projects = projRepo.findAll();

        return projMapper.toCollectionDto(projects);
    }

    public OutputProjectDTO showProject(String id) throws Exception {

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
            throw new Exception("Sprint does not exist");
        }
    }
}
