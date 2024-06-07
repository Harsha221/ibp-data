<form name="categorySearchForm">
    <div class="row">
        <div class="col-sm-2">
            <div class="form-group">
                <input type="text" name="name" id="name" class="form-control input-md" placeholder="Config Name"
                value="${params?.name}">
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">

                <g:select
                        name="searchConfigStatus"
                        class="form-control"
                        from="['Active', 'In-Active']"
                        id="searchConfigStatus"
                        noSelection="['': ' -- Select Status -- ']"/>
            </div>
        </div>
        <div class="col-sm-1">
            <g:submitToRemote
                class="btn btn-success pull-right"
                value="Search"
                before="showLoader('config-list-div')"
                update="config-list-div" />
        </div>
    </div>
</form>
<script type="text/javascript">
    $( document ).ready(function() {
        $('#searchConfigStatus').select2()
    });

</script>