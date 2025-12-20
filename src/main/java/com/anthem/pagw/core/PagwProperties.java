package com.anthem.pagw.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

/**
 * Configuration properties for PAGW Core.
 * Prefix: pagw
 */
@Data
@ConfigurationProperties(prefix = "pagw")
public class PagwProperties {

    private String applicationId = "pagw";
    private Aws aws = new Aws();
    private Database database = new Database();
    private Queues queues = new Queues();

    @Data
    public static class Aws {
        private String region = "us-east-1";
        private String accountId;
        private Sqs sqs = new Sqs();
        private S3 s3 = new S3();
        private Secrets secrets = new Secrets();
        private DynamoDb dynamodb = new DynamoDb();
    }

    @Data
    public static class Sqs {
        private boolean enabled = true;
    }

    @Data
    public static class S3 {
        private boolean enabled = true;
        private String attachmentBucket;
    }

    @Data
    public static class Secrets {
        private boolean enabled = true;
        private String auroraSecretName;
    }

    @Data
    public static class DynamoDb {
        private boolean enabled = false;
        private String idempotencyTable = "pagw-idempotency";
    }

    @Data
    public static class Database {
        private String host;
        private int port = 5432;
        private String name = "pagw";
        private String schema = "pagw";
    }

    @Data
    public static class Queues {
        private String orchestrator;
        private String parser;
        private String validator;
        private String enricher;
        private String attachment;
        private String mapper;
        private String apiConnector;
        private String responseBuilder;
        private String callback;
    }
}
