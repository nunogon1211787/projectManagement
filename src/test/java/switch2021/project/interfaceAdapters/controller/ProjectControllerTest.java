package switch2021.project.interfaceAdapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.embedded.OutputStreamFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import switch2021.project.applicationServices.service.ProjectService;
import switch2021.project.dtoModel.dto.ErrorMessage;
import switch2021.project.dtoModel.dto.OutputProjectDTO;
import switch2021.project.dtoModel.dto.ProjectDTO;
import switch2021.project.dtoModel.dto.TypologyDTO;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    ProjectController ctrl;

    @MockBean
    ProjectService service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProject() {

        ProjectDTO test = new ProjectDTO();

        test.projectName = "Project 2";
        test.description = "Isto é um projecto test";
        test.businessSector = "IT";
        test.typology = "costfix";
        test.customer = "Internal";
        test.startDate = "2022-05-27";
        test.endDate = "2022-06-27";
        test.numberOfSprints = "1";
        test.budget = "1000";
        test.sprintDuration = "7";

        ctrl.createProject(test);
    }


    @Test
    void getAllProjectSuccess() {

        OutputProjectDTO test = mock(OutputProjectDTO.class);
        OutputProjectDTO test2 = mock(OutputProjectDTO.class);
        OutputProjectDTO test3 = mock(OutputProjectDTO.class);

        when(service.showAllProjects()).thenReturn(CollectionModel.of(List.of(new OutputProjectDTO[]{test, test2, test3})));
        ResponseEntity<?> response = ctrl.showAllProjects();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);


    }
}
