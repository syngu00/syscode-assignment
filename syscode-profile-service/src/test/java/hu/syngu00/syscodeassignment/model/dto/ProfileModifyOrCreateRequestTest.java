package hu.syngu00.syscodeassignment.model.dto;

import am.ik.yavi.core.ConstraintViolations;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class ProfileModifyOrCreateRequestTest {

    @Test
    public void validatorShouldPassForValidProfile() {
        ProfileModifyOrCreateRequest validProfile = new ProfileModifyOrCreateRequest("John Doe", "john.doe@example.com");
        ConstraintViolations violations = ProfileModifyOrCreateRequest.validator.validate(validProfile);

        MatcherAssert.assertThat(violations.isValid(), is(true));
        MatcherAssert.assertThat(violations.size(), is(0));
    }

    @Test
    public void validatorShouldFailForBlankName() {
        ProfileModifyOrCreateRequest invalidProfile = new ProfileModifyOrCreateRequest("", "john.doe@example.com");
        ConstraintViolations violations = ProfileModifyOrCreateRequest.validator.validate(invalidProfile);

        MatcherAssert.assertThat(violations.isValid(), is(false));
        MatcherAssert.assertThat(violations.size(), is(greaterThan(0)));
        MatcherAssert.assertThat(violations.get(0).message(), containsString("name"));
    }

    @Test
    public void validatorShouldFailForInvalidEmail() {
        ProfileModifyOrCreateRequest invalidProfile = new ProfileModifyOrCreateRequest("John Doe", "not-an-email");
        ConstraintViolations violations = ProfileModifyOrCreateRequest.validator.validate(invalidProfile);

        MatcherAssert.assertThat(violations.isValid(), is(false));
        MatcherAssert.assertThat(violations.size(), is(greaterThan(0)));
        MatcherAssert.assertThat(violations.get(0).message(), containsString("email"));
    }
}
