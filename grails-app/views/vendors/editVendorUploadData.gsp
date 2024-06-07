<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <div class="row">
        <g:render template="/common/global/errorMessage" bean="${vendorUploadDataInstance}" />
        <form:validateForm name="updateVendorForm" action="correctVendorUploadData" id="${vendorUploadDataInstance?.id}" method="put" class="form-horizontal bv-form" novalidate="novalidate">
            <g:render template="vendorUploadDataForm" model="[data: vendorUploadDataInstance, params: params]"/>
        </form:validateForm>
    </div>
</body>
</html>
