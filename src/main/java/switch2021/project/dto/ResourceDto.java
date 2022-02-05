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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceDto that = (ResourceDto) o;
        return Double.compare(that.costPerHour, costPerHour) == 0 && Double.compare(that.percentageOfAllocation, percentageOfAllocation) == 0 && Objects.equals(userName, that.userName) && Objects.equals(role, that.role) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }
}
