package switch2021.project.dto;

import lombok.Getter;

import java.util.Objects;


@Getter
public class ResourceDto {

    /**
     * Attributes
     **/

    private String userName;
    private String role;
    private String startDate;
    private String endDate;
    private double costPerHour;
    private double percentageOfAllocation;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public ResourceDto(String name, String role, String startDate, String endDate, double costPerHour, double percentageOfAllocation){
        if(role == null){
            role = "";
        }
        this.userName = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.role = role;
        this.costPerHour = costPerHour;
        this.percentageOfAllocation = percentageOfAllocation;
    }
}
