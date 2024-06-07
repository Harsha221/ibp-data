<form name="categorySearchForm">
    <div class="row">
        <div class="col-sm-2">
            <div class="form-group">
                <input type="hidden" name="searchPage" value="category" />
                <input type="text" name="name" id="name" class="form-control input-md" placeholder="Category Name"
                value="${params?.name}">
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">

                <g:select
                        name="searchCategoryStatus"
                        class="form-control"
                        from="['Activated', 'Deactivated']"
                        id="searchCategoryStatus"
                        noSelection="['': ' -- Select Status -- ']"/>
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">

                <g:select
                        name="searchSequenceStatus"
                        class="form-control"
                        from="${(1..20)}"
                        id="searchSequenceStatus"
                        noSelection="['': ' -- Select Sequence -- ']"/>
            </div>
        </div>
        <div class="col-sm-1">
            <g:submitToRemote
                class="btn btn-success pull-right"
                value="Search"
                before="showLoader('category-list-div')"
                update="category-list-div" />
        </div>
    </div>
</form>

<script type="text/javascript">
    $( document ).ready(function() {
        $('#searchCategoryStatus').select2()
    });

</script>
