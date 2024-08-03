package beta.com.discordbotwebsite.dcmodbot.serversettings;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.List;

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

    public boolean isAntiSpamEnabled(String discordServerId) {
        var filter = Filters.eq("_id", discordServerId);
        Document document = collection.find(filter).first();
        if (document != null) {
            return document.getEmbedded(List.of("settings", "antispam"), Boolean.class);
        }
        return false;
    }

    public void setAntiSpamMessageLimit(String discordServerId, int messageLimit) {
        var filter = Filters.eq("_id", discordServerId);
        var update = Updates.set("settings.antiSpamMessageLimit", messageLimit);
        collection.updateOne(filter, update, new UpdateOptions().upsert(true));
    }

    public Integer getAntiSpamMessageLimit(String discordServerId) {
        var filter = Filters.eq("_id", discordServerId);
        Document document = collection.find(filter).first();
        if (document != null) {
            return document.getEmbedded(List.of("settings", "antiSpamMessageLimit"), Integer.class);
        }
        return null;
    }

    public void setAntiSpamTimeLimit(String discordServerId, int timeLimit) {
        var filter = Filters.eq("_id", discordServerId);
        var update = Updates.set("settings.antiSpamTimeLimit", timeLimit);
        collection.updateOne(filter, update, new UpdateOptions().upsert(true));
    }

    public Integer getAntiSpamTimeLimit(String discordServerId) {
        var filter = Filters.eq("_id", discordServerId);
        Document document = collection.find(filter).first();
        if (document != null) {
            return document.getEmbedded(List.of("settings", "antiSpamTimeLimit"), Integer.class);
        }
        return null;
    }

    // AntiVirus Feature
    public void setAntiVirusEnabled(String discordServerId, boolean antiVirusEnabled) {
        var filter = Filters.eq("_id", discordServerId);
        var update = Updates.set("settings.antivirus", antiVirusEnabled);
        collection.updateOne(filter, update, new UpdateOptions().upsert(true));
    }

    public boolean isAntiVirusEnabled(String discordServerId) {
        var filter = Filters.eq("_id", discordServerId);
        Document document = collection.find(filter).first();
        if (document != null) {
            return document.getEmbedded(List.of("settings", "antivirus"), Boolean.class);
        }
        return false;
    }

    // Verify System Feature
    public void setVerifySystemEnabled(String discordServerId, boolean verifySystemEnabled) {
        var filter = Filters.eq("_id", discordServerId);
        var update = Updates.set("settings.verifySystem", verifySystemEnabled);
        collection.updateOne(filter, update, new UpdateOptions().upsert(true));
    }

    public boolean isVerifySystemEnabled(String discordServerId) {
        var filter = Filters.eq("_id", discordServerId);
        Document document = collection.find(filter).first();
        if (document != null) {
            return document.getEmbedded(List.of("settings", "verifySystem"), Boolean.class);
        }
        return false;
    }

    // Voice Action Feature
    public void setVoiceActionEnabled(String discordServerId, boolean voiceActionEnabled) {
        var filter = Filters.eq("_id", discordServerId);
        var update = Updates.set("settings.voiceAction", voiceActionEnabled);
        collection.updateOne(filter, update, new UpdateOptions().upsert(true));
    }

    public boolean isVoiceActionEnabled(String discordServerId) {
        var filter = Filters.eq("_id", discordServerId);
        Document document = collection.find(filter).first();
        if (document != null) {
            return document.getEmbedded(List.of("settings", "voiceAction"), Boolean.class);
        }
        return false;
    }
}
