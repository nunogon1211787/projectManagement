package switch2021.project.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OutputProjectDTO {

    public String code;
    public String projectName;
    public String description;
    public String customer;
    public String businessSector;
    public String typo;
    public String numberOfSprints;
    public String budget;
    public String status;
    public String startDate;

    public OutputProjectDTO(String code, String projectName, String description, String businessSector,
                            String numberOfSprints, String budget, String status, String startDate) {
        this.code = code;
        this.projectName = projectName;
        this.description = description;
        this.businessSector = businessSector;
        this.numberOfSprints = numberOfSprints;
        this.budget = budget;
        this.status = status;
        this.startDate = startDate;
    }
}
