package com.awscamp.step2.app.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import software.amazon.awssdk.core.SdkBytes
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.ses.SesClient
import software.amazon.awssdk.services.ses.model.RawMessage
import software.amazon.awssdk.services.ses.model.SendRawEmailRequest
import software.amazon.awssdk.services.ses.model.SesException
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.util.Properties
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Service
class AwsSesService {

    @Value("\${aws.ses.mail}")
    private val sender: String = ""

    @Value("\${aws.ses.mail}")
    private val recipient: String = ""

    fun sendSes(
        subject: String, // タイトル
        bodyText: String // 本文
    ) {
        val client = SesClient.builder()
            .region(Region.AP_NORTHEAST_1)
            .build()

        try {
            client.use {
                send(
                    client = it,
                    sender = sender,
                    recipient = recipient,
                    subject = subject,
                    bodyText = bodyText
                )
            }
        } catch (e: IOException) {
            e.stackTrace
        } catch (e: MessagingException) {
            e.stackTrace
        }
    }

    private fun send(
        client: SesClient,
        sender: String, // 送信者
        recipient: String, // 受信者
        subject: String, // タイトル
        bodyText: String // 本文
    ) {
        val session = Session.getDefaultInstance(Properties())
        val message = MimeMessage(session)
        message.setSubject(subject, "UTF-8")
        message.setFrom(InternetAddress(sender))
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient))
        message.setText(bodyText, "UTF-8")

        try {
            println("Attempting to send an email through Amazon SES " + "using the AWS SDK for Java...")
            val outputStream = ByteArrayOutputStream()
            message.writeTo(outputStream)

            val buf = ByteBuffer.wrap(outputStream.toByteArray())
            val arr = ByteArray(buf.remaining())
            buf.get(arr)

            val data = SdkBytes.fromByteArray(arr)
            val rawMessage = RawMessage.builder()
                .data(data)
                .build()

            val rawEmailRequest = SendRawEmailRequest.builder()
                .rawMessage(rawMessage)
                .build()

            client.sendRawEmail(rawEmailRequest)
            println("Email message sent")
        } catch (e: SesException) {
            println(e.awsErrorDetails().errorMessage())
        }
    }
}
