<%@ page import="com.ibp.admin.Advertisement; com.ibp.admin.Association" %>
<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Advertisement Name<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="text"
                id="name"
                name="name"
                value="${data?.title}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Advertisement Name"
                required="true" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Ad For<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:select
                id="associationId"
                name="associationId"
                class="form-control"
                from="${Association.findAllByActive(true)}"
                value="${data?.associationId}"
                optionValue="name"
                optionKey="id"
                required="true"
                noSelection="['':' -- Select Association -- ']"

        />
    </div>
</div>

<div class="form-group">
    <label class="col-md-4 control-label" for="mediaSelection">
        Ads Type<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:select
                id="mediaSelection"
                name="mediaSelection"
                class="form-control"
                from="['Image', 'Video']"
                value="${data?.adsType}"
                noSelection="['':' -- Select Type -- ']"
                required="true"
        />

    </div>
</div>

<div id="adsP" class="form-group">
    <label class="col-md-4 control-label" for="adsPosition">
        Ads Position<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:select
                id="adsPosition"
                name="adsPosition"
                class="form-control"
                from="['Left','Right','Bottom','Top']"
                value="${data?.adsPosition}"
                noSelection="['':' -- Select Type -- ']"
                required="true"
        />

    </div>
</div>

<div id="showImage" class="form-group">
    <label class="col-md-4 control-label" for="imageFile">
        Image<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <g:if test="${data?.url}">
           <div class="fileinput fileinput-exists" data-provides="fileinput">
             <div class="fileinput-new thumbnail" style="height: 150px; line-height: 150px; width: 400px;"></div>
             <div class="fileinput-preview fileinput-exists thumbnail" style="height: 150px; line-height: 150px; width: 400px;">
               <img src="${data?.url}"/>
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

                        accept="image/*"
                        required="${data?.url ? false : true}"
                     />
            </span>
%{--            <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>--}%
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
                                accept="image/*"
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

<div id="showVideo" class="form-group">

    <label class="col-md-4 control-label" for="mySelections">
        Video
    </label>
    <div class="col-md-6">
        <form:select
                id="mySelections"
                name="mySelections"
                class="form-control"
                from="['Youtube', 'Custom']"
                value="${data?.videoType}"
                noSelection="['':' -- Select Type -- ']"
                required="true"/>

    </div>
</div>

<div id="showYoutube" class="form-group">

    <label class="col-md-4 control-label" for="URL">
        Youtube URL
    </label>

    <div class="col-md-6">
        <form:input
                type="text"
                id="URL"
                name="youtubeUrl"
                value="${data?.youtubeUrl}"
                bean="${data}"
                class="form-control"
                placeholder="Enter url"
                required="true" />
    </div>
</div>
<div id="showCustom" class="form-group">

    <label class="col-md-4 control-label" for="videoFile">
        Video File
    </label>
    <div class="col-md-8">
        <g:if test="${data?.videoUrl}">
            <div class="fileinput fileinput-exists" data-provides="fileinput">
                <div class="fileinput-new thumbnail" style="height: 150px; line-height: 150px; width: 400px;"></div>
                <div class="fileinput-preview fileinput-exists thumbnail" style="height: 250px; line-height: 250px; width: 500px;">
                    <video width="470" height="255" controls>
                        <source src="${data?.videoUrl}" type="video/wmv">
                        <source src="${data?.videoUrl}" type="video/mp4">
                        <source src="${data?.videoUrl}" type="video/ogg">
                        <source src="${data?.videoUrl}" type="video/webm">
                    </video>
                </div>
                <div>
                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select video</span><span class="fileinput-exists">Change</span>
                    <form:input
                            type="file"
                            id="videoFile"
                            name="videoFile"
                            bean="${data}"
                            class="form-control"
                            placeholder="Choose file"
                            accept="video/*"
                            value="${data?.videoUrl}"
                            required="true"/>
                    </span>
                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                </div>
            </div>
        </g:if>
        <g:else>
            <div class="fileinput fileinput-new" data-provides="fileinput">
                <div class="fileinput-preview  thumbnail" data-trigger="fileinput" style="height: 150px; line-height: 150px; width: 400px;"></div>
                <div>
                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select video</span><span class="fileinput-exists">Change</span>
                    <form:input
                        type="file"
                        id="videoFile"
                        name="videoFile"
                        bean="${data}"
                        class="form-control"
                        placeholder="Choose file"
                        accept="video/*"
                        value=""
                        required="true"/>
                    </span>
                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                </div>
            </div>
        </g:else>
    </div>
</div>

<div class="form-group">
    <label class="col-md-4 control-label" for="tags">
        Hash Tag<span class="text-danger"></span>
    </label>
    <div class="col-md-6">
        <input type="text" name="tags" id="tags" value="${data?.tags}" data-role="tagsinput"/>
    </div>
</div>


<div class="form-group">
    <label class="col-md-4 control-label" for="active">
        Active<span class="text-danger"></span>
    </label>
    <div class="col-md-6">
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
    $(document).ready(function() {

            $('#associationId').select2()
            $('#mediaSelection').select2()
            $('#mySelections').select2()
            $('#adsPosition').select2()




        function toggleAdsPosition() {
            var associationId = $("#associationId").find("option:selected").text();
            var adsType = $("#mediaSelection").val();

            if (associationId === 'GCCI' && adsType === 'Image') {
                $("#adsP").show(); // Show the Ads Position div
            } else {
                $("#adsP").hide(); // Hide the Ads Position div
            }
        }

        function calculateFileSize(width, height, bytesPerPixel) {
            const fileSize = width * height * bytesPerPixel;
            return fileSize;
        }

                $('#imageFile').on('change', function() {
                    var adsPos = $("#adsPosition").val();
                    var associationId = $("#associationId").find("option:selected").text();
                    var adsType = $("#mediaSelection").val();
                    console.log("Ads Position : "+adsPos)

                    if (associationId === 'GCCI' && adsType === 'Image' && (adsPos === 'Right' || adsPos ==='Left')) {

                        const width = 780;
                        const height = 624;
                        const bytesPerPixel = 3;

                        const fileSizeInBytes = calculateFileSize(width, height, bytesPerPixel);
                        console.log("expected byte size left right: " + fileSizeInBytes)

                        if (this.files[0]) {
                            const fileSize = this.files[0].size;
                            console.log("actual Filesize left right: " + fileSize);

                            if (fileSize < fileSizeInBytes) { // Convert 5MB to bytes
                                // alert(2)
                                $('#imageFile').val(null);
                                $('#imageFileValidation').text('Please choose an image with a minimum size of [780]x[624] pixels.' + fileSizeInBytes);

                                var validationElement = document.querySelector('[data-bv-for="imageFile"]');
                                document.querySelector('[data-bv-for="imageFile"]').parentElement.parentElement.classList.remove("has-success");
                                document.querySelector('[data-bv-for="imageFile"]').parentElement.parentElement.classList.add("has-error");
                                validationElement.setAttribute('data-bv-result', 'INVALID');
                                validationElement.setAttribute('style', '');
                            } else {
                                $('#imageFileValidation').text('File size is within the limit.');
                            }
                        }
                    } else if(associationId === 'GCCI' && adsType === 'Image' && (adsPos === 'Top' || adsPos ==='Bottom')){
                        console.log("position changed to top and bottom....")
                        const width = 1340;
                        const height = 170;
                        const bytesPerPixel = 3;

                        const fileSizeInBytes = calculateFileSize(width, height, bytesPerPixel);
                        console.log("expected byte size top bottom: " + fileSizeInBytes)

                        if (this.files[0]) {
                            const fileSize = this.files[0].size;
                            console.log("actual Filesize top bottom : " + fileSize);

                            if (fileSize < fileSizeInBytes) { // Convert 5MB to bytes
                                // alert(2)
                                $('#imageFile').val(null);
                                $('#imageFileValidation').text('Please choose an image with a minimum size of [1340]x[170] pixels.' + fileSizeInBytes);

                                var validationElement = document.querySelector('[data-bv-for="imageFile"]');
                                document.querySelector('[data-bv-for="imageFile"]').parentElement.parentElement.classList.remove("has-success");
                                document.querySelector('[data-bv-for="imageFile"]').parentElement.parentElement.classList.add("has-error");
                                validationElement.setAttribute('data-bv-result', 'INVALID');
                                validationElement.setAttribute('style', '');
                            } else {
                                $('#imageFileValidation').text('File size is within the limit.');
                            }
                        }
                    }
                });




        toggleAdsPosition();


        // Listen for changes in association and ads type
        $('#associationId, #mediaSelection, #adsPosition').on('change', function () {
            toggleAdsPosition(); // Call the function whenever there is a change

        });




    $("div#showImage").hide();
    $("div#showVideo").hide();

    $("div#showYoutube").hide();
    $("div#showCustom").hide();

    var adsType = $("#mediaSelection").val()
    if(adsType === 'Image') {
        $("div#showVideo").hide();
        $("div#showYoutube").hide();
        $("div#showCustom").hide();
        $("#show"+adsType).show();
    } else {
        $("div#showImage").hide();
        $("#show"+adsType).show();
        var videoType = $("#mySelections").val()
        if(videoType === 'Custom') {
            $("div#showYoutube").hide();
            $("#show"+videoType).show();
        } else {
            $("div#showCustom").hide();
            $("#show"+videoType).show();
        }
    }
    $('#mediaSelection').on('change', function(){
        var demovalue = $(this).val();
        if(demovalue === '') {
            $("div#showVideo").hide();
            $("div#showImage").hide();
            $("div#showYoutube").hide();
            $("div#showCustom").hide();
        }
        else if(demovalue === 'Image') {
            $("div#showVideo").hide();
            $("div#showYoutube").hide();
            $("div#showCustom").hide();
            $("#show"+demovalue).show();

        }
        else if (demovalue === 'Video') {
            $("div#showImage").hide();
            $("#show"+demovalue).show();
            $("#mySelections").val("");
        }
        else {
            $("#show"+demovalue).show();
        }
    });


      $('#mySelections').on('change', function(){
        var demovalue = $(this).val();
        if(demovalue === 'Select Type') {
            $("div#showVideo").hide();
            $("div#showImage").hide();
            $("div#showYoutube").hide();
            $("div#showCustom").hide();
        }
        else {
            $("#show"+demovalue).show();
        }
        if(demovalue === 'Custom') {
            $("div#showYoutube").hide();
        } else {
            $("div#showCustom").hide();
        }
        $("#show"+demovalue).show();

     })
    });


</script>





