<%@ page import="com.ibp.admin.enums.VendorDataStatus" %>
<form name="uploadDataSearchForm">
    <input type="hidden" name="id" value="${params?.id}" />
    <div class="row">
        <div class="col-sm-2">
            <div class="form-group">
                <input type="text" name="businessName" id="businessName" class="form-control input-md" placeholder="Business Name" />
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">
                <form:select
                        id="status"
                        name="status"
                        class="form-control"
                        from="${VendorDataStatus.values()}"
                        optionValue="label"
                        optionKey="status"
                        noSelection="['':' -- Select Status -- ']" />
            </div>
        </div>
        <div class="col-sm-1">
            <g:submitToRemote
                action="uploadView"
                class="btn btn-success pull-right"
                value="Search"
                before="showLoader('vendor-upload-data-list-div')"
                update="vendor-upload-data-list-div" />
        </div>
    </div>
</form>
