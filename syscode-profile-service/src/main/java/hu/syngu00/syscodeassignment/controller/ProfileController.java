package hu.syngu00.syscodeassignment.controller;

import hu.syngu00.syscodeassignment.model.dto.Profile;
import hu.syngu00.syscodeassignment.model.dto.ProfileModifyOrCreateRequest;
import hu.syngu00.syscodeassignment.service.ProfileCommandService;
import hu.syngu00.syscodeassignment.service.ProfileQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/profile")
public class ProfileController {

    private final ProfileCommandService profileCommand;
    private final ProfileQueryService profileQuery;

    @PostMapping
    @Operation(summary = "Create a new profile",
            responses = {@ApiResponse(responseCode = "200", description = "Profile created", content = @Content(schema = @Schema(implementation = Profile.class)))}
    )
    public Profile create(@RequestBody ProfileModifyOrCreateRequest request) {
        return profileCommand.create(request);
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Update an existing profile",
            responses = {@ApiResponse(responseCode = "200", description = "Profile updated", content = @Content(schema = @Schema(implementation = Profile.class)))}
    )
    public Profile update(@PathVariable(name = "id") UUID id, @RequestBody ProfileModifyOrCreateRequest request) {
        return profileCommand.update(id, request);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a profile",
            responses = {@ApiResponse(responseCode = "200", description = "Profile deleted")}
    )
    public void delete(@PathVariable(name = "id") UUID id) {
        profileCommand.delete(id);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Find a profile by ID",
            responses = {@ApiResponse(responseCode = "200", description = "Profile found", content = @Content(schema = @Schema(implementation = Profile.class)))}
    )
    public Profile findOne(@Parameter(description = "ID of the profile to be found") @PathVariable(name = "id") UUID id) {
        return profileQuery.findOne(id);
    }

    @GetMapping
    @Operation(summary = "List all profiles",
            responses = {@ApiResponse(responseCode = "200", description = "Profiles listed", content = @Content(schema = @Schema(implementation = Profile.class)))}
    )
    public List<Profile> list() {
        return profileQuery.list();
    }
}
