<g:if test="${required == true}">
<textarea id="${id}" required="${required}"
          name="${name}"
          rows="${rows}"
          class="${className}"
          placeholder="${placeholder}">${value}</textarea>
</g:if>
<g:else>
    <textarea id="${id}"
              name="${name}"
              rows="${rows}"
              class="${className}"
              placeholder="${placeholder}">${value}</textarea>
</g:else>
<g:render template="/common/tags-template/validation" />
