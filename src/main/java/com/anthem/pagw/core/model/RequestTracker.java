package com.anthem.pagw.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.Map;

/**
 * Core request tracker model - represents a PA request throughout its lifecycle.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestTracker {

    private String pagwId;
    private String externalRequestId;
    private String clientId;
    private String memberId;
    private String providerId;
    
    private RequestType requestType;
    private RequestStatus status;
    private String currentStage;
    
    private String fhirBundleS3Key;
    private String x12RequestS3Key;
    private String x12ResponseS3Key;
    private String fhirResponseS3Key;
    
    private Map<String, Object> metadata;
    private String errorCode;
    private String errorMessage;
    
    private Instant createdAt;
    private Instant updatedAt;
    private Instant completedAt;

    public enum RequestType {
        PA_SUBMIT,
        PA_INQUIRY,
        PA_CANCEL,
        PA_UPDATE
    }

    public enum RequestStatus {
        RECEIVED,
        PARSING,
        VALIDATING,
        ENRICHING,
        PROCESSING_ATTACHMENTS,
        MAPPING,
        CALLING_DOWNSTREAM,
        BUILDING_RESPONSE,
        COMPLETED,
        FAILED,
        CANCELLED
    }
}
