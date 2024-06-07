<label class="col-md-4 control-label" for="product">
    Product<span class="text-danger">*</span>
</label>
<div class="col-md-6">

        <g:if test="${params?.productIds}">
            <select id="product" name="product" class="form-control select2" multiple style="width:100%">
            <g:each in="${subCategoriesProductsList?.keySet()}" var="subCategory">
                <optgroup label="${subCategory}">
                    <g:each in="${subCategoriesProductsList[subCategory]}" var="product">
                        <option value="${product?.id}" ${params?.productIds?.split(',')?.contains(product?.id?.toString()) ? 'selected' : ''}>${product?.name}</option>
                    </g:each>
                </optgroup>
            </g:each>
            </select>
        </g:if>
        <g:else>
            <select id="product" name="product" class="form-control select2" multiple style="width:100%">
            <g:each in="${subCategoriesProductsList?.keySet()}" var="subCategory">
                <optgroup label="${subCategory}">
                    <g:each in="${subCategoriesProductsList[subCategory]}" var="product">
                        <option value="${product?.id}" ${data?.product?.split(',')?.contains(product?.id?.toString()) ? 'selected' : ''}>${product?.name}</option>
                    </g:each>
                </optgroup>
            </g:each>
            </select>
        </g:else>
    <small class="invalid-feedback" id="productErrorMessage" data-bv-for="product"></small>
</div>
<script>
    $("#product").select2({
        theme: "bootstrap",
        placeholder: "Select Products"
    });
</script>