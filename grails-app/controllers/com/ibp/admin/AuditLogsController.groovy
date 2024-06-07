package com.ibp.admin

class AuditLogsController {
    def auditLogsService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def auditLogsList = auditLogsService.list(params)
        if(request.xhr){
            render template: 'list',model: [
                    auditLogsList: auditLogsList,
                    auditLogsCount : auditLogsList.totalCount
            ]
            return
        }
        [auditLogsList: auditLogsList,auditLogsCount: auditLogsList?.totalCount,params:params]
    }


}
