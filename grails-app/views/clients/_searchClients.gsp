
<div class="panel ">

    <div class="panel-body" >
        <form name="categorySearchForm">

            <div class="row">

                <div class="col-sm-2">
                    <div class="form-group">
                        <input type="text" name="name" id="name" class="form-control input-md" placeholder="Name"
                               value="${params?.name}">
                    </div>
                </div>



                <div class="col-sm-2">
                    <div class="form-group">

                        <g:select
                                name="searchClientsStatus"
                                class="form-control"
                                from="['Activated', 'Deactivated']"
                                id="searchClientsStatus"
                                noSelection="['': ' -- Select Status -- ']"/>
                    </div>
                </div>







                <div class="col-sm-1">
                    <g:submitToRemote
                            class="btn btn-success pull-right"
                            value="Search"
                            before="showLoader('clients-list-div')"
                            update="clients-list-div" />
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