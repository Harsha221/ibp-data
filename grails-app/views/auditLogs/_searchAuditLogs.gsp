<div class="panel ">
    <div class="panel-body" >
        <form name="categorySearchForm">

            <div class="row">

                <div class="col-sm-2">
                    <div class="form-group">
                        <input type="text" name="entityType" id="entityType" class="form-control input-md" placeholder="Entity Type"
                               value="${params?.entityType}">
                    </div>
                </div>

                <div class="col-sm-1">
                    <g:submitToRemote
                            class="btn btn-success pull-right"
                            value="Search"
                            before="showLoader('auditLogs-list-div')"
                            update="auditLogs-list-div" />
                </div>
            </div>
        </form>
    </div>

</div>










%{--<div class="panel panel-default">--}%
%{--    <div class="panel-heading">--}%
%{--        <h3 class="panel-title">Search Audit Log</h3>--}%
%{--    </div>--}%
%{--    <div class="panel-body">--}%
%{--        <form action="${createLink(controller: 'auditLogs', action: 'index')}">--}%
%{--            <div class="form-group">--}%
%{--                <label for="user">User:</label>--}%
%{--                <input type="text" class="form-control" id="user" name="user" value="${params.user}">--}%
%{--            </div>--}%
%{--            <div class="form-group">--}%
%{--                <label for="entityType">Entity Type:</label>--}%
%{--                <input type="text" class="form-control" id="entityType" name="entityType" value="${params.entityType}">--}%
%{--            </div>--}%
%{--            <button type="submit" class="btn btn-primary">Search</button>--}%
%{--        </form>--}%
%{--    </div>--}%
%{--</div>--}%

