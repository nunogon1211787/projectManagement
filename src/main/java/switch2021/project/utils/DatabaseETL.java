package switch2021.project.utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.interfaceAdapters.controller.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;


@Component
public class DatabaseETL {

    @Autowired
    UserController userController;
    @Autowired
    UserProfileController userProfileController;
    @Autowired
    TypologyController typologyController;
    @Autowired
    ProjectController projectController;
    @Autowired
    ResourceController resourceController;
    @Autowired
    SprintController sprintController;
    @Autowired
    UserStoryController userStoryController;

    ClassPathResource cpr = new ClassPathResource("data.xlsx");

    @PostConstruct //runs after all beans are loaded
    public void initUserTable() throws IOException {
        // Load file from resources
        InputStream is = cpr.getInputStream();
        // Open Excel file
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        // Get first Excel sheet aka Users
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {

                XSSFRow row = worksheet.getRow(index);

                //Integer id = (int) row.getCell(0).getNumericCellValue();
                String userName = row.getCell(0).getStringCellValue();
                String email = row.getCell(1).getStringCellValue();
                String function = row.getCell(2).getStringCellValue();
                String password = row.getCell(3).getStringCellValue();
                String passwordConfirmation = row.getCell(4).getStringCellValue();

                NewUserInfoDTO newUserInfoDTO = new NewUserInfoDTO(userName, email, function, password,
                        passwordConfirmation, "");
                userController.registerUser(newUserInfoDTO);
                userController.activateUser(email);
            }
        }
    }

    @PostConstruct
    public void initProfilesTable() throws IOException {
        InputStream is = cpr.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        // Get second Excel sheet aka Profiles
        XSSFSheet worksheet = workbook.getSheetAt(1);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);
                String description = row.getCell(0).getStringCellValue();
                UserProfileDTO userProfileDTO = new UserProfileDTO(description);
                userProfileController.createUserProfile(userProfileDTO);
            }
        }
    }

    @PostConstruct
    public void initGlobalRolesTable() throws IOException {
        InputStream is = cpr.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet worksheet = workbook.getSheetAt(2);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);

                String id = row.getCell(0).getStringCellValue();
                String profileId = row.getCell(1).getStringCellValue();

                UpdateUserProfileDTO updateUserProfileDTO = new UpdateUserProfileDTO(profileId);
                userController.assignProfile(id, updateUserProfileDTO);
            }
        }
    }

    @PostConstruct
    public void initProjectsTable() throws IOException {
        InputStream is = cpr.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet worksheet = workbook.getSheetAt(3);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);

                String projectName = row.getCell(1).getStringCellValue();
                String description = row.getCell(2).getStringCellValue();
                String businessSector = row.getCell(3).getStringCellValue();
                String typology = row.getCell(4).getStringCellValue();
                String customer = row.getCell(5).getStringCellValue();
                //String startDate = row.getCell(6).getStringCellValue();
                String numberOfSprints = row.getCell(8).getStringCellValue();
                String budget = row.getCell(9).getStringCellValue();
                String sprintDuration = row.getCell(11).getStringCellValue();
                String populateTypology = row.getCell(18).getStringCellValue();

                TypologyDTO typologyDTO = new TypologyDTO(populateTypology);
                typologyController.createTypology(typologyDTO);

                ProjectDTO projectDTO = new ProjectDTO(projectName, description, businessSector, "2021-03-01",
                        numberOfSprints, budget, sprintDuration, typology, customer);
                projectController.createProject(projectDTO);
            }
        }
    }

    @PostConstruct
    public void initProjectTeamsTable() throws IOException {
        InputStream is = cpr.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet worksheet = workbook.getSheetAt(5);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);

                String systemUserID = row.getCell(0).getStringCellValue();
                String projectId = row.getCell(1).getStringCellValue();
                String projectRole = row.getCell(2).getStringCellValue();
                //String startDate = row.getCell(3).getStringCellValue();
                //String endDate = row.getCell(4).getStringCellValue();
                double costPerHour = row.getCell(5).getNumericCellValue();
                double percentageOfAllocation = row.getCell(6).getNumericCellValue();

                CreateResourceDTO resourceDTO = new CreateResourceDTO(systemUserID, projectId, projectRole, "2021-03" +
                        "-02", "2021-06-28", costPerHour, percentageOfAllocation);
                resourceController.createResource(resourceDTO);
            }
        }
    }

    /*@PostConstruct
    public void initProjectSprintsTable() throws IOException {
        InputStream is = cpr.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet worksheet = workbook.getSheetAt(6);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);

                String projectId = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();

                NewSprintDTO sprintDTO = new NewSprintDTO(projectId, name);
                sprintController.createAndSaveSprint(sprintDTO);
            }
        }
    }*/

    @PostConstruct
    public void initProjectBacklogTable() throws IOException {
        InputStream is = cpr.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet worksheet = workbook.getSheetAt(8);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);

                String projectId = row.getCell(0).getStringCellValue();
                String title = row.getCell(1).getStringCellValue();
                String description = row.getCell(2).getStringCellValue();

                UserStoryDTO userStoryDTO = new UserStoryDTO(projectId, title, 0, description, 0);
                userStoryController.createAndSaveUserStory(userStoryDTO);
            }
        }
    }


/*    @PostConstruct
    public void initSprintBacklogTable() throws IOException {
        InputStream is = cpr.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet worksheet = workbook.getSheetAt(10);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);

                String projectId = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                String title = row.getCell(2).getStringCellValue();
                String usResult = row.getCell(3).getStringCellValue();

            }
        }
    }*/
    @PostConstruct
    public void initUserStoriesTable () throws Exception{
        // Load file from resources
        InputStream is = cpr.getInputStream();
        // Open Excel file
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        // Get first Excel sheet aka Users
        XSSFSheet worksheet = workbook.getSheetAt(7);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);


                String projectID = row.getCell(0).getStringCellValue();
                String title = row.getCell(1).getStringCellValue();
                int priority = (int) row.getCell(5).getNumericCellValue();
                String description = row.getCell(2).getStringCellValue();
                double timeEstimate = row.getCell(7).getNumericCellValue();

                UserStoryDTO userStoryDTO = new UserStoryDTO(projectID, title, priority, description, timeEstimate);
                userStoryController.createAndSaveUserStory(userStoryDTO);
            }
        }
    }

}