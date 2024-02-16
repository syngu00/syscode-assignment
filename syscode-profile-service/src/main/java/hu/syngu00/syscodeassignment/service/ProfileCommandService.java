package hu.syngu00.syscodeassignment.service;

import hu.syngu00.syscodeassignment.model.dto.Profile;
import hu.syngu00.syscodeassignment.model.dto.ProfileModifyOrCreateRequest;

import java.util.UUID;

public interface ProfileCommandService {

    Profile create(ProfileModifyOrCreateRequest profile);

    Profile update(UUID id, ProfileModifyOrCreateRequest profile);

    void delete(UUID id);

}
