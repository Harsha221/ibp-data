<%@ page import="com.ibp.admin.Role" %>
<div class="form-group">
    <label class="col-md-4 control-label" for="firstName">
        First Name<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
            type="text"
            id="firstName"
            name="firstName"
            value="${data?.firstName}"
            bean="${data}"
            class="form-control"
            placeholder="Enter First Name"
            required="true" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="lastName">
        Last Name<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="text"
                id="lastName"
                name="lastName"
                value="${data?.lastName}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Last Name"
                required="true" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="email">
        Email<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="email"
                id="email"
                name="email"
                value="${data?.email}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Email"
                required="true" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="contactNo">
        Contact No<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="number"
                id="contactNo"
                name="contactNo"
                value="${data?.contactNo}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Contact No"
                required="true" />
        <small class="invalid-feedback" id="contactNoError" data-bv-for="contactNo"></small>
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="contactNo">
        Username<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="text"
                id="user.username"
                name="user.username"
                value="${data?.user?.username}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Username"
                required="true" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="contactNo">
        Password<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="password"
                id="user.password"
                name="user.password"
                value="${data?.user?.password}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Password"
                required="true" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="contactNo">
        Role<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:select
                id="role"
                name="role"
                class="form-control"
                from="${Role.list()}"
                value="${data?.role}"
                optionValue="roleName"
                optionKey="roleName"
                noSelection="['':' -- Select Role -- ']"
                required="true"
        />
    </div>
</div>
<div class="form-group form-actions">
    <div class="col-md-8 col-md-offset-4">
        <button type="submit" class="btn btn-effect-ripple btn-primary">${actionName in ['create', 'save'] ? 'Create' : 'Update'}</button>
        <g:link action="index" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>

<script>
    $( document ).ready(function() {
        let contactNo =  document.getElementById('contactNo').value;
        if(contactNo) {
            if(contactNo.length === 10){
                $("#contactNoError").text("");
                $(':input[type="submit"]').prop('disabled', false);
            } else {
                $(':input[type="submit"]').prop('disabled', true);
                $("#contactNoError").text("Enter valid 10 digit MobileNumber");
            }
        }
        $("#contactNo").on("input", function(e) {
            let mobileNumberLength = $(this).val().length
            if(mobileNumberLength === 10){
                $("#contactNoError").text("");
                $(':input[type="submit"]').prop('disabled', false);
            } else {
                $(':input[type="submit"]').prop('disabled', true);
                $("#contactNoError").text("Enter valid 10 digit MobileNumber");
            }
        });

    });

</script>
