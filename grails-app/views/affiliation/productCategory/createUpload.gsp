<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-10">
        <g:render template="/common/global/errorMessage" bean="${this.productFileUpload}" />
        <div class="panel ">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-fw ti-star"></i> Category Upload</h3>
            </div>
            <div class="panel-body">
                <form:validateUploadForm name="create-product-upload" action="saveAffiliationProductUpload" method="POST" class="form-horizontal bv-form" novalidate="novalidate">
                    <g:render template="productCategory/uploadForm" model="[data: this.productFileUpload]"/>
                </form:validateUploadForm>
            </div>
        </div>
    </div>
</div>
</body>
</html>
