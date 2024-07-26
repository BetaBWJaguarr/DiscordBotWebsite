package beta.com.discordbotwebsite.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class ProfilePicture {

    @NotNull
    @Size(max = 255)
    private String url;

    @Size(max = 255)
    private String filename;

    @Size(max = 255)
    private String fileType;

    private Long size;

}
