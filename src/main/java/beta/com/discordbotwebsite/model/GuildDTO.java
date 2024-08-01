package beta.com.discordbotwebsite.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GuildDTO {
    private String id;
    private String name;
    private String iconUrl;

    public GuildDTO(String id, String name, String iconUrl) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
    }
}
