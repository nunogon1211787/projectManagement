package switch2021.project.entities.valueObjects.vos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.utils.ValueObject;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
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
    @AttributeOverride(name = "text", column = @Column(name = "EffortComment"))
    private Description comment;
    @Embedded
    @AttributeOverride(name = "extension", column = @Column(name = "EffortAttachment"))
    private Attachment attachment;

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

    @Override
    public String toString() {
        return this.effortHours.getEffortHours() + "&" + this.effortMinutes.getEffortMinutes() + "&" + this.effortDate.getEffortDate().toString();
    }
}

