<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-10">
        <g:render template="/common/global/errorMessage" bean="${this.affiliationProductCategory}" />
        <div class="panel ">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-fw ti-star"></i> Affiliation Product Category</h3>
            </div>
            <div class="panel-body">
                <form:validateUploadForm name="update-category" action="updateProductCategory" id="${this.affiliationProductCategory?.id}" method="put" class="form-horizontal bv-form" novalidate="novalidate" >
                    <g:render template="productCategory/form" model="[data: this.affiliationProductCategory]"/>
                </form:validateUploadForm>
            </div>
        </div>
    </div>
</div>
</body>
</html>
