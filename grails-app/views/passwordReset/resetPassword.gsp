<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="login">
    <title>Reset Password</title>
</head>
<body>
<div class="col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 login-form">
    <div class="panel-header">
        <h2 class="text-center">
            <g:img src="img/headerIBPlogo.svg" width="250" height="63" alt="IBP"/>
        </h2>
    </div>
    <div class="panel-header">
        <h3 class="text-center" >Reset Password</h3>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-xs-12">
                <g:if test="${flash.message}">
                    <div class="alert alert-success">${flash.message}</div>
                </g:if>
                <g:if test="${flash.messages}">
                    <div class="alert alert-success">${flash.message}</div>
                </g:if>
                <g:if test="${flash.error}">
                    <div class="alert alert-danger">${flash.error}</div>
                </g:if>
                <g:if test="${userProfileId}">
                    <form action="${createLink(controller: 'passwordReset', action: 'saveNewPassword')}" method="post">
                        <input type="hidden" name="email" value="${email}">
                        <input type="hidden" name="userProfileId" value="${params.userProfileid}">
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-fw ti-lock"></i></span>
                                <input type="password" id="newPassword" name="newPassword" class="form-control" placeholder="New Password" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-fw ti-lock"></i></span>
                                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="Confirm Password" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block">Submit</button>
                        </div>
                    </form>
                </g:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>



