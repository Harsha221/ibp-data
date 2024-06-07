<%@ page import="com.ibp.admin.Association" %>
<%Boolean access = false %>
<sec:access url="/advertisement/edit/**"> <% access = true %> </sec:access>
<sec:access url="/advertisement/approve/**"> <% access = true %> </sec:access>
<div class="table-responsive table_data">
    <table class="table table-striped" id="categoryListTable">
        <thead class="thead_data">
        <tr>
            <th>Name</th>
            <th>Status</th>
            <th>Association</th>
            <th>Ads Type</th>
            <th>Created On</th>
            <th>Created By</th>
            <th>Updated By</th>
%{--            <th>Updated On</th>--}%
            <g:if test="${access}"><th>Actions</th></g:if>
        </tr>
        </thead>
        <tbody>
        <g:each in="${advertisementList}" var="data">
            <%
               /* System.out.println("association: " +data?.association);
                System.out.println("association name: " +Association.findById(data?.association?.id)?.name);
                String[] aidArray = data?.associationId?.split(",");
                String associationName = "";

                for (String associationId : aidArray) {
                    associationName += com.ibp.admin.Association.findById(Long.valueOf(associationId)).name + ", ";
                }

                if (associationName.endsWith(", ")) {
                    associationName = associationName.substring(0, associationName.length() - 2);
                }
*/
            %>
            <tr>
                <td>${data?.title}</td>
                <td>${data?.status ? 'Activated' : 'Deactivated'}</td>
                <td>${Association.findById(data?.association?.id)?.name}</td>
                <td>${data?.adsType}</td>
                %{--                <td>${data?.advertiseType}</td>--}%
                <td>${com.ibp.admin.utils.IbpHubUtils.convert_DD_MM_YYYY_to_DD_MMM_YYYY(data?.dateCreatedText)}</td>
%{--                <td>${com.ibp.admin.utils.IbpHubUtils.convert_DD_MM_YYYY_to_DD_MMM_YYYY(data?.lastUpdatedText)}</td>--}%
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
                                    <sec:link action="edit"  id="${data?.id}" data-job-id="${data?.id}" class="status-link">Edit</sec:link>
                                </li>
                                <sec:access url="/advertisement/approve/**">
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
        <g:if test="${!advertisementList}">
            <tr>
                <td colspan="7" class="text-center"><strong>No Advertisement found</strong></td>
            </tr>
        </g:if>
        </tbody>
    </table>
</div>
<div class="page_data">
    <div><g:if test="${advertisementCount > 0}">
        Showing ${params?.offset ? Integer.parseInt(params?.offset) + 1 : 1} to ${params?.offset ? advertisementCount > (params?.max + Integer.parseInt(params?.offset)) ? params?.max + Integer.parseInt(params?.offset) : advertisementCount : advertisementCount < params?.max ? advertisementCount : params?.max} of ${advertisementCount} entries
    </g:if></div>
    <div>
        <bootstrap:remotePaginate
                class="pull-right"
                total="${advertisementCount ?: 0}"
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
            <form action="toggleStatus">
                <div class="modal-body">
                    <div class="row m-t-10">
                        <div class="col-md-12">
                            <input type="hidden" name="toggleId" id="toggleId">
                            <div class="form-group-search">
                                Are you sure you want to Deactivate Category ?
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
            $(".form-group-search").html('Are you sure you want to Deactivate Advertisement ?')
        } else {
            $(".form-group-search").html('Are you sure you want to Activate Advertisement ?')
        }
    })
</script>