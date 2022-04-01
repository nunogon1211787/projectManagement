package switch2021.project.model.valueObject;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter

public class Photo {

    private String extension;

    private final List<String> possibleExtensions = Arrays.asList("jpg", "png");

    public Photo(String photo) {
        validateExtension(photo);
        this.extension = photo;
    }


    private void validateExtension(String photo) {

        String extension = "";
        if (photo.contains(".")) {
            extension = Arrays.stream(photo.split("\\.")).reduce((a, b) -> b).orElse(null);
        }

        if (!possibleExtensions.contains(extension))
            throw new IllegalArgumentException("Invalid photo format.");
    }
}
