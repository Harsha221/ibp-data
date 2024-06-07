<%@ page import="com.ibp.admin.Association; com.ibp.admin.NewsCategory" %>
<%
    List<com.ibp.admin.NewsCategory> newsCategories = NewsCategory?.list()
    List<com.ibp.admin.Association> associations = com.ibp.admin.Association.findAllByActive(true)
%>
<div class="form-group">
    <label class="col-md-2 control-label" for="title">
      English Title<span class="text-danger">*</span>
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
    <label class="col-md-2 control-label" for="gujaratiTitle">
      Gujarati Title
    </label>
    <div class="col-md-8">
        <form:input
                type="text"
                id="gujaratiTitle"
                name="gujaratiTitle"
                value="${data?.gujaratiTitle}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Title"
        />
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label" for="sortDescription">
        Short Description<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:textarea
                name="sortDescription"
                id="sortDescription"
                required="true"
                bean="${data}"
                class="form-control"
                placeholder="Enter Short Description"
                value="${data?.sortDescription}"/>
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
                       placeholder="Enter News Description"
                       required="true"/>
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
    <label class="col-md-2 control-label" for="newsCategory">
        Category<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:select
                id="newsCategory"
                name="newsCategory"
                class="form-control resize_vertical"
                from="${NewsCategory.findAllByStatus(true)}"
                value="${data?.newsCategory?.id}"
                optionValue="name"
                optionKey="id"
                noSelection="['':' -- Select Category-- ']"
                required="true" />
    </div>
</div>


<div class="form-group">
    <label class="col-md-2 control-label" for ="imageFile">
        Thumb[100x70]<span class="text-danger">*</span>
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
<%-- --%>
<div class="form-group" >
    <label class="col-md-2 control-label" for ="imageFiles" >
        Image[1460x1000]<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <g:if test="${data?.imageUrl}">
            <div class="fileinput fileinput-exists" data-provides="fileinput">
                <div class="fileinput-new thumbnail" style="height: 150px; line-height: 150px; width: 400px;"></div>
                <div class="fileinput-preview fileinput-exists thumbnail" style="height: 150px; line-height: 150px; width: 400px;">
                    <img src="${data?.imageUrl}"/>
                </div>
                <div>
                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                        <form:input
                                type="file"
                                id="imageFiles"
                                name="imageFiles"
                                bean="${data}"
                                class="form-control"
                                placeholder="Choose file"
                                accept="jpg/jpeg/png"
                                required="${data?.imageUrl ? false : true}"/>


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
                                id="imageFiles"
                                name="imageFiles"
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
        <small class="help-block" id="imageFilesValidation" data-bv-validator="notEmpty" data-bv-for="imageFilesValidation" data-bv-result="INVALID" style="display: block;"></small>
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="isTopNews">
        Top News<span class="text-danger"></span>
    </label>
    <div class="col-md-8">
        <g:checkBox
                name="isTopNews"
                class="bs-switch"
                value="${data?.isTopNews}"
                data-size="mini"

        />
        <input type="hidden" name="active" value="${data?.active}" />

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
<div class="form-group form-actions">
    <div class="col-md-8 col-md-offset-4">
        <button type="submit" class="btn btn-effect-ripple btn-primary">${actionName in ['create', 'save'] ? 'Create' : 'Update'}</button>
        <g:link action="index" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>

<asset:javascript src="tinymce/tinymce.min.js" />
<script type="text/javascript">


    $( document ).ready(function() {
        $('#association').select2()
        $('#newsCategory').select2()


        function calculateFileSize(width, height, bytesPerPixel) {
            const fileSize = width * height * bytesPerPixel;
            return fileSize;
        }

        const width = 100; // Width in pixels
        const height = 77; // Height in pixels
        const bytesPerPixel = 1; // RGB color (3 bytes per pixel)

        $('#imageFile').change(function() {
            const fileSizeInBytes = calculateFileSize(width, height, bytesPerPixel);
            console.log("expected byte size : "+fileSizeInBytes)

            if(this.files[0]) {
                const fileSize = this.files[0].size;
                console.log("actual Filesize : "+fileSize);
                if (fileSize < fileSizeInBytes) {
                    $('#imageFile').val(null);// Convert 5MB to bytes
                 //   $('#errorMessage').text('File size below the minimum limit.'+fileSizeInBytes);
                    $('#imageFileValidation').text('Please choose an image with a minimum size of [100]x[77] pixels.'+fileSizeInBytes);

                    var validationElement = document.querySelector('[data-bv-for="imageFile"]');

                    document.querySelector('[data-bv-for="imageFile"]').parentElement.parentElement.classList.remove("has-success");
                    document.querySelector('[data-bv-for="imageFile"]').parentElement.parentElement.classList.add("has-error");

                    validationElement.setAttribute('data-bv-result', 'INVALID');
                    validationElement.setAttribute('style', '');
                    // Optionally, you can clear the file input to prevent submitting large files
                       this.value = null;
                } else {
                    $('#imageFileValidation').text('File size is within the limit.');

                }
            }
        });


        const w = 1460; // Width in pixels
        const h = 1000; // Height in pixels
        const bP = 0.01; // RGB color (3 bytes per pixel)

        $('#imageFiles').change(function() {
            const fileSizeInBytes = calculateFileSize(w, h, bP);
            console.log("expected byte size2 : "+fileSizeInBytes)

            if(this.files[0]) {
                const fileSize = this.files[0].size;
                console.log("actual Filesize2 : "+fileSize);
                if (fileSize < fileSizeInBytes) { // Convert 5MB to bytes
                    $('#imageFiles').val(null);
                    //   $('#errorMessage').text('File size below the minimum limit.'+fileSizeInBytes);
                    $('#imageFilesValidation').text('Please choose an image with a minimum size of [1460] x [1000] pixels.'+fileSizeInBytes);

                    var validationElement = document.querySelector('[data-bv-for="imageFiles"]');

                    document.querySelector('[data-bv-for="imageFiles"]').parentElement.parentElement.classList.remove("has-success");
                    document.querySelector('[data-bv-for="imageFiles"]').parentElement.parentElement.classList.add("has-error");

                    validationElement.setAttribute('data-bv-result', 'INVALID');
                    validationElement.setAttribute('style', '');
                    // Optionally, you can clear the file input to prevent submitting large files

                } else {
                    $('#imageFilesValidation').text('File size is within the limit.');

                }
            }
        });


        let selectedCategory = $("#newsCategory").val()

    console.log("selected category : "+selectedCategory)
    let ids = $("#association").val()
    console.log("ids"+ids)
    // let name = $(this)
    var selectedName = $("#association").find("option:selected").text();
    console.log("selectedName: "+selectedName)
    if(ids) {
        $.ajax({
            url: "${createLink(controller: 'news', action: 'getCategoriesByAssociations')}",
                    type: 'GET',

                    data: {id: ids},
                    success: function (response) {

                        console.log("response : " + response)
                        populateDropdownEdit(response);
                    },
                    error: function (xhr, status, error) {

                        console.error(error);
                    }
                });
            }

        function populateDropdownEdit(data) {
            var select = $('#newsCategory');
            var selectedCategory = select.val(); // Get the selected category
            console.log("selected category : "+selectedCategory);
            select.empty();
            select.append('<option value=""> -- Select Category-- </option>');
            $.each(data, function (index, option) {
                if (option.id === Number(selectedCategory)) {
                    console.log("selected....");
                    select.append('<option selected value="' + option.id + '">' + option.name + '</option>');
                } else {
                    select.append('<option value="' + option.id + '">' + option.name + '</option>');
                }
            });
        }


        $("#association").on("change", function () {
            let ids = $(this).val()

            // let name = $(this)
            var selectedName = $(this).find("option:selected").text();
            console.log("selectedName: "+selectedName)
            if(ids) {
                $.ajax({
                    url: "${createLink(controller: 'news', action: 'getCategoriesByAssociations')}",
                    type: 'GET',

                    data: {id: ids},
                    success: function (response) {
                        // Handle the successful JSON response
                        console.log("response : " + response)
                        console.log("selected category : "+selectedCategory)
                        populateDropdown(response);
                    },
                    error: function (xhr, status, error) {
                        // Handle errors
                        console.error(error);
                    }
                });
            }

        });


        function populateDropdown(data) {
            console.log("data : "+JSON.stringify(data));
            var select = $('#newsCategory');  // Replace 'yourDropdownId' with the actual ID of your select dropdown
            // Clear existing options
            select.empty();
            // Iterate over the data and add options to the dropdown
            select.append('<option value=""> -- Select Category-- </option>');
            $.each(data, function (index, option) {
                select.append('<option value="' + option.id + '">' + option.name + '</option>');
            });
        }


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


