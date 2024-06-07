<%@ page import="com.ibp.admin.Association" %>
<%
    List<com.ibp.admin.Association> associations = com.ibp.admin.Association.findAllByActive(true)
%>
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
                placeholder="Enter Title"
                required="true" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="association">
        Associations<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:select
                id="association"
                name="association"
                value="${data?.associationId}"
                bean="${data}"
                class="form-control resize_vertical"
                from="${Association.findAllByActive(true)}"
                optionValue="name"
                optionKey="id"
                noSelection="['':' -- Select Association -- ']"
                required="true" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="eventStartDate">
        Start Date<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                type="date"
                id="eventStartDate"
                name="eventStartDate"
                value="${data?.eventStartDate}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Start Date"
                required="true" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="eventEndDate">
        End Date<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                type="date"
                id="eventEndDate"
                name="eventEndDate"
                value="${data?.eventEndDate}"
                bean="${data}"
                class="form-control"
                placeholder="Enter End Date"
                required="true" />
    </div>
</div>


<div class="form-group">
    <label class="col-md-2 control-label" for="time">
        Time<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                type="text"
                id="time"
                name="time"
                value="${data?.time}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Time"
                required="true" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="venue">
        Venue<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                type="text"
                id="venue"
                name="venue"
                value="${data?.venue}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Venue"
                required="true" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="associationWith">
        Association with<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                type="text"
                id="associationWith"
                name="associationWith"
                value="${data?.associationWith}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Association With"
                required="true" />
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
                       placeholder="Enter Event Description"
                       required="true"/>
    </div>
</div>


<div class="form-group">
    <label class="col-md-2 control-label" for ="thumbImageFile">
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
                                id="thumbImageFile"
                                name="thumbImageFile"
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
                                id="thumbImageFile"
                                name="thumbImageFile"
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
<%-- --%>
<div class="form-group" >
    <label class="col-md-2 control-label" for ="bannerImageFiles" >
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
                                id="bannerImageFiles"
                                name="bannerImageFiles"
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
                                id="bannerImageFiles"
                                name="bannerImageFiles"
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
        <input type="text" name="tags" id="tags"
               value="${data?.tags}" data-role="tagsinput"/>
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="active">
        Active<span class="text-danger"></span>
    </label>
    <div class="col-md-8">
        <g:checkBox
                name="active"
                class="bs-switch"
                value="${data?.status}"
                data-size="mini"

        />

    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="imageFiles">
        Upload Images
    </label>
    <div class="col-md-5">
        <div class="input-field">
            <div class="input-images-1" style="padding-top: .5rem;"></div>
        </div>
    </div>


</div>




<div class="form-group form-actions">
    <div class="col-md-8 col-md-offset-4">
        <button type="submit" class="btn btn-effect-ripple btn-primary">${actionName in ['create', 'save'] ? 'Create' : 'Update'}</button>
        <g:link action="index" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>


<asset:javascript src="tinymce/tinymce.min.js" />
<script>
    $( document ).ready(function() {

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
<asset:javascript src="image-uploader.min.js"/>
<g:if test="${eventPhotosList?.size() > 0}">
    <script>
        let preloaded = [];
        <g:each in="${eventPhotosList}" var="media">

            preloaded.push({id: '${media?.id}', src: '${media?.imageUrl}'})

        </g:each>
        console.log('preloaded > ', preloaded)
        $('.input-images-1').imageUploader({
            preloaded: preloaded,
            imagesInputName: 'imageFiles',
            preloadedInputName: 'old'
        });
    </script>
</g:if>
<g:else>
    <script>
        $('.input-images-1').imageUploader();
    </script>
</g:else>
