<%@ page import="com.ibp.admin.Constants; com.ibp.admin.utils.LocalDateTimeUtils; com.ibp.admin.utils.IbpHubUtils" %>
<% Boolean access = false %>
<sec:access url="/contactUs/edit/**"> <% access = true %> </sec:access>
<sec:access url="/contactUs/approve/**"> <% access = true %> </sec:access>
<div class="table-responsive table_data">
    <table class="table table-striped" id="vendorsListTable">
        <thead class=thead_data>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone No</th>
            <th>Message</th>
            <th>Updated Date</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${contactUsList}" var="data">
            <tr>
                <td>${data?.id}</td>
                <td>${data?.name}</td>
                <td>${data?.email}</td>
                <td>${data?.phoneNumber}</td>
                <td>${data?.message}</td>
                <td>${com.ibp.admin.utils.IbpHubUtils.convert_DD_MM_YYYY_to_DD_MMM_YYYY(com.ibp.admin.utils.LocalDateTimeUtils.formatFromLocalDateTime(data?.lastUpdated,com.ibp.admin.Constants.DateFormat.DD_MM_YYYY_TIME))}</td>
            </tr>
        </g:each>
        <g:if test="${!contactUsList}">
            <tr>
                <td colspan="8" class="text-center"><strong>No Record found</strong></td>
            </tr>
        </g:if>
        </tbody>
    </table>
</div>
<div class="page_data">
    <div >
        <g:if test="${contactUsCount > 0}">
            Showing ${params?.offset ? Integer.parseInt(params?.offset) + 1 : 1} to ${params?.offset ? contactUsCount > (params?.max + Integer.parseInt(params?.offset)) ? params?.max + Integer.parseInt(params?.offset) : contactUsCount : contactUsCount < params?.max ? contactUsCount : params?.max} of ${contactUsCount} entries
        </g:if>

    </div>
    <div>
        <bootstrap:remotePaginate
                class="pull-right"
                total="${contactUsCount ?: 0}"
                update="contactUs-list-div"
                onLoading="showLoader('contactUs-list-div')"
                params="${params}"
        />
    </div>
</div>
<div id="form_modal" class="modal fade animated" role="dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header acdc_btn">
                <h3 class="titletag">Contact Us</h3>
                <button type="button" class="close cls_btn" data-dismiss="modal">×</button>
            </div>
            <form action="toggleStatus">
                <div class="modal-body">
                    <div class="row m-t-10">
                        <div class="col-md-12">
                            <input type="hidden" name="toggleId" id="toggleId">
                            <div class="form-group">
                                Are you sure you want to Deactivate Contact Us ?
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
