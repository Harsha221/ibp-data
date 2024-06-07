<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-md-10">
        <g:render template="/common/global/errorMessage" bean="${this.advertisementRateCard}" />
        <div class="panel">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-fw ti-star"></i>Advertisement Rate Card</h3>
            </div>
            <div class="panel-body">
                <form:validateUploadForm name="create-advertisementRateCard" action="save" method="POST" class="form-horizontal bv-form" novalidate="novalidate">
                    <g:render template= "form" model="[data: this.advertisementRateCard]"/>
                </form:validateUploadForm>
            </div>
        </div>
    </div>
</div>
</body>
</html>