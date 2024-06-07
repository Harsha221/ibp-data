<div class="form-group ${g.hasErrors(bean: data, field: 'vendorId'){'has-error'}}">
    <label class="col-md-4 control-label" for="vendorId">
        Vendor ID<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="vendorId" name="vendorId" value="${data?.vendorId}" class="form-control" placeholder="Enter Vendor Id">
        <g:hasErrors bean="${data}" field="vendorId">
            <small class="help-block" ><g:fieldError bean="${data}" field="vendorId" /></small>
        </g:hasErrors>
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="name">
        Name<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="name" name="name" value="${data?.name}" class="form-control" placeholder="Enter Vendor Name">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="address">
        Address
        <span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <textarea id="address" name="address" rows="3" class="form-control resize_vertical" placeholder="Enter Vendor Address">${data?.address}</textarea>
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="area">
        Area<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="area" name="area" value="${data?.area}" class="form-control" placeholder="Enter Vendor Area">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="city">
        City<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="city" name="city" value="${data?.city}" class="form-control" placeholder="Enter Vendor city">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="district">
        District<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="district" name="district" value="${data?.district}" class="form-control" placeholder="Enter Vendor district">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="pincode">
        Pincode<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="pincode" name="pincode" value="${data?.pincode}" class="form-control" placeholder="Enter Vendor pincode">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="primaryLandNumber">
        Primary Landline Number<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="primaryLandNumber" name="primaryLandNumber" value="${data?.primaryLandNumber}" class="form-control" placeholder="Enter Vendor Primary Landline Number">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="secondaryLandNumber">
        Secondary Landline Number<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="secondaryLandNumber" name="secondaryLandNumber" value="${data?.secondaryLandNumber}" class="form-control" placeholder="Enter Vendor Secondary Landline Number">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="primaryPhoneNumber">
        Primary Phone Number<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="primaryPhoneNumber" name="primaryPhoneNumber" value="${data?.primaryPhoneNumber}" class="form-control" placeholder="Enter Vendor Primary Phone Number">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="secondaryPhoneNumber">
        Secondary Phone Number<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="secondaryPhoneNumber" name="secondaryPhoneNumber" value="${data?.secondaryPhoneNumber}" class="form-control" placeholder="Enter Vendor Secondary Phone Number">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="businessEmail">
        Business Email<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="businessEmail" name="businessEmail" value="${data?.businessEmail}" class="form-control" placeholder="Enter Vendor Business Email">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="secondaryEmail">
        Secondary Email<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="secondaryEmail" name="secondaryEmail" value="${data?.secondaryEmail}" class="form-control" placeholder="Enter Vendor Secondary Email">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="fax">
        Fax<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="fax" name="fax" value="${data?.fax}" class="form-control" placeholder="Enter Vendor Fax">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="websiteAddress">
        Website<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="websiteAddress" name="websiteAddress" value="${data?.websiteAddress}" class="form-control" placeholder="Enter Vendor Website">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="primaryContactPerson">
        Primary Contact Name<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="primaryContactPerson" name="primaryContactPerson" value="${data?.primaryContactPerson}" class="form-control" placeholder="Enter Primary Contact Name">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="alternateContactPerson">
        Alternate Contact Name<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="alternateContactPerson" name="alternateContactPerson" value="${data?.alternateContactPerson}" class="form-control" placeholder="Enter Alternate Contact Name">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="businessType">
        Business Type<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="businessType" name="businessType" value="${data?.businessType}" class="form-control" placeholder="Enter Business Type">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="aboutCompany">
        About Company
        <span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <textarea id="aboutCompany" name="aboutCompany" rows="7" class="form-control resize_vertical" placeholder="Enter About Company">${data?.aboutCompany}</textarea>
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="yearOfEstablishment">
        Year Of Establishment<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="number" id="yearOfEstablishment" name="yearOfEstablishment" value="${data?.yearOfEstablishment}" class="form-control" placeholder="Enter Year Of Establishment">
    </div>
</div>
<div class="form-group">
    <label class="col-md-4 control-label" for="gst">
        GST<span class="text-danger">*</span>
    </label>
    <div class="col-md-6">
        <input type="text" id="gst" name="gst" value="${data?.gst}" class="form-control" placeholder="Enter GST">
    </div>
</div>
