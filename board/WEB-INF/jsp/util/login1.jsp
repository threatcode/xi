<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <!--   <meta content="IE=edge" http-equiv="X-UA-Compatible"> -->
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <meta content="" name="description">
    <meta content="" name="author">
    <link href="ico/favicon.ico" rel="shortcut icon">

    <title>College Data, Education Board</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/board/resources/template/css/bootstrap.css">
    <!-- Bootstrap theme -->
    <!--  <link rel="stylesheet" href="css/bootstrap-theme.min.css"> -->

    <!-- Custom styles for this template -->

    <link rel="stylesheet" href="/board/resources/template/css/login.css">
    <link rel="stylesheet" href="/board/resources/template/css/theme.css">
    <link rel="stylesheet" href="/board/resources/template/css/dashboard.css">
    <link rel="stylesheet" href="/board/resources/template/css/style.css">
    <link rel="stylesheet" href="/board/resources/template/css/dripicon.css">
    <link rel="stylesheet" href="/board/resources/template/css/typicons.css" />
    <link rel="stylesheet" href="/board/resources/template/css/font-awesome.css" />
    <link rel="stylesheet" href="/board/resources/template/css/responsive.css">
    <link rel="stylesheet" href="/board/resources/template/js/tip/tooltipster.css">
    <link rel="stylesheet" type="text/css" href="/board/resources/template/js/vegas/jquery.vegas.css" />
    <link rel="stylesheet" type="text/css" href="/board/resources/template/js/number-progress-bar/number-pb.css">
    <!-- pace loader -->
    <script src="/board/resources/template/js/pace/pace.js"></script>
    <link href="/board/resources/template/js/pace/themes/orange/pace-theme-flash.css" rel="stylesheet" />
    

</head>

<body>

    <div id="preloader">
        <div id="status">&nbsp;</div>
    </div>
<br/><br/><br/>
    <div class="container-fluid ">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <!-- Comtainer -->
                <div class="paper-wrap bevel tlbr">
                    <div id="paper-top">
                        <div class="row">
                            <div class="col-lg-12 no-pad">
                                <!--     -->
                                <a class="navbar-brand logo-text" href="#">Education Board</a>
                            </div>
                        </div>
                    </div>
                     
                    <!-- CONTENT -->
                    <div style="min-height:400px;" class="wrap-fluid" id="paper-bg">
<br/><br/><br/>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="account-box">

                                    <form role="form" action="./college/checkCollegeValidity" method="post">
                                        <div class="form-group">
                                            <a href="#" class="pull-right label-forgot"><!-- Forgot email? --></a>
                                            <label>EIIN</label>
                                            <input type="text" name="user.userid" id="username" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <!--<a href="#" class="pull-right label-forgot"> Forgot password? </a>-->
                                            <label>Password</label>
                                            <input type="password" name="user.password" id="password" class="form-control">
                                        </div>
                                        <div class="checkbox pull-left">
                                            <!--<label><input type="checkbox">Remember me</label>-->
                                        </div>
                                        <button class="btn btn btn-primary pull-right" type="submit">
                                            Log In
                                        </button>
                                    </form>
                                    <a class="forgotLnk" href="index.html"></a>
                                   
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- / FOOTER -->
                <!-- Container -->
            </div>
        </div>
    </div>

    <div class="slide-out-div">
        <a class="handle" href="http://link-for-non-js-users"></a>
        <ul class="theme-bg">
            <li>
                <div id="button-bg"></div>
            </li>
            <li>
                <div id="button-bg2"></div>
            </li>
            <li>
                <div id="button-bg3"></div>
            </li>
            <li>
                <div id="button-bg4"></div>
            </li>
            <li>
                <div id="button-bg5"></div>
            </li>
            <li>
                <div id="button-bg6"></div>
            </li>
    
        </ul>
    </div>
    <!-- 
    ================================================== -->
    <!-- Main jQuery Plugins -->
    <script type='text/javascript' src="/board/resources/template/js/jquery.js"></script>
   <script type='text/javascript' src='/board/resources/template/js/vegas/jquery.vegas.js'></script>
    <script type='text/javascript' src='/board/resources/template/js/image-background.js'></script>
    <script type="text/javascript" src="/board/resources/template/js/jquery.tabSlideOut.v1.3.js"></script>
    <script type="text/javascript" src="/board/resources/template/js/bg-changer.js"></script>

    <script type="text/javascript">
    $(function() {
        $("#button-bg").click(function() {
        $("body").css({
            "background": "url('img/wg_blurred_backgrounds_11.jpg')no-repeat center center fixed"
        });
    });

    $("#button-bg2").click(function() {
        $("body").css({
            "background": "url('img/wg_blurred_backgrounds_5.jpg')no-repeat center center fixed"
        });
    });


    $("#button-bg3").click(function() {
        $("body").css({
            "background": "url('img/wg_blurred_backgrounds_9.jpg')no-repeat center center fixed"
        });
    });

    $("#button-bg4").click(function() {
        $("body").css({
            "background": "url('img/wg_blurred_backgrounds_19.jpg')no-repeat center center fixed"
        });
    });


    $("#button-bg5").click(function() {
        $("body").css({
            "background": "url('img/wg_blurred_backgrounds_3.jpg')no-repeat center center fixed"
        });

    });

    $("#button-bg6").click(function() {
        $("body").css({
            "background": "url('img/wg_blurred_backgrounds_6.jpg')no-repeat center center fixed"
        });

    });

        /**
         * Background Changer end
         */
    });
    </script>



</body>

</html>
