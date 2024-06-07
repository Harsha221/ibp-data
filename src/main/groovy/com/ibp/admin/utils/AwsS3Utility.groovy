package com.ibp.admin.utils

import com.ibp.admin.Constants
import grails.core.GrailsApplication
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.CopyObjectRequest
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest

class AwsS3Utility {

    private final S3Client client
    GrailsApplication grailsApplication

    AwsS3Utility(GrailsApplication grailsApplication) {
        this.client = S3Client.builder().region(Region.AP_SOUTH_1).credentialsProvider(
                StaticCredentialsProvider.create(AwsBasicCredentials.create(
                        grailsApplication.config.getProperty(Constants.S3Properties.AWS_ACCESS_KEY),
                        grailsApplication.config.getProperty(Constants.S3Properties.AWS_SECRET_KEY)))).build()
    }

    /*String uploadFile(byte[] bytes, String path, String fileName) {
        String fileUrl = "${grailsApplication.config.getProperty(Constants.S3Properties.FOLDER_NAME)}${path}${Constants.SLASH}${fileName}"
        PutObjectRequest request = (PutObjectRequest) PutObjectRequest.builder()
                .bucket(grailsApplication.config.getProperty(Constants.S3Properties.BUCKET))
                .key(fileUrl)
                .acl(ObjectCannedACL.PUBLIC_READ).build()
        client.putObject(request, RequestBody.fromBytes(bytes))
        fileUrl
    }*/

    void removeFileFromS3(String path) {
        try {
            DeleteObjectRequest request = (DeleteObjectRequest) DeleteObjectRequest.builder().bucket(grailsApplication.config.getProperty(Constants.S3Properties.BUCKET)).key(path).build()
            client.deleteObject(request)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void copyObject(String sourcePath, String destinationPath) {
        CopyObjectRequest copyObjRequest = CopyObjectRequest.builder()
                .sourceBucket(grailsApplication.config.getProperty(Constants.S3Properties.BUCKET))
                .sourceKey(sourcePath)
                .destinationBucket(grailsApplication.config.getProperty(Constants.S3Properties.BUCKET))
                .destinationKey(destinationPath)
                .build() as CopyObjectRequest
        client.copyObject(copyObjRequest)
    }

   /* private final S3Client s3Client = S3Client.builder().
            credentialsProvider(awsCredentialProvider).build()

    private final AwsCredentialsProvider awsCredentialProvider = StaticCredentialsProvider.create(AwsBasicCredentials.create(
            grailsApplication.config.getProperty(Constants.S3Properties.AWS_ACCESS_KEY),
            grailsApplication.config.getProperty(Constants.S3Properties.AWS_SECRET_KEY))
    )
*/
}