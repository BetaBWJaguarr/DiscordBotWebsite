package beta.com.discordbotwebsite.dcmodbot.serversettings;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository
public class ServerSettingsRepository {

    private final MongoCollection<Document> collection;

    public ServerSettingsRepository(MongoDB mongoDB) {
        this.collection = mongoDB.getCollection("ServerSettings");
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }
}
