package hu.syngu00.syscodeassignment.service.mapper;

import hu.syngu00.syscodeassignment.model.dto.Profile;
import hu.syngu00.syscodeassignment.model.entity.ProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public Profile toDto(ProfileEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Profile(entity.getId(), entity.getName(), entity.getEmail());
    }

}
