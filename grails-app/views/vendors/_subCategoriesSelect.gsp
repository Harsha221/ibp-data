<label class="col-md-4 control-label" for="subCategory">
    Sub Category<span class="text-danger">*</span>
</label>
<div class="col-md-6">
    <g:if test="${params?.subCategoryIds}">
        <select id="subCategory" name="subCategory" class="form-control select2" multiple style="width:100%">
            <optgroup label="${category?.name}">
                <g:each in="${categoriseSubCategories}" var="subCategoryIns">
                    <option value="${subCategoryIns?.id}" ${params?.subCategoryIds?.split(',')?.contains(subCategoryIns?.id?.toString()) ? 'selected' : ''}>
                        ${subCategoryIns?.name}</option>
                </g:each>
            </optgroup>
        </select>
    </g:if>
    <g:else>
        <select id="subCategory" name="subCategory" class="form-control select2" multiple style="width:100%">
            <optgroup label="${category?.name}">
                <g:each in="${categoriseSubCategories}" var="subCategoryIns">
                    <option value="${subCategoryIns?.id}" ${data?.subCategory?.split(',')?.contains(subCategoryIns?.id?.toString()) ? 'selected' : ''}>
                        ${subCategoryIns?.name}</option>
                </g:each>
            </optgroup>
        </select>
    </g:else>
    <small class="invalid-feedback" id="subCategoryErrorMessage" data-bv-for="subCategory"></small>
</div>
<script>
    $(document).ready(function() {
        var subcategory = "${data?.subCategory}"
        if(!subcategory) {
            subcategory = $('#subCategory')?.val()?.join(',')
        }
        var product = "${data?.product}"
        if(!product) {
            product = $('#product')?.val()?.join(',')
        }

            $.ajax({
                type: "GET",
                url: "${createLink(controller: 'vendors', action: 'getProductsBySubCategories')}",
                async: false,
                data: { id:  subcategory, productIds: product},
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    console.log("SUCCESS")
                    $('#ProductDiv').html(data);
                },
                error: function (textStatus, errorThrown) {

                }
            });


        $("#subCategory").on("change", function() {
            let ids = $(this).val();
            $.ajax({
                type: "GET",
                url: "${createLink(controller: 'vendors', action: 'getProductsBySubCategories')}",
                async: false,
                data: { id:  ids?.join(","), productIds: $('#product')?.val()?.join(',')},
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    console.log("SUCCESS")
                    $('#ProductDiv').html(data);
                },
                error: function (textStatus, errorThrown) {

                }
            });
        });
        $('#subCategory').select2({
            theme: "bootstrap",
            placeholder: "Select Sub Categories"
        });
    });
</script>
