<%@ page import="com.ibp.admin.NavigationUtils" %>
<section class="content-header">
    <h1>${NavigationUtils.showPageTitle(controllerName, actionName)}</h1>
    <ol class="breadcrumb">
        <li>
            <g:link url="/">
                <i class="fa fa-fw ti-home"></i> Dashboard
            </g:link>
        </li>
        <g:each in="${NavigationUtils.showBreadcrumb(controllerName, actionName)}" var="data">
            <li>
                <g:if test="${data['controller'] == controllerName && data['action'] == actionName}">
                    <a>${data['title']}</a>
                </g:if>
                <g:else>
                    <g:if test="${data?.id}">
                        <g:link controller="${data['controller']}" action="${data['action']}" id="${params?.id}">
                            ${data['title']}
                        </g:link>
                    </g:if>
                    <g:else>
                        <g:link controller="${data['controller']}" action="${data['action']}">${data['title']}</g:link>
                    </g:else>
                </g:else>
            </li>
        </g:each>
    </ol>
</section>
