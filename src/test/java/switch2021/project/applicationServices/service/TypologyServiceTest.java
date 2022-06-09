package switch2021.project.applicationServices.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import switch2021.project.dtoModel.dto.TypologyDTO;
import switch2021.project.entities.valueObjects.voFactories.voFactories.TypologyIDFactory;
import switch2021.project.entities.factories.factoryInterfaces.ITypologyFactory;
import switch2021.project.applicationServices.iRepositories.ITypologyRepo;
import switch2021.project.dtoModel.mapper.TypologyMapper;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.TypologyID;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TypologyServiceTest {

    @MockBean
    ITypologyRepo iTypologyRepo;
    @MockBean
    ITypologyFactory iTypologyFactory;
    @MockBean
    TypologyIDFactory factoryId;
    @MockBean
    TypologyMapper typologyMapper;
    @InjectMocks
    TypologyService typologyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createAndSaveTypologySuccess() {
        //Arrange
        TypologyDTO dto = mock(TypologyDTO.class);
        Description des = mock(Description.class);
        TypologyID id = mock((TypologyID.class));
        Typology typo = mock(Typology.class);
        //Act
        when(dto.getDescription()).thenReturn("Fixed Cost");
        when(iTypologyFactory.createTypology(dto)).thenReturn(typo);
        when(iTypologyRepo.save(typo)).thenReturn(typo);
        when(des.getText()).thenReturn("Fixed Cost");
        when(id.getDescription()).thenReturn(des);
        when(typo.hasID_Description(des.getText())).thenReturn(false);
        when(typologyMapper.modelToDto(typo)).thenReturn(dto);
        //Assert
        assertEquals("Fixed Cost", typologyService.createAndSaveTypology(dto).getDescription());
    }

    @Test
    public void createAndSaveTypologyFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            TypologyDTO dto = mock(TypologyDTO.class);
            Description des = mock(Description.class);
            TypologyID id = mock((TypologyID.class));
            Typology typo = mock(Typology.class);
            //Act
            when(dto.getDescription()).thenReturn("Fixed Cost");
            when(iTypologyFactory.createTypology(dto)).thenReturn(typo);
            when(iTypologyRepo.save(typo)).thenReturn(typo);
            when(iTypologyRepo.existsByTypologyId(typo.getId_description())).thenReturn(true);
            when(des.getText()).thenReturn("Fixed Cost");
            when(id.getDescription()).thenReturn(des);
            when(typo.hasID_Description(des.getText())).thenReturn(true);
            typologyService.createAndSaveTypology(dto);
        });
    }

//    @Test
//    public void findTypologyByDescription_Null() {
//        //Assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            //Arrange
//            TypologyDTO dto = mock(TypologyDTO.class);
//            Description des = mock(Description.class);
//            TypologyID id = mock((TypologyID.class));
//            Typology typo = mock(Typology.class);
//            when(factoryId.createId(dto)).thenReturn(id);
//            when(iTypologyFactory.createTypology(dto)).thenReturn(typo);
//            when(iTypologyRepo.save(typo)).thenReturn(false);
//            when(des.getText()).thenReturn("Fixed Cost");
//            when(id.getDescription()).thenReturn(des);
//            when(typo.getId_description()).thenReturn(id);
//            when(typo.hasID_Description(des.getText())).thenReturn(true);
//            when(iTypologyRepo.findByTypologyId(id)).thenReturn(null);
//            //Act
//            typologyService.findTypologyRequested("Fixed Cost");
//        });
//    }

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
        when(iTypologyFactory.createTypology(dto)).thenReturn(typo);
        when(iTypologyFactory.createTypology(dto1)).thenReturn(typo1);
        when(iTypologyFactory.createTypology(dto2)).thenReturn(typo2);
        when(iTypologyRepo.save(typo)).thenReturn(typo);
        when(iTypologyRepo.save(typo1)).thenReturn(typo1);
        when(iTypologyRepo.save(typo2)).thenReturn(typo2);
        when(des.getText()).thenReturn("Test");
        when(des.getText()).thenReturn("Test1");
        when(des.getText()).thenReturn("Test2");
        when(id.getDescription()).thenReturn(des);
        when(id1.getDescription()).thenReturn(des1);
        when(id2.getDescription()).thenReturn(des2);
        when(typo.hasID_Description(des.getText())).thenReturn(false);
        when(typo1.hasID_Description(des1.getText())).thenReturn(false);
        when(typo2.hasID_Description(des2.getText())).thenReturn(false);
        when(iTypologyRepo.findAll()).thenReturn(List.of(new Typology[]{typo, typo1, typo2}));
        when(typologyMapper.toCollectionModel(iTypologyRepo.findAll())).thenReturn(CollectionModel.of(List.of(new TypologyDTO[]{dto,
                dto1, dto2})));
        //Assert
        assertEquals(3, typologyService.findAllTypologies().getContent().size());
    }

//    @Test
//    public void findAllTypology_Empty() {
//        //Assert
//        assertThrows(NullPointerException.class, () -> {
//            //Arrange
//            when(iTypologyRepo.findAll()).thenReturn(List.of(new Typology[]{}));
//            //Act
//            typologyService.findAllTypologies();
//        });
//    }

//    @Test
//    public void deleteTypologyFail() {
//        //Assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            //Arrange
//            TypologyID id = mock(TypologyID.class);
//            //Act
//            when(iTypologyRepo.deleteByTypologyId(id)).thenReturn(false);
//            typologyService.deleteTypology("test");
//        });
//    }
}
