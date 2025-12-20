package com.anthem.pagw.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Service for S3 operations - upload, download, delete objects.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    public void uploadString(String bucket, String key, String content, String contentType) {
        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .contentType(contentType)
                    .build();

            s3Client.putObject(request, RequestBody.fromString(content, StandardCharsets.UTF_8));
            log.debug("Uploaded string to s3://{}/{}", bucket, key);
        } catch (Exception e) {
            log.error("Failed to upload string to s3://{}/{}", bucket, key, e);
            throw new RuntimeException("Failed to upload to S3", e);
        }
    }

    public void uploadBytes(String bucket, String key, byte[] content, String contentType) {
        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .contentType(contentType)
                    .build();

            s3Client.putObject(request, RequestBody.fromBytes(content));
            log.debug("Uploaded {} bytes to s3://{}/{}", content.length, bucket, key);
        } catch (Exception e) {
            log.error("Failed to upload bytes to s3://{}/{}", bucket, key, e);
            throw new RuntimeException("Failed to upload to S3", e);
        }
    }

    public void uploadStream(String bucket, String key, InputStream inputStream, 
                             long contentLength, String contentType) {
        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .contentType(contentType)
                    .contentLength(contentLength)
                    .build();

            s3Client.putObject(request, RequestBody.fromInputStream(inputStream, contentLength));
            log.debug("Uploaded stream to s3://{}/{}", bucket, key);
        } catch (Exception e) {
            log.error("Failed to upload stream to s3://{}/{}", bucket, key, e);
            throw new RuntimeException("Failed to upload to S3", e);
        }
    }

    public String downloadString(String bucket, String key) {
        try {
            GetObjectRequest request = GetObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            return s3Client.getObjectAsBytes(request).asUtf8String();
        } catch (Exception e) {
            log.error("Failed to download string from s3://{}/{}", bucket, key, e);
            throw new RuntimeException("Failed to download from S3", e);
        }
    }

    public byte[] downloadBytes(String bucket, String key) {
        try {
            GetObjectRequest request = GetObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            return s3Client.getObjectAsBytes(request).asByteArray();
        } catch (Exception e) {
            log.error("Failed to download bytes from s3://{}/{}", bucket, key, e);
            throw new RuntimeException("Failed to download from S3", e);
        }
    }

    public boolean exists(String bucket, String key) {
        try {
            HeadObjectRequest request = HeadObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            s3Client.headObject(request);
            return true;
        } catch (NoSuchKeyException e) {
            return false;
        } catch (Exception e) {
            log.error("Failed to check existence of s3://{}/{}", bucket, key, e);
            throw new RuntimeException("Failed to check S3 object existence", e);
        }
    }

    public void delete(String bucket, String key) {
        try {
            DeleteObjectRequest request = DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            s3Client.deleteObject(request);
            log.debug("Deleted s3://{}/{}", bucket, key);
        } catch (Exception e) {
            log.error("Failed to delete s3://{}/{}", bucket, key, e);
            throw new RuntimeException("Failed to delete from S3", e);
        }
    }

    public String generateKey(String pagwId, String fileType, String extension) {
        return String.format("%s/%s/%s.%s", 
                pagwId.substring(0, 2), 
                pagwId, 
                fileType, 
                extension);
    }
}
