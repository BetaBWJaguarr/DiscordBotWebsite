package beta.com.discordbotwebsite.dcmodbot.serversettings;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MongoDB {
    private static final Logger logger = Logger.getLogger(MongoDB.class.getName());
    private MongoClient mongoClient;
    private MongoDatabase database;

    @Autowired
    public MongoDB(Environment env) {
        try {
            String connectionString = env.getProperty("mongodb.connection.string");
            String databaseName = env.getProperty("mongodb.database.name");

            mongoClient = MongoClients.create(MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .build());

            database = mongoClient.getDatabase(databaseName);
            logger.info("Successfully connected to MongoDB database: " + databaseName);
        } catch (MongoException e) {
            System.err.println("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }
}
