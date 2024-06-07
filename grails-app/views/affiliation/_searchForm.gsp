<form name="categorySearchForm">
    <div class="row">
        <div class="col-sm-2">
            <div class="form-group">
                <input type="text" name="name" id="name" class="form-control input-md" placeholder="Affiliation Name"
                value="${params?.name}">
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">

                <g:select
                        name="searchAffiliationStatus"
                        class="form-control"
                        from="['Activated', 'Deactivated']"
                        id="searchAffiliationStatus"
                        noSelection="['': ' -- Select Status -- ']"/>
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
        $('#searchAffiliationStatus').select2()
    });

</script>