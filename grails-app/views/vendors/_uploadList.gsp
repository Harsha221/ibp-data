<%@ page import="com.ibp.admin.enums.VendorUploadStatus" %>
<div class="table-responsive table_data">
    <table class="table table-striped" id="vendorUploadListTable">
        <thead class="thead_data">
        <tr>
            <th>File Name</th>
            <th>Description</th>
            <th>Content Type</th>
            <th>Upload Action</th>
            <th>Size</th>
            <th>Status</th>
            <th class="text-center">Success Count</th>
            <th class="text-center">Failed Count</th>
            <th>Uploaded By</th>
            <th>Date Created</th>
            <th></th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${vendorUploadList}" var="data">
            <tr>
                <td>${data?.fileName}</td>
                <td>${data?.description}</td>
                <td>${data?.contentType}</td>
                <td>${data?.uploadAction}</td>
                <td>${data?.size}</td>
                <td>${VendorUploadStatus.parseByte(data?.status)}</td>
                <td class="text-center">${data?.successCount ?: 0}</td>
                <td class="text-center">${data?.failedCount ?: 0}</td>
                <td>${data?.uploadedBy?.username}</td>
                <td>${com.ibp.admin.utils.IbpHubUtils.convert_DD_MM_YYYY_to_DD_MMM_YYYY(data?.dateCreatedText)}</td>
                <td>
                    <g:if test="${VendorUploadStatus?.parseByte(data?.status)?.label?.equals(VendorUploadStatus.PENDING.label)}">
                        <g:remoteLink
                                action="process"
                                id="${data?.id}"
                                before="beforeProcess()"
                                onLoading="showSpinner('${data?.id}')"
                                onSuccess="callSuccess()"
                                onFailure="failedToProcess()"
                        ><i class="fa fa-gear"></i> <span id="vendor-upload-${data?.id}">Process</span></g:remoteLink> |
                    </g:if>
                </td>
                <td>
                    <div class="input-group-btn" >
                        <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            <i class="fa fa-eye"></i>
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu pull-right" style="min-width: 100px !important;">
                            <li>
                                <g:link action="uploadView" id="${data?.id}"><i class="fa fa-eye"></i> View</g:link>
                            </li>
                            <g:if test="${VendorUploadStatus?.parseByte(data?.status)?.label?.equals(VendorUploadStatus.PROCESSED.label)}">
                                <li>
                                    <export:formats formats="['csv']" class="fa fa-download"
                                                    action="exportFailedCSV"
                                                    params="['id': data?.id]" />
%{--                                    <g:link action="exportFailedCSV" id="${data?.id}"><i class="fa fa-download"></i> Export Failed Count</g:link>--}%
                                </li>
                            </g:if>
                </td>
            </tr>
        </g:each>
        <g:if test="${!vendorUploadList}">
            <tr>
                <td colspan="10" class="text-center"><strong>No vendor uploads found</strong></td>
            </tr>
        </g:if>
        </tbody>
    </table>
</div>
<div class="page_data">
    <div>
        <g:if test="${vendorUploadCount > 0}">
            Showing ${params?.offset ? Integer.parseInt(params?.offset) + 1 : 1} to ${params?.offset ? vendorUploadCount > (params?.max + Integer.parseInt(params?.offset)) ? params?.max + Integer.parseInt(params?.offset) : vendorUploadCount : vendorUploadCount < params?.max ? vendorUploadCount : params?.max} of ${vendorUploadCount} entries
        </g:if>
    </div>
    <div>
        <bootstrap:remotePaginate
                class="pull-right"
                total="${vendorUploadCount ?: 0}"
                update="vendor-upload-list-div"
                onLoading="showLoader('vendor-upload-list-div')"
                params="${params}"
        />
    </div>
</div>



<script type="text/javascript">
    const nodeList = document.querySelectorAll(".fa-download .menuButton .csv");
    for (let i = 0; i < nodeList.length; i++) {
        nodeList[i].innerHTML = "Export CSV";
    }
    function beforeProcess() {
      showSuccessNotification('Processing csv file started')
    }
    function failedToProcess() {
      showErrorNotification('Failed to process vendor csv file')
    }
    function showSpinner(id) {
        $("#vendor-upload-"+id).text('Processing...');
    }

    // function callSuccess() {
    //     showSuccessNotification('File processed successfully');
    //     window.location.reload();
    // }
    function callSuccess() {
        showSuccessNotification('File processed successfully');
        setTimeout(function() {
            $('#successNotification').fadeOut(1000, function() {
                window.location.reload();
            });
        }, 25000);
    }

</script>
%{--<asset:script>
  $('#vendorUploadListTable').dataTable({
    "responsive": true
  });
</asset:script>--}%
