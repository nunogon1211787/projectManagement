package switch2021.project.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import switch2021.project.model.ProjectRole;
import switch2021.project.model.SystemUser;

import java.time.LocalDate;

@Data
@Getter
public class ResourceDto {

    private String userName;
    private String role;
    private String startDate;
    private String endDate;
    private double costPerHour;
    private double percentageOfAllocation;

    /**
     * Constructor to test (without SINGLETON).
     */

    public ResourceDto(String name, String role, String startDate, String endDate, double costPerHour, double percentageOfAllocation){
        this.userName = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.role = role;
        this.costPerHour = costPerHour;
        this.percentageOfAllocation = percentageOfAllocation;
    }
}
