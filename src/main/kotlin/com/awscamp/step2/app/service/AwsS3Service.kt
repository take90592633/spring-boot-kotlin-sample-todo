package com.awscamp.step2.app.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.nio.file.Path

@Service
class AwsS3Service {

    @Value("\${aws.s3.bucket}")
    private val bucket: String = ""

    fun uploadS3(
        key: String,
        localPath: Path
    ) {
        val client = S3Client.builder()
            .region(Region.AP_NORTHEAST_1)
            .credentialsProvider(ProfileCredentialsProvider.create())
            .build()

        val request = PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .build()

        client.use {
            it.putObject(request, RequestBody.fromFile(localPath))
        }
    }
}
