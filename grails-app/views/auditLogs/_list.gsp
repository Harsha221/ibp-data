<% Boolean access = false %>
<sec:access url="/auditLogs/edit/**"> <% access = true %> </sec:access>
<sec:access url="/auditLogs/approve/**"> <% access = true %> </sec:access>
<div class="table-responsive table_data">
    <table class="table table-striped" id="vendorsListTable">
        <thead class=thead_data>
        <tr>
            <th>Action</th>
            <th>Entity Type</th>
            <th>Entity ID</th>
            <th>User</th>
            <th>Old Value</th>
            <th>New Value</th>
            <th>Timestamp</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${auditLogsList}" var="data">
            <tr>
                <td>${data?.action}</td>
                <td>${data?.entityType}</td>
                <td>${data?.entityId}</td>
                <td>${data?.user}</td>
                <td>${data?.oldValue}</td>
                <td>${data?.newValue}</td>
                <td>${data?.timestamp}</td>
            </tr>
        </g:each>
        <g:if test="${!auditLogsList}">
            <tr>
                <td colspan="8" class="text-center"><strong>No Audit Logs found</strong></td>
            </tr>
        </g:if>
        </tbody>
    </table>
</div>
<div class="page_data">
    <div >
        <g:if test="${auditLogsCount > 0}">
            Showing ${params?.offset ? Integer.parseInt(params?.offset) + 1 : 1} to ${params?.offset ? auditLogsCount > (params?.max + Integer.parseInt(params?.offset)) ? params?.max + Integer.parseInt(params?.offset) : auditLogsCount : auditLogsCount < params?.max ? auditLogsCount : params?.max} of ${auditLogsCount} entries
        </g:if>

    </div>
    <div>
        <bootstrap:remotePaginate
                class="pull-right"
                total="${auditLogsCount ?: 0}"
                update="auditLogs-list-div"
                onLoading="showLoader('auditLogs-list-div')"
                params="${params}"
        />
    </div>
</div>

