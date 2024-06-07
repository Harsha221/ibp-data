<form name="vendorsSearchForm">
    <div class="row">
        <div class="col-md-3">
            <div class="form-group">
                <input type="text" name="name" id="name" class="form-control input-md" placeholder="Username, Email, Mobile, Business Name" value="${params?.name}">
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <input type="text" name="businessType" id="businessType" class="form-control input-md" placeholder="Business Type" value="${params?.businessType}">
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <input type="text" name="category" id="category" class="form-control input-md" placeholder="Category" value="${params?.category}">
            </div>
        </div>
        <div class="col-sm-1">
            <g:submitToRemote
                url="[controller:'vendors', action:'businessList']"
                class="btn btn-success pull-right"
                value="Search"
                before="showLoader('vendor-list-div')"
                update="vendor-list-div" />
        </div>
    </div>
</form>
