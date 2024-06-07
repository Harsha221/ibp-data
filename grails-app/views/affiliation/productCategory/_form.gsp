<%@ page import="com.ibp.admin.Affiliation" %>

<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Affiliation Name<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="text"
                id="name"
                name="name"
                value="${data?.name}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Affiliation Product Category Name"
                required="true" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Affiliation<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:select
                id="affiliation"
                name="affiliation"
                class="form-control"
                from="${com.ibp.admin.Affiliation.findAllByActive(true)}"
                value="${data?.affiliation?.id}"
                optionValue="name"
                optionKey="id"
                noSelection="['':' -- Select Affiliation -- ']"
                required="true"/>
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="websiteLink">
        Website Link<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="text"
                id="websiteLink"
                name="websiteLink"
                value="${data?.websiteLink}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Affiliation Product Category's Website Link"
                required="true" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="imagePath">
        Image Path<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <form:input
                type="text"
                id="imagePath"
                name="imagePath"
                value="${data?.imagePath}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Affiliation Product Category's ImagePath"
                required="true" />
    </div>
</div>
<div class="form-group form-actions">
    <div class="col-md-8 col-md-offset-4">
        <button type="submit" class="btn btn-effect-ripple btn-primary">${actionName in ['createProductCategory', 'saveProductCategory'] ? 'Create' : 'Update'}</button>
        <g:link action="affiliationProduct" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>


<script type="text/javascript">
    $( document ).ready(function() {
        $('#affiliation').select2()
    });

</script>
