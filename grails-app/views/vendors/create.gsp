<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <div class="row">
        <g:render template="/common/global/errorMessage" bean="${this.vendorUploadDataInstance}" />
        <form:validateForm name="create-vendor" action="save" enctype="multipart/form-data" method="POST" class="form-horizontal bv-form" novalidate="novalidate">
            <g:render template="form" model="[data: vendorUploadDataInstance]"/>
        </form:validateForm>
    </div>
</body>
</html>
