package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.utils.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class TaskEffort implements ValueObject<TaskEffort> {

    /**
     * Attributes
     **/
    @Embedded
    private Hours effortHours;
    @Embedded
    private Minutes effortMinutes;
    @Embedded
    private Date effortDate;
    @Embedded
    private Description comment;
    @Embedded
    private Attachment attachment;


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

    /**
     * Override Methods
     */
    @Override
    public boolean sameValueAs(final TaskEffort other) {
        return other != null && this.effortHours.equals(other.effortHours)
                && this.effortMinutes.equals(other.effortMinutes)
                && this.effortDate.equals(other.effortDate)
                && this.comment.equals(other.comment)
                && this.attachment.equals(other.attachment);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TaskEffort that = (TaskEffort) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(effortHours, effortMinutes, effortDate, comment, attachment);
    }
}

