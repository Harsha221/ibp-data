<g:if test="${data?.roleName}">
    <div class="form-group">
        <label class="col-md-6 control-label">
            Role Name : <b>${data?.roleName}</b>
        </label>
    </div>
</g:if>
<g:else>
    <div class="form-group">
        <label class="col-md-4 control-label" for="roleName">
            Role Name<span class="text-danger">*</span>
        </label>
        <div class="col-md-6">
            <form:input
                    type="text"
                    id="roleName"
                    name="roleName"
                    value="${data?.roleName}"
                    bean="${data}"
                    class="form-control"
                    placeholder="Enter Role Name"
                    required="true" />
        </div>
    </div>
</g:else>
<div class="table-responsive">
    <table class="table table-striped" id="vendorsListTable">
        <thead>
        <tr>
            <th class="text-center">Module</th>
            <th class="text-center">Permissions</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${modulePermissions?.groupBy { it.moduleName }}" var="module">
            <tr>
                <td nowrap="nowrap"><strong>${module?.key}</strong></td>
                <td nowrap="nowrap">
                    <g:each in="${module?.value}" var="permission">
                        <g:checkBox name="modulePermissions"
                                    class="bs-switch"
                                    value="${permission?.id}"
                                    data-size="mini"
                                    checked="${(data?.permissions as List)?.contains(permission)}"
                        />
                            &nbsp;<strong>${permission?.permissionName}</strong> &nbsp;&nbsp;
                    </g:each>
                </td>
            </tr>
        </g:each>
        <g:if test="${!modulePermissions}">
            <tr>
                <td colspan="2" class="text-center"><strong>No permissions found</strong></td>
            </tr>
        </g:if>
        </tbody>
    </table>
</div>

<div class="form-group form-actions">
    <div class="col-md-12 col-md-offset-4">
        <button type="submit" class="btn btn-effect-ripple btn-primary">${actionName in ['create', 'save'] ? 'Create' : 'Update'}</button>
        <g:link action="index" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>
