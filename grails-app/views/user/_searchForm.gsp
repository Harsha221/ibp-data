<%@ page import="com.ibp.admin.Role" %>
<form name="roleSearchForm">
    <div class="row">
        <div class="col-sm-2">
            <div class="form-group">
                <input type="text" name="name" id="name" class="form-control input-md" placeholder="User Name">
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">
                <input type="text" name="email" id="email" class="form-control input-md" placeholder="Email">
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">
                <g:select
                        id="role"
                        name="role"
                        class="form-control"
                        from="${Role.findAll()}"
                        optionValue="roleName"
                        optionKey="id"
                        noSelection="['': ' -- Select Role -- ']"/>
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">

                <g:select
                        name="searchUserStatus"
                        class="form-control"
                        from="['Activated', 'Deactivated']"
                        id="searchUserStatus"
                        noSelection="['': ' -- Select Status -- ']"/>
            </div>
        </div>
        <div class="col-sm-1">
            <g:submitToRemote
                class="btn btn-success pull-right"
                value="Search"
                before="showLoader('user-list-div')"
                update="user-list-div" />
        </div>
    </div>
</form>
<script type="text/javascript">
    $( document ).ready(function() {
        $('#searchUserStatus').select2()
        $('#role').select2()
    });

</script>