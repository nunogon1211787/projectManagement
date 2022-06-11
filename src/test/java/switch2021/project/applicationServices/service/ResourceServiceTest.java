package switch2021.project.applicationServices.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.applicationServices.iRepositories.IResourceRepo;
import switch2021.project.dtoModel.mapper.ResourceMapper;
import switch2021.project.entities.aggregates.Resource.ManagementResourcesService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
class ResourceServiceTest {

    @MockBean IProjectRepo projRepo;
    @MockBean IResourceRepo resRepo;
    @MockBean
    ResourceMapper map;
    @MockBean
    ManagementResourcesService dsrv;
    @InjectMocks
    ResourceService srv;

    @BeforeEach
    void TestConfiguration(){
        MockitoAnnotations.openMocks(this);
    }



//    @Test
//    void getProjectTeamSuccess(){
//        //Arrange
//        IdDTO idDto = mock(IdDTO.class);
//        DateDTO dateDto = mock(DateDTO.class);
//        ResourceReeng res = mock(ResourceReeng.class);
//        OutputResourceDTO resDto = mock(OutputResourceDTO.class);
//        List<ResourceReeng> resources = new ArrayList<>(List.of(res, res, res));
//        List<ResourceReeng> currentResources = new ArrayList<>(List.of(res, res));
//        //Act
//        when(projRepo.existById(idDto.id)).thenReturn(true);
//        when(resRepo.findAllByProject(idDto.id)).thenReturn(resources);
//        when(dsrv.currentResourcesByDate(resources, LocalDate.parse(dateDto.date))).thenReturn(currentResources);
//        when(map.model2Dto(res)).thenReturn(resDto);
//        List<OutputResourceDTO> result = srv.showCurrentProjectTeam(idDto, dateDto);
//        //Assert
//        List<OutputResourceDTO> expected = new ArrayList<>(List.of(resDto, resDto));
////        assertEquals(expected, result);
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