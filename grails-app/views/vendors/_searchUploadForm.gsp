<%@ page import="com.ibp.admin.enums.VendorUploadStatus;" %>
<form name="uploadSearchForm">
    <div class="row">
        <div class="col-sm-2">
            <div class="form-group">
                <input type="text" name="fileName" id="fileName" class="form-control input-md" placeholder="File Name"
                       value="${params?.fileName}"/>
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">
                <form:select
                        id="status"
                        name="status"
                        class="form-control"
                        value="${params?.status}"
                        from="${VendorUploadStatus.values()}"
                        optionValue="label"
                        optionKey="status"
                        noSelection="['':' -- Select Status -- ']" />
            </div>
        </div>
        <div class="col-sm-1">
            <g:submitToRemote
                action="uploads"
                class="btn btn-success pull-right"
                value="Search"
                before="showLoader('vendor-upload-list-div')"
                update="vendor-upload-list-div" />
        </div>
    </div>
</form>

<script type="text/javascript">
    $( document ).ready(function() {
        $('#status').select2()
    });
</script>