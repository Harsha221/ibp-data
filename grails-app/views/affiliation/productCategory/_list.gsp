<%Boolean access = false %>
<sec:access url="/affiliation/editProductCategory/**"> <% access = true %> </sec:access>
<sec:access url="/affiliation/approveProductCategory/**"> <% access = true %> </sec:access>
<div class="table-responsive table_data">
    <table class="table table-striped" id="categoryListTable">
        <thead class="thead_data">
        <tr>
            <th>Name</th>
            <th>Affiliation</th>
            <th>Status</th>
            <th>Website Link</th>
            <th>Image Path</th>
            <th>Created On</th>
            <th>Created By</th>
            <th>Updated By</th>
            <g:if test="${access}"><th>Actions</th></g:if>
        </tr>
        </thead>
        <tbody>
        <g:each in="${affiliationList}" var="data">
            <tr>
                <td>${data?.name}</td>
                <td>${data?.affiliation?.name}</td>
                <td>${data?.active ? 'Activated' : 'Deactivated'}</td>
                <td style="width: 50px !important;">${data?.websiteLink}</td>
                <td><g:if test="${data?.imagePath}" ><img height="50px" width="100px" src="${data?.imagePath}"/></g:if></td>
                <td>${com.ibp.admin.utils.IbpHubUtils.convert_DD_MM_YYYY_to_DD_MMM_YYYY(data?.dateCreatedText)}</td>
                <td>${data?.createdBy}</td>
                <td>${data?.updatedBy}</td>
                 <g:if test="${access}">
                <td>
                    <div class="input-group-btn" >
                        <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            <i class="fa fa-edit"></i>
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu pull-right" style="min-width: 100px !important;">
                            <li>
                                <sec:link action="editProductCategory"  id="${data?.id}"  class="status-link">Edit</sec:link>
                            </li>
                            <sec:access url="/affiliation/approveProductCategory/**">
                                <li> <a data-job-id="${data?.id}" class="status-link" data-toggle="modal" data-target="#form_modal" style="cursor: pointer">
                                ${data?.active ? 'Deactivated' : 'Activated'}</a>
                            </li>
                        </sec:access>
                        </ul>
                    </div>
                </td>
            </g:if>
            </tr>
        </g:each>
        <g:if test="${!affiliationList}">
            <tr>
                <td colspan="7" class="text-center"><strong>No Affiliation found</strong></td>
            </tr>
        </g:if>
        </tbody>
    </table>
</div>
<div class="page_data">
    <div>
        <g:if test="${affiliationCount > 0}">
            Showing ${params?.offset ? Integer.parseInt(params?.offset) + 1 : 1} to ${params?.offset ? affiliationCount > (params?.max + Integer.parseInt(params?.offset)) ? params?.max + Integer.parseInt(params?.offset) : affiliationCount : affiliationCount < params?.max ? affiliationCount : params?.max} of ${affiliationCount} entries
        </g:if>
    </div>
    <div>
        <bootstrap:remotePaginate
                class="pull-right"
                total="${affiliationCount ?: 0}"
                update="category-list-div"
                onLoading="showLoader('category-list-div')"
                params="${params}"
        />
    </div>
</div>


<div id="form_modal" class="modal fade animated" role="dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>

            </div>
            <form action="toggleProductStatus">
                <div class="modal-body">
                    <div class="row m-t-10">
                        <div class="col-md-12">
                            <input type="hidden" name="toggleId" id="toggleId">
                            <div class="form-group-search">
                                Are you sure you want to Deactivate SubCategory ?
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
            $(".form-group-search").html('Are you sure you want to Deactivate Affiliation Product Category ?')
        } else {
            $(".form-group-search").html('Are you sure you want to Activate Affiliation Product Category ?')
        }
    })
</script>