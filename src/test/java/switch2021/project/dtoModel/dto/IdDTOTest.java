package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdDTOTest {
    @Test
    void getSystemUserID() {
        //Arrange
        String id = "zbz@mymail.com";

        IdDTO dtoAll = new IdDTO(id);
        //Act + Assert
        assertEquals(id, dtoAll.getId());

    }
}
