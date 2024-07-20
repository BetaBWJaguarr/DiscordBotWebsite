package beta.com.discordbotwebsite.repos;

import beta.com.discordbotwebsite.domain.User;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, UUID> {

    User findByEmailIgnoreCase(String email);

    Page<User> findAllById(UUID id, Pageable pageable);

    boolean existsByEmailIgnoreCase(String email);

}
