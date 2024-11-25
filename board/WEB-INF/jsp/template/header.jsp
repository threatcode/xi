<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <!--   <meta content="IE=edge" http-equiv="X-UA-Compatible"> -->
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <meta content="" name="description">
    <meta content="" name="author">
    <link href="ico/favicon.ico" rel="shortcut icon">

    <title>xi class admission system</title>
	<!-- Popup modal related -->
	<script type='text/javascript' src="/board/resources/js/jquery.min.js"></script>

  	<!-- Popup modal related -->
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/board/resources/template/css/bootstrap.css">
    <!-- Bootstrap theme -->
    <!--  <link rel="stylesheet" href="css/bootstrap-theme.min.css"> -->

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/board/resources/template/css/theme.css">
	<link rel="stylesheet" href="/board/resources/template/css/dashboard.css">
    <link rel="stylesheet" href="/board/resources/template/css/style.css">
    <link rel="stylesheet" href="/board/resources/template/css/dripicon.css">
    <link rel="stylesheet" href="/board/resources/template/css/typicons.css" />
    <link rel="stylesheet" href="/board/resources/template/css/font-awesome.css" />
    <link rel="stylesheet" href="/board/resources/template/css/responsive.css">
    <link rel="stylesheet" href="/board/resources/template/js/tip/tooltipster.css">
    <link rel="stylesheet" type="text/css" href="/board/resources/template/js/vegas/jquery.vegas.css" />
    
    <!-- Data Tables  -->
    <link href="/board/resources/template/js/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
    
    <link href="/board/resources/template/js/footable/css/footable.core.css?v=2-0-1" rel="stylesheet" type="text/css" />
    <link href="/board/resources/template/js/footable/css/footable.standalone.css" rel="stylesheet" type="text/css" />
    <link href="/board/resources/template/js/footable/css/footable-demos.css" rel="stylesheet" type="text/css" /> 
    
    <!-- pace loader -->
    <script src="/board/resources/template/js/pace/pace.js"></script>
    
    <link href="/board/resources/template/js/pace/themes/orange/pace-theme-flash.css" rel="stylesheet" />
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


</head>

			
			
					
<body role="document">

    <div id="preloader">
        <div id="status">&nbsp;</div>
    </div>

    <!-- TOPNAV -->

    <div class="row">
        <div class="col-lg-3">
            <ul class="nav navbar-nav navbar-left list-unstyled list-inline text-amber date-list">
                <li><i class="fontello-th text-amber"></i>
                </li>
                <li id="Date"></li>
            </ul>
            <ul class="nav navbar-nav navbar-left list-unstyled list-inline text-amber date-list">
                <li><i class="fontello-stopwatch text-amber"></i>
                </li>
                <li id="hours"></li>
                <li class="point">:</li>
                <li id="min"></li>
                <li class="point">:</li>
                <li id="sec"></li>
            </ul>


        </div>
        <div class="col-lg-6">
            <div style="margin-bottom:0;" class="alert text-white ">
                <button data-dismiss="alert" class="close" type="button">×</button>
                <span class="entypo-info-circled"></span>
                <strong>Smart Admission System</strong>
            </div>
        </div>

        <div class="col-lg-3">
            <ul class="nav navbar-nav navbar-right">
<!--                 <li>
                    <a data-toggle="dropdown" class="dropdown-toggle text-white" href="#">
                        Setting <b class="caret"></b>
                    </a>
                    <ul style="margin:25px 15px 0 0;" role="menu" class="dropdown-setting dropdown-menu bg-amber">
                        <li>
                            <a href="#">
                                <span class="entypo-user"></span>&nbsp;&nbsp;My Profile</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="entypo-vcard"></span>&nbsp;&nbsp;Account Setting</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="entypo-lifebuoy"></span>&nbsp;&nbsp;Help</a>
                        </li>

                        <li>
                            <a href="http://themeforest.net/item/apricot-navigation-admin-dashboard-template/7664475?WT.ac=category_item&amp;WT.z_author=themesmile">
                                <span class="entypo-basket"></span>&nbsp;&nbsp; Purchase</a>
                        </li>
                    </ul>
                </li>
 -->
                <li>
                    <a data-toggle="dropdown" class="dropdown-toggle text-white" href="#">
                        <i class="icon-gear"></i>&nbsp;Setting <b class="caret"></b>
                    </a>
                    <ul style="margin:25px 15px 0 0;" role="menu" class="dropdown-setting dropdown-menu bg-amber">
                        <li>
                            <a href="#">
                                <span class="entypo-user"></span>&nbsp;&nbsp;My Profile</a>
                        </li>
                        <li>
                            <a href="logout">
                                <span class="entypo-vcard"></span>&nbsp;&nbsp;Log Out</a>
                        </li>

                    </ul>
                </li>

            </ul>
        </div>

    </div>
    <!-- END OF TOPNAV -->
    <!-- Comtainer -->
    <div class="container-fluid paper-wrap bevel tlbr">
        <div id="paper-top">
            <div class="row">
                <div class="col-sm-3 no-pad" style="width: 100%">

                    <a class="navbar-brand logo-text" href="#">
                    <c:if test="${sessionScope.role=='college'}">
                          ${sessionScope.user.college_name}(${sessionScope.user.eiin}), Mobile No. ${sessionScope.user.collegeMobile}                          
                    </c:if>
                    <c:if test="${sessionScope.role=='board'}">
                      		${sessionScope.user.boardName} 
                    </c:if>
					<c:if test="${sessionScope.role=='admin'}">
                      		${sessionScope.user.college_name}
                    </c:if>
					</a>

                    <!-- <ul class="list-unstyled list-inline noft-btn">
                        <li data-toggle="tooltip" data-placement="bottom" title="Profile"><i class=" icon-user"></i>
                        </li>

                        <li data-toggle="tooltip" data-placement="bottom" title="Log Out"> <a href="login.html" class="text-white"><i class="icon-upload"></i></a>
                        </li> 

                    </ul>-->
                </div>

<!--                 <div class="col-sm-6 no-pad">
                    <ul style="margin-top:8px;" class="nav navbar-nav navbar-left list-unstyled list-inline text-gray date-list news-list">
                        <li><i class="fontello-doc-text text-gray"></i>
                        </li>
                        <li>
                            <ul class="list-unstyled top-newsticker text-gray news-list">
                                <li><i class="fontello-cloud-flash-inv text-gray"></i>&nbsp;&nbsp;
                                    <strong>Yogyakarta,</strong>Achmad Dhani Partly Cloudy Feels Like &nbsp;<b>22 °C</b>
                                </li>
                                <li><i class="fontello-cloud-sun-inv text-gray"></i>&nbsp;&nbsp;
                                    <strong>Bandung,</strong>Jln Sudirman, Sunny Feels Like &nbsp;<b>31 °C</b>
                                </li>
                                <li><i class="fontello-rain-inv text-gray"></i>&nbsp;&nbsp;
                                    <strong>Jakarta,</strong>Tomang, Rain Like &nbsp;<b>19 °C</b>
                                </li>
                            </ul>
                        </li>
                    </ul>

                </div>
 -->


<!--                 <div class="col-sm-3 no-pad">
                    menu right
                    <div class="navbar-right">
                        <ul class="nav navbar-nav margin-left">
                            Messages: style can be found in dropdown.less
                            <li class="dropdown messages-menu">
                                <div class="drop-btn dropdown-toggle bg-white" data-toggle="dropdown">
                                    <i class="fa  fa-envelope text-navy"></i>
                                    <span class="label label-success label-drop">4</span>
                                </div>
                                <ul class="dropdown-menu drop-msg ">
                                    <li class="header bg-green">
                                        You have 4 messages</li>

                                    <li>
                                        inner menu: contains the actual data
                                        <ul class="menu bg-white">
                                            <li>
                                                start message
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="http://api.randomuser.me/portraits/thumb/men/37.jpg" class="img-circle" alt="User Image" />
                                                    </div>
                                                    <h4>
                                                        Developer
                                                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                                    </h4>
                                                    <p>Bug fixed level 90%</p>
                                                </a>
                                            </li>
                                            end message
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="http://api.randomuser.me/portraits/thumb/women/36.jpg" class="img-circle" alt="user image" />
                                                    </div>
                                                    <h4>
                                                        Aplication Support

                                                    </h4>
                                                    <p>There is some bug in your last submit</p>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="http://api.randomuser.me/portraits/thumb/men/35.jpg" class="img-circle" alt="user image" />
                                                    </div>
                                                    <h4>
                                                        Lead Developers

                                                    </h4>
                                                    <p>Please check again your submit</p>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="http://api.randomuser.me/portraits/thumb/women/34.jpg" class="img-circle" alt="user image" />
                                                    </div>
                                                    <h4>
                                                        Web Designer

                                                    </h4>
                                                    <p>Art has done</p>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="http://api.randomuser.me/portraits/thumb/men/33.jpg" class="img-circle" alt="user image" />
                                                    </div>
                                                    <h4>
                                                        General Manager

                                                    </h4>
                                                    <p>Employed newslatter</p>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>

                                    <li class="footer-green">
                                           <div class="btn btn-xs bg-opacity-white-btn  fontello-arrows-cw"></div>
                                        <div class="btn btn-xs bg-opacity-white-btn fontello-trash"></div>
                                        <div class="btn btn-xs bg-opacity-white-btn fontello-eye-outline"></div>
                                    </li>
                                </ul>
                            </li>
                            Notifications: style can be found in dropdown.less
                            <li class="dropdown notifications-menu">
                                <div class="drop-btn dropdown-toggle bg-white" data-toggle="dropdown">
                                    <i class="fa  fa-exclamation-triangle text-navy"></i>
                                    <span class="label bg-aqua label-drop">7</span>
                                </div>
                                <ul class="dropdown-menu drop-noft">

                                    <li class="header bg-aqua">
                                        You have 10 notifications</li>

                                    <li>
                                        inner menu: contains the actual data
                                        <ul class="menu bg-white">
                                            <li>
                                                <a href="#">
                                                    <i class="fa icon-user"></i> New developer registered
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="fa icon-cloud"></i> 2 item commit
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="fa icon-download"></i> 3 members joined
                                                </a>
                                            </li>

                                            <li>
                                                <a href="#">
                                                    <i class="fa icon-tag"></i> 22 sales made
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="fa icon-document"></i> New task from manager
                                                </a>
                                            </li>
                                        </ul>
                                    </li>

                                    <li class="footer-blue">

                                    </li>
                                </ul>
                            </li>
                            Tasks: style can be found in dropdown.less
                            <li class="dropdown tasks-menu">
                                <div class="drop-btn bg-white dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-briefcase text-navy"></i>
                                    <span class="label bg-red label-drop">9</span>
                                </div>
                                <ul class="dropdown-menu drop-task">

                                    <li class="header bg-red">
                                        <span></span>You have 9 tasks</li>

                                    <li>
                                        inner menu: contains the actual data
                                        <ul class="menu bg-white">
                                            <li>
                                                Task item
                                                <div class="task-list-item">
                                                    <h2>Wed, 25 Mar 2014
                                                        <span>9:32
                                                            <small>PM</small>
                                                        </span>
                                                    </h2>
                                                    <h1>Finished task Testing.</h1>
                                                    <p>Lorem ipsum dollor si amet amet jabang bayi</p>
                                                </div>
                                            </li>
                                            end task item
                                            <li>
                                                Task item

                                                <div class="task-list-item">
                                                    <h2>Thu, 23 Mar 2014
                                                        <span>7:54
                                                            <small>PM</small>
                                                        </span>
                                                    </h2>
                                                    <h1>Creat the documentation</h1>
                                                    <p>Lorem ipsum dollor si amet amet jabang bayi</p>
                                                </div>

                                            </li>
                                            end task item
                                            <li>
                                                Task item
                                                <div class="task-list-item">
                                                    <h2>Wed, 21 Mar 2014
                                                        <span>12:43
                                                            <small>PM</small>
                                                        </span>
                                                    </h2>
                                                    <h1>Repository you file now!</h1>
                                                    <p>Lorem ipsum dollor si amet amet jabang bayi</p>
                                                </div>
                                            </li>
                                            end task item
                                            <li>
                                                Task item
                                                <div class="task-list-item">
                                                    <h2>Fri, 20 Mar 2014
                                                        <span>8:00
                                                            <small>PM</small>
                                                        </span>
                                                    </h2>
                                                    <h1>Fill the job description</h1>
                                                    <p>Lorem ipsum dollor si amet amet jabang bayi</p>
                                                </div>
                                            </li>
                                            end task item
                                        </ul>
                                    </li>

                                    <li class="footer-red">

                                    </li>
                                </ul>
                            </li>


                        </ul>
                    </div>
                </div> -->
                <!-- end of menu right -->
            </div>
        </div>