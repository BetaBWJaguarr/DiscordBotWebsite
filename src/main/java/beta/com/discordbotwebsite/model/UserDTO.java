package beta.com.discordbotwebsite.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private UUID id;

    @NotNull(message = "{register.username.required}")
    @Size(max = 255)
    private String username;

    @NotNull(message = "{register.email.required}")
    @Size(max = 255)
    @UserEmailUnique(message = "{register.email.unique}")
    private String email;

    @NotNull(message = "{register.password.required}")
    @Size(max = 255)
    private String password;

    @NotNull(message = "{register.phonenumber.required}")
    @Size(max = 255)
    private String phonenumber;

    @NotNull(message = "{register.discordid.required}")
    @Size(max = 255)
    private String discordid;

    @Valid
    private Address userAddress;

    private Roles roles;

    private ApproveStatus approveStatus;

    @Valid
    private ProfilePicture profilePictures;

    @Valid
    private Subscription suscribeStatus;

    @Valid
    private Interests interests;

    private Boolean status;

}
