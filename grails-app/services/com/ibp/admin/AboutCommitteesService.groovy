package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class AboutCommitteesService {
    def list(Map params) {
        System.out.println("search called..."+params)
        List<CommitteeMembers> committeeMembersList = CommitteeMembers.createCriteria().list(params) {
            if(params?.associationId) {
                Association association = Association.findById(params?.associationId)
                eq('association' , association)
            }
            if(params?.type){
                eq('typeId',Integer.parseInt(params?.type))
            }

            if (params?.name) {
                ilike('name', "%${params?.name}%")
            }
            if (params?.searchAboutCommitteeStatus) {
                if(params?.searchAboutCommitteeStatus?.equals("Activated")) {
                    eq('status', true)
                } else {
                    eq('status', false)
                }
            }
            order("dateCreated", "desc")
        } as List<CommitteeMembers>
        log.info("list of committee members : "+committeeMembersList?.toString())
        committeeMembersList
    }

    @Transactional
    CommitteeMembers save(CommitteeMembers committeeMembers) {
        committeeMembers.save(flush: true, failOnError: true)
    }

    @Transactional
    CommitteeMembers get(Long id) {
        CommitteeMembers.read(id)
    }

    @Transactional
    CommitteeMembers delete(Long id) {
        get(id).delete(flush: true, failOnError: true)
    }

}
