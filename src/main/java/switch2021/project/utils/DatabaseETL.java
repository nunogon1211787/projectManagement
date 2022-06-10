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

    @PostConstruct
    public void init() throws Exception {
        initGlobalRolesTable();
        initUserTable();
        initProfilesTable();
        initProjectsTable();
        initProjectBacklogTable();
        initProjectTeamsTable();
        initProjectSprintsTable();
        ChangeUsStatus();
    }

    public void initUserTable() throws Exception {
        // Load file from resources
        InputStream is = cpr.getInputStream();
        // Open Excel file
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        // Get first Excel sheet aka Users
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {

                XSSFRow row = worksheet.getRow(index);

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

    public void initProfilesTable() throws Exception {
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

    public void initGlobalRolesTable() throws Exception {
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

    public void initProjectsTable() throws Exception {
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
                String startDate = row.getCell(6).getStringCellValue();
                String endDate = row.getCell(7).getStringCellValue();
                String numberOfSprints = row.getCell(8).getStringCellValue();
                String budget = row.getCell(9).getStringCellValue();
                String sprintDuration = row.getCell(11).getStringCellValue();
                String populateTypology = row.getCell(17).getStringCellValue();
                String id = row.getCell(0).getStringCellValue();
                String projectStatus = row.getCell(10).getStringCellValue();

                TypologyDTO typologyDTO = new TypologyDTO(populateTypology);
                typologyController.createTypology(typologyDTO);

                ProjectDTO projectDTO = new ProjectDTO(projectName, description, businessSector, startDate,
                        numberOfSprints, budget, sprintDuration, typology, customer);
                projectController.createProject(projectDTO);

                EditProjectInfoDTO editProjectInfoDTO = new EditProjectInfoDTO(projectName, description,
                        businessSector, typology, customer, startDate, endDate, numberOfSprints, budget,
                        projectStatus, sprintDuration);
                projectController.updateProjectPartially(id, editProjectInfoDTO);
            }
        }
    }

    public void initProjectTeamsTable() throws Exception {
        InputStream is = cpr.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet worksheet = workbook.getSheetAt(5);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);

                String systemUserID = row.getCell(0).getStringCellValue();
                String projectId = row.getCell(1).getStringCellValue();
                String projectRole = row.getCell(2).getStringCellValue();
                String startDate = row.getCell(3).getStringCellValue();
                String endDate = row.getCell(4).getStringCellValue();
                double costPerHour = row.getCell(5).getNumericCellValue();
                double percentageOfAllocation = row.getCell(6).getNumericCellValue();

                CreateResourceDTO resourceDTO = new CreateResourceDTO(systemUserID, projectId, projectRole, startDate
                        , endDate, costPerHour, percentageOfAllocation);
                resourceController.createResource(resourceDTO);
            }
        }
    }

    public void initProjectSprintsTable() throws Exception {
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
    }

    public void initProjectBacklogTable() throws Exception {
        InputStream is = cpr.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet worksheet = workbook.getSheetAt(8);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);

                String projectId = row.getCell(0).getStringCellValue();
                String title = row.getCell(1).getStringCellValue();
                int priority = (int) row.getCell(2).getNumericCellValue();
                String description = row.getCell(3).getStringCellValue();
                double timeEstimate = row.getCell(4).getNumericCellValue();

                UserStoryDTO userStoryDTO = new UserStoryDTO(projectId, title, priority, description, timeEstimate);
                userStoryController.createAndSaveUserStory(userStoryDTO);
            }
        }
    }

    public void ChangeUsStatus() throws Exception {
        InputStream is = cpr.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet worksheet = workbook.getSheetAt(8);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);


                int priority = (int) row.getCell(2).getNumericCellValue();
                String description = row.getCell(3).getStringCellValue();
                double timeEstimate = row.getCell(4).getNumericCellValue();
                String usStartDate = row.getCell(7).getStringCellValue();
                String usEndDate = row.getCell(8).getStringCellValue();
                String usID = row.getCell(6).getStringCellValue();

                UserStoryUpdateDTO userStoryUpdateDTO = new UserStoryUpdateDTO(priority, description, timeEstimate,
                        usStartDate, usEndDate);
                userStoryController.updateUserStoryPartially(usID, userStoryUpdateDTO);

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
}