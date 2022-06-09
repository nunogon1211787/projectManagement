package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.applicationServices.iRepositories.IResourceRepo;
import switch2021.project.applicationServices.iRepositories.ITypologyRepo;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.dto.DateDTO;
import switch2021.project.dtoModel.dto.EditProjectInfoDTO;
import switch2021.project.dtoModel.dto.OutputProjectDTO;
import switch2021.project.dtoModel.dto.ProjectDTO;
import switch2021.project.dtoModel.mapper.ProjectMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Resource.ManageResourcesService;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.factories.factoryInterfaces.IProjectFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    private ManageResourcesService resService;


    public ProjectService() {
    }

    public OutputProjectDTO createAndSaveProject(ProjectDTO projDTO) throws Exception {

        Project newProject;

        if (iTypologyRepo.existsByTypologyId(new TypologyID(new Description(projDTO.typology/*.toLowerCase(Locale.ROOT)*/)))) {
            newProject = iProjectFactory.createProject(projDTO);
            newProject.setProjectCode(new ProjectID("Project_" + LocalDate.now().getYear() + "_" + (projRepo.findAll().size() + 1)));
        }else {
            throw new Exception("Typology does not exist");
        }

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
//        String[] x = id.split("_");
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

            Optional<Project> savedProject = projRepo.save(proj);

            return savedProject.map(project -> projMapper.model2Dto(project)).orElse(null);
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
//        String[] x = id.split("_");
        ProjectID projID = new ProjectID(id);

        Optional<Project> foundProject = projRepo.findById(projID);

        if (foundProject.isEmpty()) {
            throw new Exception("Project does not exist");
        }

        return projMapper.model2Dto(foundProject.get());
    }

    public List<OutputProjectDTO> showCurrentProjectsByUser(String id, DateDTO dateDto) {
        List<OutputProjectDTO> projectsDto = new ArrayList<>();
        UserID userID = userIDFactory.createUserID(id);

        if (userRepo.existsById(userID)) {
            List<Resource> userResources = resRepo.findAllByUser(userID);

            List<Resource> currentUserResources = resService.currentResourcesByDate(userResources,
                                                                                    LocalDate.parse(dateDto.date));

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
