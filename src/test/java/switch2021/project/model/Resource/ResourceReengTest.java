package switch2021.project.model.Resource;

import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResourceReengTest {

    @Test
    void withDateBeforeStartDate() {

        //Arrange
        ResourceIDReeng id = mock(ResourceIDReeng.class);
        ResourceReeng res = new ResourceReeng(id);
        LocalDate end = LocalDate.of(2022, 12, 31);
        res.setEndDate(end);

        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        LocalDate start = LocalDate.of(2022, 5, 30);
        when(id.getStartDate()).thenReturn(start);

        //Assert
        assertFalse(res.isActiveToThisDate(date));

    }

    @Test
    void withDateEqualStartDate() {

        //Arrange
        ResourceIDReeng id = mock(ResourceIDReeng.class);
        ResourceReeng res = new ResourceReeng(id);
        LocalDate end = LocalDate.of(2022, 12, 31);
        res.setEndDate(end);

        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        LocalDate start = LocalDate.of(2022, 5, 2);
        when(id.getStartDate()).thenReturn(start);

        //Assert
        assertTrue(res.isActiveToThisDate(date));

    }

    @Test
    void withDateAfterStartDate() {

        //Arrange
        ResourceIDReeng id = mock(ResourceIDReeng.class);
        ResourceReeng res = new ResourceReeng(id);
        LocalDate end = LocalDate.of(2022, 12, 31);
        res.setEndDate(end);

        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        LocalDate start = LocalDate.of(2022, 5, 1);
        when(id.getStartDate()).thenReturn(start);

        //Assert
        assertTrue(res.isActiveToThisDate(date));

    }

    @Test
    void withDateAfterEndDate() {

        //Arrange
        ResourceIDReeng id = mock(ResourceIDReeng.class);
        ResourceReeng res = new ResourceReeng(id);
        LocalDate end = LocalDate.of(2022, 4, 30);
        res.setEndDate(end);

        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        LocalDate start = LocalDate.of(2022, 4, 1);
        when(id.getStartDate()).thenReturn(start);

        //Assert
        assertFalse(res.isActiveToThisDate(date));

    }

    @Test
    void withDateEqualEndDate() {

        //Arrange
        ResourceIDReeng id = mock(ResourceIDReeng.class);
        ResourceReeng res = new ResourceReeng(id);
        LocalDate end = LocalDate.of(2022, 5, 2);
        res.setEndDate(end);

        //Act
        LocalDate date = LocalDate.of(2022, 5, 2);
        LocalDate start = LocalDate.of(2022, 4, 1);
        when(id.getStartDate()).thenReturn(start);

        //Assert
        assertTrue(res.isActiveToThisDate(date));

    }
}