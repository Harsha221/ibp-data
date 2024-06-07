<!-- forgetPassword.gsp -->
<html>
<head>
  <meta name="layout" content="login"/>
  <title>Forget Password</title>
</head>
<body>
<div class="col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 login-form">
  <div class="panel-header">
    <h2 class="text-center">
      <g:img src="img/headerIBPlogo.svg" width="250" height="63" alt="IBP"/>
    </h2>
  </div>
  <div class="panel-body">
    <div class="row">
      <div class="col-xs-12">
        <g:if test="${flash.message}">
          <div class="alert alert-success">${flash.message}</div>
        </g:if>
        <g:if test="${flash.error}">
          <div class="alert alert-danger">${flash.error}</div>
        </g:if>
        <g:form controller="passwordReset" action="sendResetLink" class="login_validator">
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon"><i class="fa fa-fw ti-email"></i></span>
              <input type="text" class="form-control" id="email" name="email" placeholder="Enter your email" required="true"/>
            </div>
          </div>
          <div class="form-group">
            <input type="submit" value="Submit" class="btn btn-primary btn-block"/>
          </div>
        </g:form>
      </div>
    </div>
  </div>
</div>
</body>
</html>



