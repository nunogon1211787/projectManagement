package switch2021.project.dtoModel.mapper;

import org.junit.jupiter.api.Test;
import switch2021.project.dtoModel.dto.TypologyDTO;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.TypologyID;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TypologyMapperTest {

    @Test
    public void typologyDTOListSuccess() {
        //Arrange
        TypologyMapper mapper = new TypologyMapper();
        Typology typo1 = mock(Typology.class);
        Typology typo2 = mock(Typology.class);
        Typology typo3 = mock(Typology.class);
        Typology typo4 = mock(Typology.class);
        TypologyID id = mock(TypologyID.class);
        Description des = mock(Description.class);
        List<Typology> list = new ArrayList<>();
        // Act
        list.add(typo1);
        list.add(typo2);
        list.add(typo3);
        list.add(typo4);
        when(typo1.getId_description()).thenReturn(id);
        when(typo2.getId_description()).thenReturn(id);
        when(typo3.getId_description()).thenReturn(id);
        when(typo4.getId_description()).thenReturn(id);
        when(id.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("Test");
        List<TypologyDTO> dto = mapper.modelToDto(list);
        //Assert
        assertEquals(4, dto.size());
    }

    @Test
    public void dto() {
        //Arrange
        TypologyMapper mapper = new TypologyMapper();
        Typology typo = mock(Typology.class);
        TypologyID id = mock(TypologyID.class);
        Description des = mock(Description.class);
        //Act
        when(typo.getId_description()).thenReturn(id);
        when(id.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("Test");
        TypologyDTO dto = mapper.modelToDto(typo);
        //Assert
        assertEquals("Test", dto.description);
    }

    @Test
    public void dtoNull() {
        //Arrange
        TypologyMapper mapper = new TypologyMapper();
        Typology typo = mock(Typology.class);
        TypologyID id = mock(TypologyID.class);
        Description des = mock(Description.class);
        //Act
        when(typo.getId_description()).thenReturn(id);
        when(id.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("Test");
        TypologyDTO dto = null;
        //Assert
        assertNotEquals(dto, mapper.modelToDto(typo));
    }
}
