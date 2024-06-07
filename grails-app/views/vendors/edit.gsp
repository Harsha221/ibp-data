<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <div class="row">
        <g:render template="/common/global/errorMessage" bean="${vendorCreateCommand}" />
        <form:validateForm name="updateVendorForm" enctype="multipart/form-data" action="update" id="${vendorCreateCommand?.id}" method="put" class="form-horizontal bv-form" novalidate="novalidate">
            <g:render template="form" model="[multipleVideoMediaList: multipleVideoMediaList, multipleMediaList: multipleMediaList, vendorMediaList: vendorMediaList, data: vendorCreateCommand, categoriseSubCategories: categoriseSubCategories,
                                              subCategoriesProductsList: subCategoriesProductsList, category: category]"/>
        </form:validateForm>
    </div>
</body>
</html>
