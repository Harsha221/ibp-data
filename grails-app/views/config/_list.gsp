
<div class="table-responsive table_data">
    <table class="table table-striped" id="categoryListTable">
        <thead class="thead_data">
        <tr>
            <th>Name</th>
            <th>Value</th>
            <th>Status</th>
            <th>Created On</th>
            <th>Updated On</th>
            <th>Created By</th>
            <th>Updated By</th>
            <sec:access url="/config/edit/**"><th>Actions</th></sec:access>
        </tr>
        </thead>
        <tbody>
        <g:each in="${configList}" var="data">
            <tr>
                <td>${data?.name}</td>
                <td style="max-width: 300px">${data?.value}</td>
                <td>${data?.isActive ? 'Active' : 'In-Active'}</td>
                <td>${com.ibp.admin.utils.IbpHubUtils.convert_DD_MM_YYYY_to_DD_MMM_YYYY(data?.dateCreatedText)}</td>
                <td>${com.ibp.admin.utils.IbpHubUtils.convert_DD_MM_YYYY_to_DD_MMM_YYYY(data?.lastUpdatedText)}</td>
                <td>${data?.createdBy}</td>
                <td>${data?.updatedBy}</td>
                <td class="d-flex justify-content-between">
                    <sec:link action="edit" id="${data?.id}"><i class="fa fa-edit"></i></sec:link>
                    <a data-config-id="${data?.id}" data-config-status="${data?.isActive ? 'Active' : 'In-Active'}" class="status-link" data-toggle="modal"
                       data-target="#config_activation_form" style="cursor: pointer; margin-left: 1rem">
                        <i class="fa fa-check"></i></a>
                </td>
            </tr>
        </g:each>
        <g:if test="${!configList}">
            <tr>
                <td colspan="7" class="text-center"><strong>No config found</strong></td>
            </tr>
        </g:if>
        </tbody>
    </table>
</div>
<div class="page_data">
    <div>
        <bootstrap:remotePaginate
                class="pull-right"
                total="${configCount ?: 0}"
                update="config-list-div"
                onLoading="showLoader('config-list-div')"
                params="${params}"
        />
    </div>
</div>


<div id="config_activation_form" class="modal fade animated" role="dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header d-flex">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h4>Config Activate / In-Activate</h4>
            </div>
            <form action="activateDeactivate">
                <div class="modal-body">
                    <div class="row m-t-10">
                        <div class="col-md-12">
                            <input type="hidden" name="toggleId" id="toggleId">
                            <div class="change-status-div">
                                Are you sure you want to <span id="status-span">Active</span> this Config ?
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
        $('#toggleId').val($(this).data('config-id'))
        if($(this).data('config-status').trim() === 'In-Active') {
            $("#status-span").html('Activate').addClass('status-activate-text');
        } else {
            $("#status-span").html('In-Activate').addClass('status-in-activate-text');
        }
    })
</script>
