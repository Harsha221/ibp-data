<g:hasErrors bean="${bean}" field="${name}">
    <small class="help-block"><g:fieldError bean="${bean}" field="${name}"/></small>
    <asset:script type="text/javascript">
        $('#${id}').closest('.form-group').addClass('has-error')
    </asset:script>
</g:hasErrors>
<asset:script>
  fieldValidations['${id}'] = {
    validators: {
      <g:if test="${required == 'true' ?: false}">
      notEmpty: {
        message: 'This is required'
      },
      </g:if>
      %{--<g:if test="${type == 'email'}">
      regexp: {
        regexp: /^\S+@\S{1,}\.\S{1,}$/,
        message: 'Please enter valid email format'
      }
      </g:if>--}%
    }
  }
</asset:script>
