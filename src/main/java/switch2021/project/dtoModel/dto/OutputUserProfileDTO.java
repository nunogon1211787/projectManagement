package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor

    public class OutputUserProfileDTO extends RepresentationModel<OutputUserProfileDTO> {

        /**
         * Attributes
         */

        public String userProfileName;

    }
