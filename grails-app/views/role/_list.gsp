<div class="table-responsive table_data">
    <table class="table table-striped" id="roleListTable">
        <thead class="thead_data">
        <tr>
            <th>Role Name</th>
            <th>Created On</th>
            <th>Updated On</th>
            <sec:access url="/role/edit/**"><th>Actions</th></sec:access>
        </tr>
        </thead>
        <tbody>
        <g:each in="${roleList}" var="data">
            <tr>
                <td>${data?.roleName}</td>
                <td>${data?.dateCreatedText}</td>
                <td>${data?.lastUpdatedText}</td>
                <td>
                    <g:if test="${data?.authority != 'ROLE_SUPER_ADMIN'}">
                        <sec:link action="edit" id="${data?.id}"><i class="fa fa-edit"></i> Edit</sec:link>
                    </g:if>
                </td>
            </tr>
        </g:each>
        <g:if test="${!roleList}">
            <tr>
                <td colspan="5" class="text-center"><strong>No roles found</strong></td>
            </tr>
        </g:if>
        </tbody>
    </table>
</div>
    <bootstrap:remotePaginate
        class="pull-right"
        total="${vendorsCount ?: 0}"
        update="vendor-list-div"
        onLoading="showLoader('role-list-div')"
        params="${params}"
    />

%{--<asset:script>
  $('#roleListTable').dataTable({
    "responsive": true
  });
</asset:script>--}%
