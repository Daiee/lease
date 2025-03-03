package com.daie.lease.service.impl;

import com.daie.lease.common.config.minio.MinioProperties;
import com.daie.lease.service.FileService;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public FileServiceImpl(MinioClient minioClient, MinioProperties minioProperties) {
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }

    @Override
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException,
        IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException,
        InternalException {

        String bucketName = minioProperties.getBucketName();
        boolean isBucketExists = minioClient.bucketExists(
            BucketExistsArgs.builder()
                .bucket(bucketName)
                .build()
        );
        if (!isBucketExists) {

            minioClient.makeBucket(
                MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build()
            );

            minioClient.setBucketPolicy(
                SetBucketPolicyArgs.builder()
                    .bucket(bucketName)
                    .config(createBucketPolicyConfig(bucketName))
                    .build()
            );
        }

        String fileName = new SimpleDateFormat("yyyyMMdd").format(new Date()) +
            "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(bucketName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .object(fileName)
                .contentType(file.getContentType())
                .build()
        );

        return String.join("/", minioProperties.getEndpoint(), bucketName, fileName);
    }

    private static String createBucketPolicyConfig(String bucketName) {
        return """
            {
              "Statement" : [ {
                "Action" : "s3:GetObject",
                "Effect" : "Allow",
                "Principal" : "*",
                "Resource" : "arn:aws:s3:::%s/*"
              } ],
              "Version" : "2012-10-17"
            }
            """.formatted(bucketName);
    }
}
