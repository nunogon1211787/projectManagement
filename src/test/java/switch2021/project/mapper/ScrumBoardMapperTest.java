package switch2021.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dto.ScrumBoardDTO;
import switch2021.project.model.UserStory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScrumBoardMapperTest {


    @Test
    @DisplayName("Test DTO creation")
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
    @DisplayName("Test DTOList creation")
    public void scrumBoardDTOListTest() {
        //Arrange
        List<UserStory> list = new ArrayList<>();
        ScrumBoardMapper mapper = new ScrumBoardMapper();
        UserStory userStory = new UserStory("name", 5, "description", 5);
        userStory.setIdUserStory(0);
        UserStory userStory2 = new UserStory("name2", 4, "description2", 4);
        userStory2.setIdUserStory(1);
        UserStory userStory3 = new UserStory("name3", 3, "description3", 3);
        userStory3.setIdUserStory(2);
        list.add(userStory);
        list.add(userStory2);
        list.add(userStory3);
        ScrumBoardDTO dto = mapper.toDTO(userStory);
        ScrumBoardDTO dto2 = mapper.toDTO(userStory2);
        ScrumBoardDTO dto3 = mapper.toDTO(userStory3);


        //Act
        List<ScrumBoardDTO> dtoList = mapper.toDtoList(list);

        //Assert
        assertEquals(3, dtoList.size());
        assertEquals(dto.getUserStoryName(), dtoList.get(0).getUserStoryName());
        assertEquals(dto2.getUserStoryDescription(), dtoList.get(1).getUserStoryDescription());
        assertEquals(dto3.getUserStoryStatus(), dtoList.get(2).getUserStoryStatus());

    }
}
