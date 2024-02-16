package hu.syngu00.syscodeassignment.service;

import hu.syngu00.syscodeassignment.model.dto.Profile;

import java.util.List;
import java.util.UUID;

public interface ProfileQueryService {

    Profile findOne(UUID id);

    List<Profile> list();
}
