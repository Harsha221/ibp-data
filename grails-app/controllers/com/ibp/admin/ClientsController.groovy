package com.ibp.admin

import com.ibp.admin.commands.ClientsCommand
import grails.converters.JSON

import javax.xml.bind.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class ClientsController {
    ClientsService clientsService
    AwsService awsService
    UserService userService
    AuditLogsService auditLogsService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def clientsList = clientsService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    clientsList : clientsList,
                    clientsCount: clientsList.totalCount
            ]
            return
        }
        [clientsList : clientsList, clientsCount: clientsList.totalCount, params: params]
    }

    def create(){
        respond new Clients(params)
    }

    def save(ClientsCommand clientsCommand){
        if(clientsCommand == null){
            notfound()
            return
        }
        if(clientsCommand?.hasErrors()){
            render view:'create' , model:[clientsCommand:clientsCommand]
            return
        }
        Clients clients = new Clients()
        bindData(clients,clientsCommand)
        try{
            clients?.status = clientsCommand?.active
            if(clientsCommand?.imageFile?.size>0){
                String logoUrl = awsService.uploadImageToS3(clientsCommand?.imageFile,Constants.S3Folders.CLIENTS,"logoUrl_"+clients?.clientsId)
                clients?.logoUrl = logoUrl
            }
            clients?.createdBy = userService?.getLoggedInUser()?.username
            clients?.updatedBy = userService?.getLoggedInUser()?.username
            clientsService.save(clients)
            auditLogsService.logAction('Save','Clients',clients?.id,userService?.getLoggedInUser()?.username,(clients as JSON).toString(false),(clients as JSON).toString(false))
        }
        catch (ValidationException e){
            respond clients.errors,view:'create'
            return
        }
        request.withFormat {
            form multipartForm{
                flash.message = 'Clients Created successfully'
                redirect action: 'index'
            }
            '*'{ respond clients, [status: CREATED] }
        }

    }
    def edit(Long id){
        Clients clients = clientsService.get(id)
        respond clients
    }

    def update(ClientsCommand clientsCommand){
        if(clientsCommand == null){
            notFound()
            return
        }

        Clients clients = Clients.findById(params?.id)
        if(!clients){
            notFound()
            return
        }
        def oldClients = clients.clone()
        try {
            clients?.status = clientsCommand?.active
            String logoUrl = clients?.logoUrl
            bindData(clients,clientsCommand)
            if(clientsCommand?.imageFile?.size>0){
                logoUrl = awsService.uploadImageToS3(clientsCommand?.imageFile,'clients',"logoUrl_"+clients?.clientsId)
                clients?.logoUrl = logoUrl
            }
            else if(clientsCommand?.imageFile?.size ==0 && logoUrl){
                clients?.logoUrl = logoUrl
            }
            else{
                awsService.removeImageFromS3(logoUrl,'prod/clients',"logoUrl_"+clients?.clientsId)
            }
            clients?.updatedBy = userService?.getLoggedInUser()?.username
            clientsService.save(clients)
            auditLogsService.logAction('Update','Clients',clients?.id,userService?.getLoggedInUser()?.username,(oldClients as JSON).toString(false),(clients as JSON).toString(false))
        }
        catch (ValidationException ignored){
            respond clients.errors,view: 'edit'
            return
        }
        request.withFormat {
            from multipartForm{
                flash.message = 'Clients updated successfully '
                redirect action: 'index'
            }
            '*'{ respond clients, [status: OK] }
        }

    }
    protected void notFound(){
        request.withFormat {
            form multipartForm{
                flash.message = 'default.not.found.message'
                redirect action: "index",method:"GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def toggleStatus (Long toggleId){
        if(toggleId == null){
            notFound()
            return
        }
        String message
        Clients clients = Clients.findById(toggleId)
        def oldClientsStatus = clients.clone()
        if(clients?.status){
            clients.status = false
            message = 'Clients has been Deactivated successfully'
        }
        else{
            clients.status = true
            message = 'Clients  has been Activated successfully'
        }
        clientsService.save(clients)
        if(clients?.status == false){
            auditLogsService.logAction('DeActivated','Client',clients?.id,userService?.getLoggedInUser()?.username,(oldClientsStatus as JSON).toString(false),(clients as JSON).toString(false))
        }
        else{
            auditLogsService.logAction('Activated','Client',clients?.id,userService?.getLoggedInUser()?.username,(oldClientsStatus as JSON).toString(false),(clients as JSON).toString(false))
        }
        request.withFormat {
            form multipartForm{
                flash.message = message
                redirect action: "Index",method: "GET"
            }
        }
    }

}
