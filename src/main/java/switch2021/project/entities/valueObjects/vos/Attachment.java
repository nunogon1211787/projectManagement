package switch2021.project.entities.valueObjects.vos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import switch2021.project.utils.ValueObject;

import javax.persistence.Embeddable;
import java.util.*;

@Getter
@ToString
@Embeddable
@NoArgsConstructor


public class Attachment implements ValueObject<Attachment> {

    private String extension;

    private static final List<String> possibleExtensions = Arrays.asList("pdf", "txt", "jpg", "doc", "docx", "xls", "csv", "xlsb","");

    public Attachment(String attachment) {
        validateExtension(attachment);
        this.extension = attachment;
    }


    private void validateExtension(String fileName) {

        String ext = "";
        if (fileName.contains(".")) {
            ext = Arrays.stream(fileName.split("\\.")).reduce((a, b) -> b).orElse(null);
        }

        if (!possibleExtensions.contains(ext))
            throw new IllegalArgumentException("Invalid format document");
    }


    @Override
    public boolean sameValueAs(Attachment other) {
        return false;
    }
}
