package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SprintTest {


    @Test
    @DisplayName("Teste ao Construtor")
    public void SprintConstructorSucess() {
        //Arrange
        Sprint sprint = new Sprint(1, "Sprint_1", LocalDate.now());
        //Act
        long x = sprint.getId();
        String name = sprint.getName();
        LocalDate date = sprint.getStartDate();
        //Assert
        assertEquals(1, x);
        assertEquals("Sprint_1", name);
        assertEquals(LocalDate.now(), date);
    }

    @Test
    @DisplayName("Teste ao Construtor, com falha no nome do Sprint")
    public void SprintConstructorNameEmptyFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Sprint sprint = new Sprint(1, " ", LocalDate.now());
        });
    }

    @Test
    @DisplayName("Teste ao Construtor, com falha no nome do Sprint")
    public void SprintConstructorNameLowLengthFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Sprint sprint = new Sprint(1, "S", LocalDate.now());
        });
    }

    @Test
    @DisplayName("Teste à alteração do EndDate")
    public void ChangeEndDateSucess() {

        //Assert
        Sprint sprint = new Sprint(1, "Sprint_1", LocalDate.now());
        sprint.changeEndDate(2);
        LocalDate endadate = sprint.getEndDate();
        //Assert
        assertEquals(LocalDate.of(2022, 2, 3), endadate);
    }


}
