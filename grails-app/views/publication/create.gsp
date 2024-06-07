<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-10">
        <g:render template="/common/global/errorMessage" bean="${this.publication}" />
        <div class="panel">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-fw ti-star"></i>Publication</h3>
            </div>
            <div class="panel-body">
                <form:validateUploadForm name="create-clients" action="save" method="POST" class="form-horizontal bv-form" novalidate="novalidate">
                    <g:render template= "form" model="[data: this.publication]"/>
                </form:validateUploadForm>
            </div>
        </div>
    </div>
</div>
</body>
</html>