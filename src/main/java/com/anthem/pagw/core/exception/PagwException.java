package com.anthem.pagw.core.exception;

/**
 * Base exception for PAGW application errors.
 */
public class PagwException extends RuntimeException {

    private final String errorCode;
    private final String pagwId;

    public PagwException(String message) {
        super(message);
        this.errorCode = "PAGW_ERROR";
        this.pagwId = null;
    }

    public PagwException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.pagwId = null;
    }

    public PagwException(String errorCode, String message, String pagwId) {
        super(message);
        this.errorCode = errorCode;
        this.pagwId = pagwId;
    }

    public PagwException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.pagwId = null;
    }

    public PagwException(String errorCode, String message, String pagwId, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.pagwId = pagwId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getPagwId() {
        return pagwId;
    }
}
