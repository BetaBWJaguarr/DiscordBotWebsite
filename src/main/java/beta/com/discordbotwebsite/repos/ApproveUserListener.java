package beta.com.discordbotwebsite.repos;

import beta.com.discordbotwebsite.domain.ApproveUser;
import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class ApproveUserListener extends AbstractMongoEventListener<ApproveUser> {

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<ApproveUser> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(UUID.randomUUID());
        }
    }

}
