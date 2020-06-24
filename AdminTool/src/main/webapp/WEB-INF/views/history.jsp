<!--　購入履歴閲覧画面
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@page import="rl.knockout.taiei.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <title>購入履歴閲覧</title>
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
					            <div class="panel-title">購入履歴閲覧</div>
					          
					            <div class="panel-options">
					              <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
					              <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
					            </div>
					        </div>
			  				<div class="panel-body">
			  					<form:form modelAttribute="history" action="historyEdit" method="get" role="form">
			  						<label>履歴閲覧</label>
			  						<div class="form-group">
										<form:label path="order_id">注文ID</form:label>
										<form:input path="order_id" type="text" class="form-control" value="${history.getOrder_id()}" placeholder="Enter Order ID" readonly="true" /><%//html4.01以降では readonly="readonly"なのにタグを使うときreadonly="true"でうまくいく。なんでそんな仕様にした！言え！！ %>
										<form:errors path="order_id" />
									</div>
									<div class="form-group">
										<form:label path="login_name">ユーザー名</form:label>
										<form:input path="login_name" type="text" class="form-control" value="${history.getLogin_name()}" placeholder="Enter Login name" readonly="true" />
										<form:errors path="login_name" />
									</div>
									<div class="form-group">
										<form:label path="acc_id">ログインID</form:label>
										<form:input path="acc_id" type="text" class="form-control" value="${history.getAcc_id()}" placeholder="Enter Account ID" readonly="true" />
										<form:errors path="acc_id" />
									</div>
									<div class="form-group">
										<form:label path="whole_amount">購入総額</form:label>
										<form:input path="whole_amount" type="text" class="form-control" value="${history.getWhole_amount()}" placeholder="Enter Whole Amount" readonly="true" />
										<form:errors path="whole_amount" />
									</div>
									<div class="form-group">
										<form:label path="tax">税</form:label>
										<form:input path="tax" type="text" class="form-control" value="${history.getTax()}" placeholder="Enter Tax" readonly="true" />
										<form:errors path="tax" />
									</div>
									<div class="form-group">
										<form:label path="order_date">注文日時</form:label>
										<form:input path="order_date" type="date" class="form-control" value="${history.getOrder_date().substring(0,10)}" placeholder="Enter Order Date" readonly="true" />
										<form:errors path="order_date" />
									</div>
									<div class="form-group">
										<form:label path="limit_date">支払い期限</form:label>
										<form:input path="limit_date" type="date" class="form-control" value="${history.getLimit_date().substring(0,10)}" placeholder="Enter Limit Date" readonly="true" />
										<form:errors path="limit_date" />
									</div>
									<div class="form-group">
										<form:label path="confirm_date">支払い日時</form:label>
										<form:input path="confirm_date" type="date" class="form-control" value="${history.getConfirm_date().substring(0,10)}" placeholder="Enter Confirm Date" readonly="true" />
										<form:errors path="confirm_date" />
									</div>
									<div class="form-group">
										<form:label path="order_status">支払い状況</form:label>
										<form:input path="order_status" type="text" class="form-control" value="${history.getOrder_status()}" placeholder="Enter Order Status" readonly="true" />
										<form:errors path="order_status" />
									</div>
									<c:forEach var="key" items="${history.getOrderDetail()}">
									<div>
									  <div>明細ID：<c:out value="${key.getVoucher_id()}"/></div>
									  <div>商品ID：<c:out value="${key.getProduct_id()}"/></div>
									  <div>個数：<c:out value="${key.getStock()}"/></div>
									  <div>単価：<c:out value="${key.getAmount()}"/></div>
									  <div>商品名：<c:out value="${key.getProduct_name()}"/></div>
									  <div>商品単価：<c:out value="${key.getProduct_price()}"/></div>
									</div>
									</c:forEach>
								</form:form>
								<form action="historyEdit" method="get"><button type="submit" class="btn btn-primary signup">編集画面へ</button></form>
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