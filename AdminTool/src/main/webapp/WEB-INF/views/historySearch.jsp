<!--購入履歴検索画面
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="rl.knockout.taiei.model.*" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
  <head>
    <title>購入履歴検索</title>
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
					            <div class="panel-title">購入履歴検索</div>
					          
					            <div class="panel-options">
					              <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
					              <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
					            </div>
					        </div>
			  				<div class="panel-body">
			  					<form:form modelAttribute="historySearchForm" action="historySearch" method="post">
			  						<div class="form-group">
										<form:label path="acc_id">ログインID</form:label>
										<form:input path="acc_id" type="text" class="form-control" placeholder="Enter Login ID" />
										<form:errors path="acc_id" />
									</div>
									<div class="form-group">
										<form:label path="login_name">ログイン名</form:label>
										<form:input path="login_name" type="text" class="form-control" placeholder="Enter login_name" />
										<form:errors path="login_name" />
									</div>
									<div class="form-group">
										<form:label path="order_status">支払い状況</form:label>
										<form:input path="order_status" type="text" class="form-control" placeholder="Enter order_status" />
										<form:errors path="order_status" />
									</div>
									<label>時間検索</label>	　　
									<div class="form-group">
										<form:label path="dateKindString">日時の種類</form:label>
										<form:select path="dateKindString"><form:option value="none">なし</form:option><form:option value="order_date">注文日時</form:option><form:option value="limit_date">支払い期限</form:option><form:option value="confirm_date">支払い日時</form:option></form:select>
										<form:errors path="dateKindString" />
									</div>
									<div>
										<form:label path="beginDate">開始日時</form:label>
										<form:input path="beginDate" type="date"/>
										<form:errors path="beginDate" />
									</div>
									<div>
										<form:label path="endDate">終了日時</form:label>
										<form:input path="endDate" type="date"/>
										<form:errors path="endDate" />
									</div>
									<div class="form-group">
										<form:label path="nowPage">ページ数</form:label>
										<form:input path="nowPage" type="number" value="${historySearchForm.getNowPage()}"></form:input>
										<form:errors path="nowPage" />
									</div>
									<!--<form:button path="nowPage" value="${historySearchForm.getNowPage()+1}" name="confirm" class="btn btn-primary signup">次のページへ</form:button>
									<form:button path="nowPage" value="${historySearchForm.getNowPage()-1}" name="confirm" class="btn btn-primary signup">前のページへ</form:button>-->
									<form:button name="confirm" class="btn btn-primary signup">検索</form:button>
								</form:form>
								<div class="context-box-large">
					  				<c:forEach var="key" items="${historySummaries}">
					  					<div>ログインID：<c:out value="${key.getAcc_id()}" /></div>
					  					<div>ユーザー名：<c:out value="${key.getLogin_name()}" /></div>
					  					<div>支払い状況：<c:out value="${key.getOrder_status()}" /></div>
					  					<div>注文日時：<c:out value="${key.getOrder_date()}" /></div>
					  					<div>支払い期限：<c:out value="${key.getLimit_date()}" /></div>
					  					<div>支払い日時：<c:out value="${key.getConfirm_date()}" /></div>
					  					<form action="history" method="get"><input type="hidden" name="order_id" value="${key.getOrder_id()}"><button type="submit" class="btn btn-info">履歴詳細へ</button></form>
		                    		</c:forEach>
		                    	</div>
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