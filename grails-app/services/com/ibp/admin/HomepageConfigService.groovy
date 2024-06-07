package com.ibp.admin

import com.ibp.admin.commands.HomepageConfigCommand
import grails.gorm.transactions.Transactional

@Transactional
class HomepageConfigService {

    def awsService

    def list() {
        HomepageConfig.list()
    }

    def save(HomepageConfig homepageConfig) {
        homepageConfig.save(flush: true)
    }

    def saveHomePageAds(HomepageConfig homepageConfig, HomepageConfigCommand homepageConfigCommand, Map params) {
        List<HomepageAds> configAds = []
        String oldAds = ''
        if (params?.old)
        {
            if (params?.old instanceof List) {
                oldAds = params?.old ? (params?.old as List).join(',') : ''
            }
            else
            {
                oldAds = params?.old
            }

        }
        homepageConfig?.homePageAds?.each {
              if(oldAds?.indexOf(it?.id?.toString()) < 0) {
                configAds.add(it)
            }
        }

        configAds?.each {
                awsService.removeImageFromS3(it.imageUrl, Constants.S3Folders.HOME_PAGE_ADS, it?.homepageAdsId)
                homepageConfig.removeFromHomePageAds(it)
                it.delete(flush: true)
        }

        homepageConfigCommand?.imageFiles?.each {
            if (!it.empty) {
                String imageId = UUID.randomUUID()
                String imageUrl = awsService.uploadImageToS3(it, Constants.S3Folders.HOME_PAGE_ADS, imageId)
                homepageConfig.addToHomePageAds(new HomepageAds(homepageAdsId: imageId, imageUrl: imageUrl,
                        homepageConfig: homepageConfig))
                save(homepageConfig)
            }
        }
    }
}
