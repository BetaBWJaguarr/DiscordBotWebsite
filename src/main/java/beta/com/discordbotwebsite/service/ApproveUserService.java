package beta.com.discordbotwebsite.service;

import beta.com.discordbotwebsite.domain.ApproveUser;
import beta.com.discordbotwebsite.model.ApproveUserDTO;
import beta.com.discordbotwebsite.repos.ApproveUserRepository;
import beta.com.discordbotwebsite.util.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ApproveUserService {

    private final ApproveUserRepository approveUserRepository;
    private final ApproveUserMapper approveUserMapper;

    public ApproveUserService(final ApproveUserRepository approveUserRepository,
            final ApproveUserMapper approveUserMapper) {
        this.approveUserRepository = approveUserRepository;
        this.approveUserMapper = approveUserMapper;
    }

    public List<ApproveUserDTO> findAll() {
        final List<ApproveUser> approveUsers = approveUserRepository.findAll(Sort.by("id"));
        return approveUsers.stream()
                .map(approveUser -> approveUserMapper.updateApproveUserDTO(approveUser, new ApproveUserDTO()))
                .toList();
    }

    public ApproveUserDTO get(final UUID id) {
        return approveUserRepository.findById(id)
                .map(approveUser -> approveUserMapper.updateApproveUserDTO(approveUser, new ApproveUserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final ApproveUserDTO approveUserDTO) {
        final ApproveUser approveUser = new ApproveUser();
        approveUserMapper.updateApproveUser(approveUserDTO, approveUser);
        return approveUserRepository.save(approveUser).getId();
    }

    public void update(final UUID id, final ApproveUserDTO approveUserDTO) {
        final ApproveUser approveUser = approveUserRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        approveUserMapper.updateApproveUser(approveUserDTO, approveUser);
        approveUserRepository.save(approveUser);
    }

    public void delete(final UUID id) {
        approveUserRepository.deleteById(id);
    }

}
