package com.ibp.admin


import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class ConfigController {

    def configService
    UserService userService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", get: 'GET']

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def configList = configService.listConfig(params)
        if (request.xhr) {
            render template: 'list', model: [
                    configList : configList,
                    configCount: configList?.totalCount,
                    params: params
            ]
            return
        }
        [configList: configList, configCount: configList?.totalCount, params: params]
    }


//    def show(Long id) {
//        respond configService.get(id)
//    }

    def create() {

    }

    def save(Config config) {
        if (config == null) {
            notFound()
            return
        }
        if (config?.hasErrors()) {
            render view: 'create', model: [configInstance: config]
            return
        }

        try {
            config?.createdBy = userService?.getLoggedInUser()?.username
            config?.updatedBy = userService?.getLoggedInUser()?.username
            configService.saveConfigData(config)
        } catch (ValidationException e) {
            respond config.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Config created successfully'
                redirect action: 'index'
            }
            '*' { respond config, [status: CREATED] }
        }
    }

    def edit(Long id) {
        [configInstance: configService.get(id)]
    }

    def update(Config config) {

        if (config == null) {
            notFound()
            return
        }

        Config configInstance = Config.get(params?.id)
        if (!configInstance) {
            notFound()
            return
        }

        try {
            bindData(configInstance, config)
            configInstance?.updatedBy = userService?.getLoggedInUser()?.username
            configService.saveConfigData(configInstance)
        } catch (ValidationException ignored) {
            respond configInstance.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Config updated successfully'
                redirect action: 'index'
            }
            '*'{ respond configInstance, [status: OK] }
        }
    }


    def activateDeactivate(Long toggleId) {
        if (toggleId == null) {
            notFound()
            return
        }
        String message
        Config config = Config.findById(toggleId)
        config.isActive = !config.isActive
        configService.saveConfigData(config)
        message = "Config has been ${config.isActive ? 'Activated' : 'In-Activated'} successfully."
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"index", method:"GET"
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = 'default.not.found.message'//, args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
