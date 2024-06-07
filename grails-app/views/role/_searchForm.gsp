<form name="roleSearchForm">
    <div class="row">
        <div class="col-sm-2">
            <div class="form-group">
                <input type="text" name="roleName" id="roleName" class="form-control input-md" placeholder="Role Name">
            </div>
        </div>
        <div class="col-sm-1">
            <div class="form-group">
            <g:submitToRemote
                class="btn btn-success pull-right"
                value="Search"
                before="showLoader('role-list-div')"
                update="role-list-div" />
            </div>
        </div>
    </div>
</form>
