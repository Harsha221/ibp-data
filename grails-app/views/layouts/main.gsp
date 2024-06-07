<%@ page import="com.ibp.admin.NavigationUtils" %>
<!DOCTYPE html>
<html>
<head>
    <title>${NavigationUtils.showTitle(controllerName, actionName)}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta charset="UTF-8">
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <!-- Bootstrap -->
    <asset:stylesheet src="css/bootstrap.min.css"/>
    <!-- end of bootstrap -->
    <!-- global css -->
    <asset:stylesheet src="tagsinput/tags-input.css"/>
    <asset:stylesheet src="css/app.css"/>
    <asset:stylesheet src="css/custom.css"/>
    <asset:link rel="icon" href="img/favicon.png" type="image/png"/>
    <!--page level css -->
    <asset:stylesheet src="themify/css/themify-icons.css"/>
    <asset:stylesheet src="iCheck/css/all.css"/>
    <asset:stylesheet src="css/alertmessage.css"/>
    <asset:stylesheet src="bootstrapvalidator/css/bootstrapValidator.min.css"/>
    <asset:stylesheet src="jasny-bootstrap/css/jasny-bootstrap.css"/>

    <asset:stylesheet src="animate/animate.min.css"/>
    <asset:stylesheet src="pnotify/css/pnotify.css"/>
    <asset:stylesheet src="pnotify/css/pnotify.brighttheme.css"/>
    <asset:stylesheet src="pnotify/css/pnotify.buttons.css"/>
    <asset:stylesheet src="pnotify/css/pnotify.mobile.css"/>
    <asset:stylesheet src="pnotify/css/pnotify.history.css"/>
    <asset:stylesheet src="css/custom_css/toastr_notificatons.css" />

    <asset:stylesheet src="datatables/css/dataTables.bootstrap.css"/>
    <asset:stylesheet src="css/custom_css/datatables_custom.css" />
    <asset:stylesheet src="css/custom_css/widgets.css" />
    <asset:stylesheet src="css/ibp-common.css" />

    <asset:stylesheet src="bootstrap-multiselect/css/bootstrap-multiselect.css" />
    <asset:stylesheet src="select2/css/select2.min.css" />
    <asset:stylesheet src="select2/css/select2-bootstrap.css" />
    <asset:stylesheet src="image-uploader.min.css" />
    %{--<asset:stylesheet src="css/form_editors.css" />--}%

    <!--end page level css-->

    <!-- end of global css -->
    <asset:javascript src="jquery-3.5.1.js"/>
    <g:layoutHead/>
</head>
<body class="skin-default">
%{--<div class="preloader">--}%
%{--    <div class="loader_img"><asset:image src="img/loader.gif" alt="loading..." height="64" width="64"/></div>--}%
%{--</div>--}%
<!-- header logo: style can be found in header-->
<g:render template="/layouts/header" />
<div class="wrapper row-offcanvas row-offcanvas-left">
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="left-side sidebar-offcanvas">
        <!-- sidebar: style can be found in sidebar-->
        <g:render template="/layouts/sidebar" />
        <!-- /.sidebar -->
    </aside>
    <aside class="right-side">
        <!-- Content Header (Page header) -->
        <g:render template="/layouts/breadcrumb" />
        <!-- Main content -->
        <section class="content">
            <g:layoutBody/>
            <g:render template="/layouts/rightSidebar" />
            <div class="background-overlay"></div>
        </section>
        <!-- /.content -->
    </aside>
    <!-- /.right-side -->
</div>
<!-- /.right-side -->
<!-- ./wrapper -->
<!-- global js -->
<asset:javascript src="js/app.js"/>

<!-- end of page level js -->
<asset:javascript src="datatables/js/jquery.dataTables.js" />
<asset:javascript src="bootstrapvalidator/js/bootstrapValidator.min.js"/>
<asset:javascript src="pnotify/js/pnotify.js"/>
<asset:javascript src="pnotify/js/pnotify.animate.js"/>
<asset:javascript src="pnotify/js/pnotify.buttons.js"/>
<asset:javascript src="pnotify/js/pnotify.confirm.js"/>
<asset:javascript src="pnotify/js/pnotify.nonblock.js"/>
<asset:javascript src="pnotify/js/pnotify.mobile.js"/>
<asset:javascript src="pnotify/js/pnotify.desktop.js"/>
<asset:javascript src="pnotify/js/pnotify.history.js"/>
<asset:javascript src="pnotify/js/pnotify.callbacks.js"/>
<asset:javascript src="jasny-bootstrap/js/jasny-bootstrap.js"/>
<asset:javascript src="image-uploader.min.js"/>

<asset:javascript src="datatables/js/dataTables.bootstrap.js" />
<asset:javascript src="bootstrap-switch/js/bootstrap-switch.js" />

<asset:javascript src="bootstrap-multiselect/js/bootstrap-multiselect.js" />
<asset:javascript src="select2/js/select2.js" />
<asset:javascript src="tagsinput/tags-input.js" />
<asset:javascript src="js/ibp-common.js"/>
<asset:deferredScripts />
<g:if test="${flash.message}">
    <script type="text/javascript">
      showSuccessNotification('${flash.message}')
    </script>
</g:if>
<g:if test="${flash.errorMessage}">
    <script type="text/javascript">
      showErrorNotification('${flash.errorMessage}')
    </script>
</g:if>
<script>
    $("#subCategory").select2({
        theme: "bootstrap",
        placeholder: "Select Sub Categories"
    });
    $("#product").select2({
        theme: "bootstrap",
        placeholder: "Select Products"
    });
</script>
</body>
</html>
