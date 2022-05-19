package switch2021.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.interfaces.IProjectRepo;
import switch2021.project.interfaces.IResourceRepo;
import switch2021.project.mapper.ResourceMapper;
import switch2021.project.model.Resource.ManageResourcesService;

import static org.mockito.Mockito.mock;

@SpringBootTest
class ResourceServiceTest {

    @MockBean IProjectRepo projRepo;
    @MockBean IResourceRepo resRepo;
    @MockBean ResourceMapper map;
    @MockBean ManageResourcesService dsrv;
    @InjectMocks
    ResourceService srv;

    @BeforeEach
    void TestConfiguration(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProjectTeamSuccess(){
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
//        assertEquals(expected, result);
    }

    @Test
    void getProjectTeamFail(){

    }

    @Test
    void withProjectIdNotFound(){

    }

}