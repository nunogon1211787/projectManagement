package switch2021.project.model.valueObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.util.*;

@Getter
@ToString
@Embeddable
@NoArgsConstructor

public class Attachment {

    private String extension;

    private final List<String> possibleExtensions = Arrays.asList("pdf", "txt", "jpg", "doc", "docx", "xls", "csv", "xlsb","");

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


}
