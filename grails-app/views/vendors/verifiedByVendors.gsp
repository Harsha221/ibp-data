<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 18-06-2024
  Time: 16:01
--%>

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
    <sec:link params="[name: 'businessList']" class="btn btn-primary pull-right" controller="vendors" action="create">Export to IBPHub</sec:link>
  </div>

  <div class="col-md-12 m-t-10" id="vendor-list-div">
    <g:render template="verifiedByVendorsList" model="[vendorsList: vendorsList, vendorsCount: vendorsCount]"/>
  </div>
</div>
</body>
</html>
