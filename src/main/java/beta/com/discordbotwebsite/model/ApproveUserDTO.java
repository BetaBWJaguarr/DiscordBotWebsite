package beta.com.discordbotwebsite.model;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ApproveUserDTO {

    private UUID id;

    @NotNull
    private UUID userid;

    @NotNull
    private UUID adminid;

    @NotNull
    private Boolean status;

}
