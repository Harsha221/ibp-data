<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <g:render template="subCategory/searchCategory" model="[type: 'subCategory']"/>
    </div>
    <div class="col-md-12">
        <%
            System.out.println("cat Id: " +params?.categoryId)
        %>
        <sec:access  url="/category/createSubCategory">
            <div>
                <g:if test="${params?.categoryId}">
                    <sec:link params='[categoryId: "${params?.categoryId}"]' class="btn btn-primary pull-right" action="createSubCategory">+ Add</sec:link>
                </g:if>
                <g:else>
                    <sec:link class="btn btn-primary pull-right" action="createSubCategory">+ Add</sec:link>
                </g:else>
                    </div>
        </sec:access>
        <sec:access  url="/category/uploadSubCategory">
            <div>
                <sec:link class="btn btn-primary pull-right mr-3" action="createSubCategoryUpload"><i class="fa fa-upload"></i> Upload</sec:link>
            </div>
        </sec:access>
    </div>
    <div class="col-md-12 m-t-10" id="sub-category-list-div">
        <g:render template="subCategory/subCategorylist" model="[categoryList: categoryList]"/>
    </div>
</div>
</body>
</html>
