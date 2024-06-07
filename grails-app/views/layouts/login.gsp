<!DOCTYPE html>
<html>
<head>
    <title><g:layoutTitle default="::Admin Login::"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <asset:link rel="icon" href="img/favicon.png" type="image/png"/>
    <!-- Bootstrap -->
    <asset:stylesheet src="css/bootstrap.min.css"/>
    <!-- end of bootstrap -->
    <!--page level css -->
    <asset:stylesheet src="themify/css/themify-icons.css"/>
    <asset:stylesheet src="iCheck/css/all.css"/>
    <asset:stylesheet src="bootstrapvalidator/css/bootstrapValidator.min.css"/>
    <asset:stylesheet src="css/login.css"/>
    <!--end page level css-->
    <g:layoutHead/>
</head>
<body id="sign-in">
%{--<div class="preloader">--}%
%{--    <div class="loader_img"><asset:image src="img/loader.gif" alt="loading..." height="64" width="64"/></div>--}%
%{--</div>--}%
<div class="container">
    <div class="row">
        <g:layoutBody/>
    </div>
</div>
<!-- global js -->
<asset:javascript src="js/jquery.min.js"/>
<asset:javascript src="js/bootstrap.min.js"/>
<!-- end of global js -->
<!-- page level js -->
<asset:javascript src="iCheck/js/icheck.js"/>
<asset:javascript src="bootstrapvalidator/js/bootstrapValidator.min.js"/>
<asset:javascript src="js/custom_js/login.js"/>
<!-- end of page level js -->
</body>
</html>
