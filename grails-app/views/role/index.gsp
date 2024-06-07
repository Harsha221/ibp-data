<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <g:render template="searchRoles"/>
    </div>
    <div class="col-md-12">
        <sec:link class="btn btn-primary pull-right" controller="role" action="create">+ Add</sec:link>
    </div>
    <div class="col-md-12 m-t-10" id="role-list-div">
        <g:render template="list" model="[roleList: roleList]"/>
    </div>
</div>
</body>
</html>
