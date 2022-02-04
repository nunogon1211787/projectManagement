package switch2021.project.mapper;

import org.junit.jupiter.api.Test;
import switch2021.project.dto.ScrumBoardDTO;
import switch2021.project.model.UserStory;
import switch2021.project.stores.UserStoryStatusStore;

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

    @Test
    public void overrideTest() {
        //Arrange
        UserStoryStatusStore store = new UserStoryStatusStore();
        store.populateDefault();
        ScrumBoardMapper mapper = new ScrumBoardMapper();
        UserStory userStory = new UserStory("name", 5, "description", 5);
        UserStory userStory_2 = new UserStory("name_2", 4, "description_2", 6);
        UserStory userStory_3 = new UserStory("name_2", 4, "description_2", 6);
        UserStory userStory_4 = new UserStory("name_2", 4, "description_3", 6);
        userStory_4.setUserStoryStatus(store.getUserStoryStatusByDescription("Done"));
        UserStory userStory_5 = new UserStory("name_2", 4, "description_3", 6);
        UserStory userStory_6 = new UserStory("name_asd", 4, "description_3", 6);
        UserStory userStory_7 = new UserStory("name_asd", 4, "description_7", 6);
        UserStory userStory_8 = new UserStory("name_asd", 4, "description_7", 6);
        userStory_8.setTimeEstimate(555);

        //Act
        ScrumBoardDTO dto = mapper.toDTO(userStory);
        ScrumBoardDTO dto_2 = mapper.toDTO(userStory_2);
        ScrumBoardDTO dto_3 = mapper.toDTO(userStory_3);
        ScrumBoardDTO dto_4 = mapper.toDTO(userStory_4);
        ScrumBoardDTO dto_5 = mapper.toDTO(userStory_5);
        ScrumBoardDTO dto_6 = mapper.toDTO(userStory_6);
        ScrumBoardDTO dto_7 = mapper.toDTO(userStory_7);
        ScrumBoardDTO dto_8 = mapper.toDTO(userStory_8);

        //Assert
        assertNotEquals(dto,dto_2);
        assertNotEquals(dto_4,dto_3);
        assertNotEquals(dto_4,dto_5);
        assertNotEquals(dto_6,dto_5);
        assertNotEquals(dto_6, null);
        assertNotEquals(dto_6, dto_7);
        assertNotEquals(dto_8, dto_7);
        assertEquals(dto_2,dto_3);
        assertEquals(dto.getClass(),dto_2.getClass());

    }
}
