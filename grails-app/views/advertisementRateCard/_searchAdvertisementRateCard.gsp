<div class="panel ">

    <div class="panel-body" >
        <form name="categorySearchForm">

            <div class="row">

                <div class="col-sm-2">
                    <div class="form-group">
                        <input type="text" name="title" id="title" class="form-control input-md" placeholder="title"
                               value="${params?.title}">
                    </div>
                </div>



                <div class="col-sm-2">
                    <div class="form-group">

                        <g:select
                                name="searchAdvertisementRateCardStatus"
                                class="form-control"
                                from="['Activated', 'Deactivated']"
                                id="searchAdvertisementRateCardStatus"
                                noSelection="['': ' -- Select Status -- ']"/>
                    </div>
                </div>







                <div class="col-sm-1">
                    <g:submitToRemote
                            class="btn btn-success pull-right"
                            value="Search"
                            before="showLoader('advertisementRateCard-list-div')"
                            update="advertisementRateCard-list-div" />
                </div>
            </div>
        </form>
    </div>

</div>