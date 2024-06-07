<asset:script>
  let fieldValidations = {}
</asset:script>
<g:uploadForm name="${name}"
        action="${action}"
        controller="${controller}"
        id="${id}"
        method="${method}"
        class="${className}"
        novalidate="${novalidate}">
    ${raw(body())}
</g:uploadForm>
<asset:script>
    $('#${name}').bootstrapValidator({
        fields: fieldValidations
    })
</asset:script>

