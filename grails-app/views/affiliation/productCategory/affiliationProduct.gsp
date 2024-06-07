<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <g:render template="productCategory/searchAffiliationProduct"/>
    </div>
    <div class="col-md-12">
        <sec:access  url="/affiliation/createProductCategory">
            <div>
            <sec:link class="btn btn-primary pull-right" action="createProductCategory">+ Add</sec:link>
            </div>
        </sec:access>
        <sec:access  url="/affiliation/uploadsProductCategory">
            <div >
                <sec:link class="btn btn-primary pull-right mr-3" action="creatAffiliationProductUpload"><i class="fa fa-upload"></i> Upload</sec:link>
            </div>
        </sec:access>
    </div>
    <div class="col-md-12 m-t-10" id="category-list-div">
        <g:render template="productCategory/list" model="[affiliationList: affiliationList]"/>
    </div>
</div>
</body>
</html>
