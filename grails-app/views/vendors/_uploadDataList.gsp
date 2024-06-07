<%@ page import="com.ibp.admin.VendorUploadData; com.ibp.admin.utils.VendorUploadDataUtils;" %>
<div class="table-responsive table_datas">
    <table class="table table-striped" id="vendorUploadDataListTable">
        <thead class="thead_updatas">
        <tr>
            <th>#</th>
            <g:each in="${VendorUploadDataUtils.FIELD_COLUMN}" var="col">
                <th nowrap="nowrap">${col?.header}</th>
            </g:each>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${vendorUploadDataList}" var="data" status="idx">
            <tr>
                <td>${(params?.offset?.toInteger() ?: 0) + (idx+1)}</td>
                <g:each in="${VendorUploadDataUtils.FIELD_COLUMN}" var="col">
                    <td ${col?.wrap?.toBoolean() ? '' : 'nowrap="nowrap"'}>${raw(VendorUploadDataUtils.renderFieldValue(col, data as VendorUploadData))}</td>
                </g:each>
                <td>
                    <g:if test="${data?.status == 0}">
                        <g:link action="editVendorUploadData" id="${data?.id}" alt="Correct"><i class="fa fa-check-square-o" title="Correct"></i></g:link>
                    </g:if>
                </td>
            </tr>
        </g:each>
        <g:if test="${!vendorUploadDataList}">
            <tr>
                <td colspan="${VendorUploadDataUtils.FIELD_COLUMN.size()}" class="text-center"><strong>No vendor data found</strong></td>
            </tr>
        </g:if>
        </tbody>
    </table>
</div>
<div class="page_data">
    <div>
        <g:if test="${vendorUploadDataCount > 0}">
            Showing ${params?.offset ? Integer.parseInt(params?.offset) + 1 : 1} to ${params?.offset ? vendorUploadDataCount > (params?.max + Integer.parseInt(params?.offset)) ? params?.max + Integer.parseInt(params?.offset) : vendorUploadDataCount : vendorUploadDataCount < params?.max ? vendorUploadDataCount : params?.max} of ${vendorUploadDataCount} entries
        </g:if>
    </div>
    <div>
        <bootstrap:remotePaginate
                class="pull-right"
                total="${vendorUploadDataCount ?: 0}"
                update="vendor-upload-data-list-div"
                onLoading="showLoader('vendor-upload-list-div')"
                params="${params}"
        />
    </div>

</div>


%{--<asset:script>
  $('#vendorUploadDataListTable').dataTable({
    "responsive": true
  });
</asset:script>--}%
