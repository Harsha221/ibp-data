<div class="panel ">
    <div class="panel-body">
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
                        <input type="text" name="emailAddress" id="emailAddress" class="form-control input-md" placeholder="Email"
                               value="${params?.emailAddress}">
                    </div>
                </div>

                <div class="col-sm-2">
                    <div class="form-group">
                        <input type="text" name="contactNumber" id="contactNumber" class="form-control input-md"
                               placeholder="Phone Number"
                               value="${params?.contactNumber}">
                    </div>
                </div>



                <div class="col-sm-1">
                    <g:submitToRemote
                            class="btn btn-success pull-right"
                            value="Search"
                            before="showLoader('bookAdvertisement-list-div')"
                            update="bookAdvertisement-list-div"/>
                </div>
            </div>
        </form>
    </div>

</div>