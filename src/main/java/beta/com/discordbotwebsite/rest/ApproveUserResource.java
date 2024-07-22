package beta.com.discordbotwebsite.rest;

import beta.com.discordbotwebsite.model.ApproveUserDTO;
import beta.com.discordbotwebsite.service.ApproveUserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/approveUsers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApproveUserResource {

    private final ApproveUserService approveUserService;

    public ApproveUserResource(final ApproveUserService approveUserService) {
        this.approveUserService = approveUserService;
    }

    @GetMapping
    public ResponseEntity<List<ApproveUserDTO>> getAllApproveUsers() {
        return ResponseEntity.ok(approveUserService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApproveUserDTO> getApproveUser(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(approveUserService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createApproveUser(
            @RequestBody @Valid final ApproveUserDTO approveUserDTO) {
        final UUID createdId = approveUserService.create(approveUserDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateApproveUser(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final ApproveUserDTO approveUserDTO) {
        approveUserService.update(id, approveUserDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteApproveUser(@PathVariable(name = "id") final UUID id) {
        approveUserService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
