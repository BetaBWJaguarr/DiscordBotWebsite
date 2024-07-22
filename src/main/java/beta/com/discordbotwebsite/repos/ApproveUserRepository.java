package beta.com.discordbotwebsite.repos;

import beta.com.discordbotwebsite.domain.ApproveUser;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ApproveUserRepository extends MongoRepository<ApproveUser, UUID> {
}
