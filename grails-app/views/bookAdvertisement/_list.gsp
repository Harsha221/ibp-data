<%@ page import="com.ibp.admin.Constants; com.ibp.admin.utils.LocalDateTimeUtils; com.ibp.admin.utils.IbpHubUtils" %>
<% Boolean access = false %>
<sec:access url="/bookAdvertisement/edit/**"> <% access = true %> </sec:access>
<sec:access url="/bookAdvertisement/approve/**"> <% access = true %> </sec:access>
<div class="table-responsive table_data">
    <table class="table table-striped" id="vendorsListTable">
        <thead class=thead_data>
        <tr>
            <th>Id</th>
            <th>Full Name</th>
            <th>Contact No</th>
            <th>Email</th>
            <th>Title</th>
            <th>Amount</th>
            <th>Payment Type</th>
            <th>Payment Status</th>
            <th>order Id</th>
            <th>expiration Date</th>
            <th>Payment Date</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${bookAdvertisementList}" var="data">
            <tr>
                <td>${data?.id}</td>
                <td>${data?.firstName} ${data?.lastName}</td>
                <td>${data?.contactNumber}</td>
                <td>${data?.emailAddress}</td>
                <td>${data?.title}</td>
                <td>${data?.amount}</td>
                <td>${data?.paymentType}</td>
                <td>${data?.paymentStatus}</td>
                <td>${data?.orderId}</td>
                <td>${com.ibp.admin.utils.IbpHubUtils.convert_DD_MM_YYYY_to_DD_MMM_YYYY(com.ibp.admin.utils.LocalDateTimeUtils.formatFromLocalDateTime(data?.expirationDate,com.ibp.admin.Constants.DateFormat.DD_MM_YYYY_TIME))}</td>
                <td>${com.ibp.admin.utils.IbpHubUtils.convert_DD_MM_YYYY_to_DD_MMM_YYYY(com.ibp.admin.utils.LocalDateTimeUtils.formatFromLocalDateTime(data?.paymentDate,com.ibp.admin.Constants.DateFormat.DD_MM_YYYY_TIME))}</td>
            </tr>
        </g:each>
        <g:if test="${!bookAdvertisementList}">
            <tr>
                <td colspan="8" class="text-center"><strong>No Record found</strong></td>
            </tr>
        </g:if>
        </tbody>
    </table>
</div>
<div class="page_data">
    <div >
        <g:if test="${bookAdvertisementCount > 0}">
            Showing ${params?.offset ? Integer.parseInt(params?.offset) + 1 : 1} to ${params?.offset ? bookAdvertisementCount > (params?.max + Integer.parseInt(params?.offset)) ? params?.max + Integer.parseInt(params?.offset) : bookAdvertisementCount : bookAdvertisementCount < params?.max ? bookAdvertisementCount : params?.max} of ${bookAdvertisementCount} entries
        </g:if>

    </div>
    <div>
        <bootstrap:remotePaginate
                class="pull-right"
                total="${bookAdvertisementCount ?: 0}"
                update="bookAdvertisement-list-div"
                onLoading="showLoader('bookAdvertisement-list-div')"
                params="${params}"
        />
    </div>
</div>
