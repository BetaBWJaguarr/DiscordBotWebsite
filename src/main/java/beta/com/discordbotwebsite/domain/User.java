package beta.com.discordbotwebsite.domain;

import beta.com.discordbotwebsite.model.Address;
import beta.com.discordbotwebsite.model.Roles;
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

    @NotNull
    @Size(max = 255)
    private String username;

    @Indexed(unique = true)
    @NotNull
    @Size(max = 255)
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

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
