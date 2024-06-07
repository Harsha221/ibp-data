<%@ page import="com.ibp.admin.SubCategory; com.ibp.admin.Category" %>
<form name="categorySearchForm">
    <div class="row">
        <div class="col-sm-2">
            <div class="form-group">
                <input type="text" name="name" id="name" class="form-control input-md" placeholder="Product Name">
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">

                <g:select
                        name="searchProductStatus"
                        class="form-control"
                        from="['Activated', 'Deactivated']"
                        id="searchProductStatus"
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
        <div class="col-md-3">
            <div class="form-group">
                <g:select
                        id="subCategoryId"
                        name="subCategoryId"
                        class="form-control"
                        from="${SubCategory.findAllByActive(true)}"
                        optionValue="name"
                        optionKey="id"
                        noSelection="['': ' -- Select SubCategory -- ']"/>
            </div>
        </div>
        <div class="col-sm-1">
            <g:submitToRemote
                class="btn btn-success pull-right"
                value="Search"
                before="showLoader('product-list-div')"
                update="product-list-div" />
        </div>
    </div>
</form>

<script>
    $(document).ready(function (){
        $('#searchProductStatus').select2()
        $('#categoryId').select2()
        $('#subCategoryId').select2()
    })
</script>