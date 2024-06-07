<%@ page import="com.ibp.admin.Association" %>

<form name="categorySearchForm">
    <div class="row">
        <div class="col-sm-2">
            <div class="form-group">
                <input type="text" name="name" id="name" class="form-control input-md" placeholder="Advertisement Name"
                value="${params?.name}">
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">

                <g:select
                        name="mediaSelection"
                        class="form-control"
                        from="['Image', 'Video']"
                        id="mediaSelection"
                        noSelection="['': ' -- Select Ads Type -- ']"/>
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">

                <g:select
                        name="searchVendorStatus"
                        class="form-control"
                        from="['Activated', 'Deactivated']"
                        id="searchVendorStatus"
                        noSelection="['': ' -- Select Status -- ']"/>
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <g:select
                        id="associationId"
                        name="associationId"
                        class="form-control"
                        from="${Association.findAllByActive(true)}"
                        optionValue="name"
                        optionKey="id"
                        noSelection="['': ' -- Select Association -- ']"/>
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

<script>
    $(document).ready(function (){
        $('#mediaSelection').select2()
        $('#searchVendorStatus').select2()
        $('#associationId').select2()
    })
</script>