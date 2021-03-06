<!--　購入履歴編集画面
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@page import="rl.knockout.taiei.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <title>購入履歴編集</title>
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
					            <div class="panel-title">購入履歴編集</div>
					          
					            <div class="panel-options">
					              <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
					              <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
					            </div>
					        </div>
			  				<div class="panel-body">
			  					<form:form modelAttribute="history" action="historyEdit" method="post" role="form" accept-charset="UTF-8">
			  						<label>履歴編集</label>
			  						<div class="form-group">
										<form:label path="order_id">注文ID</form:label>
										<form:input path="order_id" type="text" class="form-control" value="${history.getOrder_id()}" placeholder="Enter Order ID" readonly="true"/>
										<form:errors path="order_id" />
									</div>
									<div class="form-group">
										<form:label path="login_name">ユーザー名</form:label>
										<form:input path="login_name" type="text" class="form-control" value="${history.getLogin_name()}" placeholder="Enter Login name" readonly="true" />
										<form:errors path="login_name" />
									</div>
									<div class="form-group">
										<form:label path="acc_id">ログインID</form:label>
										<form:input path="acc_id" type="text" class="form-control" value="${history.getAcc_id()}" placeholder="Enter Account ID" readonly="true"/>
										<form:errors path="acc_id" />
									</div>
									<div class="form-group">
										<form:label path="whole_amount">購入総額</form:label>
										<form:input path="whole_amount" type="text" class="form-control" value="${history.getWhole_amount()}" placeholder="Enter Whole Amount" />
										<form:errors path="whole_amount" />
									</div>
									<div class="form-group">
										<form:label path="tax">税</form:label>
										<form:input path="tax" type="text" class="form-control" value="${history.getTax()}" placeholder="Enter Tax" />
										<form:errors path="tax" />
									</div>
									<div class="form-group">
										<form:label path="order_date">注文日時</form:label>
										<form:input path="order_date" type="date" class="form-control" value="${history.getOrder_date().substring(0,10)}" placeholder="Enter Order Date" />
										<form:errors path="order_date" />
									</div>
									<div class="form-group">
										<form:label path="limit_date">支払い期限</form:label>
										<form:input path="limit_date" type="date" class="form-control" value="${history.getLimit_date().substring(0,10)}" placeholder="Enter Limit Date" />
										<form:errors path="limit_date" />
									</div>
									<div class="form-group">
										<form:label path="confirm_date">支払い日時</form:label>
										<form:input path="confirm_date" type="date" class="form-control" value="${history.getConfirm_date().substring(0,10)}" placeholder="Enter Confirm Date" />
										<form:errors path="confirm_date" />
									</div>
									<div class="form-group">
										<form:label path="order_status">支払い状況</form:label>
										<form:input path="order_status" type="text" class="form-control" value="${history.getOrder_status()}" placeholder="Enter Order Status" />
										<form:errors path="order_status" />
									</div>
									
								<c:forEach var="key" items="${history.getOrderDetail()}" varStatus="varStatus"><%//varStatus.indexでループカウンタをとれる %>
									<div class="form-group">
										<form:label path="orderDetail[${varStatus.index}].voucher_id">明細ID</form:label>
										<form:input path="orderDetail[${varStatus.index}].voucher_id" type="text" class="form-control" value="${key.getVoucher_id()}" placeholder="Enter Voucher ID" readonly="true"/>
										<form:errors path="orderDetail[${varStatus.index}].voucher_id" />
									</div>
									<div class="form-group">
										<form:label path="orderDetail[${varStatus.index}].product_id">商品ID</form:label>
										<form:input path="orderDetail[${varStatus.index}].product_id" type="text" class="form-control" value="${key.getProduct_id()}" placeholder="Enter Product ID" />
										<form:errors path="orderDetail[${varStatus.index}].product_id" />
									</div>
									<div class="form-group">
										<form:label path="orderDetail[${varStatus.index}].stock">個数</form:label>
										<form:input path="orderDetail[${varStatus.index}].stock" type="text" class="form-control" value="${key.getStock()}" placeholder="Enter Stock" />
										<form:errors path="orderDetail[${varStatus.index}].stock" />
									</div>
									<div class="form-group">
										<form:label path="orderDetail[${varStatus.index}].amount">単価</form:label>
										<form:input path="orderDetail[${varStatus.index}].amount" type="text" class="form-control" value="${key.getAmount()}" placeholder="Enter Amount" />
										<form:errors path="orderDetail[${varStatus.index}].amount" />
									</div>
									<div class="form-group">
										<form:label path="orderDetail[${varStatus.index}].product_name">商品名</form:label>
										<form:input path="orderDetail[${varStatus.index}].product_name" type="text" class="form-control" value="${key.getProduct_name()}" placeholder="Enter Product Name" />
										<form:errors path="orderDetail[${varStatus.index}].product_name" />
									</div>
									<div class="form-group">
										<form:label path="orderDetail[${varStatus.index}].product_price">商品単価</form:label>
										<form:input path="orderDetail[${varStatus.index}].product_price" type="text" class="form-control" value="${key.getProduct_price()}" placeholder="Enter Product Price" />
										<form:errors path="orderDetail[${varStatus.index}].product_price" />
									</div>
								</c:forEach>
								
									<form:button name="confirm" class="btn btn-primary signup">編集を反映</form:button>
								</form:form>
								
								<p>削除はやりなおせません</p>
								<form action="deleteHistory" method="post"><button type="submit">この購入履歴を削除</button></form>
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