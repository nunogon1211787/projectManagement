package switch2021.project.applicationServices.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.applicationServices.iRepositories.IResourceRepo;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.dto.CreateResourceDTO;
import switch2021.project.dtoModel.dto.OutputResourceDTO;
import switch2021.project.dtoModel.mapper.ResourceMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Resource.ManagementResourcesService;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.factories.factoryInterfaces.IResourceFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IResourceIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.valueObjects.vos.UserID;

import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ResourceServiceTest {


    @MockBean
    IProjectRepo projRepo;
    @MockBean
    IResourceRepo resRepo;
    @MockBean
    IUserRepo userRepo;
    @MockBean
    ResourceMapper mapper;
    @MockBean
    ManagementResourcesService manageSrv;
    @MockBean
    IResourceFactory resourceFactory;
    @MockBean
    IResourceIDFactory iResourceIDFactory;
    @MockBean
    IProjectIDFactory projectIDFactory;
    @MockBean
    IUserIDFactory userIDFactory;
    @InjectMocks
    ResourceService service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void CreateAndSaveResourceSuccess() {
//        //Arrange
//        CreateResourceDTO dto = mock(CreateResourceDTO.class);
//
//        String email = "tdc@mymail.com";
//        String code = "Project_2022_3";
//        String data = "2022-03-10";
//        String data2 = "2023-03-10";
//        String role = "ScrumMaster";
//        Double percentage = 0.9;
//
//        when(dto.getSystemUserID()).thenReturn(email);
//        when(dto.getProjectId()).thenReturn(code);
//        when(dto.getStartDate()).thenReturn(data);
//        when(dto.getStartDate()).thenReturn(data2);
//        when(dto.getPercentageOfAllocation()).thenReturn(percentage);
//        when(dto.getProjectRole()).thenReturn(role);
//
//        UserID userId = mock(UserID.class);
//        when(userIDFactory.createUserID(email)).thenReturn(userId);
//        when(userRepo.existsById(userId)).thenReturn(true);
//
//        ProjectID projID = mock(ProjectID.class);
//        when(projectIDFactory.create(code)).thenReturn(projID);
//        when(projRepo.existsById(projID)).thenReturn(true);
//
//        List<Resource> projectTeamList = new ArrayList<>();
//        Resource x = mock(Resource.class);
//        projectTeamList.add(x);
//        when(resRepo.findAllByProject(projID)).thenReturn(projectTeamList);
//
//        when(manageSrv.validateProjectRole(projectTeamList, data, data2, role)).thenReturn(true);
//
//        Project project = mock(Project.class);
//        when(projRepo.findById(projID)).thenReturn(Optional.of(project));
//
//        when(project.isActiveInThisDate(any())).thenReturn(true);
//
//        List<Resource> resourceAllocatedProjects = new ArrayList<>();
//        when(resRepo.findAllByUser(userId)).thenReturn(resourceAllocatedProjects);
//
//        when(manageSrv.validateAllocation(projectTeamList, data, data2, percentage)).thenReturn(true);
//
//        Resource newResource = mock(Resource.class);
//        ResourceID id = mock(ResourceID.class);
//        newResource.setId(id);
//        when(resourceFactory.createResource(dto)).thenReturn(newResource);
//
//        List<Resource> resources = new ArrayList<>();
//        ProjectID projID2 = mock(ProjectID.class);
//        when(resRepo.findAllByProject(projID2)).thenReturn(resources);
//
//        //jumping
//
//        when(resRepo.existsById(id)).thenReturn(true);
//
//        Resource saved = mock(Resource.class);
//        when(resRepo.save(newResource)).thenReturn(saved);
//
//        OutputResourceDTO expected = any();
//        when(mapper.toDto(saved)).thenReturn(expected);
//
//        OutputResourceDTO result = service.createAndSaveResource(dto);
//
//        assertEquals(expected, result);
//
//
//
//    }

    @SneakyThrows
    @Test
    void ShowResourceRequestedSuccess() {
        //Arrange
        ResourceID resID = mock(ResourceID.class);
        when(iResourceIDFactory.create("tdc@mymail.com", "Project_2022_3", "2022-03-10")).thenReturn(resID);
        when(projRepo.existsById(any())).thenReturn(true);
        when(userRepo.existsById(any())).thenReturn(true);
        Resource resource = mock(Resource.class);
        when(resRepo.findById(resID)).thenReturn(Optional.of(resource));
        OutputResourceDTO outputDTO = mock(OutputResourceDTO.class);
        when(mapper.toDto(resource)).thenReturn(outputDTO);
        //Act
        OutputResourceDTO result = service.showResourceRequested("tdc@mymail.com&Project_2022_3&2022-03-10");
        //Assert
        assertEquals(outputDTO, result);
    }

    @Test
    void ShowResourceRequestedNoProject() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            ResourceID resID = mock(ResourceID.class);
            when(iResourceIDFactory.create("tdc@mymail.com", "Project_2022_3", "2022-03-10")).thenReturn(resID);
            when(projRepo.existsById(any())).thenReturn(false);
            when(userRepo.existsById(any())).thenReturn(true);
            Resource resource = mock(Resource.class);
            when(resRepo.findById(resID)).thenReturn(Optional.of(resource));
            OutputResourceDTO outputDTO = mock(OutputResourceDTO.class);
            when(mapper.toDto(resource)).thenReturn(outputDTO);
            //Act
            service.showResourceRequested("tdc@mymail.com&Project_2022_3&2022-03-10");
        });
    }

    @Test
    void ShowResourceRequestedNoUser() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            ResourceID resID = mock(ResourceID.class);
            when(iResourceIDFactory.create("tdc@mymail.com", "Project_2022_3", "2022-03-10")).thenReturn(resID);
            when(projRepo.existsById(any())).thenReturn(true);
            when(userRepo.existsById(any())).thenReturn(false);
            Resource resource = mock(Resource.class);
            when(resRepo.findById(resID)).thenReturn(Optional.of(resource));
            OutputResourceDTO outputDTO = mock(OutputResourceDTO.class);
            when(mapper.toDto(resource)).thenReturn(outputDTO);
            //Act
            service.showResourceRequested("tdc@mymail.com&Project_2022_3&2022-03-10");
        });
    }

    @Test
    void ShowResourceRequestedNoResource() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            ResourceID resID = mock(ResourceID.class);
            when(iResourceIDFactory.create("tdc@mymail.com", "Project_2022_3", "2022-03-10")).thenReturn(resID);
            when(projRepo.existsById(any())).thenReturn(true);
            when(userRepo.existsById(any())).thenReturn(false);
            Resource resource = mock(Resource.class);
            when(resRepo.findById(resID)).thenReturn(null);
            OutputResourceDTO outputDTO = mock(OutputResourceDTO.class);
            when(mapper.toDto(resource)).thenReturn(outputDTO);
            //Act
            service.showResourceRequested("tdc@mymail.com&Project_2022_3&2022-03-10");
        });
    }

    @Test
    void showCurrentProjectTeamSuccess() {
        //Arrange
        String projectId = "projID";
        ProjectID projectID = mock(ProjectID.class);
        CollectionModel<OutputResourceDTO> outputResourcesDTOs = CollectionModel.empty();
        Resource res = mock(Resource.class);
        when(projectIDFactory.create(projectId)).thenReturn(projectID);
        when(projRepo.existsById(projectID)).thenReturn(true);
        List<Resource> resources = new ArrayList<>();
        when(resRepo.findAllByProject(projectID)).thenReturn(resources);
        List<Resource> projectTeam = new ArrayList<>();
        when(manageSrv.currentResourcesByDate(resources)).thenReturn(projectTeam);
        projectTeam.add(res);
        when(mapper.toCollectionDto(projectTeam)).thenReturn(outputResourcesDTOs);
        //Act
        CollectionModel<OutputResourceDTO> result = service.showCurrentProjectTeam(projectId);
        //Assert
        assertEquals(outputResourcesDTOs, result);
    }

    @Test
    void ShowCurrentProjectTeamNoResources() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            String projectId = "projID";
            ProjectID projectID = mock(ProjectID.class);
            CollectionModel<OutputResourceDTO> outputResourcesDTOs = CollectionModel.empty();
            when(projectIDFactory.create(projectId)).thenReturn(projectID);
            when(projRepo.existsById(projectID)).thenReturn(true);
            List<Resource> resources = new ArrayList<>();
            when(resRepo.findAllByProject(projectID)).thenReturn(resources);
            List<Resource> projectTeam = new ArrayList<>();
            when(manageSrv.currentResourcesByDate(resources)).thenReturn(projectTeam);
            when(mapper.toCollectionDto(projectTeam)).thenReturn(outputResourcesDTOs);
            //Act
            CollectionModel<OutputResourceDTO> result = service.showCurrentProjectTeam(projectId);
        });
    }

    @Test
    void showProjectTeamSuccess() {
        //Arrange
        ProjectID projID = mock(ProjectID.class);
        when(projectIDFactory.create(anyString())).thenReturn(projID);
        List<OutputResourceDTO> resourcesDto = new ArrayList<>();
        when(projRepo.existsById(projID)).thenReturn(true);
        List<Resource> projectTeam = new ArrayList<>();
        when(resRepo.findAllByProject(projID)).thenReturn(projectTeam);
        Resource x = mock(Resource.class);
        projectTeam.add(x);
        OutputResourceDTO outDTO = mock(OutputResourceDTO.class);
        when(mapper.toDto(any())).thenReturn(outDTO);
        resourcesDto.add(outDTO);
        //Act
        List<OutputResourceDTO> result = service.showProjectTeam("ok");
        //Assert
        assertEquals(resourcesDto, result);
    }

    @Test
    void ShowProjectTeamNoResources() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            ProjectID projID = mock(ProjectID.class);
            when(projectIDFactory.create(anyString())).thenReturn(projID);
            when(projRepo.existsById(projID)).thenReturn(true);
            when(resRepo.findAllByProject(projID)).thenReturn(null);
            OutputResourceDTO outDTO = mock(OutputResourceDTO.class);
            when(mapper.toDto(any())).thenReturn(outDTO);
            //Act
            service.showProjectTeam("ok");
        });
    }


    @Test
    void getAllResourcesSuccess() {
        //Arrange
        Resource x = mock(Resource.class);
        List<Resource> allResources = new ArrayList<>();
        allResources.add(x);
        when(resRepo.findAll()).thenReturn(allResources);
        List<OutputResourceDTO> allResDto = new ArrayList<>();
        OutputResourceDTO outDTO = mock(OutputResourceDTO.class);
        when(mapper.toDto(any())).thenReturn(outDTO);
        allResDto.add(outDTO);
        //Act
        List<OutputResourceDTO> result = service.showAllResources();
        //Assert
        assertEquals(allResDto, result);
    }

    @Test
    void getAllResourceNonExist() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            List<Resource> allResources = new ArrayList<>();
            when(resRepo.findAll()).thenReturn(allResources);
            OutputResourceDTO outDTO = mock(OutputResourceDTO.class);
            when(mapper.toDto(any())).thenReturn(outDTO);

            //Act
            service.showAllResources();
        });
    }

    @Test
    void deleteResourceTestSuccess() {
        //Assert
        assertDoesNotThrow(() -> {
            //Arrange
            String res = "tdc@mymail.com&Project_2022_3&2022-03-10";
            ResourceID resID = mock(ResourceID.class);
            when(iResourceIDFactory.create("tdc@mymail.com", "Project_2022_3", "2022-03-10")).thenReturn(resID);
            when(resRepo.existsById(resID)).thenReturn(true);
            when(resRepo.deleteByResourceID(resID)).thenReturn(true);
            //Act
            service.deleteResourceRequest(res);
        });
    }

    @Test
    void deleteResourceNonExist() {
        //Assert
        assertThrows(Exception.class, () -> {
            //Arrange
            String res = "tdc@mymail.com&Project_2022_3&2022-03-10";
            ResourceID resID = mock(ResourceID.class);
            when(iResourceIDFactory.create("tdc@mymail.com", "Project_2022_3", "2022-03-10")).thenReturn(resID);
            when(resRepo.existsById(resID)).thenReturn(false);
            when(resRepo.deleteByResourceID(resID)).thenReturn(true);
            //Act
            service.deleteResourceRequest(res);
        });
    }



//    @Test
//    void createAndSaveResourceSuccess() {
//        //Arrange
//        CreateResourceDTO resourceDTO = mock(CreateResourceDTO.class);
//        OutputResourceDTO outputResourceDTO = mock(OutputResourceDTO.class);
//        Project project = mock(Project.class);
//        Optional<Project> op = Optional.of(project);
//        List<Resource> resourceList = new ArrayList<>();
//        Resource resource = mock(Resource.class);
//        ResourceID id = mock(ResourceID.class);
//        ProjectID projectID = mock(ProjectID.class);
//        Resource saved = mock(Resource.class);
//        //Act
//        when(userRepo.existsById(null)).thenReturn(true);
//        when(projRepo.existsById(null)).thenReturn(true);
//        when(projRepo.findById(projectID)).thenReturn(op);
//        when(resource.getId()).thenReturn(id);
//        when(projRepo.findById(projectID)).thenReturn(op);
//        when(op.get()).thenReturn(project);
//        when(id.getProject()).thenReturn(projectID);
//        when(project.isActiveInThisDate(null)).thenReturn(true);
//        when(resRepo.findAllByUser(null)).thenReturn(resourceList);
//        when(resRepo.findAllByProject(projectID)).thenReturn(resourceList);
//        when(manageSrv.validateAllocation(resourceList, null, null, 0)).thenReturn(true);
//        when(manageSrv.validateProjectRole(resourceList, null, null, null)).thenReturn(true);
//        when(resourceFactory.createResource(resourceDTO)).thenReturn(resource);
//        when(resRepo.existsById(id)).thenReturn(false);
//        when(resRepo.save(resource)).thenReturn(saved);
//        when(map.toDto(saved)).thenReturn(outputResourceDTO);
//        OutputResourceDTO expected = service.createAndSaveResource(resourceDTO);
//        //Assert
//        assertEquals(expected, outputResourceDTO);
//    }

//    @Test
//    void getProjectTeamSuccess(){
//        //Arrange
//        String projId = "Project_2022_1";
//        ProjectID id = mock(ProjectID.class);
//        StartSprintDTO dateDto = mock(StartSprintDTO.class);
//        Resource res = mock(Resource.class);
//        OutputResourceDTO resDto = mock(OutputResourceDTO.class);
//        List<Resource> resources = new ArrayList<>(List.of(res, res, res));
//        List<Resource> currentResources = new ArrayList<>(List.of(res, res));
//        List<OutputResourceDTO> outputResourceDTOS = new ArrayList<>();
//        outputResourceDTOS.add(resDto);
//        outputResourceDTOS.add(resDto);
//        outputResourceDTOS.add(resDto);
//        //Act
//        when(projRepo.existsById(id)).thenReturn(true);
//        when(resRepo.findAllByProject(id)).thenReturn(resources);
//        when(manageSrv.currentResourcesByDate(resources)).thenReturn(currentResources);
//        when(map.toDto(res)).thenReturn(resDto);
//        CollectionModel<OutputResourceDTO> result = srv.showCurrentProjectTeam(projId);
//        CollectionModel<OutputResourceDTO> expected = CollectionModel.of(outputResourceDTOS);
//        //Assert
//        assertEquals(expected, result);
//    }
//
//    @Test
//    void getProjectTeamFail(){
//
//    }
//
//    @Test
//    void withProjectIdNotFound(){
//
//    }
//
//    @Test
//    void showProjectTeam() {
//        ProjectID proj = mock(ProjectID.class) ;
//        Resource res1 = mock(Resource.class) ;
//        Resource res2 = mock(Resource.class) ;
//        Resource res3 = mock(Resource.class) ;
//
//        List<Resource> projTeam = new ArrayList<>();
//
//        projTeam.add(res1);
//        projTeam.add(res2);
//        projTeam.add(res3);
//
//        when(projRepo.existsById(proj)).thenReturn(true);
//        when(resRepo.findAllByProject(proj)).thenReturn(projTeam);
//
//
//
//        List<OutputResourceDTO> result = srv.showProjectTeam(proj.toString());
//
//        assertEquals();
//
//    }

}