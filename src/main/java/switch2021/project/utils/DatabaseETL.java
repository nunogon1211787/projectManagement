package switch2021.project.utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.interfaceAdapters.controller.ProjectController;
import switch2021.project.interfaceAdapters.controller.TypologyController;
import switch2021.project.interfaceAdapters.controller.UserController;
import switch2021.project.interfaceAdapters.controller.UserProfileController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Date;


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

    ClassPathResource cpr = new ClassPathResource("data.xlsx");

    @PostConstruct //runs after all beans are loaded
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
                String business_sector = row.getCell(3).getStringCellValue();
                String typologyID = row.getCell(4).getStringCellValue();
                String customer = row.getCell(5).getStringCellValue();
                Date startDate = row.getCell(6).getDateCellValue();
                Double numberOfSprints = row.getCell(8).getNumericCellValue();
                Double budget = row.getCell(9).getNumericCellValue();
                Double sprintDuration = row.getCell(11).getNumericCellValue();

                String typology = row.getCell(18).getStringCellValue();

                TypologyDTO typologyDTO = new TypologyDTO(typology);
                typologyController.createTypology(typologyDTO);

                /*ProjectDTO projectDTO = new ProjectDTO(projectName, description, business_sector, typologyID,
                        customer, startDate, numberOfSprints, budget, sprintDuration);
                projectController.createProject(projectDTO);*/
            }
        }
    }

}