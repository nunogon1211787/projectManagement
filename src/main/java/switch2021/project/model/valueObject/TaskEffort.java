package switch2021.project.model.valueObject;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class TaskEffort {

    /**
     * Attributes
     **/
    private final Hours effortHours;
    private final Minutes effortMinutes;
    private final Date effortDate;
    private final Description comment;
    private final Attachment attachment;


    /**
     * Constructor (without SINGLETON)
     **/
    public TaskEffort(int effortHours, int effortMinutes, Date effortDate, String comment, String attachment) {
        this.effortDate = effortDate;
        this.effortHours = new Hours(effortHours);
        this.effortMinutes = new Minutes(effortMinutes);
        this.comment = new Description(comment);
        this.attachment = new Attachment(attachment);
    }
}
