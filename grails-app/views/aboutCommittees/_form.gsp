<%@ page import="com.ibp.admin.Advertisement; com.ibp.admin.Association" %>
<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Name<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="text"
                id="name"
                name="name"
                value="${data?.name}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Name"
                required="true" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Designation<span class="text-danger">*</span>
    </label>
    <input type="hidden" id="level" name="level1" value="${data?.designation}" />
    <div class="col-md-6">
        <form:select
                id="designation"
                name="designation1"
                class="form-control"
                from="${['1':'President', '2':'Sr. Vice President', '3':'Vice President', '4':'Hon. Secretary', '5':'Hon. Secretary (R)', '6':'Hon.Treasurer', '7':'Immediate Past President', '8':'Executive Committee Member', '9':'Co-Option', '10':'Invitee', '11':'SP-Invitee']}"
                value="${data?.level}"
                optionValue="value"
                optionKey="key"
                required="true"
                noSelection="['':' -- Select Designation -- ']" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Company Name
    </label>
    <div class="col-md-6">
        <form:input
                type="text"
                id="companyName"
                name="companyName"
                value="${data?.companyName}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Company Name"
        />
    </div>
</div>

<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Address<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:textarea
                id="address" rows="3"
                name="address"
                value="${data?.address}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Address"
                required="true" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Mobile No
    </label>
    <div class="col-md-6">
        <form:input
                type="text"
                id="mobileNo"
                name="mobileNo"
                value="${data?.mobileNo}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Mobile No"
        />
    </div>
</div>

<div class="form-group">
    <label class="col-md-4 control-label" for="name">
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
    <label class="col-md-4 control-label" for="name">
        Type<span class="text-danger">*</span>
    </label>
    <input type="hidden" id="typeId" name="typeId1" value="${data?.type}" />
    <div class="col-md-6">
        <form:select
                id="type"
                name="type1"
                class="form-control"
                from="${['1':'Office Bearers', '2':'Executive Committee', '3':'Past Presidents']}"
                value="${data?.typeId}"
                optionValue="value"
                optionKey="key"
                required="true"
                noSelection="['':' -- Select Type -- ']"

        />
    </div>
</div>

<div id="showYear" class="form-group">
    <label class="col-md-4 control-label" for="name">
        Year
    </label>
    <div class="col-md-6">
        <form:input
                type="text"
                id="year"
                name="year"
                value="${data?.year}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Year"
        />
    </div>
</div>

<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Association<span class="text-danger">*</span>
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


<div id="showImage" class="form-group">

    <label class="col-md-4 control-label" for="imageFile">
        Thumb
    </label>
    <div class="col-md-8">
        <g:if test="${data?.thumbUrl}">
            <div class="fileinput fileinput-exists" data-provides="fileinput">
                <div class="fileinput-new thumbnail" style="height: 150px; line-height: 150px; width: 400px;"></div>
                <div class="fileinput-preview fileinput-exists thumbnail" style="height: 150px; line-height: 150px; width: 400px;">
                    <img id="srcImage" />
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
                                value="${data?.thumbUrl}"
                                accept="image/*"
                        />
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
                                accept="image/*"
                                value=""
                        />
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

    var type = $("#type").val()
    if (type === '3') {
        $("div#showYear").show();
    } else {
        $("div#showYear").hide();
    }
    $( document ).ready(function() {

        $("#srcImage").attr('src',"${data?.thumbUrl}" + '?' + (new Date()).getTime());

        $('#designation').on('change', function(){
            const designationName = $("#designation").find("option:selected").text();
            $("#level").val(designationName);
        });

        $('#type').on('change', function(){
            const typeName = $("#type").find("option:selected").text();
            $("#typeId").val(typeName);

            var type = $(this).val();
            if(type === '3') {
                $("div#showYear").show();
            }
            else {
                $("div#showYear").hide();
            }
        });
    });
</script>





