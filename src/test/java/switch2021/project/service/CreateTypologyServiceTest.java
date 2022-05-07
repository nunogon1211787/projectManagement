package switch2021.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.factoryInterface.IFactoryTypology;
import switch2021.project.interfaces.IRepoTypology;
import switch2021.project.mapper.TypologyMapper;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.TypologyID;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration
public class CreateTypologyServiceTest {


    @MockBean
    IRepoTypology iRepoTypology;
    @MockBean
    IFactoryTypology iFactoryTypology;
    @MockBean
    TypologyMapper typologyMapper;
    @InjectMocks
    CreateTypologyService createTypologyService;

    @Autowired
    CreateTypologyService service1;

    @BeforeEach
    void setUp() throws Exception {MockitoAnnotations.openMocks(this);}

    @Test
    public void createAndSaveTypologySuccess() {
        //Arrange
        TypologyDTO dto = mock(TypologyDTO.class);
        Description des = mock(Description.class);
        TypologyID id = mock((TypologyID.class));
        Typology typo = mock(Typology.class);
        //Act
        when(dto.getDescription()).thenReturn("Fixed Cost");
        when(iFactoryTypology.createTypology(dto)).thenReturn(typo);
        when(iRepoTypology.saveTypology(typo)).thenReturn(true);
        when(des.getText()).thenReturn("Fixed Cost");
        when(id.getDescription()).thenReturn(des);
        when(typo.hasID_Description(des.getText())).thenReturn(false);
        when(typologyMapper.modelToDto(typo)).thenReturn(dto);
        //Assert
        assertEquals("Fixed Cost", createTypologyService.createAndSaveTypology(dto).getDescription());
    }

//    @Test
//    public void createAndSaveTypologyFail() {
//        //Arrange
//        TypologyDTO dto = mock(TypologyDTO.class);
//        Description des = mock(Description.class);
//        TypologyID id = mock((TypologyID.class));
//        Typology typo = mock(Typology.class);
//        //Act
//        when(dto.getDescription()).thenReturn("Fixed Cost");
//        when(iFactoryTypology.createTypology(dto)).thenReturn(typo);
//        when(iRepoTypology.saveTypology(typo)).thenReturn(false);
//        when(des.getText()).thenReturn("Fixed Cost");
//        when(id.getDescription()).thenReturn(des);
//        when(typo.hasID_Description(des.getText())).thenReturn(true);
//        //Assert
//        assertNull(createTypologyService.createAndSaveTypology(dto));
//    }
//
//    @Test
//    public void findTypologyByDescription() {
//        //Arrange
//        TypologyDTO dto = mock(TypologyDTO.class);
//        Description des = mock(Description.class);
//        TypologyID id = mock((TypologyID.class));
//        Typology typo = mock(Typology.class);
//        //Act
//        when(dto.getDescription()).thenReturn("Fixed Cost");
//        when(iFactoryTypology.createTypology(dto)).thenReturn(typo);
//        when(iRepoTypology.saveTypology(typo)).thenReturn(false);
//        when(des.getText()).thenReturn("Fixed Cost");
//        when(id.getDescription()).thenReturn(des);
//        when(typo.getId_description()).thenReturn(id);
//        when(typo.hasID_Description(des.getText())).thenReturn(true);
//        createTypologyService.createAndSaveTypology(dto);
//        when(iRepoTypology.findTypologyByDescription("Fixed Cost")).thenReturn(typo);
//        when(typologyMapper.modelToDto(typo)).thenReturn(dto);
//        //Assert
//        assertNotNull(createTypologyService.findTypologyByDescription("Fixed Cost"));
//    }

    @Test
    public void findTypologyByDescription_Null() {
        //Arrange
        TypologyDTO dto = mock(TypologyDTO.class);
        Description des = mock(Description.class);
        TypologyID id = mock((TypologyID.class));
        Typology typo = mock(Typology.class);
        //Act
        when(dto.getDescription()).thenReturn("Fixed Cost");
        when(iFactoryTypology.createTypology(dto)).thenReturn(typo);
        when(iRepoTypology.saveTypology(typo)).thenReturn(false);
        when(des.getText()).thenReturn("Fixed Cost");
        when(id.getDescription()).thenReturn(des);
        when(typo.getId_description()).thenReturn(id);
        when(typo.hasID_Description(des.getText())).thenReturn(true);
        when(iRepoTypology.findTypologyById("Fixed Cost")).thenReturn(null);
        //Assert
        assertNull(createTypologyService.findTypologyByDescription(dto));
    }

    @Test
    public void findAllTypology() {
        //Arrange
        TypologyDTO dto = mock(TypologyDTO.class);
        TypologyDTO dto1 = mock(TypologyDTO.class);
        TypologyDTO dto2 = mock(TypologyDTO.class);
        Description des = mock(Description.class);
        Description des1 = mock(Description.class);
        Description des2 = mock(Description.class);
        TypologyID id = mock((TypologyID.class));
        TypologyID id1 = mock((TypologyID.class));
        TypologyID id2 = mock((TypologyID.class));
        Typology typo = mock(Typology.class);
        Typology typo1 = mock(Typology.class);
        Typology typo2 = mock(Typology.class);
        //Act
        when(dto.getDescription()).thenReturn("Test");
        when(dto1.getDescription()).thenReturn("Test1");
        when(dto2.getDescription()).thenReturn("Test2");
        when(iFactoryTypology.createTypology(dto)).thenReturn(typo);
        when(iFactoryTypology.createTypology(dto1)).thenReturn(typo1);
        when(iFactoryTypology.createTypology(dto2)).thenReturn(typo2);
        when(iRepoTypology.saveTypology(typo)).thenReturn(true);
        when(iRepoTypology.saveTypology(typo1)).thenReturn(true);
        when(iRepoTypology.saveTypology(typo2)).thenReturn(true);
        when(des.getText()).thenReturn("Test");
        when(des.getText()).thenReturn("Test1");
        when(des.getText()).thenReturn("Test2");
        when(id.getDescription()).thenReturn(des);
        when(id1.getDescription()).thenReturn(des1);
        when(id2.getDescription()).thenReturn(des2);
        when(typo.hasID_Description(des.getText())).thenReturn(false);
        when(typo1.hasID_Description(des1.getText())).thenReturn(false);
        when(typo2.hasID_Description(des2.getText())).thenReturn(false);
        when(iRepoTypology.findAllTypology()).thenReturn(List.of(new Typology[]{typo, typo1, typo2}));
        when(typologyMapper.modelToDto(iRepoTypology.findAllTypology())).thenReturn(List.of(new TypologyDTO[]{dto,
                dto1, dto2}));
        //Assert
        assertEquals(3, createTypologyService.findAllTypologies().size());
    }

//    @Test //REVIEW TEST
//    public void deleteTypology() {
//        //Arrange
//        TypologyDTO dto = mock(TypologyDTO.class);
//        TypologyDTO dto1 = mock(TypologyDTO.class);
//        TypologyDTO dto2 = mock(TypologyDTO.class);
//        Description des = mock(Description.class);
//        Description des1 = mock(Description.class);
//        Description des2 = mock(Description.class);
//        TypologyID id = mock((TypologyID.class));
//        TypologyID id1 = mock((TypologyID.class));
//        TypologyID id2 = mock((TypologyID.class));
//        Typology typo = mock(Typology.class);
//        Typology typo1 = mock(Typology.class);
//        Typology typo2 = mock(Typology.class);
//        //Act
//        when(dto.getDescription()).thenReturn("Test");
//        when(dto1.getDescription()).thenReturn("Test1");
//        when(dto2.getDescription()).thenReturn("Test2");
//        when(iFactoryTypology.createTypology(dto)).thenReturn(typo);
//        when(iFactoryTypology.createTypology(dto1)).thenReturn(typo1);
//        when(iFactoryTypology.createTypology(dto2)).thenReturn(typo2);
//        when(iRepoTypology.saveTypology(typo)).thenReturn(true);
//        when(iRepoTypology.saveTypology(typo1)).thenReturn(true);
//        when(iRepoTypology.saveTypology(typo2)).thenReturn(true);
//        when(iRepoTypology.getTypologyList()).thenReturn(List.of(new Typology[]{typo, typo1}));
//        when(des.getText()).thenReturn("Test");
//        when(des.getText()).thenReturn("Test1");
//        when(des.getText()).thenReturn("Test2");
//        when(id.getDescription()).thenReturn(des);
//        when(id1.getDescription()).thenReturn(des1);
//        when(id2.getDescription()).thenReturn(des2);
//        createTypologyService.deleteTypology("Test2");
//        //Assert
//        assertEquals(2, iRepoTypology.getTypologyList().size());
//    }
//
//    @Test
//    @DisplayName("Integration Test")
//    public void createTypologyIntegrationTest() {
//        //Arrange
//        TypologyDTO inputDto = new TypologyDTO();
//        inputDto.description = "Test Service Integration";
//        service1.createAndSaveTypology(inputDto);
//        //Act
//        TypologyDTO outputDto = service1.createAndSaveTypology(inputDto);
//        //Assert
//        assertEquals("Typology already exists!" , outputDto.description);
//    }
}
