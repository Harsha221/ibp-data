<%@ page import="com.ibp.admin.Association; java.time.LocalDate; java.time.format.DateTimeFormatter; com.ibp.admin.SubCategory;  com.ibp.admin.Category" %>
<%
    List<com.ibp.admin.Category> categories = Category.findAllByActive(true)
//    List<com.ibp.admin.Association> associations = Association.findAllByActive(true)
%>

<div class="col-md-6">
    <div class="panel">
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
                    Username
                </label>
                <div class="col-md-6">
                    <form:input
                            type="text"
                            id="username"
                            name="username"
                            value="${data?.username}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Primary Contact Name"
                    />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="secondaryUsername">
                    Secondary Contact Name
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
                    Location
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
                            placeholder="Enter Meta Keywords"/>
                    <g:hiddenField name="latitude" id="latitude"/>
                    <g:hiddenField name="longitude" id="longitude"/>
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
                            required="true" />
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
                            required="true" />
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
                            required="true" />
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
                            placeholder="Enter Vendor Pincode"
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
                            required="false"
                            maxlength="12" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="secondaryLandNumber">
                    Tertiary Landline Number
                </label>
                <div class="col-md-6">
                    <form:input
                            type="text"
                            id="tertiaryLandNumber"
                            name="tertiaryLandNumber"
                            value="${data?.tertiaryLandNumber}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor Tertiary Landline Number"
                            required="false"
                            maxlength="12" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="primaryPhoneNumber">
                    Primary Phone Number<span class="text-danger">*</span>
                </label>
                <div class="col-md-6">
                    <form:input
                            type="number"
                            id="primaryPhoneNumber"
                            name="primaryPhoneNumber"
                            value="${data?.primaryPhoneNumber}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor Primary Mobile Number"
                            required="true"
                            maxlength="10" />
                    <small class="invalid-feedback" id="primaryPhoneNumberError" data-bv-for="primaryPhoneNumber"></small>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="secondaryPhoneNumber">
                    Secondary Phone Number
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
                            required="false"
                            maxlength="10" />
                    <small class="invalid-feedback" id="secondaryPhoneNumberError" data-bv-for="secondaryPhoneNumber"></small>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="secondaryPhoneNumber">
                    Third Phone Number
                </label>
                <div class="col-md-6">
                    <form:input
                            type="text"
                            id="thirdPhoneNumber"
                            name="thirdPhoneNumber"
                            value="${data?.thirdPhoneNumber}"
                            bean="${data}"
                            class="form-control"
                            placeholder="Enter Vendor Third Mobile Number"
                            required="false"
                            maxlength="10" />
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
                    <label class="col-md-4 control-label" for="directory">
                        Directory
                    </label>
                    <div class="col-md-6">
                        <form:input
                                type="text"
                                id="directory"
                                name="directory"
                                value="${data?.directory}"
                                bean="${data}"
                                class="form-control"
                                placeholder="Enter Directory"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="businessType">
                        Business Type
                    </label>
                    <div class="col-md-6">
                        <form:input
                                type="text"
                                id="businessType"
                                name="businessType"
                                value="${data?.businessType}"
                                bean="${data}"
                                class="form-control"
                                placeholder="Enter Business Type"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="category">
                            Category
                        </label>
                        <div class="col-md-6">
                            <form:input
                                    type="text"
                                    id="category"
                                    name="category"
                                    value="${data?.category}"
                                    bean="${data}"
                                    class="form-control"
                                    placeholder="Enter Category"
                            />
                        </div>
                    </div>
                </div>
                <div class="form-group" id="SubCategoryDiv">
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="subCategory">
                            Sub Category
                        </label>
                        <div class="col-md-6">
                            <form:input
                                    type="text"
                                    id="subCategory"
                                    name="subCategory"
                                    value="${data?.subCategory}"
                                    bean="${data}"
                                    class="form-control"
                                    placeholder="Enter Sub Category"
                            />
                        </div>
                    </div>
                </div>
                <div class="form-group" id="ProductDiv">
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="product">
                            Product
                        </label>
                        <div class="col-md-6">
                            <form:input
                                    type="text"
                                    id="product"
                                    name="product"
                                    value="${data?.product}"
                                    bean="${data}"
                                    class="form-control"
                                    placeholder="Enter Product Name"
                            />
                        </div>
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
                        Days Open
                    </label>
                    <div class="col-md-6">

                        <input type="checkbox" id="selectAllDays" name="selectAllDays" value=""/>
                        <label for="selectAllDays">Select All</label>

                        <select id="daysOpen" name="daysOpen" class="form-control select2" multiple style="width:100%">
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
                                id="gst"
                                name="gst"
                                value="${data?.gst}"
                                bean="${data}"
                                class="form-control"
                                placeholder="Enter GST"/>
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
                <div class="form-group">
                    <label class="col-md-4 control-label" for="error">
                        Error
                    </label>
                    <div class="col-md-6">
                        <form:input
                                type="textarea"
                                id="error"
                                name="error"
                                value="${data?.error}"
                                bean="${data}"
                                rows="3"
                                class="form-control"
                                />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="form-group form-actions">
    <div class="col-md-12" style="text-align: right;padding-right: 30px;">
        <button type="submit" class="btn btn-effect-ripple btn-primary">${actionName in ['create', 'save'] ? 'Create' : 'Update'}</button>
        <g:link action="uploadView" id="${data?.vendorUpload?.id}" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>

<script src="https://maps.googleapis.com/maps/api/js?libraries=places&key=AIzaSyD09xHsThzn5yXT8G5LXajnerrZ5sDjom0"></script>

<script>
    $( document ).ready(function() {

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

        $("#daysOpen").select2({
            theme: "bootstrap",
            placeholder: "Select Days Open"
        });
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

        // let searchInput = 'search_input';
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
                        break;
                    case 'administrative_area_level_3':
                        document.getElementById('district').value = near_place.address_components[i].long_name;
                        break;
                    case 'administrative_area_level_1':
                        document.getElementById('state').value = near_place.address_components[i].long_name;
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