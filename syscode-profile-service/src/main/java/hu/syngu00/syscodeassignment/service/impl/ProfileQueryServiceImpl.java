package hu.syngu00.syscodeassignment.service.impl;

import hu.syngu00.syscodeassignment.exception.ResourceNotFoundException;
import hu.syngu00.syscodeassignment.model.dto.Profile;
import hu.syngu00.syscodeassignment.repository.ProfileRepository;
import hu.syngu00.syscodeassignment.service.ProfileQueryService;
import hu.syngu00.syscodeassignment.service.mapper.ProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository repository;
    private final ProfileMapper mapper;

    @Override
    public Profile findOne(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public List<Profile> list() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}
