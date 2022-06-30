package switch2021.project.applicationServices.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.hateoas.Link;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.applicationServices.iRepositories.IResourceRepo;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.dto.DefineRoleOfResourceDTO;
import switch2021.project.dtoModel.dto.OutputResourceDTO;
import switch2021.project.dtoModel.mapper.ProjectRoleMapper;
import switch2021.project.dtoModel.mapper.ResourceMapper;
import switch2021.project.entities.aggregates.Resource.ManagementResourcesService;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.factories.factoryInterfaces.IResourceFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IResourceIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.vos.Email;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.valueObjects.vos.UserID;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc

public class ResourceServiceIntegrationTest {


    @Autowired
    ResourceService service;
    @Autowired
    private IProjectRepo projRepo;
    @Autowired
    private IResourceRepo resRepo;
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private ResourceMapper mapper;
    @Autowired
    private ProjectRoleMapper roleMapper;
    @Autowired
    private ManagementResourcesService managementService;
    @Autowired
    private IResourceFactory iResourceFactory;
    @Autowired
    private IResourceIDFactory iResourceIDFactory;
    @Autowired
    private IProjectIDFactory iProjIDFactory;
    @Autowired
    private IUserIDFactory iUserIDFactory;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void DefineProjectRoleSuccess () {
        String resId = "xfz@mymail.com&Project_2022_3&2022-03-05";
        String email = "xfz@mymail.com";
        String role = "ScrumMaster";
        String projid = "Project_2022_3";
        String data = "2022-03-05";
        String data2 = "2023-01-10";
        Double percentage = 0.9;
        Double cost = 20.0;

        DefineRoleOfResourceDTO dto = new DefineRoleOfResourceDTO(role, data,
                data2, cost, percentage);

        OutputResourceDTO expected = new OutputResourceDTO();
        expected.project = projid;
        expected.role = role;
        expected.startDate = data;
        expected.endDate = data2;
        expected.resourceID = resId;
        expected.allocation = percentage;
        expected.cost = cost;
        expected.user = email;

        OutputResourceDTO result = service.defineProjectRole(resId, dto);
        result.removeLinks();

        assertEquals(expected, result);
    }

    @Test
    void DefineProjectRoleResourceNotOnProjectTeam() {
        assertThrows(Exception.class, () -> {
        String resId = "xfz@mymail.com&Project_2022_3&2022-03-06";
        String email = "xfz@mymail.com";
        String role = "ScrumMaster";
        String projid = "Project_2022_3";
        String data = "2022-03-06";
        String data2 = "2023-01-10";
        Double percentage = 0.9;
        Double cost = 20.0;

        DefineRoleOfResourceDTO dto = new DefineRoleOfResourceDTO(role, data,
                data2, cost, percentage);

        OutputResourceDTO expected = new OutputResourceDTO();
        expected.project = projid;
        expected.role = role;
        expected.startDate = data;
        expected.endDate = data2;
        expected.resourceID = resId;
        expected.allocation = percentage;
        expected.cost = cost;
        expected.user = email;

        OutputResourceDTO result = service.defineProjectRole(resId, dto);
        result.removeLinks();

        assertEquals(expected, result);
        });
    }

    @Test
    void DefineProjectRoleUserNotFound() {
        assertThrows(Exception.class, () -> {
            String resId = "xfzasd@mymail.com&Project_2022_3&2022-03-05";
            String email = "xfzasdasd@mymail.com";
            String role = "ScrumMaster";
            String projid = "Project_2022_3";
            String data = "2022-03-05";
            String data2 = "2023-01-10";
            Double percentage = 0.9;
            Double cost = 20.0;

            DefineRoleOfResourceDTO dto = new DefineRoleOfResourceDTO(role, data,
                    data2, cost, percentage);

            OutputResourceDTO expected = new OutputResourceDTO();
            expected.project = projid;
            expected.role = role;
            expected.startDate = data;
            expected.endDate = data2;
            expected.resourceID = resId;
            expected.allocation = percentage;
            expected.cost = cost;
            expected.user = email;

            OutputResourceDTO result = service.defineProjectRole(resId, dto);
            result.removeLinks();

            assertEquals(expected, result);
        });
    }
}
