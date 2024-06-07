<form:select
        id="subCategoryId"
        name="subCategoryId"
        class="form-control"
        value="${subCategoryInstance?.id}"
        from="${subCategory}"
        optionValue="name"
        optionKey="id"
        noSelection="['':' -- Select SubCategory -- ']" />


<script type="text/javascript">
    $( document ).ready(function() {
        $('#subCategoryId').select2()
    });

</script>
