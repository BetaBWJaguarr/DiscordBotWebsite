package beta.com.discordbotwebsite.domain;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter
public class ApproveUser {

    @Id
    private UUID id;

    @NotNull
    private UUID userid;

    @NotNull
    private UUID adminid;

    @NotNull
    private Boolean status;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
