<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <g:render template="searchAboutCommittees"/>
    </div>
    <div class="col-md-12">
        <g:link class="btn btn-primary pull-right" action="create">+ Add</g:link>
    </div>
    <div class="col-md-12 m-t-10" id="category-list-div">
        <g:render template="list" model="[aboutCommitteesList: aboutCommitteesList]"/>
    </div>
</div>
</body>
</html>
