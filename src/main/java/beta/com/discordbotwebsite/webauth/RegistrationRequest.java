package beta.com.discordbotwebsite.webauth;

import beta.com.discordbotwebsite.model.Address;
import beta.com.discordbotwebsite.model.ApproveStatus;
import beta.com.discordbotwebsite.model.Roles;
import beta.com.discordbotwebsite.model.UserEmailUnique;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegistrationRequest {

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

    private Boolean status;

}
