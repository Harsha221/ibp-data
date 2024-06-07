package com.ibp.admin

import com.ibp.admin.commands.AboutCommitteeCommand
import com.ibp.admin.commands.AdvertisementCommand
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class AboutCommitteesController {

    AboutCommitteesService aboutCommitteesService
    def awsService
    UserService userService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def aboutCommitteesList = aboutCommitteesService.list(params)
        if (request.xhr) {
            log.info("XHR : ")
            render template: 'list', model: [
                    aboutCommitteesList : aboutCommitteesList,
                    aboutCommitteesCount: aboutCommitteesList?.totalCount
            ]
            return
        }
        [aboutCommitteesList: aboutCommitteesList, aboutCommitteesCount: aboutCommitteesList?.totalCount, params: params]
    }

    def create() {
        respond new CommitteeMembers(params)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = 'default.not.found.message'
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def save(AboutCommitteeCommand aboutCommitteeCommand) {
        if (aboutCommitteeCommand == null) {
            notFound()
            return
        }

        if (aboutCommitteeCommand?.hasErrors()) {
            render view: 'create', model: [aboutCommitteeCommand: aboutCommitteeCommand]
            return
        }

        CommitteeMembers committeeMembers = new CommitteeMembers()
        bindData(committeeMembers, aboutCommitteeCommand)
        try {


            committeeMembers.designation = aboutCommitteeCommand?.level1
            committeeMembers.level = aboutCommitteeCommand?.designation1

            committeeMembers.type = aboutCommitteeCommand?.typeId1
            committeeMembers.typeId = aboutCommitteeCommand?.type1

            committeeMembers.status = aboutCommitteeCommand.active
            if (!committeeMembers?.committeeMembersId) {
                committeeMembers?.committeeMembersId = UUID.randomUUID().toString()
            }
            Association association = Association.findById(aboutCommitteeCommand?.associationId)
            committeeMembers?.association = association
            if (aboutCommitteeCommand?.imageFile?.size > 0) {
                String thumbUrl = awsService.uploadImageToS3(aboutCommitteeCommand?.imageFile ,Constants.S3Folders.COMMITTEEMEMBERS,"thumbUrl_"+committeeMembers?.committeeMembersId)
                committeeMembers?.thumbUrl = thumbUrl
            }


            committeeMembers?.createdBy = userService?.getLoggedInUser()?.username
            committeeMembers?.updatedBy = userService?.getLoggedInUser()?.username
            log.info("Save Method called finally...")
            aboutCommitteesService.save(committeeMembers)

        } catch (ValidationException e) {
            log.info("validation exception : "+e.stackTrace)
            log.error("error : "+committeeMembers.errors)
            respond committeeMembers.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Committee Members created successfully'
                redirect action: 'index'
            }
            '*' { respond committeeMembers, [status: CREATED] }
        }
    }

    def edit(Long id) {
        CommitteeMembers committeeMembers = aboutCommitteesService.get(id)
        [committeeMembers: committeeMembers]
    }

    def update(AboutCommitteeCommand aboutCommitteeCommand) {

        if (aboutCommitteeCommand == null) {
            notFound()
            return
        }

        CommitteeMembers committeeMembers = CommitteeMembers.findById(params?.id)
        if (!committeeMembers) {
            notFound()
            return
        }


        try {
            bindData(committeeMembers, aboutCommitteeCommand)

            committeeMembers.designation = aboutCommitteeCommand?.level1
            committeeMembers.level = aboutCommitteeCommand?.designation1

            committeeMembers.type = aboutCommitteeCommand?.typeId1
            committeeMembers.typeId = aboutCommitteeCommand?.type1

            String imageurl = committeeMembers?.thumbUrl

            committeeMembers.status = aboutCommitteeCommand.active
            if (!committeeMembers?.committeeMembersId) {
                committeeMembers?.committeeMembersId = UUID.randomUUID().toString()
            }

                if (aboutCommitteeCommand?.imageFile?.size > 0) {
                    awsService.removeImageFromS3(committeeMembers.thumbUrl, 'prod/committeeMembers', "thumbUrl_"+committeeMembers?.committeeMembersId)
                    String thumbUrl = awsService.uploadImageToS3(aboutCommitteeCommand?.imageFile ,Constants.S3Folders.COMMITTEEMEMBERS,"thumbUrl_"+committeeMembers?.committeeMembersId)
                    committeeMembers?.thumbUrl = thumbUrl
                } else if (aboutCommitteeCommand?.imageFile?.size == 0 && imageurl) {
                    committeeMembers?.thumbUrl = imageurl
                } else {
                    awsService.removeImageFromS3(committeeMembers.thumbUrl, 'prod/committeeMembers', "thumbUrl_"+committeeMembers?.committeeMembersId)
                }

            committeeMembers?.updatedBy = userService?.getLoggedInUser()?.username
            aboutCommitteesService.save(committeeMembers)

            request.withFormat {
                form multipartForm {
                    flash.message = 'Committee Members updated successfully'
                    redirect action: 'index'
                }
                '*' { respond committeeMembers, [status: OK] }
            }

        } catch (ValidationException ignored) {
            respond committeeMembers.errors, view: 'edit'
            return
        }
    }
    def toggleStatus(Long toggleId) {
        System.out.println("toggleid : " + toggleId)
        if (toggleId == null) {
            notFound()
            return
        }
        String message
        CommitteeMembers committeeMembers = CommitteeMembers.findById(toggleId)
        if (committeeMembers?.status) {
            committeeMembers.status = false
            message = 'Committee Members has been Deactivated successfully'
        } else {
            committeeMembers.status = true
            message = 'Committee Members has been Activated successfully'
        }
        aboutCommitteesService.save(committeeMembers)
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action: "index", method: "GET"
            }
        }
    }
}
