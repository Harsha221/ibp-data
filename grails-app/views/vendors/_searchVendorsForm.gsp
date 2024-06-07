<%@ page import="com.ibp.admin.enums.VendorBusinessStatus" %>
<form name="vendorsSearchForm">
    <div class="row">
        <div class="col-md-3">
            <div class="form-group">
                <input type="text" name="name" id="name" class="form-control input-md" placeholder="Contact Name, Email, Mobile, Business Name" value="${params?.name}">
            </div>
        </div>
        <div class="col-md-2">
            <div class="form-group">
                <input type="text" name="businessType" id="businessType" class="form-control input-md" placeholder="Business Type" value="${params?.businessType}">
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <input type="text" name="category" id="category" class="form-control input-md" placeholder="Category" value="${params?.category}">
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <g:select
                    id="searchVendorStatus"
                    name="vendorStatus"
                    class="form-control"
                    from="${VendorBusinessStatus.values()}"
                    noSelection="['': ' -- Select Status -- ']"/>
            </div>
        </div>
        <div class="col-sm-1">
            <g:submitToRemote
                class="btn btn-success pull-right"
                value="Search"
                before="showLoader('vendor-list-div')"
                update="vendor-list-div" />
        </div>
    </div>
</form>
<script type="text/javascript">
    $( document ).ready(function() {
        $('#searchVendorStatus').select2()

    });

</script>