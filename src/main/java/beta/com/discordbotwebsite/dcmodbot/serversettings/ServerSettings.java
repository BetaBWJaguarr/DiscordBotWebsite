package beta.com.discordbotwebsite.dcmodbot.serversettings;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
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

    //Auto Punish Feature
    public void setAutoPunishEnabled(String discordServerId, boolean enabled) {
        Bson filter = Filters.eq("_id", discordServerId);
        Bson update = Updates.set("settings.autopunish", enabled);
        collection.updateOne(filter, update, new UpdateOptions().upsert(true));
    }

    public boolean isAutoPunishEnabled(String discordServerId) {
        Document document = collection.find(Filters.eq("_id", discordServerId)).first();
        if (document != null) {
            Boolean autoPunish = document.getEmbedded(List.of("settings", "autopunish"), Boolean.class);
            return autoPunish != null ? autoPunish : false;
        }
        return false;
    }

    // AntiSwear Feature
    public void setAntiSwearEnabled(String discordServerId, boolean enabled) {
        Bson filter = Filters.eq("_id", discordServerId);
        Bson update = Updates.set("settings.antiswearfeatures.enabled", enabled);
        collection.updateOne(filter, update, new UpdateOptions().upsert(true));
    }

    public boolean getAntiSwearEnabled(String discordServerId) {
        try {
            Document document = collection.find(Filters.eq("_id", discordServerId)).first();
            if (document != null) {
                Document settings = document.get("settings", Document.class);
                if (settings != null) {
                    Document antiSwearFeatures = settings.get("antiswearfeatures", Document.class);
                    if (antiSwearFeatures != null) {
                        Boolean enabled = antiSwearFeatures.getBoolean("enabled");
                        return enabled != null ? enabled : false;
                    }
                }
            }
        } catch (MongoException e) {
            System.err.println("Error retrieving AntiSwear status: " + e.getMessage());
        }
        return false;
    }

    public void addAntiSwearWord(String discordServerId, String word) {
        Bson filter = Filters.eq("_id", discordServerId);
        Bson update = Updates.addToSet("settings.antiswearfeatures.words-list", word);
        collection.updateOne(filter, update, new UpdateOptions().upsert(true));
    }

    public void removeAntiSwearWord(String discordServerId, String word) {
        Bson filter = Filters.eq("_id", discordServerId);
        Bson update = Updates.pull("settings.antiswearfeatures.words-list", word);
        collection.updateOne(filter, update, new UpdateOptions().upsert(true));
    }

    public List<String> getAntiSwearWordsList(String discordServerId) {
        try {
            Document document = collection.find(Filters.eq("_id", discordServerId)).first();
            if (document != null) {
                Document settings = document.get("settings", Document.class);
                if (settings != null) {
                    Document antiSwearFeatures = settings.get("antiswearfeatures", Document.class);
                    if (antiSwearFeatures != null) {
                        List<String> wordsList = (List<String>) antiSwearFeatures.get("words-list");
                        return wordsList != null ? wordsList : new ArrayList<>();
                    }
                }
            }
        } catch (MongoException e) {
            System.err.println("Error retrieving AntiSwear words list: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
