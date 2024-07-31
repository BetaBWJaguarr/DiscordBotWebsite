package beta.com.discordbotwebsite.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class Subscription {

    @NotNull
    private UUID id;

    @NotNull
    private UUID userid;

    @NotNull
    @Size(max = 255)
    private String plan;

    @NotNull
    private Boolean active;

    @NotNull
    @Size(max = 255)
    private String description;

}
