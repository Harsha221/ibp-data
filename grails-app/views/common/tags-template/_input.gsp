<g:if test="${required == true}">
    <input type="${type}" required="${required}"
           id="${id}"
           name="${name}"
           value="${value}"
           class="${className}"
           placeholder="${placeholder}"
           maxlength="${maxlength}"
           minlength="${minlength}"
           max="${max}"
           accept="${accept}">
</g:if>
<g:else>
    <input type="${type}"
           id="${id}"
           name="${name}"
           value="${value}"
           class="${className}"
           placeholder="${placeholder}"
           maxlength="${maxlength}"
           minlength="${minlength}"
           max="${max}"
           accept="${accept}">
</g:else>
<g:render template="/common/tags-template/validation" />
