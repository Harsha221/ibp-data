package com.ibp.admin.commands

import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

import javax.imageio.ImageIO

class AssociationCommand implements Validateable {

    String id
    String name
    String logoUrl
    String headerUrl
    String bannerUrl
    String mobileBannerUrl
    String redirectLink
    Boolean active = Boolean.FALSE
    MultipartFile bannerFile
    MultipartFile mobileBannerFile
    MultipartFile headerFile
    MultipartFile logoFile

    static constraints = {
        name nullable: false, blank: false
        List fileFormats = Holders.grailsApplication.getConfig().getProperty('ibp.vendors.upload.imageFormat', List)
        headerFile nullable: false, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val.empty) {
                return true
            }
         /*   def headerImage = ImageIO.read(val.getInputStream())
            headerImage.width > 500 || headerImage.height > 500 ? 'bookmaker.logo.invalid.format' : true
*/
            fileFormats.any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension as String)
            }
        }
        logoFile nullable: false, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val.empty) {
                return true
            }
            fileFormats.any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension as String)
            }
        }
        bannerFile nullable: false, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val.empty) {
                return true
            }
            fileFormats.any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension as String)
            }
        }
        mobileBannerFile nullable: false, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val.empty) {
                return true
            }
            fileFormats.any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension as String)
            }
        }
    }
}
