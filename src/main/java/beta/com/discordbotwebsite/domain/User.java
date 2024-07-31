package beta.com.discordbotwebsite.domain;

import beta.com.discordbotwebsite.model.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter
public class User {

    @Id
    private UUID id;

    @NotNull(message = "{register.username.required}")
    @Size(max = 255)
    private String username;

    @NotNull(message = "{register.email.required}")
    @Size(max = 255)
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

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
