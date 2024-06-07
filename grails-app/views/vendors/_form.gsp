<%@ page import="com.ibp.admin.enums.MediaType; com.ibp.admin.VendorMedia; com.ibp.admin.BusinessType; com.ibp.admin.Association; java.time.LocalDate; java.time.format.DateTimeFormatter; com.ibp.admin.SubCategory;  com.ibp.admin.Category" %>
<%
    List<com.ibp.admin.Category> categories = Category.findAllByActive(true)
    List<com.ibp.admin.Association> associations = Association.findAllByActive(true)
%>

<div class="col-md-6">
    <div class="panel">
        <g:if test="${params.name.equals("businessList")}">
            <input type="hidden" name="name" id="name" value="businessList">
        </g:if>
        <g:if test="${params.name.equals("unknown")}">
            <input type="hidden" name="name" id="name" value="unknown">
        </g:if>
        <div class="panel-heading">
            <h3 class="panel-title"><i class="fa fa-fw ti-star"></i> Vendor Details</h3>
        </div>
        <div class="panel-body">
            <div class="form-group">
                <label class="col-md-4 control-label" for="businessName">
                    Business Name<span class="text-danger">*</span>
                </label>
                <div class="col-md-6">
                    <form:input
                            type="text"
                            id="businessName"
                            name="businessName"
                            value="${data?.businessName}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor's Business Name"
                            required="true" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="username">
                    Primary Contact Name<span class="text-danger">*</span>
                </label>
                <div class="col-md-6">
                    <form:input
                            type="text"
                            id="username"
                            name="username"
                            value="${data?.username}"
                            bean="${data}"
                            class="form-control"
                            required="true"
                            placeholder="Enter Primary Contact Name"
                    />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="secondaryUsername">
                    Alternate Contact Name
                </label>
                <div class="col-md-6">
                    <form:input
                            type="text"
                            id="secondaryUsername"
                            name="secondaryUsername"
                            value="${data?.secondaryUsername}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Secondary Contact Name"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="businessEmail">
                    Business Email<span class="text-danger">*</span>
                </label>
                <div class="col-md-6">
                    <input type="hidden" id="hiddenBusinessEmail" name="hiddenBusinessEmail" value="${data?.hiddenBusinessEmail ? data?.hiddenBusinessEmail : data?.businessEmail}" />
                    <form:input
                            type="email"
                            id="businessEmail"
                            name="businessEmail"
                            value="${data?.businessEmail}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor Business Email"
                            required="true" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="secondaryEmail">
                    Secondary Email
                </label>
                <div class="col-md-6">
                    <form:input
                            type="email"
                            id="secondaryEmail"
                            name="secondaryEmail"
                            value="${data?.secondaryEmail}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor Secondary Email"
                            required="false" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="address">
                    Address<span class="text-danger">*</span>
                </label>
                <div class="col-md-6">
                    <form:textarea
                            id="address"
                            name="address"
                            value="${data?.address}"
                            bean="${data}"
                            rows="3"
                            class="form-control resize_vertical"
                            placeholder="Enter Vendor Address"
                            required="true" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="area">
                    Area
                </label>
                <div class="col-md-6">
                    <form:input
                            type="text"
                            id="area"
                            name="area"
                            value="${data?.area}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor Area"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="metaKeywords">
                    Location<span class="text-danger">*</span>
                </label>
                <div class="col-md-6">
                    <form:input
                            type="textarea"
                            id="location"
                            name="location"
                            value="${data?.city ? data?.city + ',' + data?.district+ ',' + data?.state : ''}"
                            bean="${data}"
                            rows="3"
                            class="form-control"
                            required="true"
                            placeholder="Enter Vendor Location"/>
                    <g:hiddenField name="latitude" id="latitude" value="${data?.latitude}"/>
                    <g:hiddenField name="longitude" id="longitude" value="${data?.longitude}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="city">
                    City<span class="text-danger">*</span>
                </label>
                <div class="col-md-6">
                    <form:input
                            type="text"
                            id="city"
                            name="city"
                            value="${data?.city}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor city"
                    />
                    <small class="invalid-feedback" id="cityErrorMessage" data-bv-for="city"></small>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="district">
                    District<span class="text-danger">*</span>
                </label>
                <div class="col-md-6">
                    <form:input
                            type="text"
                            id="district"
                            name="district"
                            value="${data?.district}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor district"
                    />
                    <small class="invalid-feedback" id="districtErrorMessage" data-bv-for="district"></small>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="state">
                    State<span class="text-danger">*</span>
                </label>
                <div class="col-md-6">
                    <form:input
                            type="text"
                            id="state"
                            name="state"
                            value="${data?.state}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor State"
                    />
                    <small class="invalid-feedback" id="stateErrorMessage" data-bv-for="state"></small>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="pinCode">
                    Pincode
                </label>
                <div class="col-md-6">
                    <form:input
                            type="text"
                            id="pinCode"
                            name="pinCode"
                            value="${data?.pinCode}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor Pin Code"
                            maxlength="6"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="primaryLandNumber">
                    Primary Landline Number
                </label>
                <div class="col-md-6">
                    <form:input
                            type="text"
                            id="primaryLandNumber"
                            name="primaryLandNumber"
                            value="${data?.primaryLandNumber}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor Primary Landline Number"
                            maxlength="12" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="secondaryLandNumber">
                    Secondary Landline Number
                </label>
                <div class="col-md-6">
                    <form:input
                            type="text"
                            id="secondaryLandNumber"
                            name="secondaryLandNumber"
                            value="${data?.secondaryLandNumber}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor Secondary Landline Number"
                            maxlength="12" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="primaryPhoneNumber">
                    Primary Mobile Number<span class="text-danger">*</span>
                </label>
                <div class="col-md-6">
                    <input type="hidden" id="hiddenPrimaryPhoneNumber" name="hiddenPrimaryPhoneNumber" value="${data?.hiddenPrimaryPhoneNumber ? data?.hiddenPrimaryPhoneNumber : data?.primaryPhoneNumber}" />

                    <form:input
                            type="number"
                            id="primaryPhoneNumber"
                            name="primaryPhoneNumber"
                            value="${data?.primaryPhoneNumber}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor Primary Mobile Number"
                            required="true"

                    />
                    <small class="invalid-feedback" id="primaryPhoneNumberError" data-bv-for="primaryPhoneNumber"></small>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="secondaryPhoneNumber">
                    Secondary Mobile Number
                </label>
                <div class="col-md-6">
                    <form:input
                            type="number"
                            id="secondaryPhoneNumber"
                            name="secondaryPhoneNumber"
                            value="${data?.secondaryPhoneNumber}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor Secondary Mobile Number"
                    />
                    <small class="invalid-feedback" id="secondaryPhoneNumberError" data-bv-for="secondaryPhoneNumber"></small>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="tags">
                    Hash Tag<span class="text-danger"></span>
                </label>
                <div class="col-md-6">
                    <input type="text" name="tags" id="tags" placeholder="Enter Vendor Tags" value="${data?.tags}" data-role="tagsinput"/>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="col-md-6">
    <div class="panel">
        <div class="panel-heading">
            <h3 class="panel-title">
                <i class="fa fa-fw ti-star"></i>Vendor Business Details
            </h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="form-group">
                    <label class="col-md-4 control-label" for="association">
                        Association<span class="text-danger">*</span>
                    </label>
                    <div class="col-md-6">
                        <select id="association" name="association" class="form-control select2" multiple style="width:100%" required="true">
                            <option value=""></option>
                            <g:each in="${associations}" var="association">
                                <g:if test="${data?.association}">
                                    <option value="${association?.id}" ${Arrays.asList(data?.association?.split(','))?.contains(association?.id?.toString()) ? 'selected' : ''}>
                                        ${association?.name}
                                    </option>
                                </g:if>
                                <g:else>
                                    <option value="${association?.id}">
                                        ${association?.name}
                                    </option>
                                </g:else>
                            </g:each>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="businessType">
                        Business Type
                    </label>
                    <div class="col-md-6">
                        <form:select
                                id="businessType"
                                name="businessType"
                                value="${data?.businessType}"
                                rows="3"
                                class="form-control resize_vertical"
                                from="${com.ibp.admin.BusinessType.list()}"
                                optionValue="name"
                                optionKey="name"
                                noSelection="['':' -- Select Business Type -- ']"
                        />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="websiteAddress">
                        Website Address
                    </label>
                    <div class="col-md-6">
                        <form:input
                                type="text"
                                id="websiteAddress"
                                name="websiteAddress"
                                value="${data?.websiteAddress}"
                                bean="${data}"
                                class="form-control"
                                placeholder="Enter Vendor Website"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="address">
                        Category<span class="text-danger">*</span>
                    </label>
                    <div class="col-md-6">
                        <form:select
                                id="category"
                                name="category"
                                value="${data?.category}"
                                rows="3"
                                class="form-control resize_vertical"
                                from="${categories}"
                                optionValue="name"
                                optionKey="id"
                                noSelection="['':' -- Select Category -- ']"
                                required="true" />
                    </div>
                </div>
                <div class="form-group" id="SubCategoryDiv">
                    <g:render template="subCategoriesSelect" model="[data: data, categoriseSubCategories: categoriseSubCategories,
                                                                     params: params]"/>
                </div>
                <div class="form-group" id="ProductDiv">
                    <g:render template="productSelect" model="[data: data, subCategoriesProductsList: subCategoriesProductsList,
                                                               params: params]"/>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="aboutCompany">
                        About Company
                    </label>
                    <div class="col-md-6">
                        <form:textarea
                                id="aboutCompany"
                                name="aboutCompany"
                                value="${data?.aboutCompany}"
                                bean="${data}"
                                rows="5"
                                class="form-control resize_vertical"
                                placeholder="Enter About Company"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="aboutCustomers">
                        About Customer
                    </label>
                    <div class="col-md-6">
                        <form:textarea
                                id="aboutCustomers"
                                name="aboutCustomers"
                                value="${data?.aboutCustomers}"
                                bean="${data}"
                                rows="3"
                                class="form-control resize_vertical"
                                placeholder="Enter About Customers"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="aboutSuppliers">
                        About Suppliers
                    </label>
                    <div class="col-md-6">
                        <form:textarea
                                id="aboutSuppliers"
                                name="aboutSuppliers"
                                value="${data?.aboutSuppliers}"
                                bean="${data}"
                                rows="3"
                                class="form-control resize_vertical"
                                placeholder="Enter About Suppliers"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="businessType">
                        Days Open<span class="text-danger">*</span>
                    </label>
                    <div class="col-md-6">

                        <input type="checkbox" id="selectAllDays" name="selectAllDays" value=""/>
                        <label for="selectAllDays">Select All</label>

                        <select id="daysOpen" name="daysOpen" class="form-control select2" multiple style="width:100%" required="true">
                            <option value=""></option>
                            <g:each in="${com.ibp.admin.enums.DaysOpen.list()}" var="day">
                                <option value="${day.value}" ${data?.daysOpen?.split(',')?.contains(day?.value) ? 'selected' : ''}>
                                    ${day?.name}
                                </option>
                            </g:each>
                        </select>
                    </div>
                </div>



                <div class="form-group">
                    <label class="col-md-4 control-label" for="yearOfEstablishment">
                        Year Of Establishment
                    </label>
                    <div class="col-md-6">
                        <form:input
                                type="date"
                                id="yearOfEstablishment"
                                name="yearOfEstablishment"
                                value="${data?.yearOfEstablishment}"
                                class="form-control"
                                placeholder="Enter Year Of Establishment"
                        />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="gst">
                        GST
                    </label>
                    <div class="col-md-6">
                        <form:input
                                type="text"
                                id="gstNo"
                                name="gstNo"
                                value="${data?.gstNo}"
                                bean="${data}"
                                class="form-control"
                                placeholder="Enter GST"/>
                        <small class="invalid-feedback" id="gstErrorMessage" data-bv-for="gstNo"></small>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="metaTitle">
                        Meta Title
                    </label>
                    <div class="col-md-6">
                        <form:input
                                type="text"
                                id="metaTitle"
                                name="metaTitle"
                                value="${data?.metaTitle}"
                                bean="${data}"
                                class="form-control"
                                placeholder="Enter Meta Title" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="metaDescription">
                        Meta Description
                    </label>
                    <div class="col-md-6">
                        <form:input
                                type="textarea"
                                id="metaDescription"
                                name="metaDescription"
                                value="${data?.metaDescription}"
                                bean="${data}"
                                rows="3"
                                class="form-control"
                                placeholder="Enter Meta Description"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="metaKeywords">
                        Meta Keywords
                    </label>
                    <div class="col-md-6">
                        <form:input
                                type="textarea"
                                id="metaKeywords"
                                name="metaKeywords"
                                value="${data?.metaKeywords}"
                                bean="${data}"
                                rows="3"
                                class="form-control"
                                placeholder="Enter Meta Keywords"/>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>








<div class="col-md-12">
    <div class="panel">
        <div class="panel-heading">
            <h3 class="panel-title">
                <i class="fa fa-fw ti-star"></i>Vendor Media Details
            </h3>
        </div>
        <div class="panel-body">
            <div class="row">

                <%
                    boolean isPan = false;
                    boolean isAadhar = false;
                    boolean isCertificate = false;
                    boolean isLogo = false;
                    boolean isLicense = false;
                    boolean isUploadMedia = false;
                %>
                <g:if test="${vendorMediaList?.size() > 0}">
                    <div class="form-group">
                        <g:each in="${vendorMediaList}" var="vendorMedia">

                            <g:if test="${vendorMedia?.type == MediaType.PAN_CARD.value}">
                                <% isPan = true;%>

                                <label class="col-md-2 control-label" for="panCardFile">
                                    Pan Card
                                </label>
                                <div class="col-md-4">
                                    <g:if test="${vendorMedia?.path}">
                                        <div class="fileinput fileinput-exists" data-provides="fileinput">
                                            <div class="fileinput-new thumbnail" style="height: 150px; line-height: 150px; width: 300px;"></div>
                                            <div class="fileinput-preview fileinput-exists thumbnail" style="height: 150px; line-height: 150px; width: 300px;">
                                                <img src="${vendorMedia?.path}"/>
                                            </div>
                                            <div>
                                                <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                                                    <form:input
                                                            type="file"
                                                            id="panCardFile"
                                                            name="panCardFile"
                                                            class="form-control"
                                                            placeholder="Choose file"
                                                            value="${vendorMedia?.path}"
                                                            accept="image/*"/>
                                                </span>
                                                <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                                            </div>
                                        </div>
                                    </g:if>
                                </div>
                            </g:if>

                            <g:if test="${vendorMedia?.type == MediaType.AADHAAR_CARD.value}">
                                <% isAadhar = true;%>

                                <label class="col-md-2 control-label" for="aadhaarCardFile">
                                    Aadhaar Card
                                </label>
                                <div class="col-md-4">
                                    <g:if test="${vendorMedia?.path}">
                                        <div class="fileinput fileinput-exists" data-provides="fileinput">
                                            <div class="fileinput-new thumbnail" style="height: 150px; line-height: 150px; width: 300px;"></div>
                                            <div class="fileinput-preview fileinput-exists thumbnail" style="height: 150px; line-height: 150px; width: 300px;">
                                                <img src="${vendorMedia?.path}"/>
                                            </div>
                                            <div>
                                                <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                                                    <form:input
                                                            type="file"
                                                            id="aadhaarCardFile"
                                                            name="aadhaarCardFile"
                                                            class="form-control"
                                                            placeholder="Choose file"
                                                            value="${vendorMedia?.path}"
                                                            accept="image/*"/>
                                                </span>
                                                <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                                            </div>
                                        </div>
                                    </g:if>
                                </div>

                            </g:if>

                            <g:if test="${vendorMedia?.type == MediaType.CERTIFICATE.value}">
                                <% isCertificate = true;%>

                                <label class="col-md-2 control-label" for="certificationFile">
                                    Certification
                                </label>
                                <div class="col-md-4">
                                    <g:if test="${vendorMedia?.path}">
                                        <div class="fileinput fileinput-exists" data-provides="fileinput">
                                            <div class="fileinput-new thumbnail" style="height: 150px; line-height: 150px; width: 300px;"></div>
                                            <div class="fileinput-preview fileinput-exists thumbnail" style="height: 150px; line-height: 150px; width: 300px;">
                                                <img src="${vendorMedia?.path}"/>
                                            </div>
                                            <div>
                                                <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                                                    <form:input
                                                            type="file"
                                                            id="certificationFile"
                                                            name="certificationFile"
                                                            class="form-control"
                                                            placeholder="Choose file"
                                                            value="${vendorMedia?.path}"
                                                            accept="image/*"/>
                                                </span>
                                                <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                                            </div>
                                        </div>
                                    </g:if>
                                </div>

                            </g:if>

                            <g:if test="${vendorMedia?.type == MediaType.LOGO.value}">
                                <% isLogo = true;%>

                                <label class="col-md-2 control-label" for="logoFile">
                                    Logo
                                </label>
                                <div class="col-md-4">
                                    <g:if test="${vendorMedia?.path}">
                                        <div class="fileinput fileinput-exists" data-provides="fileinput">
                                            <div class="fileinput-new thumbnail" style="height: 150px; line-height: 150px; width: 300px;"></div>
                                            <div class="fileinput-preview fileinput-exists thumbnail" style="height: 150px; line-height: 150px; width: 300px;">
                                                <img src="${vendorMedia?.path}"/>
                                            </div>
                                            <div>
                                                <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                                                    <form:input
                                                            type="file"
                                                            id="logoFile"
                                                            name="logoFile"
                                                            class="form-control"
                                                            placeholder="Choose file"
                                                            value="${vendorMedia?.path}"
                                                            accept="image/*"/>
                                                </span>
                                                <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                                            </div>
                                        </div>
                                    </g:if>
                                </div>

                            </g:if>

                            <g:if test="${vendorMedia?.type == MediaType.BUSINESS_LICENCE.value}">
                                <% isLicense = true;%>

                                <label class="col-md-2 control-label" for="businessLicenceFile">
                                    Business Licence
                                </label>
                                <div class="col-md-4">
                                    <g:if test="${vendorMedia?.path}">
                                        <div class="fileinput fileinput-exists" data-provides="fileinput">
                                            <div class="fileinput-new thumbnail" style="height: 150px; line-height: 150px; width: 300px;"></div>
                                            <div class="fileinput-preview fileinput-exists thumbnail" style="height: 150px; line-height: 150px; width: 300px;">
                                                <img src="${vendorMedia?.path}"/>
                                            </div>
                                            <div>
                                                <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                                                    <form:input
                                                            type="file"
                                                            id="businessLicenceFile"
                                                            name="businessLicenceFile"
                                                            class="form-control"
                                                            placeholder="Choose file"
                                                            value="${vendorMedia?.path}"
                                                            accept="image/*"/>
                                                </span>
                                                <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                                            </div>
                                        </div>
                                    </g:if>
                                </div>
                            </g:if>
                        </g:each>
                    </div>
                </g:if>

                <div class="form-group">
                    <g:if test="${!isPan}">
                        <label class="col-md-2 control-label" for="panCardFile">
                            Pan Card
                        </label>
                        <div class="col-md-4">
                            <div class="fileinput fileinput-new" data-provides="fileinput">
                                <div class="fileinput-preview  thumbnail" data-trigger="fileinput" style="height: 150px; line-height: 150px; width: 300px;"></div>
                                <div>
                                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                                        <form:input
                                                type="file"
                                                id="panCardFile"
                                                name="panCardFile"
                                                class="form-control"
                                                bean="${data}"
                                                placeholder="Choose file"
                                                value="${data?.panCardFile}"
                                                accept="image/*"/>
                                    </span>
                                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                                </div>
                            </div>
                        </div>
                    </g:if>

                    <g:if test="${!isAadhar}">

                        <label class="col-md-2 control-label" for="aadhaarCardFile">
                            Aadhaar Card
                        </label>
                        <div class="col-md-4">
                            <div class="fileinput fileinput-new" data-provides="fileinput">
                                <div class="fileinput-preview  thumbnail" data-trigger="fileinput" style="height: 150px; line-height: 150px; width: 300px;"></div>
                                <div>
                                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                                        <form:input
                                                type="file"
                                                id="aadhaarCardFile"
                                                name="aadhaarCardFile"
                                                class="form-control"
                                                bean="${data}"
                                                placeholder="Choose file"
                                                value="${data?.aadhaarCardFile}"
                                                accept="image/*"/>
                                    </span>
                                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                                </div>
                            </div>
                        </div>

                    </g:if>
                    <g:if test="${!isCertificate}">

                        <label class="col-md-2 control-label" for="certificationFile">
                            Certification
                        </label>
                        <div class="col-md-4">
                            <div class="fileinput fileinput-new" data-provides="fileinput">
                                <div class="fileinput-preview  thumbnail" data-trigger="fileinput" style="height: 150px; line-height: 150px; width: 300px;"></div>
                                <div>
                                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                                        <form:input
                                                type="file"
                                                id="certificationFile"
                                                name="certificationFile"
                                                class="form-control"
                                                bean="${data}"
                                                placeholder="Choose file"
                                                value="${data?.certificationFile}"
                                                accept="image/*"/>
                                    </span>
                                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                                </div>
                            </div>
                        </div>

                    </g:if>
                    <g:if test="${!isLogo}">

                        <label class="col-md-2 control-label" for="logoFile">
                            Logo
                        </label>
                        <div class="col-md-4">
                            <div class="fileinput fileinput-new" data-provides="fileinput">
                                <div class="fileinput-preview  thumbnail" data-trigger="fileinput" style="height: 150px; line-height: 150px; width: 300px;"></div>
                                <div>
                                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                                        <form:input
                                                type="file"
                                                id="logoFile"
                                                name="logoFile"
                                                class="form-control"
                                                placeholder="Choose file"
                                                bean="${data}"
                                                value="${data?.logoFile}"
                                                accept="image/*"/>
                                    </span>
                                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                                </div>
                            </div>
                        </div>

                    </g:if>
                    <g:if test="${!isLicense}">

                        <label class="col-md-2 control-label" for="businessLicenceFile">
                            Business Licence
                        </label>
                        <div class="col-md-4">
                            <div class="fileinput fileinput-new" data-provides="fileinput">
                                <div class="fileinput-preview  thumbnail" data-trigger="fileinput" style="height: 150px; line-height: 150px; width: 300px;"></div>
                                <div>
                                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span><span class="fileinput-exists">Change</span>
                                        <form:input
                                                type="file"
                                                id="businessLicenceFile"
                                                name="businessLicenceFile"
                                                class="form-control"
                                                placeholder="Choose file"
                                                bean="${data}"
                                                value="${data?.businessLicenceFile}"
                                                accept="image/*"/>
                                    </span>
                                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                                </div>
                            </div>
                        </div>
                    </g:if>
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
                    <label class="col-md-2 control-label" for="videoFiles">
                        Upload Videos
                    </label>
                    <div class="col-md-4">
                        <g:if test="${multipleVideoMediaList?.size() > 0}">
                            <g:each in="${multipleVideoMediaList}" var="media">
                                <g:if test="${media?.docType?.contains("video")}" >
                                    <div class="fileinput-preview fileinput-exists thumbnail" style="height: 250px; line-height: 250px; width: 500px;">
                                        <video width="470" height="255" controls>
                                            <source src="${media?.path}" type="video/wmv">
                                            <source src="${media?.path}" type="video/mp4">
                                            <source src="${media?.path}" type="video/ogg">
                                            <source src="${media?.path}" type="video/webm">
                                        </video>
                                    </div>
                                    <input type="checkbox" name="removeVideoMedia" id="${media?.id}" value="${media?.id}" />
                                    <label for="${media?.id}">Remove</label>
                                </g:if>
                            </g:each>
                            <div class="fileinput fileinput-new" data-provides="fileinput">
                                <div class="fileinput-preview  thumbnail" data-trigger="fileinput" style="height: 150px; line-height: 150px; width: 400px;"></div>
                                <div>
                                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select video</span><span class="fileinput-exists">Change</span>
                                        <input
                                                type="file"
                                                id="videoFiles"
                                                name="videoFiles"
                                                class="form-control"
                                                placeholder="Choose file"
                                                accept="video/*"
                                                multiple
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
                                    <span class="btn btn-default btn-file"><span class="fileinput-new">Select video</span><span class="fileinput-exists">Change</span>
                                        <input
                                                type="file"
                                                id="videoFiles"
                                                name="videoFiles"
                                                class="form-control"
                                                placeholder="Choose file"
                                                accept="video/*"
                                                multiple
                                        />
                                    </span>
                                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                                </div>
                            </div>
                        </g:else>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>






<div class="form-group form-actions">
    <div class="col-md-12" style="text-align: right;padding-right: 30px;">
        <button type="submit" class="btn btn-effect-ripple btn-primary">${actionName in ['create', 'save'] ? 'Create' : 'Updated'}</button>
        <g:if test="${params.name.equals("businessList")}">
            <g:link action="businessList" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
        </g:if>
        <g:elseif test="${params.name.equals("unknown")}">
            <g:link action="login" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
        </g:elseif>
        <g:else>
            <g:link action="index" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
        </g:else>
    </div>
</div>


<asset:javascript src="image-uploader.min.js"/>
<g:if test="${multipleMediaList?.size() > 0}">
    <script>
        let preloaded = [];
        <g:each in="${multipleMediaList}" var="media">
        <g:if test="${media?.docType?.contains("image")}" >
        preloaded.push({id: '${media?.id}', src: '${media?.path}'})
        </g:if>
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

<script src="https://maps.googleapis.com/maps/api/js?libraries=places&key=AIzaSyD09xHsThzn5yXT8G5LXajnerrZ5sDjom0"></script>

<script>

    $( document ).ready(function() {
        $('#businessType').select2()
        $('#category').select2()

        if($("#daysOpen").val() && $("#daysOpen").val().toString() === "monday,tuesday,wednesday,thursday,friday,saturday,sunday") {
            $("#selectAllDays").prop("checked", true)
        } else {
            $("#selectAllDays").prop("checked", false)
        }
        $("#selectAllDays").click(function () {
            if($(this).is(':checked')) {
                var values = new Array();
                values.push("monday");
                values.push("tuesday");
                values.push("wednesday");
                values.push("thursday");
                values.push("friday");
                values.push("saturday")
                values.push("sunday")
                $("#daysOpen").val(values).trigger('change');
            } else {
                var values = new Array();
                $("#daysOpen").val(values).trigger('change');
            }
        });


        $('button[type="submit"]').on('click', function (e) {
            let bName = $('#businessName')?.val()
            let username = $('#username')?.val()
            let bEmail = $('#businessEmail')?.val()
            let address = $('#address')?.val()
            let location = $('#location')?.val()
            let city = $('#city')?.val()
            let district = $('#district')?.val()
            let state = $('#state')?.val()
            let mobileNo = $('#primaryPhoneNumber')?.val()
            let association = $('#association')?.val()
            let category = $('#category')?.val()
            let daysOpen = $('#daysOpen')?.val()
            let flag = false
            if(bName && username && bEmail && address && location && mobileNo && association && category && daysOpen) {
                flag = true
            }

            let subCatValue = $('#subCategory')?.val()
            let productValue = $('#product')?.val()

            if (flag && (city == null || city === "")) {
                $("#cityErrorMessage").text("This is required");
                e.preventDefault()
            } else {
                $("#cityErrorMessage").text("");
            }

            if (flag && (district == null || district === "")) {
                $("#districtErrorMessage").text("This is required");
                e.preventDefault()
            } else {
                $("#districtErrorMessage").text("");
            }

            if (flag && (state == null || state === "")) {
                $("#stateErrorMessage").text("This is required");
                e.preventDefault()
            } else {
                $("#stateErrorMessage").text("");
            }

            if (flag && (subCatValue == null || subCatValue === "")) {
                $("#subCategoryErrorMessage").text("This is required");
                e.preventDefault()
            } else {
                $("#subCategoryErrorMessage").text("");
            }
            if (flag && (productValue == null || productValue === "")) {
                $("#productErrorMessage").text("This is required");
                e.preventDefault()
            } else {
                $("#productErrorMessage").text("");
            }
        });

        let inputvalues1 =  document.getElementById('gstNo').value;
        let gstinformat1 = new RegExp('^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$');
        if(inputvalues1) {
            if (gstinformat1.test(inputvalues1)) {
                $(':input[type="submit"]').prop('disabled', false);
                $("#gstErrorMessage").text("");
            } else {
                $(':input[type="submit"]').prop('disabled', true);
                $("#gstErrorMessage").text("Enter valid GST No");
            }
        }

        $("#gstNo").on("input", function(){
            let inputvalues =  document.getElementById('gstNo').value;
            if(inputvalues) {
                let gstinformat = new RegExp('^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$');
                if (gstinformat.test(inputvalues)) {
                    $(':input[type="submit"]').prop('disabled', false);
                    $("#gstErrorMessage").text("");
                    return true;
                } else {
                    $(':input[type="submit"]').prop('disabled', true);
                    $("#gstErrorMessage").text("Enter valid GST No");
                }
            } else {
                $("#gstErrorMessage").text("");
            }
        });

        let primaryPhoneNumber =  document.getElementById('primaryPhoneNumber').value;
        if(primaryPhoneNumber) {
            if(primaryPhoneNumber.length === 10){
                $("#primaryPhoneNumberError").text("");
                $(':input[type="submit"]').prop('disabled', false);
            } else {
                $(':input[type="submit"]').prop('disabled', true);
                $("#primaryPhoneNumberError").text("Enter valid 10 digit MobileNumber");
            }
        }
        $("#primaryPhoneNumber").on("input", function(e) {
            let mobileNumberLength = $(this).val().length
            if(mobileNumberLength === 10){
                $("#primaryPhoneNumberError").text("");
                $(':input[type="submit"]').prop('disabled', false);
            } else {
                $(':input[type="submit"]').prop('disabled', true);
                $("#primaryPhoneNumberError").text("Enter valid 10 digit MobileNumber");
            }
        });

        let secondaryPhoneNumber =  document.getElementById('secondaryPhoneNumber').value;
        if(secondaryPhoneNumber) {
            if(secondaryPhoneNumber.length === 10){
                $("#secondaryPhoneNumberError").text("");
                $(':input[type="submit"]').prop('disabled', false);
            } else {
                $(':input[type="submit"]').prop('disabled', true);
                $("#secondaryPhoneNumberError").text("Enter valid 10 digit MobileNumber");
            }
        }
        $("#secondaryPhoneNumber").on("input", function(e) {
            let mobileNumberLength = $(this).val().length
            if(mobileNumberLength === 10){
                $("#secondaryPhoneNumberError").text("");
                $(':input[type="submit"]').prop('disabled', false);
            } else {
                $(':input[type="submit"]').prop('disabled', true);
                $("#secondaryPhoneNumberError").text("Enter valid 10 digit MobileNumber");
            }
        });



        var category = $('#category')?.val()
        var subCategory = $('#subCategory')?.val()
        if(subCategory) {
            $.ajax({
                type: "GET",
                url: "${createLink(controller: 'vendors', action: 'getSubCategoriesByCategory')}",
                async: false,
                data: { id:  category, subCategoryIds: subCategory?.join(',')},
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    console.log("SUCCESS")
                    $('#SubCategoryDiv').html(data);
                },
                error: function (textStatus, errorThrown) {

                }
            });
        } else {
            var subCategoryData = "${data?.subCategory}"
            $.ajax({
                type: "GET",
                url: "${createLink(controller: 'vendors', action: 'getSubCategoriesByCategory')}",
                async: false,
                data: { id:  category, subCategoryIds: subCategoryData},
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    console.log("SUCCESS")
                    $('#SubCategoryDiv').html(data);
                },
                error: function (textStatus, errorThrown) {

                }
            });
        }


        $("#category").on("change", function() {
            console.log("onchage called on category....")
            let ids = $(this).val();
            console.log(ids);
            $.ajax({
                type: "GET",
                url: "${createLink(controller: 'vendors', action: 'getSubCategoriesByCategory')}",
                async: false,
                data: { id:  ids, subCategoryIds: $('#subCategory')?.val()?.join(',')},
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    console.log("SUCCESS")
                    $('#SubCategoryDiv').html(data);
                },
                error: function (textStatus, errorThrown) {

                }
            });
        });

        $("#daysOpen").select2({
            theme: "bootstrap",
            placeholder: "Select Days Open"
        });

        $("#association").select2({
            theme: "bootstrap",
            placeholder: "Select Associations"
        });
        let searchInput = 'location';
        let autocomplete;
        autocomplete = new google.maps.places.Autocomplete((document.getElementById(searchInput)), {
            // types: ['geocode'],
            types: ['(cities)'],
            // types: ['address', 'geocode', 'establishment'],
            componentRestrictions: {
                country: "IN"
            }
        });

        google.maps.event.addListener(autocomplete, 'place_changed', function () {
            var near_place = autocomplete.getPlace();
            console.log("Place : ", near_place)
            const latitude = near_place.geometry.location.lat();
            const longitude = near_place.geometry.location.lng();
            document.getElementById('location').value = near_place.formatted_address;
            document.getElementById('latitude').value = latitude;
            document.getElementById('longitude').value = longitude;

            for (var i = 0; i < near_place.address_components.length; i++) {
                const type = near_place.address_components[i].types[0]
                switch (type?.toString()?.toLowerCase()?.trim()) {
                    case 'locality':
                        document.getElementById('city').value = near_place.address_components[i].long_name;
                        /* var validationElement = document.querySelector('[data-bv-for="city"]');
                         // Update the data-bv-result attribute to make it VALID
                         validationElement.setAttribute('data-bv-result', 'VALID');
                         validationElement.setAttribute('style', 'display: none;');

                         document.querySelector('[data-bv-for="city"]').parentElement.parentElement.classList.remove("has-error");
                         document.querySelector('[data-bv-for="city"]').parentElement.parentElement.classList.add("has-success");
 */
                        break;
                    case 'administrative_area_level_3':
                        document.getElementById('district').value = near_place.address_components[i].long_name;
                        /* var validationElement = document.querySelector('[data-bv-for="district"]');
                         // Update the data-bv-result attribute to make it VALID
                         validationElement.setAttribute('data-bv-result', 'VALID');
                         validationElement.setAttribute('style', 'display: none;');

                         document.querySelector('[data-bv-for="district"]').parentElement.parentElement.classList.remove("has-error");
                         document.querySelector('[data-bv-for="district"]').parentElement.parentElement.classList.add("has-success");*/
                        break;
                    case 'administrative_area_level_1':
                        document.getElementById('state').value = near_place.address_components[i].long_name;
                        /*var validationElement = document.querySelector('[data-bv-for="state"]');
                        // Update the data-bv-result attribute to make it VALID
                        validationElement.setAttribute('data-bv-result', 'VALID');
                        validationElement.setAttribute('style', 'display: none;');

                        document.querySelector('[data-bv-for="state"]').parentElement.parentElement.classList.remove("has-error");
                        document.querySelector('[data-bv-for="state"]').parentElement.parentElement.classList.add("has-success");*/
                        break;
                }
            }

            if (latitude && longitude && latitude?.length > 0 && longitude?.length > 0) {
                $("#errorMessage").text("${message(code: 'service.not.available')}");
            }
        });

        $(document).on('change', '#'+searchInput, function () {
            document.getElementById('location').value = '';
            document.getElementById('latitude').value = '';
            document.getElementById('longitude').value = '';
        });

    });

</script>