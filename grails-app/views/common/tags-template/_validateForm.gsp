<asset:script>
  let fieldValidations = {}
</asset:script>
<g:form name="${name}" enctype="${enctype}"
        action="${action}"
        controller="${controller}"
        id="${id}"
        method="${method}"
        class="${className}"
        novalidate="${novalidate}">
    ${raw(body())}
</g:form>
<asset:script>
    $('#${name}').bootstrapValidator({
        fields: fieldValidations
    })
</asset:script>

