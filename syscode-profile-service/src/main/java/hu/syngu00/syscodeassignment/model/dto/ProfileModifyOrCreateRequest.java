package hu.syngu00.syscodeassignment.model.dto;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.constraint.CharSequenceConstraint;
import am.ik.yavi.core.Validator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModifyOrCreateRequest {
    public static final Validator<ProfileModifyOrCreateRequest> validator = ValidatorBuilder.<ProfileModifyOrCreateRequest>of()
            .constraint(ProfileModifyOrCreateRequest::getName, "name", CharSequenceConstraint::notBlank)
            .constraint(ProfileModifyOrCreateRequest::getName, "name", c -> c.lessThanOrEqual(255))
            .constraint(ProfileModifyOrCreateRequest::getEmail, "email", CharSequenceConstraint::notBlank)
            .constraint(ProfileModifyOrCreateRequest::getEmail, "email", c -> c.lessThanOrEqual(255))
            .constraint(ProfileModifyOrCreateRequest::getEmail, "email", CharSequenceConstraint::email)
            .build();
    private String name;
    private String email;
}
