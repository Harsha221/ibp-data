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
    <label class="col-md-2 control-label" for="amazonLink">
        Amazon Link
    </label>
    <div class="col-md-8">
        <form:input
                type="text"
                id="amazonLink"
                name="amazonLink"
                value="${data?.amazonLink}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Amazon Link"/>
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="flipkartLink">
        Flipkart Link
    </label>
    <div class="col-md-8">
        <form:input
                type="text"
                id="flipkartLink"
                name="flipkartLink"
                value="${data?.flipkartLink}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Flipkart Link"/>
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for ="imageFile">
        Thumb[170x96]<span class="text-danger">*</span>
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
                <div class="fileinput-preview  thumbnail" data-trigger="fileinput" style="height: 150px; line-height: 150px; width: 400px;" data-word="100 x 100"></div>
                <div>
                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                        <form:input
                                type="file"
                                id="imageFile"
                                name="imageFile"
                                bean="${data}"
                                class="form-control"
                                value="${data?.thumbUrl}"
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
    <label class="col-md-2 control-label" for="imageFiles">
        Upload Images
    </label>
    <div class="col-md-5">
        <div class="input-field">
            <div class="input-images-1" style="padding-top: .5rem;"></div>
        </div>
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
        $('#association').select2()
        function calculateFileSize(width,height,bytesPerPixel ){
            const fileSize = width * height * bytesPerPixel;
            return fileSize;
        }
        const width = 700;
        const height = 700;
        const  bytesPerPixel = 0.1;

        $('#imageFile').change(function() {
            const fileSizeInBytes = calculateFileSize(width,height,bytesPerPixel);
            console.log("expected byte size: "+fileSizeInBytes)

            if(this.files[0]) {
                const fileSize = this.files[0].size;
                console.log("actual Filesize : "+fileSize);
                if (fileSize < fileSizeInBytes) { // Convert 5MB to bytes
                    $('#imageFile').val(null);
                    //   $('#errorMessage').text('File size below the minimum limit.'+fileSizeInBytes);
                    $('#imageFileValidation').text('Please choose an image with a minimum size of [700]x[700] pixels.'+fileSizeInBytes);

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
<asset:javascript src="image-uploader.min.js"/>
<g:if test="${publicationPhotosList?.size() > 0}">
    <script>
        let preloaded = [];
        <g:each in="${publicationPhotosList}" var="media">

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
