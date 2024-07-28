package beta.com.discordbotwebsite.mailmanager;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;


public interface PasswordResetTokenRepository extends MongoRepository<PasswordResetToken, UUID> {

    Optional<PasswordResetToken> findByToken(String token);

    void deleteByEmail(String email);

}
