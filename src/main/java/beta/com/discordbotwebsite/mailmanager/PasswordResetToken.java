package beta.com.discordbotwebsite.mailmanager;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@Document
public class PasswordResetToken {

    @Id
    private String id;
    private String email;
    private String token;
    private LocalDateTime expirationTime;

    public PasswordResetToken(String email, String token, LocalDateTime expirationTime) {
        this.email = email;
        this.token = token;
        this.expirationTime = expirationTime;
    }

}
