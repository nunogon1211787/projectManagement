package switch2021.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dto.UserStoryStatusDTO;
import switch2021.project.model.UserStory.UserStory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScrumBoardMapperTest {


    @Test
    @DisplayName("Test DTO creation")
    public void scrumBoardDTOTest() {
        //Arrange
        ScrumBoardMapper mapper = new ScrumBoardMapper();
        UserStory userStory = new UserStory("As a PO, i want to test this string", 5, "description", 5);
        userStory.setIdUserStory(0);

        //Act
        UserStoryStatusDTO dto = mapper.toDTO(userStory);

        //Assert
        assertEquals(dto.getUserStoryTitle(),userStory.getTitle().getTitleUs());
        assertEquals("As a PO, i want to test this string",dto.getUserStoryTitle());
        assertEquals(dto.getUserStoryDescription(),userStory.getDescription().getText());
        assertEquals("description", dto.getUserStoryDescription());
        assertEquals(dto.getUserStoryPriority(),userStory.getPriority().getPriorityUs());
        assertEquals(5, dto.getUserStoryPriority());
        assertEquals(dto.getUserStoryTimeEstimate(),userStory.getTimeEstimate().getUsHours());
        assertEquals(5, dto.getUserStoryPriority());
        assertEquals(dto.getUserStoryStatus(),userStory.getUserStoryStatus().getDescription().getText());
        assertEquals("To do", dto.getUserStoryStatus());
        assertEquals(dto.getUserStoryId(),userStory.getIdUserStory());
        assertEquals(0, dto.getUserStoryId());
    }

    @Test
    @DisplayName("Test DTOList creation")
    public void scrumBoardDTOListTest() {
        //Arrange
        List<UserStory> list = new ArrayList<>();
        ScrumBoardMapper mapper = new ScrumBoardMapper();
        UserStory userStory = new UserStory("As a PO, i want to test this string", 5, "description", 5);
        userStory.setIdUserStory(0);
        UserStory userStory2 = new UserStory("As a VO, i want to test this string", 4, "description2", 4);
        userStory2.setIdUserStory(1);
        UserStory userStory3 = new UserStory("As a TO, i want to test this string", 3, "description3", 3);
        userStory3.setIdUserStory(2);
        list.add(userStory);
        list.add(userStory2);
        list.add(userStory3);
        UserStoryStatusDTO dto = mapper.toDTO(userStory);
        UserStoryStatusDTO dto2 = mapper.toDTO(userStory2);
        UserStoryStatusDTO dto3 = mapper.toDTO(userStory3);


        //Act
        List<UserStoryStatusDTO> dtoList = mapper.toDtoList(list);

        //Assert
        assertEquals(3, dtoList.size());
        assertEquals(dto.getUserStoryTitle(), dtoList.get(0).getUserStoryTitle());
        assertEquals(dto2.getUserStoryDescription(), dtoList.get(1).getUserStoryDescription());
        assertEquals(dto3.getUserStoryStatus(), dtoList.get(2).getUserStoryStatus());
        assertEquals(dto3.getUserStoryId(), dtoList.get(2).getUserStoryId());

    }
}
