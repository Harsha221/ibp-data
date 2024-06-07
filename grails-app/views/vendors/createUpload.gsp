<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-6">
        <g:render template="/common/global/errorMessage" bean="${this.vendorFileUpload}" />
        <div class="panel ">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-fw ti-star"></i> Vendor Upload Details</h3>
            </div>
            <div class="panel-body">
                <form:validateUploadForm name="create-vendor-upload" action="saveUpload" method="POST" class="form-horizontal bv-form" novalidate="novalidate">
                    <g:render template="uploadForm" model="[data: this.vendorFileUpload]"/>
                </form:validateUploadForm>
            </div>
        </div>
    </div>
</div>
</body>
</html>
