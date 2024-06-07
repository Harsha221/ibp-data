<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <g:render template="searchCategory"/>
    </div>
    <div  class="col-md-12 ">
        <div>
            <sec:link  class="btn btn-primary pull-right" action="create">+ Add</sec:link>
        </div>
        <div >
            <sec:link class="btn btn-primary pull-right mr-3" action="createUpload"><i class="fa fa-upload"></i> Upload</sec:link>
        </div>
    </div>
    <div class="col-md-12 m-t-10" id="category-list-div">
        <g:render template="list" model="[categoryList: categoryList]"/>
    </div>
</div>
</body>
</html>
