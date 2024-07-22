package beta.com.discordbotwebsite.service;

import beta.com.discordbotwebsite.domain.ApproveUser;
import beta.com.discordbotwebsite.model.ApproveUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ApproveUserMapper {

    ApproveUserDTO updateApproveUserDTO(ApproveUser approveUser,
            @MappingTarget ApproveUserDTO approveUserDTO);

    @Mapping(target = "id", ignore = true)
    ApproveUser updateApproveUser(ApproveUserDTO approveUserDTO,
            @MappingTarget ApproveUser approveUser);

}
