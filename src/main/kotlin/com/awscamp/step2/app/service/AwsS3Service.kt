package com.awscamp.step2.app.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.ListObjectsRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.File

@Service
class AwsS3Service {

    @Value("\${aws.s3.bucket}")
    private val bucket: String = ""

    fun upload(
        key: String,
        file: File
    ) {
        val client = S3Client.builder()
            .region(Region.AP_NORTHEAST_1)
            .build()

        val request = PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .build()

        client.use {
            it.putObject(request, RequestBody.fromFile(file))
        }
    }

    fun delete(
        key: String
    ) {
        val client = S3Client.builder()
            .region(Region.AP_NORTHEAST_1)
            .build()

        val deleteObjectRequest = DeleteObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .build()

        client.use {
            it.deleteObject(deleteObjectRequest)
        }
    }

    fun objectList(): List<String> {
        val client = S3Client.builder()
            .region(Region.AP_NORTHEAST_1)
            .build()

        val listObject = ListObjectsRequest
            .builder()
            .bucket(bucket)
            .build()

        val objectList = mutableListOf<String>()
        client.use { s3Client ->
            val res = s3Client.listObjects(listObject)
            val resObjects = res.contents()
            resObjects.forEach {
                objectList.add(it.key())
            }
        }
        return objectList
    }
}
