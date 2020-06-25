<!-- メニュー画面
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%//EL式 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <title>メニュー</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta http-equiv="Content-Style-Type" content="text/css">
    <meta http-equiv="Content-Script-Type" content="text/javascript">
    <meta http-equiv="content-language" content="ja">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- jQuery UI -->
    <link href="https://code.jquery.com/ui/1.10.3/themes/redmond/jquery-ui.css" rel="stylesheet" media="screen">

    <!-- Bootstrap -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- styles -->
    <link href="resources/css/styles.css" rel="stylesheet">

    <link href="resources/vendors/fontawesome-free-5.13.1-web/css/fontawesome.min.css" rel="stylesheet">
    <link href="resources/vendors/form-helpers/css/bootstrap-formhelpers.min.css" rel="stylesheet">
    <link href="resources/vendors/select/bootstrap-select.min.css" rel="stylesheet">
    <link href="resources/vendors/tags/css/bootstrap-tags.css" rel="stylesheet">

    <link href="resources/css/forms.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  	<div class="header">
	     <div class="container">
	        <div class="row">
	           <div class="col-md-5">
	              <div class="logo">
	                 <h1><a href="menu">Bootstrap Admin Theme</a></h1>
	              </div>
	           </div>
	           <div class="col-md-5">
	              <div class="row">
	                <div class="col-lg-12">
	                  <!--<div class="input-group form">
	                       <input type="text" class="form-control" placeholder="Search...">
	                       <span class="input-group-btn">
	                         <button class="btn btn-primary" type="button">Search</button>
	                       </span>
	                  </div>-->
	                </div>
	              </div>
	           </div>
	           <div class="col-md-2">
	              <div class="navbar navbar-inverse" role="banner">
	                  <nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
	                    <ul class="nav navbar-nav">
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">スタッフ情報<b class="caret"></b></a>
	                        <ul class="dropdown-menu animated fadeInUp">
	                          <li><a href="login">ログアウト</a></li>
	                        </ul>
	                      </li>
	                    </ul>
	                  </nav>
	              </div>
	           </div>
	        </div>
	     </div>
	</div>

    <div class="page-content">
    	<div class="row">
		  <div class="col-md-2">
		  	<div class="sidebar content-box" style="display: block;">
                <ul class="nav">
                    <li class="current"><a href="menu"><i class="glyphicon glyphicon-home"></i> メニュー＆管理者ログイン</a></li>
                    <li><a href="historySearch"><i class="glyphicon glyphicon-pencil"></i>購入履歴検索</a></li>
                    <li class="submenu">
                         <a href="#">
                            <i class="glyphicon glyphicon-lock"></i>管理者専用
                            <span class="caret pull-right"></span>
                         </a>
                         <ul>
                         	<li><a href="administratorMenu">管理者メニュー</a></li>
                            <li><a href="staffManagement">スタッフ管理</a></li>
                            <li><a href="staffRegistration">スタッフ登録</a></li>
                        </ul>
                    </li>
                </ul>
             </div>
		  </div>
		  
		  <div class="col-md-10">
	  			<div class="row">  				
	  				<div class="col-md-6">
	  					<div class="content-box-large">
			  				<div class="panel-heading">
					            <div class="panel-title">メニュー</div>
					          
					            <div class="panel-options">
					              <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
					              <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
					            </div>
					        </div>
			  				<div class="panel-body">
			  					<label>履歴検索ページへ</label>
			  					<form action="historySearch" method="get">
		        					<button type="submit" class="btn btn-primary signup">履歴検索ページへ</button>
			  					</form>
			  					<label>管理者ログイン</label>
			  					<form:form modelAttribute="loginForm" action="administratorMenu" method="post">
									<div class="form-group">
	        							<form:label path="staff_id" >ログインID</form:label>
	        							<form:input path="staff_id" type="number" value="0" class="form-control"/>
	        							<form:errors path="staff_id" />
	        						</div>
	        						<div class="form-group">
	        							<form:label path="staff_pw">パスワード</form:label>
			        					<form:input path="staff_pw" type="password" class="form-control" placeholder="Enter Password"/>
			        					<form:errors path="staff_pw" />
			        				</div>
									<form:button name="confirm" class="btn btn-primary signup">ログイン</form:button>
								</form:form>
			  				</div>
			  			</div>
	  				</div>
	  			</div>
	  		<!--  Page content -->
			</div>
		</div>
    </div>

    <footer>
         <div class="container">
         
            <div class="copy text-center">
               Copyright 2014 <a href='https://github.com/VinceG/Bootstrap-Admin-Theme-3'>Bootstrap Admin Theme Github</a>
            </div>
            
         </div>
      </footer>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- jQuery UI -->
    <script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>

    <script src="resources/vendors/form-helpers/js/bootstrap-formhelpers.min.js"></script>

    <script src="resources/vendors/select/bootstrap-select.min.js"></script>

    <script src="resources/vendors/tags/js/bootstrap-tags.min.js"></script>

    <script src="resources/vendors/mask/jquery.maskedinput.min.js"></script>

    <script src="resources/vendors/moment/moment.min.js"></script>

    <script src="resources/vendors/wizard/jquery.bootstrap.wizard.min.js"></script>
    
    <link href="resources/vendors/x-editable/bootstrap-editable.css" rel="stylesheet"/>
	<script src="resources/vendors/x-editable/bootstrap-editable.min.js"></script>

    <script src="resources/js/custom.js"></script>
    <script src="resources/js/forms.js"></script>
  </body>
</html>