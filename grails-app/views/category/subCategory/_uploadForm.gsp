<%@ page import="grails.util.Holders" %>
<div class="form-group ">
    <div class="col-md-8 col-md-offset-4">
    <label style="display: flex;">Download Format :  <export:formats formats="['csv']" /></label>
</div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="uploadFile">
        File<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="file"
                id="uploadFile"
                name="uploadFile"
                value="${data?.uploadFile}"
                bean="${data}"
                class="form-control"
                placeholder="Choose file"
                required="true"
                accept="text/csv"/>
        <span class="pull-right text-danger">Valid formats: ${Holders.grailsApplication.getConfig().getProperty('ibp.vendors.upload.fileFormat', List).join(', ')}</span>
    </div>
</div>
<div class="form-group form-actions">
    <div class="col-md-8 col-md-offset-4">
        <button type="button" id="uploadButton"  class="btn btn-effect-ripple btn-primary"><i class="fa fa-upload"></i> Upload</button>
        <g:link action="subcategory" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>
<div id="successMessage" style="display: none;" class="alert alert-success">Subcategory file uploaded successfully</div>
<div id="errorMessage" style="display: none;" class="alert alert-danger">Failed to upload Subcategory file</div>
<div id="timeoutMessage" style="display: none;" class="alert alert-danger">The CSV import is taking longer than expected. Please be patient</div>

<script>
    $(document).ready(function () {
        $('#uploadButton').click(function () {
            var formData = new FormData();
            formData.append('uploadFile', $('#uploadFile')[0].files[0]);

            $.ajax({
                url: '${createLink(controller: 'category', action: 'saveSubCategoryUpload')}',
                type: 'POST',
                data: formData,
                timeout:30000,
                processData: false,
                contentType: false,
                beforeSend: function() {
                    // Display waiting message or loading spinner
                    $('#uploadButton').prop('disabled', true).html('<i class="fa fa-spinner fa-spin"></i> Uploading...');
                },
                success: function (response) {
                    // Handle success response
                    $('#uploadButton').prop('disabled', false).html('<i class="fa fa-upload"></i> Upload');
                    console.log(response);
                    $('#successMessage').fadeIn();
                    // flash.message = 'Category file uploaded successfully'
                    // Optionally, display a success message
                    // alert('File uploaded successfully.');
                },
                error: function (xhr, status, error) {
                    // Handle error response
                    $('#uploadButton').prop('disabled', false).html('<i class="fa fa-upload"></i> Upload');
                    console.error(xhr, status, error);
                    if(status ==='timeout'){
                        // Handle timeout response

                        $('#timeoutMessage').fadeIn();

                    } else {
                        // Handle error response

                        $('#errorMessage').fadeIn();

                    }
                    // Optionally, display an error message
                    // alert('Error uploading file. Please try again.');
                }
            });
        });
    });
</script>