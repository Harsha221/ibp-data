<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-10">
        <g:render template="/common/global/errorMessage" bean="${this.event}" />
        <div class="panel ">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-fw ti-star"></i> Event</h3>
            </div>
            <div class="panel-body">
                <form:validateUploadForm name="update-event" action="update" id="${this.event?.id}" method="put" class="form-horizontal bv-form" novalidate="novalidate" >
                    <g:render template="form" model="[data: this.event, eventPhotosList:eventPhotosList]"/>
                </form:validateUploadForm>
            </div>
        </div>
    </div>
</div>
</body>
</html>