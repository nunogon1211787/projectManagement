package switch2021.project.mapper;

import org.junit.jupiter.api.Test;
import switch2021.project.dto.ScrumBoardDTO;
import switch2021.project.model.UserStory;
import static org.junit.jupiter.api.Assertions.*;

public class ScrumBoardMapperTest {


    @Test
    public void scrumBoardDTOTest() {
        //Arrange
        ScrumBoardMapper mapper = new ScrumBoardMapper();
        UserStory userStory = new UserStory("name", 5, "description", 5);
        userStory.setIdUserStory(0);

        //Act
        ScrumBoardDTO dto = mapper.toDTO(userStory);

        //Assert
        assertEquals(dto.getUserStoryName(),userStory.getName());
        assertEquals(dto.getUserStoryName(),"name");
        assertEquals(dto.getUserStoryDescription(),userStory.getDescription());
        assertEquals(dto.getUserStoryDescription(),"description");
        assertEquals(dto.getUserStoryPriority(),userStory.getPriority());
        assertEquals(dto.getUserStoryPriority(),5);
        assertEquals(dto.getUserStoryTimeEstimate(),userStory.getTimeEstimate());
        assertEquals(dto.getUserStoryPriority(),5);
        assertEquals(dto.getUserStoryStatus(),userStory.getUserStoryStatus().getDescription());
        assertEquals(dto.getUserStoryStatus(),"To do");
        assertEquals(dto.getUserStoryId(),userStory.getIdUserStory());
        assertEquals(dto.getUserStoryId(),0);
    }

    @Test
    public void hashCodeTest() {
        //Arrange
        ScrumBoardMapper mapper = new ScrumBoardMapper();
        UserStory userStory = new UserStory("name", 5, "description", 5);
        UserStory userStory_2 = new UserStory("name_2", 4, "description_2", 6);

        //Act
        ScrumBoardDTO dto = mapper.toDTO(userStory);
        ScrumBoardDTO dto_2 = mapper.toDTO(userStory_2);

        //Assert
        assertNotEquals(dto.hashCode(),dto_2.hashCode());
    }
}
