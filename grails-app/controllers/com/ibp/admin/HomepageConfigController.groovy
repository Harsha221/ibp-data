package com.ibp.admin

import com.ibp.admin.commands.HomepageConfigCommand

import static org.springframework.http.HttpStatus.CREATED

class HomepageConfigController {

    def homepageConfigService

    def index() {
        [homepageConfigInstance: HomepageConfig?.first(), params: params]
    }

    def save(HomepageConfigCommand homepageConfigCommand) {
        log.info('Store Home page config >> ' + homepageConfigCommand?.properties)
        if (homepageConfigCommand?.hasErrors()) {
            render view: 'index', model: [homepageConfigInstance: homepageConfigCommand]
            return
        }

        HomepageConfig homepageConfig = null
        if (homepageConfigCommand?.id) {
            homepageConfig = HomepageConfig?.get(homepageConfigCommand?.id)
            homepageConfig.hashTags = homepageConfigCommand?.hashTags
        } else {
            homepageConfig = new HomepageConfig(hashTags: homepageConfigCommand?.hashTags)
        }

        homepageConfigService.save(homepageConfig)
        homepageConfigService.saveHomePageAds(homepageConfig, homepageConfigCommand, params)
        request.withFormat {
            form multipartForm {
                flash.message = "Homepage config ${homepageConfigCommand?.id ? 'updated' : 'saved'} successfully"
                redirect action: 'index'
            }
            '*' { [status: CREATED] }
        }
    }
}
