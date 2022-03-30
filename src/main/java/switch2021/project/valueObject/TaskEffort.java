package switch2021.project.valueObject;

import lombok.Getter;

@Getter
public class TaskEffort {

    /**
     * Attributes
     **/
    private final Hours effortHours;
    private final Minutes effortMinutes;
    private final Date effortDate;
    private final String comment;
    private final String attachment;


    /**
     * Constructor (without SINGLETON)
     **/
    public TaskEffort(int effortHours, int effortMinutes, Date effortDate, String comment, String attachment) {
        this.effortDate = effortDate;
        this.effortHours = new Hours(effortHours);
        this.effortMinutes = new Minutes(effortMinutes);
        this.comment = comment;
        this.attachment = attachment;
    }
}
