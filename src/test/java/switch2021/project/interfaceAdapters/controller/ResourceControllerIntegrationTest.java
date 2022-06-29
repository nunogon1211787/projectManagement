package switch2021.project.interfaceAdapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2021.project.applicationServices.service.ResourceService;
import switch2021.project.dtoModel.dto.CreateResourceDTO;
import switch2021.project.dtoModel.dto.DefineRoleOfResourceDTO;
import switch2021.project.dtoModel.dto.OutputResourceDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ResourceControllerIntegrationTest {

    public static final String BASE_URL = "https://localhost:8443";

   @Autowired
   ResourceController ctrl;

   @Autowired
   MockMvc mockMvc;

   @Autowired
   ObjectMapper objectMapper;

   @Autowired
   ResourceService service;

    @BeforeEach
    void TestConfiguration() {
        MockitoAnnotations.openMocks(this);
    }

    @SneakyThrows
    @Test
    void createResourceSuccess() {
        //Arrange
        CreateResourceDTO dto = new CreateResourceDTO("asz@mymail.com", "Project_2022_3", "TeamMember",
                "2022-03-12", "2023-01-12", 500, 0.1);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/resources")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(result.getResponse().getContentAsString());
    }

    @SneakyThrows
    @Test
    void defineProjectRole() {
        //Arrange
        DefineRoleOfResourceDTO dto = new DefineRoleOfResourceDTO("ProductOwner", "2022-03-12", "2023-01-12", 500, 0.2);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.patch(BASE_URL + "/resources/nel.m@mymail.com&Project_2022_3&2022-03-07")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @SneakyThrows
    @Test
    void defineProjectRoleFalse() {
        //Arrange
        DefineRoleOfResourceDTO dto = new DefineRoleOfResourceDTO("ProductOwner", "2022-03-12", "2023-01-12", 500, 1.1);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.patch(BASE_URL + "/resources/nel.m@mymail.com&Project_2022_3&2022-03-07")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertNotNull(result.getResponse().getContentAsString());
    }

}