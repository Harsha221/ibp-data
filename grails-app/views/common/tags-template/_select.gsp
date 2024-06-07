<g:if test="${required == true}">
    <g:select type="${type}" multiple="${multiple}" required="${required}"
              id="${id}"
              name="${name}"
              value="${value}"
              class="${className}"
              from="${from}"
              optionValue="${optionValue}"
              optionKey="${optionKey}"
              onchange="${onchange}"
              noSelection="${noSelection}"/>
</g:if>
<g:else>
    <g:select type="${type}" multiple="${multiple}"
              id="${id}"
              name="${name}"
              value="${value}"
              class="${className}"
              from="${from}"
              optionValue="${optionValue}"
              optionKey="${optionKey}"
              onchange="${onchange}"
              noSelection="${noSelection}"/>
</g:else>

<g:render template="/common/tags-template/validation" />
