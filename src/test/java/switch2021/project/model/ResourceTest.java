package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {

    @Test
    @DisplayName("Teste de criação de Resource")
    public void associateResource() {
        //Arrange


        LocalDate startDate = LocalDate.of(2021,12,12);
        LocalDate endDate = LocalDate.of(2022,01,05);


        //
        Resource assResource = new Resource(2, "123testcode", startDate, endDate, 100, 50 );

        //Act

        //Assert

    }


}