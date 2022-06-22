package switch2021.project.entities.factories.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dtoModel.dto.CreateResourceDTO;
import switch2021.project.dtoModel.dto.DefineRoleOfResourceDTO;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.*;

import switch2021.project.entities.valueObjects.vos.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ResourceFactoryTest {

    @Mock
    private IUserIDFactory iUserIDFactory;
    @Mock
    private UserID userID;
    @Mock
    private IProjectIDFactory projectIDF;
    @Mock
    private ProjectID projectID;
    @Mock
    private IResourceIDFactory iResourceIDFactory;
    @Mock
    private ResourceID resourceID;
    @Mock
    private CreateResourceDTO createResourceDTO;
    @Mock
    ICostPerHourFactory iCostPerHourFactory;
    @Mock
    private CostPerHour costPerHour;
    @Mock
    private IPercOfAllocationFactory iPercOfAllocationFactory;
    @Mock
    private PercentageOfAllocation percentageOfAllocation;
    @Mock
    DefineRoleOfResourceDTO defineRoleOfResourceDTO;
    @Mock
    ResourceID id;
    @Mock
    Email email;


    @InjectMocks
    ResourceFactory resourceFactory;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("createResource - with success")
    void createResourceWithSuccess() {
        //Arrange
        when(iUserIDFactory.createUserID(createResourceDTO.systemUserID)).thenReturn(userID);
        when(projectIDF.create(createResourceDTO.projectId)).thenReturn(projectID);
        when(iResourceIDFactory.create(createResourceDTO.systemUserID, createResourceDTO.projectId,
                createResourceDTO.startDate)).thenReturn(resourceID);
        when(iCostPerHourFactory.create(createResourceDTO.costPerHour)).thenReturn(costPerHour);
        when(iPercOfAllocationFactory.create(createResourceDTO.percentageOfAllocation)).thenReturn(percentageOfAllocation);
        LocalDate date = LocalDate.of(2000, 1, 1);
        createResourceDTO.startDate = date.toString();
        createResourceDTO.endDate = date.toString();
        //Act
        Resource newResource = resourceFactory.createResource(createResourceDTO);
        //Assert
        assertNotNull(newResource);
    }


}
