<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 06-06-2024
  Time: 07:16
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="login"/>
    <title>IBP Vendor Login</title>
</head>

<body>

<div class="col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 login-form">

    <div class="panel-header">
        <h2 class="text-center">
            <g:img src="img/headerIBPlogo.svg" alt="IBP"/>
        </h2>
    </div>

    <div class="panel-body">
        <div class="row">
            <div class="col-xs-12">
                <g:if test='${flash.message}'>
                    <div class="alert alert-danger">${flash.message}</div>
                </g:if>
                <g:if test="${flash.messages}">
                    <div class="alert alert-success">${flash.messages}</div>
                </g:if>
                <g:form controller="vendorsLogin" action="sendOtp">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-fw ti-user"></i></span>
                            <g:textField name="mobileNo" class="form-control"
                                         placeholder="Phone Number" id="phoneNumber" value="${params?.mobileNo}" required="true"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <g:submitButton class="btn btn-ibp-login btn-block" name="login" value="Send OTP"/>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>