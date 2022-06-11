package switch2021.project.entities.aggregates.Resource;

import static org.mockito.Mockito.mock;

//TODO REVER TESTES
class ResourceTest {
//
//    @Test
//    void withDateBeforeStartDate() {
//
//        //Arrange
//        ResourceID id = mock(ResourceID.class);
//        PercentageOfAllocation all = mock(PercentageOfAllocation.class);
//        CostPerHour costPerHour = mock(CostPerHour.class);
//        ProjectRoleReeng projRole = null;
//        LocalDate endDate = LocalDate.of(2021, 12, 10);
//        ResourceReeng res = new ResourceReeng(id, all, costPerHour,projRole,endDate);
//        LocalDate end = LocalDate.of(2022, 12, 31);
//        res.setEndDate(end);
//
//        //Act
//        LocalDate date = LocalDate.of(2022, 5, 2);
//        LocalDate start = LocalDate.of(2022, 5, 30);
//        when(id.getStartDate()).thenReturn(start);
//
//        //Assert
//        assertFalse(res.isActiveToThisDate(date));
//
//    }
//
//    @Test
//    void withDateEqualStartDate() {
//
//        //Arrange
//        ResourceID id = mock(ResourceID.class);
//        ResourceReeng res = new ResourceReeng(id);
//        LocalDate end = LocalDate.of(2022, 12, 31);
//        res.setEndDate(end);
//
//        //Act
//        LocalDate date = LocalDate.of(2022, 5, 2);
//        LocalDate start = LocalDate.of(2022, 5, 2);
//        when(id.getStartDate()).thenReturn(start);
//
//        //Assert
//        assertTrue(res.isActiveToThisDate(date));
//
//    }
//
//    @Test
//    void withDateAfterStartDate() {
//
//        //Arrange
//        ResourceID id = mock(ResourceID.class);
//        ResourceReeng res = new ResourceReeng(id);
//        LocalDate end = LocalDate.of(2022, 12, 31);
//        res.setEndDate(end);
//
//        //Act
//        LocalDate date = LocalDate.of(2022, 5, 2);
//        LocalDate start = LocalDate.of(2022, 5, 1);
//        when(id.getStartDate()).thenReturn(start);
//
//        //Assert
//        assertTrue(res.isActiveToThisDate(date));
//
//    }
//
//    @Test
//    void withDateAfterEndDate() {
//
//        //Arrange
//        ResourceID id = mock(ResourceID.class);
//        ResourceReeng res = new ResourceReeng(id);
//        LocalDate end = LocalDate.of(2022, 4, 30);
//        res.setEndDate(end);
//
//        //Act
//        LocalDate date = LocalDate.of(2022, 5, 2);
//        LocalDate start = LocalDate.of(2022, 4, 1);
//        when(id.getStartDate()).thenReturn(start);
//
//        //Assert
//        assertFalse(res.isActiveToThisDate(date));
//
//    }
//
//    @Test
//    void withDateEqualEndDate() {
//
//        //Arrange
//        ResourceID id = mock(ResourceID.class);
//        ResourceReeng res = new ResourceReeng(id);
//        LocalDate end = LocalDate.of(2022, 5, 2);
//        res.setEndDate(end);
//
//        //Act
//        LocalDate date = LocalDate.of(2022, 5, 2);
//        LocalDate start = LocalDate.of(2022, 4, 1);
//        when(id.getStartDate()).thenReturn(start);
//
//        //Assert
//        assertTrue(res.isActiveToThisDate(date));
//
//    }
}