package switch2021.project.mapper;

import org.junit.jupiter.api.Test;
import switch2021.project.dto.old.TypologyDTO;
import switch2021.project.mapper.old.TypologyMapper;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ID_Typology;

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
        ID_Typology id = mock(ID_Typology.class);
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
        List<TypologyDTO> dto = mapper.toDTO(list);
        //Assert
        assertEquals(4, dto.size());
    }

    @Test
    public void dto() {
        //Arrange
        TypologyMapper mapper = new TypologyMapper();
        Typology typo = mock(Typology.class);
        ID_Typology id = mock(ID_Typology.class);
        Description des = mock(Description.class);
        //Act
        when(typo.getId_description()).thenReturn(id);
        when(id.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("Test");
        TypologyDTO dto = mapper.toDTO(typo);
        //Assert
        assertEquals("Test", dto.getDescription());
    }

    @Test
    public void dtoNull() {
        //Arrange
        TypologyMapper mapper = new TypologyMapper();
        Typology typo = mock(Typology.class);
        ID_Typology id = mock(ID_Typology.class);
        Description des = mock(Description.class);
        //Act
        when(typo.getId_description()).thenReturn(id);
        when(id.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("Test");
        TypologyDTO dto = null;
        //Assert
        assertNotEquals(dto, mapper.toDTO(typo));
    }
}
