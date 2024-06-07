<%@ page import="com.ibp.admin.Affiliation" %>
<form name="categorySearchForm">
    <div class="row">
        <div class="col-sm-2">
            <div class="form-group">
                <input type="hidden" name="affiliationProduct" value="affiliationProduct" />
                <input type="text" name="name" id="name" class="form-control input-md" placeholder="Affiliation Name"
                value="${params?.name}">
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <form:select
                        id="affiliation"
                        name="affiliation"
                        class="form-control"
                        from="${Affiliation.findAllByActive(true)}"
                        optionValue="name"
                        optionKey="id"
                        noSelection="['':' -- Select Affiliation -- ']"
                        />
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">

                <g:select
                        name="searchAffiliationProductCategoryStatus"
                        class="form-control"
                        from="['Activated', 'Deactivated']"
                        id="searchAffiliationProductCategoryStatus"
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

<script>
    $(document).ready(function (){
        $('#affiliation').select2()
        $('#searchAffiliationProductCategoryStatus').select2()
    })
</script>