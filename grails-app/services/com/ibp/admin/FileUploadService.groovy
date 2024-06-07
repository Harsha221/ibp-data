package com.ibp.admin

import grails.core.GrailsApplication
import groovy.util.logging.Slf4j
import org.springframework.web.multipart.MultipartFile

@Slf4j
class FileUploadService {

    GrailsApplication grailsApplication

    String upload(MultipartFile multipartFile , String filePath) {
    //    String filePath = grailsApplication.config.getProperty('user.file_upload.path')
        File uploadFolder = new File(filePath)
        if (!uploadFolder.exists()) {
            log.info "[upload] Creating directories: ${uploadFolder.absolutePath}"
            uploadFolder.mkdirs()
        }
        String fileUuid = UUID.randomUUID().toString()
        String uploadFilePath = "${uploadFolder}/${fileUuid}"
        log.info "[upload] uploadFilePath: ${uploadFilePath}"
        multipartFile.transferTo(new File(uploadFilePath))
        uploadFilePath
    }
}
