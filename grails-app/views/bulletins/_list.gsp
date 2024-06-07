<%@ page import="com.ibp.admin.Association" %>
<% Boolean access = false %>
<sec:access url="/bulletins/edit/**"> <% access = true %> </sec:access>
<sec:access url="/bulletins/approve/**"> <% access = true %> </sec:access>
<div class="table-responsive">
    <table class="table table-striped" id="vendorsListTable">
        <thead>
        <tr>
            <th>Title</th>
            <th>Status</th>
            <th>Association</th>
            <th>YouTube Url</th>
            <th>Created On</th>

            <g:if test="${access}"><th>Actions</th></g:if>
        </tr>
        </thead>
        <tbody>
        <g:each in="${bulletinsList}" var="data">
            <tr>
                <td>${data?.title}</td>
                <td>${data?.status ? 'Activated' : 'Deactivated'}</td>
                <td>${Association.findById(data?.association?.id)?.name}</td>
                <td>${data?.youtubeVideoUrl}</td>
                <td>${com.ibp.admin.utils.IbpHubUtils.convert_DD_MM_YYYY_to_DD_MMM_YYYY(data?.dateCreatedText)}</td>
                <g:if test="${access}">
                    <td>
                        <div class="input-group-btn" >
                            <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <i class="fa fa-edit"></i>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right" style="min-width: 100px !important;">
                                <li>
                                    <sec:link action="edit"  id="${data?.id}" data-job-id="${data?.id}" class="status-link">Edit</sec:link>
                                </li>
                                <sec:access url="/bulletins/approve/**">
                                    <li> <a data-job-id="${data?.id}" class="status-link" data-toggle="modal" data-target="#form_modal" style="cursor: pointer">
                                        ${data?.status ? 'Deactivated' : 'Activated'}</a>
                                    </li>
                                </sec:access>
                            </ul>
                        </div>
                    </td>
                </g:if>
            </tr>
        </g:each>
        <g:if test="${!bulletinsList}">
            <tr>
                <td colspan="8" class="text-center"><strong>No Bulletins found</strong></td>
            </tr>
        </g:if>
        </tbody>
    </table>
    <g:if test="${bulletinsCount > 0}">
        Showing ${params?.offset ? Integer.parseInt(params?.offset) + 1 : 1} to ${params?.offset ? bulletinsCount > (params?.max + Integer.parseInt(params?.offset)) ? params?.max + Integer.parseInt(params?.offset) : bulletinsCount : bulletinsCount < params?.max ? bulletinsCount : params?.max} of ${bulletinsCount} entries
    </g:if>
    <bootstrap:remotePaginate
            class="pull-right"
            total="${bulletinsCount ?: 0}"
            update="bulletins-list-div"
            onLoading="showLoader('bulletins-list-div')"
            params="${params}"
    />
</div>
<div id="form_modal" class="modal fade animated" role="dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
            </div>
            <form action="toggleStatus">
                <div class="modal-body">
                    <div class="row m-t-10">
                        <div class="col-md-12">
                            <input type="hidden" name="toggleId" id="toggleId">
                            <div class="form-group-search">
                                Are you sure you want to Deactivate Bulletins ?
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
            $(".form-group-search").html('Are you sure you want to Deactivate Bulletins ?')
        } else {
            $(".form-group-search").html('Are you sure you want to Activate Bulletins ?')
        }
    })
</script>