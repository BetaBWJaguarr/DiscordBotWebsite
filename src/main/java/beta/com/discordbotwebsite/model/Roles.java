package beta.com.discordbotwebsite.model;

import lombok.experimental.FieldNameConstants;


@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum Roles {

    @FieldNameConstants.Include
    ADMINISTRATOR,
    @FieldNameConstants.Include
    USER

}
