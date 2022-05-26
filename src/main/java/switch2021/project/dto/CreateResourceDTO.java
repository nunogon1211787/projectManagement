package switch2021.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
public class CreateResourceDTO extends RepresentationModel<CreateResourceDTO> {
    public String systemUserID;
    public String projectId;
    public String projectRole;
        public String startDate;
//    public int yearStartDate;
//    public int monthStartDate;
//    public int dayStartDate;
//    public int yearEndDate;
//    public int monthEndDate;
//    public int dayEndDate;
        public String endDate;
    public double costPerHour;
    public double percentageOfAllocation;

//    private static final SimpleDateFormat dateFormat
//            = new SimpleDateFormat("yyyy-MM-dd");
//
//    public void setSubmissionDate(String date) {
//        this.startDate = dateFormat.format(date);
//    }
}
