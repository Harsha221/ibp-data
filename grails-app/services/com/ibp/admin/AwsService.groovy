package com.ibp.admin

import com.ibp.admin.utils.IbpHubUtils
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import org.apache.commons.io.FilenameUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.ObjectCannedACL
import software.amazon.awssdk.services.s3.model.PutObjectRequest

@Transactional
class AwsService {

    GrailsApplication grailsApplication
    private final S3Client s3client

    AwsService (GrailsApplication grailsApplication) {
        this.grailsApplication = grailsApplication
        s3client = S3Client.builder().region(Region.AP_SOUTH_1).credentialsProvider(
                StaticCredentialsProvider.create(AwsBasicCredentials.create(
                        grailsApplication.config.getProperty(Constants.S3Properties.AWS_ACCESS_KEY),
                        grailsApplication.config.getProperty(Constants.S3Properties.AWS_SECRET_KEY)))).build()
    }

    String uploadFile(byte[] bytes, String path, String fileName) {
        String fileUrl = "${grailsApplication.config.getProperty(Constants.S3Properties.FOLDER_NAME)}${path}${Constants.SLASH}${fileName}"
        PutObjectRequest request = (PutObjectRequest) PutObjectRequest.builder()
                .bucket(grailsApplication.config.getProperty(Constants.S3Properties.BUCKET))
                .key(fileUrl)
                .acl(ObjectCannedACL.PUBLIC_READ).build()
        s3client.putObject(request, RequestBody.fromBytes(bytes))
        String baseUrl = grailsApplication.config.getProperty(Constants.S3Properties.S3_BASE_URL).replaceFirst('', Constants.BLANK)
        return IbpHubUtils.buildURI(baseUrl, "/${fileUrl}")
    }

    void removeFileFromS3(String path, String fileName) {
        try {
            path += "${Constants.SLASH}${fileName}"
            DeleteObjectRequest request = (DeleteObjectRequest) DeleteObjectRequest.builder().bucket(
                    grailsApplication.config.getProperty(Constants.S3Properties.BUCKET)).key(path).build()
            s3client.deleteObject(request)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
    String uploadImageToS3(MultipartFile imageFile, String path, String fileName) {
        String extension = FilenameUtils.getExtension(imageFile.getOriginalFilename())
        fileName += Constants.DOT + extension
        uploadFile(imageFile.getBytes() , path , fileName)
    }

    Void removeImageFromS3(String imageFile, String path, String fileName) {
        String extension = FilenameUtils.getExtension(imageFile)
        fileName += Constants.DOT + extension
        removeFileFromS3( path , fileName)
    }
}
