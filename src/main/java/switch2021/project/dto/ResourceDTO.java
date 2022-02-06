package switch2021.project.dto;
import lombok.Getter;

@Getter

public class ResourceDTO {

    /**
     * Attributes
     **/

    private final String userName;
    private final String role;
    private final String startDate;
    private final String endDate;
    private final double costPerHour;
    private final double percentageOfAllocation;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public ResourceDTO(String name, String role, String startDate, String endDate, double costPerHour, double percentageOfAllocation) {
        if (role == null) {
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
