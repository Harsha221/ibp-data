<html>
<head>
    <meta name="layout" content="login"/>
    <title>IBP Admin Login</title>
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
                <form action="${postUrl ?: '/login/authenticate'}" autocomplete="off" id="authentication" method="post"
                      class="login_validator">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-fw ti-user"></i></span>
                            <input type="text" class="form-control" name="${usernameParameter ?: 'username'}"
                                   placeholder="Username" id="username">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-fw ti-key"></i></span>
                            <input type="password" class="form-control" name="${passwordParameter ?: 'password'}"
                                   id="password" placeholder="Password">
                        </div>
                    </div>

                    <div class="form-group">
                        <input type="submit" value="Sign In" class="btn btn-ibp-login btn-block"/>
                    </div>
                </form>
                <div class="text-center">
                    <g:link controller="passwordReset" action="forgotPassword">Forget Password?</g:link>
                </div>
                <div class="text-center">
                    <g:link controller="vendors" action="login">user?</g:link>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
