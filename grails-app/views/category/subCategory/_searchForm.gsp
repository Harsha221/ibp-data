<%@ page import="com.ibp.admin.Category" %>
<form name="categorySearchForm">
    <div class="row">
        <div class="col-sm-2">
            <input type="hidden" name="searchPage" value="subcategory" />
            <div class="form-group">
                <input type="text" name="subCategoryName" id="subCategoryName" class="form-control input-md" placeholder="Sub Category Name" />
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">

                <g:select
                        name="searchSUbCategoryStatus"
                        class="form-control"
                        from="['Activated', 'Deactivated']"
                        id="searchSubCategoryStatus"
                        noSelection="['': ' -- Select Status -- ']"/>
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <g:select
                        id="categoryId"
                        name="categoryId"
                        class="form-control"
                        from="${Category.findAllByActive(true)}"
                        optionValue="name"
                        optionKey="id"
                        noSelection="['': ' -- Select Category -- ']"/>
            </div>
        </div>
        <div class="col-sm-1">
            <g:submitToRemote
                class="btn btn-success pull-right"
                value="Search"
                before="showLoader('sub-category-list-div')"
                update="sub-category-list-div" />
        </div>
    </div>
</form>
<script type="text/javascript">
    $( document ).ready(function() {
        $('#searchSubCategoryStatus').select2()
        $('#categoryId').select2()

    });

</script>