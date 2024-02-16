package hu.syngu00.syscodeassignment.service.mapper;

import hu.syngu00.syscodeassignment.model.dto.Profile;
import hu.syngu00.syscodeassignment.model.entity.ProfileEntity;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.*;

public class ProfileMapperTest {

    private ProfileMapper profileMapper;

    @BeforeEach
    public void setUp() {
        profileMapper = new ProfileMapper();
    }

    @Test
    public void shouldMapEntityToDtoCorrectly() {
        UUID id = UUID.randomUUID();
        ProfileEntity entity = new ProfileEntity(id, "John Doe", "john.doe@example.com");
        Profile dto = profileMapper.toDto(entity);

        MatcherAssert.assertThat(dto, allOf(
                hasProperty("id", equalTo(entity.getId())),
                hasProperty("name", equalTo(entity.getName())),
                hasProperty("email", equalTo(entity.getEmail()))
        ));
    }

    @Test
    public void shouldReturnNullWhenEntityIsNull() {
        Profile dto = profileMapper.toDto(null);
        MatcherAssert.assertThat(dto, is(nullValue()));
    }
}


