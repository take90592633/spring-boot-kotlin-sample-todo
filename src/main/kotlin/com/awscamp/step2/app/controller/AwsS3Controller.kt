package com.awscamp.step2.app.controller

import com.awscamp.step2.app.service.AwsS3Service
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream

@Controller
@RequestMapping("/s3")
class AwsS3Controller(
    private val awsS3Service: AwsS3Service
) {

    @Value("\${aws.s3.folder-path}")
    private val folderPath = ""

    @GetMapping("/list")
    fun s3(
        model: Model
    ): String {
        return objectList(model = model)
    }

    @PostMapping("/upload")
    fun upload(
        @RequestParam("multipartFile") multipartFile: MultipartFile
    ): String {
        if (multipartFile.isEmpty) return "redirect:/s3/list"

        val fileName = multipartFile.originalFilename ?: return "redirect:/s3/list"

        val uploadFile = File(fileName)
        val uploadFileStream = FileOutputStream(uploadFile)
        uploadFileStream.use {
            it.write(multipartFile.bytes)

            awsS3Service.upload(
                key = fileName,
                file = uploadFile
            )
        }

        return "redirect:/s3/list"
    }

    @PostMapping("/delete")
    fun delete(
        key: String
    ): String {
        awsS3Service.delete(key = key)
        return "redirect:/s3/list"
    }

    private fun objectList(
        model: Model
    ): String {
        val objectList = awsS3Service.objectList()
        model.addAttribute("objectList", objectList)
        return "s3"
    }
}
