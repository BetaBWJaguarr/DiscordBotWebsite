package beta.com.discordbotwebsite.dcmodbot.serversettings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;

@Service
public class ServerSettingsService {

    private final MongoCollection<Document> collection;

    @Autowired
    public ServerSettingsService(ServerSettingsRepository repository) {
        this.collection = repository.getCollection();
    }

    // Setter Methods
    public void setAntiSpamEnabled(String discordServerId, boolean antiSpamEnabled) {
        ServerSettings serverSettings = new ServerSettings(collection);
        serverSettings.setAntiSpamEnabled(discordServerId, antiSpamEnabled);
    }

    public void setAntiSpamMessageLimit(String discordServerId, int messageLimit) {
        ServerSettings serverSettings = new ServerSettings(collection);
        serverSettings.setAntiSpamMessageLimit(discordServerId, messageLimit);
    }

    public void setAntiSpamTimeLimit(String discordServerId, int timeLimit) {
        ServerSettings serverSettings = new ServerSettings(collection);
        serverSettings.setAntiSpamTimeLimit(discordServerId, timeLimit);
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

    public void setAutoPunishEnabled(String discordServerId, boolean autoPunishEnabled) {
        ServerSettings serverSettings = new ServerSettings(collection);
        serverSettings.setAutoPunishEnabled(discordServerId, autoPunishEnabled);
    }

    public void setAntiSwearEnabled(String discordServerId, boolean enabled) {
        ServerSettings serverSettings = new ServerSettings(collection);
        serverSettings.setAntiSwearEnabled(discordServerId, enabled);
    }

    public void addAntiSwearWord(String discordServerId, String word) {
        ServerSettings serverSettings = new ServerSettings(collection);
        serverSettings.addAntiSwearWord(discordServerId, word);
    }

    public void removeAntiSwearWord(String discordServerId, String word) {
        ServerSettings serverSettings = new ServerSettings(collection);
        serverSettings.removeAntiSwearWord(discordServerId, word);
    }

    // Getter Methods
    public boolean isAntiSpamEnabled(String discordServerId) {
        ServerSettings serverSettings = new ServerSettings(collection);
        return serverSettings.isAntiSpamEnabled(discordServerId);
    }

    public Integer getAntiSpamMessageLimit(String discordServerId) {
        ServerSettings serverSettings = new ServerSettings(collection);
        return serverSettings.getAntiSpamMessageLimit(discordServerId);
    }

    public Integer getAntiSpamTimeLimit(String discordServerId) {
        ServerSettings serverSettings = new ServerSettings(collection);
        return serverSettings.getAntiSpamTimeLimit(discordServerId);
    }

    public boolean isAntiVirusEnabled(String discordServerId) {
        ServerSettings serverSettings = new ServerSettings(collection);
        return serverSettings.isAntiVirusEnabled(discordServerId);
    }

    public boolean isVerifySystemEnabled(String discordServerId) {
        ServerSettings serverSettings = new ServerSettings(collection);
        return serverSettings.isVerifySystemEnabled(discordServerId);
    }

    public boolean isVoiceActionEnabled(String discordServerId) {
        ServerSettings serverSettings = new ServerSettings(collection);
        return serverSettings.isVoiceActionEnabled(discordServerId);
    }

    public boolean isAutoPunishEnabled(String discordServerId) {
        ServerSettings serverSettings = new ServerSettings(collection);
        return serverSettings.isAutoPunishEnabled(discordServerId);
    }

    public boolean getAntiSwearEnabled(String discordServerId) {
        ServerSettings serverSettings = new ServerSettings(collection);
        return serverSettings.getAntiSwearEnabled(discordServerId);
    }

    public List<String> getAntiSwearWordsList(String discordServerId) {
        ServerSettings serverSettings = new ServerSettings(collection);
        return serverSettings.getAntiSwearWordsList(discordServerId);
    }
}
