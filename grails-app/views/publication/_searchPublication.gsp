
<div class="panel ">

    <div class="panel-body" >
        <form name="categorySearchForm">

            <div class="row">

                <div class="col-sm-2">
                    <div class="form-group">
                        <input type="text" name="title" id="title" class="form-control input-md" placeholder="Title"
                               value="${params?.title}">
                    </div>
                </div>



                <div class="col-sm-2">
                    <div class="form-group">

                        <g:select
                                name="searchPublicationStatus"
                                class="form-control"
                                from="['Activated', 'Deactivated']"
                                id="searchPublicationStatus"
                                noSelection="['': ' -- Select Status -- ']"/>
                    </div>
                </div>







                <div class="col-sm-1">
                    <g:submitToRemote
                            class="btn btn-success pull-right"
                            value="Search"
                            before="showLoader('publication-list-div')"
                            update="publication-list-div" />
                </div>
            </div>
        </form>
    </div>

</div>

<script>
    $(document).ready(function (){
        $('#searchClientsStatus').select2()
    })
</script>