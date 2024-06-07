<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Name<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="text"
                id="name"
                name="name"
                value="${data?.name}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Config Name"
                required="true" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="value">
        Value<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                name="value"
                id="value"
                required="true"
                class="form-control"
                placeholder="Enter Config value"
                value="${data?.value}"/>
    </div>
</div>
<div class="form-group form-actions">
    <div class="col-md-8 col-md-offset-4">
        <button type="submit" class="btn btn-effect-ripple btn-primary">${actionName in ['create', 'save'] ? 'Create' : 'Update'}</button>
        <g:link action="index" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>


