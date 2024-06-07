<%@ page import="com.ibp.admin.Association" %>

<form name="categorySearchForm">
    <div class="row">
        <div class="col-sm-2">
            <div class="form-group">
                <input type="text" name="name" id="name" class="form-control input-md" placeholder="About Committee Name"
                       value="${params?.name}">
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">

                <g:select
                        name="searchAboutCommitteeStatus"
                        class="form-control"
                        from="['Activated', 'Deactivated']"
                        id="searchAboutCommitteeStatus"
                        noSelection="['': ' -- Select Status -- ']"/>
            </div>
        </div>


        <div class="col-sm-2">
            <div class="form-group">
                <g:select
                        id="type"
                        name="type"
                        class="form-control"
                        from="${['1':'Office Bearers', '2':'Executive Committee', '3':'Past Presidents']}"
                        value="${data?.type}"
                        optionValue="value"
                        optionKey="key"
                        required="true"
                        noSelection="['':' -- Select Type -- ']"

                />
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


        <div class="col-sm-12">
            <g:submitToRemote
                    class="btn btn-success pull-right"
                    value="Search"
                    before="showLoader('category-list-div')"
                    update="category-list-div" />
        </div>
    </div>
</form>
