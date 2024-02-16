package hu.syngu00.syscodeassignment.model.dto;

public record Violation(String field, String messageKey, String message, Object[] args) {
}
