package switch2021.project.interfaceAdapters.repositories.REST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import switch2021.project.dataModel.REST.ProjectRestDTO;

import java.util.List;

public class ProjectRestRepositoryTest {


    @Test
    void doTest() throws JsonProcessingException {
        //Arrange
        String json = "[\n" +
                "  {\n" +
                "\t\"projectCode\": \"A0001\",\n" +
                "\t\"projectName\": \"Dummy 01\",\n" +
                "\t\"projectDescription\": \"Just a dummy project\",\n" +
                "\t\"projectBusinessSector\": \"It doesn't matter\",\n" +
                "\t\"projectNumberOfPlannedSprints\": 6,\n" +
                "\t\"projectSprintDuration\": 2,\n" +
                "\t\"projectBudget\": 150000.0,\n" +
                "\t\"startDate\": \"01/03/2023\",\n" +
                "\t\"endDate\": \"31/07/2024\",\n" +
                "\t\"typologyDescription\": \"Fixed cost\",\n" +
                "\t\"customerName\": \"XPTO, SA\",\n" +
                "\t\"userEmail\": null,\n" +
                "\t\"costPerHour\": 0.0,\n" +
                "\t\"percentageOfAllocation\": 0.0,\n" +
                "\t\"status\": \"Closed\",\n" +
                "\t\"links\": [{\n" +
                "\t\t\"rel\": \"A0001\",\n" +
                "\t\t\"href\": \"https://vs866.dei.isep.ipp.pt:8443/switchproject-1.0-SNAPSHOT/api/projects/A0001\"," +
                "\n" +
                "\t\t\"type\": \"GET\"\n" +
                "\t}\n" +
                "\t\t\t ]\n" +
                "}, {\n" +
                "\t\"projectCode\": \"A0002\",\n" +
                "\t\"projectName\": \"Dummy 02\",\n" +
                "\t\"projectDescription\": \"Just another dummy project\",\n" +
                "\t\"projectBusinessSector\": \"It doesn't matter\",\n" +
                "\t\"projectNumberOfPlannedSprints\": 12,\n" +
                "\t\"projectSprintDuration\": 4,\n" +
                "\t\"projectBudget\": 350000.0,\n" +
                "\t\"startDate\": \"01/06/2023\",\n" +
                "\t\"endDate\": \"29/04/2024\",\n" +
                "\t\"typologyDescription\": \"Fixed cost\",\n" +
                "\t\"customerName\": \"XYZ, Lda\",\n" +
                "\t\"userEmail\": null,\n" +
                "\t\"costPerHour\": 0.0,\n" +
                "\t\"percentageOfAllocation\": 0.0,\n" +
                "\t\"status\": \"Closed\",\n" +
                "\t\"links\": [{\n" +
                "\t\t\"rel\": \"A0002\",\n" +
                "\t\t\"href\": \"https://vs866.dei.isep.ipp.pt:8443/switchproject-1.0-SNAPSHOT/api/projects/A0002\"," +
                "\n" +
                "\t\t\"type\": \"GET\"\n" +
                "\t}]\n" +
                "}, {\n" +
                "\t\"projectCode\": \"A0666\",\n" +
                "\t\"projectName\": \"Inevitable nightmare\",\n" +
                "\t\"projectDescription\": \"Doomed from the start\",\n" +
                "\t\"projectBusinessSector\": \"Hospitality industry\",\n" +
                "\t\"projectNumberOfPlannedSprints\": 15,\n" +
                "\t\"projectSprintDuration\": 3,\n" +
                "\t\"projectBudget\": 500000.0,\n" +
                "\t\"startDate\": \"10/03/2023\",\n" +
                "\t\"endDate\": \"29/04/2024\",\n" +
                "\t\"typologyDescription\": \"Time and materials\",\n" +
                "\t\"customerName\": \"Hell, LLC\",\n" +
                "\t\"userEmail\": null,\n" +
                "\t\"costPerHour\": 0.0,\n" +
                "\t\"percentageOfAllocation\": 0.0,\n" +
                "\t\"status\": \"Planned\",\n" +
                "\t\"links\": [{\n" +
                "\t\t\"rel\": \"A0666\",\n" +
                "\t\t\"href\": \"https://vs866.dei.isep.ipp.pt:8443/switchproject-1.0-SNAPSHOT/api/projects/A0666\"," +
                "\n" +
                "\t\t\"type\": \"GET\"\n" +
                "\t}]\n" +
                "}]";
        //Act
        ObjectMapper mapper = new ObjectMapper();
        List<ProjectRestDTO> projectRestDTOList = mapper.readValue(json, new TypeReference<List<ProjectRestDTO>>(){});
        //Assert
        System.out.println(projectRestDTOList);

    }
}
