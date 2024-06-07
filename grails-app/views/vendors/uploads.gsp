<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <g:render template="searchUploads"/>
    </div>

    <div class="col-md-12" style="display: flex;justify-content: space-between">
        <label style="display: flex; padding-right: 10px">Download Template : <export:formats formats="['csv']" /></label>
        <sec:link class="btn btn-primary pull-right" controller="vendors" action="createUpload"><i class="fa fa-upload"></i> Upload</sec:link>
    </div>

    <div class="col-md-12 m-t-10" id="vendor-upload-list-div">
        <g:render template="uploadList" model="[vendorUploadList: vendorUploadList]"/>
    </div>
</div>
</body>
</html>
