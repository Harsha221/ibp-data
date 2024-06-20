<div class="table-responsive table_data">
    <table class="table table-striped" id="vendorsListTable">
        <thead class="thead_data">
        <tr>
            <th>Business Name</th>
            <th>Business Email</th>
            <th>Mobile No</th>
            <th>Business Type</th>
            <th>Category</th>
            <th>City</th>
            <th>State</th>
            <th>Paid</th>
            <th>Status</th>
            <th>Updated At</th>
            <th>Created By</th>
            <th>Updated By</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${vendorsList}" var="data">
            <tr>
                <td>${data?.businessName}</td>
                <td>${data?.businessEmail}</td>
                <td>${data?.primaryPhoneNumber}</td>
                <td>${data?.businessType}</td>
                <td>${data?.category?.name}</td>
                <td>${data?.city}</td>
                <td>${data?.state}</td>
                <td>${com.ibp.admin.enums.PaymentStatus?.parsePaymentStatus(data?.paid)?.name}</td>
                <td>${com.ibp.admin.enums.VendorBusinessStatus?.parseVendorBusinessStatus(data?.status)?.name}</td>
                <td>${com.ibp.admin.utils.IbpHubUtils.convert_DD_MM_YYYY_to_DD_MMM_YYYY(data?.lastUpdatedText)}</td>
                <td>${com.ibp.admin.Vendors.findById(data?.vendorId)?.createdBy}</td>
                <td>${com.ibp.admin.Vendors.findById(data?.vendorId)?.updatedBy}</td>
                <td>Not Exported</td>
%{--                <td class="text-center">--}%
%{--                    <a data-job-id="${data?.id}" class="status-link" data-toggle="modal" data-target="#business_activation_form" style="cursor: pointer">--}%
%{--                        <i class="fa fa-check"></i></a>--}%
%{--                </td>--}%
            </tr>
        </g:each>
        <g:if test="${!vendorsList}">
            <tr>
                <td colspan="8" class="text-center"><strong>No business found</strong></td>
            </tr>
        </g:if>
        </tbody>
    </table>
</div>
<div class="page_data">
    <div>
        <g:if test="${vendorsCount > 0}">
            Showing ${params?.offset ? Integer.parseInt(params?.offset) + 1 : 1} to ${params?.offset ? vendorsCount > (params?.max + Integer.parseInt(params?.offset)) ? params?.max + Integer.parseInt(params?.offset) : vendorsCount : vendorsCount < params?.max ? vendorsCount : params?.max} of ${vendorsCount} entries
        </g:if>
    </div>
    <div>
        <bootstrap:remotePaginate
                class="pull-right"
                total="${vendorsCount ?: 0}"
                update="vendor-list-div"
                onLoading="showLoader('vendor-list-div')"
                params="${params}"
        />
    </div>
</div>



<div id="business_activation_form" class="modal fade animated" role="dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header d-flex">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h4>Business Verification</h4>
            </div>
            <form action="toggleStatus">
                <div class="modal-body">
                    <div class="row m-t-10">
                        <div class="col-md-12">
                            <input type="hidden" name="toggleId" id="toggleId">
                            <div class="change-status-div">
                                Are you sure you want to <span class="bold">Verify</span> this Business ?
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-succes">Yes</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('a.status-link').click(function () {
        $('#toggleId').val($(this).data('job-id'));
    })
</script>
