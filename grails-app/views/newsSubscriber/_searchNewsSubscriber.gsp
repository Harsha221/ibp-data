<div class="panel ">
    <div class="panel-body" >
        <form name="categorySearchForm">

            <div class="row">
                <div class="col-sm-2">
                    <div class="form-group">
                        <input type="text" name="email" id="email" class="form-control input-md" placeholder="Email"
                               value="${params?.email}">
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">

                        <g:select
                                name="searchNewsSubscriberStatus"
                                class="form-control"
                                from="['Activated', 'Deactivated']"
                                id="searchNewsSubscriberStatus"
                                noSelection="['': ' -- Select Status -- ']"/>
                    </div>
                </div>
                <div class="col-sm-1">
                    <g:submitToRemote
                            class="btn btn-success pull-right"
                            value="Search"
                            before="showLoader('newsSubscriber-list-div')"
                            update="newsSubscriber-list-div" />
                </div>
            </div>
        </form>
    </div>

</div>

<script>
    $(document).ready(function (){
        $('#associationId').select2()
        $('#searchNewsSubscriberStatus').select2()

    })
</script>