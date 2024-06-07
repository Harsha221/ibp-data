package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class AuditLogsService {


    def list(Map params) {
        AuditLogs.createCriteria().list(params) {
            if (params?.entityType) {
                eq('entityType', params.entityType)
            }
            // Add more conditions as necessary
            // Order the results
            order(params?.sort ?: 'timestamp', params?.order ?: 'desc')
        }
    }
    def logAction(String action, String entityType, Long entityId, String user,String oldValue,String newValue ) {
        def auditLogs = new AuditLogs(
                action: action,
                entityType: entityType,
                entityId: entityId,
                user: user,
                timestamp: new Date(),
                oldValue: oldValue,
                newValue: newValue
        )
        auditLogs.save()
    }
}