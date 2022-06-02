package switch2021.project.utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import switch2021.project.applicationServices.service.ProjectService;
import switch2021.project.applicationServices.service.UserProfileService;
import switch2021.project.applicationServices.service.UserService;
import switch2021.project.applicationServices.service.UserStoryService;
import switch2021.project.dtoModel.dto.NewUserInfoDTO;
import switch2021.project.dtoModel.dto.UserProfileDTO;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Component
public class DatabaseETL {
/*
    @Autowired
    UserStoryService userStoryService;
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;

    ClassPathResource cpr = new ClassPathResource("data.xlsx");
/*
    @PostConstruct //runs after all beans are loaded
    public void initUserTable() throws Exception {

        // Load file from resources
        InputStream is = cpr.getInputStream();

        // Open excel file
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        // Get first excel sheet aka Users
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {

                XSSFRow row = worksheet.getRow(index);

                Integer id = (int) row.getCell(0).getNumericCellValue();
                String userName = row.getCell(1).getStringCellValue();
                String email = row.getCell(2).getStringCellValue();
                String function = row.getCell(3).getStringCellValue();
                String password = row.getCell(10).getStringCellValue();

                NewUserInfoDTO newUserInfoDTO = new NewUserInfoDTO(userName, email, function, password, password, "teste.png");
                userService.createAndSaveUser(newUserInfoDTO);
            }

        }
    }

 */
/*
    @PostConstruct
    public void initProfilesTable() throws Exception{
        // Load file from resources
        InputStream is = cpr.getInputStream();

        // Get second excel sheet aka Profiles
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        // Get first excel sheet aka Users
        XSSFSheet worksheet = workbook.getSheetAt(1);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);
                String description = row.getCell(1).getStringCellValue();
                UserProfileDTO userProfileDTO = new UserProfileDTO(description);
                userProfileService.createAndSaveUserProfile(userProfileDTO);
            }
        }
    }

 */
}