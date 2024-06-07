<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-10">
        <g:render template="/common/global/errorMessage" bean="${this.configInstance}" />
        <div class="panel ">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-fw ti-star"></i> Config</h3>
            </div>
            <div class="panel-body">
                <form:validateUploadForm name="update-config" action="update" id="${this.configInstance?.id}" method="put" class="form-horizontal bv-form" novalidate="novalidate" >
                    <g:render template="form" model="[data: this.configInstance]"/>
                </form:validateUploadForm>
            </div>
        </div>
    </div>
</div>
</body>
</html>
