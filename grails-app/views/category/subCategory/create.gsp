<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-10">
        <g:render template="/common/global/errorMessage" bean="${this.categoryCommand}" />
        <div class="panel">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-fw ti-star"></i> SubCategory</h3>
            </div>
            <div class="panel-body">
                <form:validateUploadForm name="create-category" action="saveSubCategory" method="POST" class="form-horizontal bv-form" novalidate="novalidate">
                    <g:render template="subCategory/form" model="[data: this.categoryCommand]"/>
                </form:validateUploadForm>
            </div>
        </div>
    </div>
</div>
</body>
</html>
