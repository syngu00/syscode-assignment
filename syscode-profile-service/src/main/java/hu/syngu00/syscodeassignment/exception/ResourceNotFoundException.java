package hu.syngu00.syscodeassignment.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Getter
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource Not Found")
public class ResourceNotFoundException extends RuntimeException {

    private final UUID resourceId;

    public ResourceNotFoundException(UUID resourceId) {
        super(String.format("Resource with ID: %s is not found", resourceId));
        this.resourceId = resourceId;
    }
}
