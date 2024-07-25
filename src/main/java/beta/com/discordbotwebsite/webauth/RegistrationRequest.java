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

    @NotNull
    @Size(max = 255)
    private String username;

    @NotNull
    @Size(max = 255)
    @UserEmailUnique
    private String email;

    @NotNull
    @Size(max = 255)
    private String password;

    @NotNull
    @Size(max = 255)
    private String phonenumber;

    @NotNull
    @Size(max = 255)
    private String discordid;

    @Valid
    private Address userAddress;

    private Roles roles;

    private ApproveStatus approveStatus;

    private Boolean status;

}
