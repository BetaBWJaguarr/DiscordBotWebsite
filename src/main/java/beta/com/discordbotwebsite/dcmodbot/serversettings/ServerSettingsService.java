package beta.com.discordbotwebsite.dcmodbot.serversettings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

@Service
public class ServerSettingsService {

    private final MongoCollection<Document> collection;

    @Autowired
    public ServerSettingsService(ServerSettingsRepository repository) {
        this.collection = repository.getCollection();
    }

    public void setAntiSpamEnabled(String discordServerId, boolean antiSpamEnabled) {
        ServerSettings serverSettings = new ServerSettings(collection);
        serverSettings.setAntiSpamEnabled(discordServerId, antiSpamEnabled);
    }

    public void setAntiVirusEnabled(String discordServerId, boolean antiVirusEnabled) {
        ServerSettings serverSettings = new ServerSettings(collection);
        serverSettings.setAntiVirusEnabled(discordServerId, antiVirusEnabled);
    }

    public void setVerifySystemEnabled(String discordServerId, boolean verifySystemEnabled) {
        ServerSettings serverSettings = new ServerSettings(collection);
        serverSettings.setVerifySystemEnabled(discordServerId, verifySystemEnabled);
    }

    public void setVoiceActionEnabled(String discordServerId, boolean voiceActionEnabled) {
        ServerSettings serverSettings = new ServerSettings(collection);
        serverSettings.setVoiceActionEnabled(discordServerId, voiceActionEnabled);
    }
}
