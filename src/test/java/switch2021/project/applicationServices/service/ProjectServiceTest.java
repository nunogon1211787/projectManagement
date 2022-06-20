package switch2021.project.applicationServices.service;

import com.sun.xml.bind.v2.schemagen.xmlschema.LocalAttribute;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import switch2021.project.entities.factories.factoryInterfaces.IProjectFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.*;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjectServiceTest {

    @MockBean
    private IProjectRepo projRepo;
    @MockBean
    private ITypologyRepo iTypologyRepo;
    @MockBean
    private IProjectFactory iProjectFactory;
    @MockBean
    private ProjectMapper projMapper;
    @MockBean
    private IUserRepo userRepo;
    @MockBean
    private IUserIDFactory userIDFactory;
    @MockBean
    private IResourceRepo resRepo;
    @MockBean
    private ManagementResourcesService resService;
    @MockBean
    private ITypologyIDFactory typologyIDFactory;
    @MockBean
    private IProjectIDFactory projectIDFactory;
    @MockBean
    private IDescriptionFactory descriptionFactory;
    @MockBean
    private INumberOfSprintsFactory numberOfSprintsFactory;
    @MockBean
    private IBudgetFactory budgetFactory;
    @MockBean
    private ISprintDurationFactory sprintDurationFactory;

    @InjectMocks
    ProjectService projectService;

    @BeforeEach
    void TestConfiguration(){
        MockitoAnnotations.openMocks(this);
    }

    /*Unitary tests to be implemented:

    createAndSave():

    void
    check setCode()

    Fail
    Typology not exist
    Project exist

    Success

    updateProject():

    void
    check all sets()

    Fail
    Project not exist

    Success
    with Typology exist
    with Typology not exist

    showAllProjects():

    success

    showProject():

    Fail
    project not exist

    success

    showProjectsByUser():

    Fail
    user not exist

    success

    */

    /**
     * Unitary tests to createAndSaveProject() method.
     */

    @Test
    void createProjectWithTypoNotExist(){
        //Assert
        assertThrows(Exception.class, () -> {
           //Arrange
            ProjectDTO projDto = mock(ProjectDTO.class);
            TypologyID typoId = mock(TypologyID.class);
            String typo = "not exist";
            when(projDto.getTypology()).thenReturn(typo);
            when(typologyIDFactory.createIdWithString(typo)).thenReturn(typoId);
            when(iTypologyRepo.existsByTypologyId(typoId)).thenReturn(false);
           //Act
            projectService.createAndSaveProject(projDto);
        });
    }

    @Test
    void createProjectWhenProjectAlreadyExist(){
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            ProjectDTO projDto = mock(ProjectDTO.class);
            TypologyID typoId = mock(TypologyID.class);
            String typo = "exist";
            Project newProject = mock(Project.class);
            ProjectID projId = mock(ProjectID.class);
            String id = "already exist";
            List<Project> allProjects = new ArrayList<>();
            when(projDto.getTypology()).thenReturn(typo);
            when(typologyIDFactory.createIdWithString(typo)).thenReturn(typoId);
            when(iTypologyRepo.existsByTypologyId(typoId)).thenReturn(true);
            when(iProjectFactory.createProject(projDto)).thenReturn(newProject);
            when(projRepo.findAll()).thenReturn(allProjects);
            when(projectIDFactory.create(id)).thenReturn(projId);
            when(newProject.getProjectCode()).thenReturn(projId);
            when(projRepo.existsById(projId)).thenReturn(true);
            //Act
            projectService.createAndSaveProject(projDto);
        });
    }

    @Test
    void createAndSaveProjectSuccess() throws Exception {
        //Arrange
        ProjectDTO projDto = mock(ProjectDTO.class);
        TypologyID typoId = mock(TypologyID.class);
        String typo = "exist";
        Project newProject = mock(Project.class);
        ProjectID projId = mock(ProjectID.class);
        String id = "already exist";
        List<Project> allProjects = new ArrayList<>();
        Project savedProject = mock(Project.class);
        OutputProjectDTO outDto = mock(OutputProjectDTO.class);

        when(projDto.getTypology()).thenReturn(typo);
        when(typologyIDFactory.createIdWithString(typo)).thenReturn(typoId);
        when(iTypologyRepo.existsByTypologyId(typoId)).thenReturn(true);
        when(iProjectFactory.createProject(projDto)).thenReturn(newProject);
        when(projRepo.findAll()).thenReturn(allProjects);
        when(projectIDFactory.create(id)).thenReturn(projId);
        when(projId.getCode()).thenReturn(id);
        doAnswer(invocationOnMock -> {
            ProjectID arg = invocationOnMock.getArgument(0);

            assertEquals(id, arg.getCode());
            return null;
        }).when(newProject).setProjectCode(projId);
        when(newProject.getProjectCode()).thenReturn(projId);
        when(projRepo.existsById(projId)).thenReturn(false);
        when(projRepo.save(newProject)).thenReturn(savedProject);
        when(projMapper.model2Dto(savedProject)).thenReturn(outDto);
        //Act
        OutputProjectDTO result = projectService.createAndSaveProject(projDto);
        //Assert
        assertEquals(outDto, result);
    }

    /**
     * Unitary tests to updateProjectPartially() method.
     */

    @Test
    void updateProjectWithProjectNotExist(){
        //Assert
        assertThrows(Exception.class, () -> {
           //Arrange
           String id = "not exist";
           EditProjectInfoDTO dto = mock(EditProjectInfoDTO.class);
           ProjectID projId = mock(ProjectID.class);
           Optional<Project> opt = Optional.empty();
           when(projectIDFactory.create(id)).thenReturn(projId);
           when(projRepo.findById(projId)).thenReturn(opt);
           //Act
           projectService.updateProjectPartially(id, dto);
        });
    }

    @Test
    void updateProjectWithTypoNotExist() throws Exception {
        //Arrange
        String id = "exist";
        EditProjectInfoDTO dto = mock(EditProjectInfoDTO.class);
        ProjectID projId = mock(ProjectID.class);
        Project proj = mock(Project.class);
        Optional<Project> opt = Optional.of(proj);
        TypologyID typoId = mock(TypologyID.class);
        Project savedProj = mock(Project.class);
        OutputProjectDTO expected = mock(OutputProjectDTO.class);
        Description description = mock(Description.class);
        when(projectIDFactory.create(id)).thenReturn(projId);
        when(projRepo.findById(projId)).thenReturn(opt);

        String resGet = "get something";

        when(dto.getProjectName()).thenReturn(resGet);
        when(dto.getDescription()).thenReturn(resGet);
        when(descriptionFactory.createDescription(resGet)).thenReturn(description);
        doNothing().when(proj).setProjectName(description);
        doNothing().when(proj).setDescription(description);

        String date = "2022-06-30";
        when(dto.getStartDate()).thenReturn(date);
        doNothing().when(proj).setStartDate(LocalDate.parse(date));

        String number = "1";
        NumberOfSprints num = mock(NumberOfSprints.class);
        when(dto.getNumberOfSprints()).thenReturn(number);
        when(numberOfSprintsFactory.create(Integer.parseInt(number))).thenReturn(num);
        doNothing().when(proj).setNumberOfSprints(num);

        Budget budget = mock(Budget.class);
        when(dto.getBudget()).thenReturn(number);
        when(budgetFactory.create(Integer.parseInt(number))).thenReturn(budget);
        doNothing().when(proj).setBudget(budget);

        SprintDuration duration = mock(SprintDuration.class);
        when(dto.getSprintDuration()).thenReturn(number);
        when(sprintDurationFactory.create(Integer.parseInt(number))).thenReturn(duration);
        doNothing().when(proj).setSprintDuration(duration);

        when(dto.getProjectStatus()).thenReturn(resGet);
        doNothing().when(proj).setProjectStatus(ProjectStatusEnum.valueOf(resGet));

        Customer customer = mock(Customer.class);
        when(dto.getCustomer()).thenReturn(resGet);
        doNothing().when(proj).setCustomer(customer);

        when(dto.getEndDate()).thenReturn(date);
        doNothing().when(proj).setEndDate(LocalDate.parse(date));

        when(dto.getTypology()).thenReturn(resGet);
        when(typologyIDFactory.createIdWithString(resGet)).thenReturn(typoId);

        when(iTypologyRepo.existsByTypologyId(typoId)).thenReturn(false);
        when(projRepo.save(proj)).thenReturn(savedProj);
        when(projMapper.model2Dto(savedProj)).thenReturn(expected);

        //Act
        OutputProjectDTO result = projectService.updateProjectPartially(id, dto);
        //Assert
        assertEquals(expected, result);
    }




}