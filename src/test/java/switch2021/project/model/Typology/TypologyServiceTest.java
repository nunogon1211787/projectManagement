package switch2021.project.model.Typology;

import org.junit.jupiter.api.Test;
import switch2021.project.repositories.TypologyRepository;
import switch2021.project.service.TypologyService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TypologyServiceTest {

    @Test
    public void createAndSaveTypologySuccess(){
        //Arrange
        TypologyRepository repository = mock(TypologyRepository.class);
        TypologyService service = new TypologyService(repository);
        //Act
        when(repository.saveTypology(new Typology("Fixed Cost"))).thenReturn(true);
        //Assert
        assertTrue(service.createAndSaveTypology("Fixed Cost"));
    }

    @Test
    public void createAndSaveTypologyFail(){
        //Arrange
        TypologyRepository repository = mock(TypologyRepository.class);
        TypologyService service = new TypologyService(repository);
        Typology newTypo = mock(Typology.class);
        //Act
        when(repository.saveTypology(new Typology("Fixed Cost"))).thenReturn(false);
        //Assert
        assertFalse(service.createAndSaveTypology("Fixed Cost"));
    }
}
