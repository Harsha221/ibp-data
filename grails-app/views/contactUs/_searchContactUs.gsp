<div class="panel ">
    <div class="panel-body">
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
                        <input type="text" name="phoneNumber" id="phoneNumber" class="form-control input-md"
                               placeholder="Phone Number"
                               value="${params?.phoneNumber}">
                    </div>
                </div>

                <div class="col-sm-2">
                    <div class="form-group">
                        <input type="text" name="name" id="name" class="form-control input-md" placeholder="Name"
                               value="${params?.name}">
                    </div>
                </div>

                <div class="col-sm-1">
                    <g:submitToRemote
                            class="btn btn-success pull-right"
                            value="Search"
                            before="showLoader('contactUs-list-div')"
                            update="contactUs-list-div"/>
                </div>
            </div>
        </form>
    </div>

</div>
