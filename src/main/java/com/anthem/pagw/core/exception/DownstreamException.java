package com.anthem.pagw.core.exception;

/**
 * Exception thrown when a downstream service call fails.
 */
public class DownstreamException extends PagwException {

    private final String serviceName;
    private final int statusCode;

    public DownstreamException(String serviceName, String message) {
        super("DOWNSTREAM_ERROR", message);
        this.serviceName = serviceName;
        this.statusCode = 0;
    }

    public DownstreamException(String serviceName, int statusCode, String message) {
        super("DOWNSTREAM_ERROR", message);
        this.serviceName = serviceName;
        this.statusCode = statusCode;
    }

    public DownstreamException(String serviceName, String message, Throwable cause) {
        super("DOWNSTREAM_ERROR", message, cause);
        this.serviceName = serviceName;
        this.statusCode = 0;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
