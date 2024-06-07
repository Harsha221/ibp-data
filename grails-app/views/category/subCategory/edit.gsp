<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-10">
        <g:render template="/common/global/errorMessage" bean="${this.category}" />
        <div class="panel ">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-fw ti-star"></i>SubCategory</h3>
            </div>
            <div class="panel-body">
                <form:validateUploadForm name="updateCategoryForm" action="updateSubCategory" id="${this.category?.id}" method="put" class="form-horizontal bv-form" novalidate="novalidate">
                    <g:render template="subCategory/form" model="[data: this.category]"/>
                </form:validateUploadForm>
            </div>
        </div>
    </div>
</div>
</body>
</html>
