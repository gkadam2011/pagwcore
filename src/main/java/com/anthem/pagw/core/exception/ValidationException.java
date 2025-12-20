package com.anthem.pagw.core.exception;

/**
 * Exception thrown when validation fails.
 */
public class ValidationException extends PagwException {

    public ValidationException(String message) {
        super("VALIDATION_ERROR", message);
    }

    public ValidationException(String message, String pagwId) {
        super("VALIDATION_ERROR", message, pagwId);
    }

    public ValidationException(String errorCode, String message, String pagwId) {
        super(errorCode, message, pagwId);
    }
}
