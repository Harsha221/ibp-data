<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-10">
        <g:render template="/common/global/errorMessage" bean="${this.blogCategory}" />
        <div class="panel ">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-fw ti-star"></i>Blog Category</h3>
            </div>
            <div class="panel-body">
                <form:validateUploadForm name="update-blogCategory" action="update" id="${this.blogCategory?.id}" method="put" class="form-horizontal bv-form" novalidate="novalidate" >
                    <g:render template="form" model="[data: this.blogCategory]"/>
                </form:validateUploadForm>
            </div>
        </div>
    </div>
</div>
</body>
</html>
