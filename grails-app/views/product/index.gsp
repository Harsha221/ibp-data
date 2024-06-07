<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <g:render template="searchProduct"/>
    </div>
    <div class="col-md-12">
        <sec:link class="btn btn-primary pull-right" action="create">+ Add</sec:link>
    </div>
    <div class="col-md-12 m-t-10" id="product-list-div">
        <g:render template="list" model="[productList: productList]"/>
    </div>
</div>
</body>
</html>
