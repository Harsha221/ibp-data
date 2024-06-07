<%@ page import="com.ibp.admin.Category" %>
<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        SubCategory Name<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
            type="text"
            id="name"
            name="name"
            value="${data?.name}"
            bean="${data}"
            class="form-control"
            placeholder="Enter SubCategory Name"
            required="true" />
    </div>
</div>


<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Category<span class="text-danger">*</span>
    </label>
    <div>
        <input type="hidden" test="${params?.categoryId}">

    </div>
    <div class="col-md-6">
        <g:if test="${params?.categoryId}">
            <input type="hidden" name="testdata" value="${params?.categoryId}" />
            <form:select
                    id="categoryId"
                    name="categoryId"
                    class="form-control"
                    from="${Category.findAllByActive(true)}"
                    value="${params?.categoryId}"
                    optionValue="name"
                    optionKey="id" required="true"
                    noSelection="['':' -- Select Category -- ']" />
        </g:if>
        <g:else>
            <form:select
                    id="categoryId"
                    name="categoryId"
                    class="form-control"
                    from="${Category.findAllByActive(true)}"
                    value="${data?.category?.id}"
                    optionValue="name"
                    optionKey="id" required="true"
                    noSelection="['':' -- Select Category -- ']" />
        </g:else>

    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="active">
        Description
    </label>
    <div class="col-md-6">
        <form:textarea
                name="description"
                id="description"
                class="form-control"
                value="${data?.description}"/>
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="active">
        Banner
    </label>
    <div class="col-md-8">
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
                                id="imageFile"
                                name="imageFile"
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
                                id="imageFile"
                                name="imageFile"
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
            <button type="submit" class="btn btn-effect-ripple btn-primary">${actionName in ['createSubCategory', 'saveSubCategory'] ? 'Create' : 'Update'}</button>
        <g:link action="subcategory" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>
<input type="hidden" name="active" value="${data?.active}" />

<script>

    $( document ).ready(function() {
        $('#categoryId').select2()
    });
</script>