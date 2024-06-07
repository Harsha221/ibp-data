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
    <label class="col-md-2 control-label" for="youtubeVideoUrl">
        YouTube Video Url<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                type="text"
                id="youtubeVideoUrl"
                name="youtubeVideoUrl"
                value="${data?.youtubeVideoUrl}"
                bean="${data}"
                class="form-control"
                placeholder="Enter YouTube Video Url"
                required="true" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for ="imageFile">
        Thumb[840x635]<span class="text-danger">*</span>
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
                                accept="jpg/jpeg/png"
                                required="${data?.thumbUrl ? false : true}"/>


                    </span>
%{--                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>--}%
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
                                accept="jpg/jpeg/png"
                                required="true"
                        />
                    </span>
                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                </div>
            </div>
        </g:else>
        <small class="help-block" id="imageFileValidation" data-bv-validator="notEmpty" data-bv-for="imageFileValidation" data-bv-result="INVALID" style="display: block;"></small>
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

<div class="form-group form-actions">
    <div class="col-md-8 col-md-offset-4">
        <button type="submit" class="btn btn-effect-ripple btn-primary">${actionName in ['create', 'save'] ? 'Create' : 'Update'}</button>
        <g:link action="index" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>

<script type="text/javascript">

    $(document).ready(function (){
        function calculateFileSize(width,height,bytesPerPixel ){
            const fileSize = width * height * bytesPerPixel;
            return fileSize;
        }
        const width = 840;
        const height = 635;
        const  bytesPerPixel = 3;

        $('#imageFile').change(function() {
            const fileSizeInBytes = calculateFileSize(width,height,bytesPerPixel);
            console.log("expected byte size: "+fileSizeInBytes)

            if(this.files[0]) {
                const fileSize = this.files[0].size;
                console.log("actual Filesize : "+fileSize);
                if (fileSize < fileSizeInBytes) { // Convert 5MB to bytes
                    $('#imageFile').val(null);
                    //   $('#errorMessage').text('File size below the minimum limit.'+fileSizeInBytes);
                    $('#imageFileValidation').text('Please choose an image with a minimum size of [840]x[635] pixels.'+fileSizeInBytes);

                    var validationElement = document.querySelector('[data-bv-for="imageFile"]');

                    document.querySelector('[data-bv-for="imageFile"]').parentElement.parentElement.classList.remove("has-success");
                    document.querySelector('[data-bv-for="imageFile"]').parentElement.parentElement.classList.add("has-error");

                    validationElement.setAttribute('data-bv-result', 'INVALID');
                    validationElement.setAttribute('style', '');

                    // Optionally, you can clear the file input to prevent submitting large files

                } else {
                    $('#imageFileValidation').text('File size is within the limit.');

                }
            }
        });
    });
</script>
















