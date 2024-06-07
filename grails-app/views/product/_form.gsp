<%@ page import="com.ibp.admin.SubCategory; com.ibp.admin.Category" %>
<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Product Name<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
            type="text"
            id="name"
            name="name"
            value="${data?.name}"
            bean="${data}"
            class="form-control"
            placeholder="Enter Product Name"
            required="true" />
        <input type="hidden" name="oldName" value="${data?.name}" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Category<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:select
                id="categoryId"
                name="categoryId"
                class="form-control"
                from="${Category.findAllByActive(true)}"
                value="${data?.category?.id}"
                optionValue="name"
                optionKey="id" required="true"
                noSelection="['':' -- Select Category -- ']" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        SubCategory<span class="text-danger">*</span>
    </label>
    <div class="col-md-6" id="subCategoryDiv">
        <g:render template="searchTemplates/subCategory" model="[data: data]"/>
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="active">
        Description<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:textarea
                name="description"
                id="description"
                class="form-control" required="true"
                value="${data?.description}"/>
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" >
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
<div class="form-group">
    <label class="col-md-4 control-label" for="metaTitle">
        Page Title
    </label>
    <div class="col-md-6">
        <form:input
                type="text"
                id="metaTitle"
                name="metaTitle"
                value="${data?.metaTitle}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Meta Tile"
        />
    </div>
</div>

<div class="form-group">
    <label class="col-md-4 control-label" for="metaDescription">
        Page Description
    </label>
    <div class="col-md-6">
        <form:textarea
                name="metaDescription"
                id="metaDescription"
                class="form-control"
                placeholder="Enter Meta Description"
                value="${data?.metaDescription}"/>
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="metaKeyword">
        Page Keywords
    </label>
    <div class="col-md-6">
        <form:textarea
                name="metaKeyword"
                id="metaKeyword"
                class="form-control"
                placeholder="Enter Meta Keywords"
                value="${data?.metaKeyword}"/>
    </div>
</div>
<div class="form-group form-actions">
    <div class="col-md-8 col-md-offset-4">
        <button type="submit" class="btn btn-effect-ripple btn-primary">${actionName in ['create', 'save'] ? 'Create' : 'Update'}</button>
        <g:link action="index" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>
<input type="hidden" name="active" value="${data?.active}" />
<script type="text/javascript">
    $( document ).ready(function() {
        $('#categoryId').select2()

        $('#categoryId').on('change', function() {

            getSubcategory(escape(this.value),'')

        });
        function getSubcategory(category , subCategory){
            ${remoteFunction(controller: 'product', action: 'onSelectCategory',
                            params: '\'category=\' + category + \'&subCategory=\' + subCategory',
                            update : 'subCategoryDiv' , contentType: 'text/plain')}
        }
        <g:if test="${data?.category?.name}">
            let categoryName = '${data?.category?.id}'
            let subCategoryName = '${data?.subCategory?.id}'
            getSubcategory(escape(categoryName),escape(subCategoryName))
        </g:if>
    });
</script>