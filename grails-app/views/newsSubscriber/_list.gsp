<%@ page import="com.ibp.admin.utils.LocalDateTimeUtils; com.ibp.admin.Constants; com.ibp.admin.utils.IbpHubUtils" %>
<% Boolean access = false %>
<sec:access url="/newsSubscriber/edit/**"> <% access = true %> </sec:access>
<sec:access url="/newsSubscriber/approve/**"> <% access = true %> </sec:access>
<div class="table-responsive table_data">
    <table class="table table-striped" id="vendorsListTable">
        <thead class=thead_data>
        <tr>
            <th>Id</th>
            <th>Email</th>
            <th>Status</th>
            <th>Subscription Date</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${newsSubscriberList}" var="data">
            <tr>
                <td>${data?.id}</td>
                <td>${data?.email}</td>
                <td>${data?.status ? 'Activated' : 'Deactivated'}</td>
                <td>${com.ibp.admin.utils.IbpHubUtils.convert_DD_MM_YYYY_to_DD_MMM_YYYY(com.ibp.admin.utils.LocalDateTimeUtils.formatFromLocalDateTime(data?.subscriptionDate,com.ibp.admin.Constants.DateFormat.DD_MM_YYYY_TIME))}</td>
            </tr>
        </g:each>
        <g:if test="${!newsSubscriberList}">
            <tr>
                <td colspan="8" class="text-center"><strong>No News Subscriber found</strong></td>
            </tr>
        </g:if>
        </tbody>
    </table>
</div>
<div class="page_data">
    <div >
        <g:if test="${newsSubscriberCount > 0}">
            Showing ${params?.offset ? Integer.parseInt(params?.offset) + 1 : 1} to ${params?.offset ? newsSubscriberCount > (params?.max + Integer.parseInt(params?.offset)) ? params?.max + Integer.parseInt(params?.offset) : newsSubscriberCount : newsSubscriberCount < params?.max ? newsSubscriberCount : params?.max} of ${newsSubscriberCount} entries
        </g:if>

    </div>
    <div>
        <bootstrap:remotePaginate
                class="pull-right"
                total="${newsSubscriberCount ?: 0}"
                update="newsSubscriber-list-div"
                onLoading="showLoader('newsSubscriber-list-div')"
                params="${params}"
        />
    </div>
</div>
<div id="form_modal" class="modal fade animated" role="dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header acdc_btn">
                <h3 class="titletag">News Subscriber</h3>
                <button type="button" class="close cls_btn" data-dismiss="modal">×</button>
            </div>
            <form action="toggleStatus">
                <div class="modal-body">
                    <div class="row m-t-10">
                        <div class="col-md-12">
                            <input type="hidden" name="toggleId" id="toggleId">
                            <div class="form-group">
                                Are you sure you want to Deactivate News Subscriber ?
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-succes">Yes</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">No
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('a.status-link').click(function () {
        $('#toggleId').val($(this).data('job-id'))
        if($(this).html().trim() === 'Deactivated') {
            $(".form-group").html('Are you sure you want to Deactivate NewsSubscriber ?')
        } else {
            $(".form-group").html('Are you sure you want to Activate NewsSubscriber ?')
        }
    })
</script>