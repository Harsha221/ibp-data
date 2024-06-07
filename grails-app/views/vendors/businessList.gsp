<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <div class="row">
        <div class="col-md-12">
            <g:render template="searchBusinessList"/>
        </div>

        <div class="col-md-12">
            <sec:link params="[name: 'businessList']" class="btn btn-primary pull-right" controller="vendors" action="create">+ Add</sec:link>
        </div>

        <div class="col-md-12 m-t-10" id="vendor-list-div">
            <g:render template="businessList" model="[vendorsList: vendorsList, vendorsCount: vendorsCount]"/>
        </div>
    </div>
</body>
</html>
