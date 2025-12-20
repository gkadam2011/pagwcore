package com.anthem.pagw.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.Map;

/**
 * SQS message wrapper for inter-service communication.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagwMessage {

    private String messageId;
    private String pagwId;
    private String eventType;
    private String sourceService;
    private String targetService;
    private String payload;
    private Map<String, String> headers;
    private Instant timestamp;
    private String correlationId;
    private int attemptNumber;

    public static PagwMessage create(String pagwId, String eventType, 
                                      String sourceService, String payload) {
        return PagwMessage.builder()
                .messageId(java.util.UUID.randomUUID().toString())
                .pagwId(pagwId)
                .eventType(eventType)
                .sourceService(sourceService)
                .payload(payload)
                .timestamp(Instant.now())
                .correlationId(pagwId)
                .attemptNumber(1)
                .build();
    }
}
