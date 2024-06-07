<%@ page import="com.ibp.admin.BlogCategory" %>

<div class="form-group">
    <label class="col-md-2 control-label" for="title">
        Title<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                type="text"
                id="title"
                name="title"
                value="${data?.title}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Blog Title"
                required="true" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label" for="sortDescription">
        Short Description<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:textarea id="sortDescription"
                       name="sortDescription"
                       value="${data?.sortDescription}"
                       bean="${data}"
                       class="form-control"
                       placeholder="Enter Blog Short Description"
                       required="true"/>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label" for="description">
        Description
    </label>
    <div class="col-md-10">
    <form:textarea id="description"
                       name="description"
                       value="${data?.description}"
                       class="form-control"
                       placeholder="Enter Blog Description"
                       required="true"/>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label" for="blogCategory">
        Category<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:select
                id="blogCategory"
                name="blogCategory"
                class="form-control resize_vertical"
                from="${BlogCategory.findAllByStatus(true)}"
                value="${data?.blogCategory?.id}"
                optionValue="name"
                optionKey="id"
                noSelection="['':' -- Select Category-- ']"
                required="true" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label" >
        Thumb
    </label>
    <div class="col-md-8">
        <g:if test="${data?.thumbUrl}">
            <div class="fileinput fileinput-exists" data-provides="fileinput">
                <div class="fileinput-new thumbnail" style="height: 150px; line-height: 150px; width: 400px;"></div>
                <div class="fileinput-preview fileinput-exists thumbnail" style="height: 150px; line-height: 150px; width: 400px;">
                    <img src="${data?.thumbUrl}"/>
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
    <label class="col-md-2 control-label" for="tags">
        Tags
    </label>
    <div class="col-md-8">
        <input type="text" name="tags" id="tags" required="true"
               value="${data?.tags}" data-role="tagsinput"/>
    </div>
</div>
<div class="form-group form-actions">
    <div class="col-md-8 col-md-offset-4">
        <button type="submit" class="btn btn-effect-ripple btn-primary" >${actionName in ['create', 'save'] ? 'Create' : 'Update'}</button>
        <g:link action="index" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>
<input type="hidden" name="active" value="${data?.active}" />
<asset:javascript src="tinymce/tinymce.min.js" />
<script type="text/javascript">
    $( document ).ready(function() {
        $('#blogCategory').select2()

    tinymce.init({
        selector: "#description",
        plugins: 'preview importcss searchreplace autolink autosave save directionality code visualblocks visualchars fullscreen image link media template codesample table charmap pagebreak nonbreaking anchor insertdatetime advlist lists wordcount help charmap  emoticons',
        toolbar: 'undo redo | bold italic underline strikethrough | fontfamily fontsize blocks | alignleft aligncenter alignright alignjustify | outdent indent |  numlist bullist | forecolor backcolor removeformat | pagebreak | charmap emoticons | fullscreen  preview save print | insertfile image media template link anchor codesample | ltr rtl',
        toolbar_sticky: true,
        autosave_ask_before_unload: false,
        autosave_interval: '30s',
        autosave_prefix: '{path}{query}-{id}-',
        autosave_restore_when_empty: false,
        autosave_retention: '2m',
        image_advtab: true,
        height: 400,
        image_caption: true,
        toolbar_mode: 'sliding',
        contextmenu: 'link image table',
        content_style: 'body { font-family:Arial; font-size:10pt }'
    });
    });
</script>


