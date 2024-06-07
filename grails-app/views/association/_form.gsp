<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Association Name<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="text"
                id="name"
                name="name"
                value="${data?.name}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Association Name"
                required="true" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="headerFile">
        Header
    </label>
    <div class="col-md-6">
    <g:if test="${data?.headerUrl}">
        <div class="fileinput fileinput-exists" data-provides="fileinput">
            <div class="fileinput-new thumbnail" style="height: 150px; line-height: 150px; width: 400px;"></div>
            <div class="fileinput-preview fileinput-exists thumbnail" style="height: 150px; line-height: 150px; width: 400px;">
                <img src="${data?.headerUrl}"/>
            </div>
            <div>
                <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                    <form:input
                            type="file"
                            id="headerFile"
                            name="headerFile"
                            bean="${data}"
                            class="form-control"
                            placeholder="Choose file"
                            accept="jpg/jpeg/png"/>
                </span>
                <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
            </div>
        </div>
    </g:if>
    <g:else>
        <div class="fileinput fileinput-new" data-provides="fileinput">
            <div class="fileinput-preview  thumbnail" data-trigger="fileinput" style="height: 150px; line-height: 150px; width: 400px;"></div>
            <div>
                <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                    <form:input
                            type="file"
                            id="headerFile"
                            name="headerFile"
                            bean="${data}"
                            class="form-control"
                            placeholder="Choose file"
                            accept="jpg/jpeg/png"/>
                </span>
                <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
            </div>
        </div>
    </g:else>
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="redirectLink">
        Redirect Link<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="text"
                id="redirectLink"
                name="redirectLink"
                value="${data?.redirectLink}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Association's Redirect Link"
                required="true" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="logoFile">
        Logo
    </label>
    <div class="col-md-6">
        <g:if test="${data?.logoUrl}">
            <div class="fileinput fileinput-exists" data-provides="fileinput">
                <div class="fileinput-new thumbnail" style="height: 150px; line-height: 150px; width: 400px;"></div>
                <div class="fileinput-preview fileinput-exists thumbnail" style="height: 150px; line-height: 150px; width: 400px;">
                    <img src="${data?.logoUrl}"/>
                </div>
                <div>
                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                        <form:input
                                type="file"
                                id="logoFile"
                                name="logoFile"
                                bean="${data}"
                                class="form-control"
                                placeholder="Choose file"
                                accept="jpg/jpeg/png"/>
                    </span>
                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                </div>
            </div>
        </g:if>
        <g:else>
            <div class="fileinput fileinput-new" data-provides="fileinput">
                <div class="fileinput-preview  thumbnail" data-trigger="fileinput" style="height: 150px; line-height: 150px; width: 400px;"></div>
                <div>
                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                        <form:input
                                type="file"
                                id="logoFile"
                                name="logoFile"
                                bean="${data}"
                                class="form-control"
                                placeholder="Choose file"
                                accept="jpg/jpeg/png"/>
                    </span>
                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                </div>
            </div>
        </g:else>
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="bannerFile">
        Website Banner
    </label>
    <div class="col-md-6">
        <g:if test="${data?.bannerUrl}">
            <div class="fileinput fileinput-exists" data-provides="fileinput">
                <div class="fileinput-new thumbnail" style="height: 150px; line-height: 150px; width: 400px;"></div>
                <div class="fileinput-preview fileinput-exists thumbnail" style="height: 150px; line-height: 150px; width: 400px;">
                    <img src="${data?.bannerUrl}"/>
                </div>
                <div>
                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                        <form:input
                                type="file"
                                id="bannerFile"
                                name="bannerFile"
                                bean="${data}"
                                class="form-control"
                                placeholder="Choose file"
                                accept="jpg/jpeg/png"/>
                    </span>
                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                </div>
            </div>
        </g:if>
        <g:else>
            <div class="fileinput fileinput-new" data-provides="fileinput">
                <div class="fileinput-preview  thumbnail" data-trigger="fileinput" style="height: 150px; line-height: 150px; width: 400px;"></div>
                <div>
                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                        <form:input
                                type="file"
                                id="bannerFile"
                                name="bannerFile"
                                bean="${data}"
                                class="form-control"
                                placeholder="Choose file"
                                accept="jpg/jpeg/png"/>
                    </span>
                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                </div>
            </div>
        </g:else>
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="mobileBannerFile">
        Mobile Banner
    </label>
    <div class="col-md-6">
        <g:if test="${data?.mobileBannerUrl}">
            <div class="fileinput fileinput-exists" data-provides="fileinput">
                <div class="fileinput-new thumbnail" style="height: 150px; line-height: 150px; width: 400px;"></div>
                <div class="fileinput-preview fileinput-exists thumbnail" style="height: 150px; line-height: 150px; width: 400px;">
                    <img src="${data?.mobileBannerUrl}"/>
                </div>
                <div>
                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                        <form:input
                                type="file"
                                id="mobileBannerFile"
                                name="mobileBannerFile"
                                bean="${data}"
                                class="form-control"
                                placeholder="Choose file"
                                accept="jpg/jpeg/png"/>
                    </span>
                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                </div>
            </div>
        </g:if>
        <g:else>
            <div class="fileinput fileinput-new" data-provides="fileinput">
                <div class="fileinput-preview  thumbnail" data-trigger="fileinput" style="height: 150px; line-height: 150px; width: 400px;"></div>
                <div>
                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                        <form:input
                                type="file"
                                id="mobileBannerFile"
                                name="mobileBannerFile"
                                bean="${data}"
                                class="form-control"
                                placeholder="Choose file"
                                accept="jpg/jpeg/png"/>
                    </span>
                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                </div>
            </div>
        </g:else>
    </div>
</div>
<div class="form-group form-actions">
    <div class="col-md-8 col-md-offset-4">
        <button type="submit" class="btn btn-effect-ripple btn-primary">${actionName in ['create', 'save'] ? 'Create' : 'Update'}</button>
        <g:link action="index" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>
<input type="hidden" name="active" value="${data?.active}" />


