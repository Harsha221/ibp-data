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
    <label class="col-md-2 control-label" for="size">
        Size <span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                type="text"
                id="size"
                name="size"
                value="${data?.size}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Size"
                required="true" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="description">
        Description<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:textarea
                name="description"
                id="description"
                required="true"
                bean="${data}"
                class="form-control"
                placeholder="Enter  Description"
                value="${data?.description}"/>
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="isMultiplePages">
        Multiple Pages<span class="text-danger"></span>
    </label>
    <div class="col-md-8">
        <g:checkBox
                name="isMultiplePages"
                id="isMultiplePages"
                class="bs-switch"
                value="${data?.isMultiplePages}"
                data-size="mini"
                onchange="togglePageAmounts()"

        />
    </div>
</div>

%{--<div class="form-group">--}%
%{--    <label class="col-md-2 control-label" for="pageSelection">--}%
%{--        Amount<span class="text-danger">*</span>--}%
%{--    </label>--}%
%{--    <div class="col-md-8">--}%
%{--        <form:select--}%
%{--                id="pageSelection"--}%
%{--                name="pageSelection"--}%
%{--                class="form-control"--}%
%{--                from="['For Single Page', 'For Multiple Pages']"--}%
%{--                value="${data?.amount}"--}%
%{--                noSelection="['':' -- Select Type -- ']"--}%
%{--                required="true"--}%
%{--        />--}%

%{--    </div>--}%
%{--</div>--}%

<div class="form-group" id="amountDiv">
    <label class="col-md-2 control-label" for="amount">
        Amount <span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                id="amount"
                name="amount"
                value="${data?.amount}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Amount"
                required="true" />
    </div>
</div>


<div class="form-group" id="amountPage1Div">
    <label class="col-md-2 control-label" for="amountPage1">
         Page Amount 1 <span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                id="amountPage1"
                name="amountPage1"
                value="${data?.amountPage1}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Amount for Page 1"
                required="true" />
    </div>
</div>

<div class="form-group" id="amountPage2Div">
    <label class="col-md-2 control-label" for="amountPage2">
        Page Amount 2 <span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                id="amountPage2"
                name="amountPage2"
                value="${data?.amountPage2}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Amount for Page 2"
                required="true" />
    </div>
</div>

<div class="form-group" id="amountPage3Div">
    <label class="col-md-2 control-label" for="amountPage3">
        Page Amount 3 <span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                id="amountPage3"
                name="amountPage3"
                value="${data?.amountPage3}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Amount for Page 3"
                required="true" />
    </div>
</div>

<div class="form-group"  id="amountPage4Div">
    <label class="col-md-2 control-label" for="amountPage4">
        Page Amount 4 <span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                id="amountPage4"
                name="amountPage4"
                value="${data?.amountPage4}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Amount for Page 4"
                required="true" />
    </div>
</div>

<div class="form-group" id="amountPage5Div">
    <label class="col-md-2 control-label" for="amountPage5">
        Page Amount 5 <span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                id="amountPage5"
                name="amountPage5"
                value="${data?.amountPage5}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Amount for Page 5"
                required="true" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for ="imageFile">
        Image <span class="text-danger">*</span>
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
                                id="imageFile"
                                name="imageFile"
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
                <div class="fileinput-preview  thumbnail" data-trigger="fileinput" style="height: 150px; line-height: 150px; width: 400px;" data-word="100 x 100"></div>
                <div>
                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                        <form:input
                                type="file"
                                id="imageFile"
                                name="imageFile"
                                bean="${data}"
                                class="form-control"
                                value="${data?.imageUrl}"
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
<script>
    document.addEventListener("DOMContentLoaded", function() {
        togglePageAmounts();
    });

    function togglePageAmounts() {

        var isMultiplePagesChecked = document.getElementById("isMultiplePages").checked;
        document.getElementById("amountDiv").style.display = isMultiplePagesChecked ? "none" : "block";
        document.getElementById("amountPage1Div").style.display = isMultiplePagesChecked ? "block" : "none";
        document.getElementById("amountPage2Div").style.display = isMultiplePagesChecked ? "block" : "none";
        document.getElementById("amountPage3Div").style.display = isMultiplePagesChecked ? "block" : "none";
        document.getElementById("amountPage4Div").style.display = isMultiplePagesChecked ? "block" : "none";
        document.getElementById("amountPage5Div").style.display = isMultiplePagesChecked ? "block" : "none";
    }
</script>
