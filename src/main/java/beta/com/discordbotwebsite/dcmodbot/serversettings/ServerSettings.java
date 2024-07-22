package beta.com.discordbotwebsite.dcmodbot.serversettings;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class ServerSettings {
    private final MongoCollection<Document> collection;

    public ServerSettings(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    // AntiSpam Feature
    public void setAntiSpamEnabled(String discordServerId, boolean antiSpamEnabled) {
        var filter = Filters.eq("_id", discordServerId);
        var update = Updates.set("settings.antispam", antiSpamEnabled);
        collection.updateOne(filter, update, new UpdateOptions().upsert(true));
    }

    // AntiVirus Feature
    public void setAntiVirusEnabled(String discordServerId, boolean antiVirusEnabled) {
        var filter = Filters.eq("_id", discordServerId);
        var update = Updates.set("settings.antivirus", antiVirusEnabled);
        collection.updateOne(filter, update, new UpdateOptions().upsert(true));
    }
}
