<%@ page import="grails.util.Holders" %>
<div class="form-group ">
    <div class="col-md-8 col-md-offset-4">
    <label style="display: flex;">Download Format :  <export:formats formats="['csv']" /></label>
</div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="uploadFile">
        File<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="file"
                id="uploadFile"
                name="uploadFile"
                value="${data?.uploadFile}"
                bean="${data}"
                class="form-control"
                placeholder="Choose file"
                required="true"
                accept="text/csv"/>
        <span class="pull-right text-danger">Valid formats: ${Holders.grailsApplication.getConfig().getProperty('ibp.vendors.upload.fileFormat', List).join(', ')}</span>
    </div>
</div>
<div class="form-group form-actions">
    <div class="col-md-8 col-md-offset-4">
        <button type="submit" class="btn btn-effect-ripple btn-primary">Upload</button>
        <g:link action="affiliationProduct" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>
