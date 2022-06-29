package switch2021.project.applicationServices.service;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
//import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.applicationServices.iRepositories.IResourceRepo;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
//import switch2021.project.dtoModel.dto.CreateResourceDTO;
//import switch2021.project.dtoModel.dto.OutputResourceDTO;
import switch2021.project.dtoModel.dto.OutputResourceDTO;
import switch2021.project.dtoModel.mapper.ResourceMapper;
import switch2021.project.entities.aggregates.Resource.ManagementResourcesService;
//import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.factories.factoryInterfaces.IResourceFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IResourceIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.UserID;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//import switch2021.project.entities.valueObjects.vos.ProjectID;
//import switch2021.project.entities.valueObjects.vos.ResourceID;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;

//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;

@SpringBootTest
class ResourceServiceTest {

    @MockBean
    private IProjectRepo projRepo;
    @MockBean
    private IResourceRepo resRepo;
    @MockBean
    private IUserRepo userRepo;
    @MockBean
    private ResourceMapper map;
    @MockBean
    private ManagementResourcesService manageSrv;
    @MockBean
    private IResourceFactory resourceFactory;
    @MockBean
    private IResourceIDFactory resourceIDFactory;
    @MockBean
    private IProjectIDFactory projectIDFactory;
    @MockBean
    private IUserIDFactory userIDFactory;

    @InjectMocks
    private ResourceService service;

    @BeforeEach
    void TestConfiguration() {
        MockitoAnnotations.openMocks(this);
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