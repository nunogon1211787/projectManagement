package switch2021.project.model.valueObject;

import lombok.Getter;

import java.io.File;
import java.util.*;

@Getter

public class Attachment {

    private String extension;

    private final List<String> possibleExtensions = Arrays.asList("pdf", "txt", "jpg", "doc", "docx", "xls", "csv", "xlsb","");

    public Attachment(String attachment) {
        validateExtension(attachment);
        this.extension = attachment;
    }


    private void validateExtension(String fileName) {

        String extension = "";
        if (fileName.contains(".")) {
            extension = Arrays.stream(fileName.split("\\.")).reduce((a, b) -> b).orElse(null);
        }

        if (!possibleExtensions.contains(extension))
            throw new IllegalArgumentException("Invalid format document");
    }


}
