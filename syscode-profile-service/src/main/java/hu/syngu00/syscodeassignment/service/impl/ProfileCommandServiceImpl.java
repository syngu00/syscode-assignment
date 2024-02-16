package hu.syngu00.syscodeassignment.service.impl;

import am.ik.yavi.core.ConstraintViolations;
import am.ik.yavi.core.ConstraintViolationsException;
import hu.syngu00.syscodeassignment.exception.ResourceNotFoundException;
import hu.syngu00.syscodeassignment.model.dto.Profile;
import hu.syngu00.syscodeassignment.model.dto.ProfileModifyOrCreateRequest;
import hu.syngu00.syscodeassignment.model.entity.ProfileEntity;
import hu.syngu00.syscodeassignment.repository.ProfileRepository;
import hu.syngu00.syscodeassignment.service.ProfileCommandService;
import hu.syngu00.syscodeassignment.service.mapper.ProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository repository;
    private final ProfileMapper mapper;

    @Override
    public Profile create(ProfileModifyOrCreateRequest profile) {
        validate(profile);

        ProfileEntity saved = repository.save(new ProfileEntity(profile.getName(), profile.getEmail()));
        return mapper.toDto(saved);
    }

    @Override
    public Profile update(UUID id, ProfileModifyOrCreateRequest profile) {
        validate(profile);

        ProfileEntity existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        existing.setName(profile.getName());
        existing.setEmail(profile.getEmail());

        return mapper.toDto(repository.save(existing));
    }

    @Override
    public void delete(UUID id) {
        boolean isExist = repository.existsById(id);
        if (!isExist) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }


    private void validate(ProfileModifyOrCreateRequest profile) {
        ConstraintViolations violations = ProfileModifyOrCreateRequest.validator.validate(profile);
        if (!violations.isValid()) {
            throw new ConstraintViolationsException(violations);
        }
    }
}
