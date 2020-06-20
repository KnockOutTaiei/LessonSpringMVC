<!-- ログイン画面
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta http-equiv="Content-Style-Type" content="text/css">
    <meta http-equiv="Content-Script-Type" content="text/javascript">
    <meta http-equiv="content-language" content="ja">
    
	<title>ログイン</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- styles -->
    <link href="resources/css/styles.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]--> 
  </head>
  <body class="login-bg">
	<div class="header">
		<div class="container">
	        <div class="row">
	           <div class="col-md-12">
	              <div class="logo">
	                 <h1><a>Bootstrap Admin Theme</a></h1>
	              </div>
	           </div>
	        </div>
		</div>
	</div>

	<div class="page-content container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-wrapper">
			        <div class="box">
			            <div class="content-wrap">
			                <h6>スタッフログイン</h6>
			                <form:form modelAttribute="loginForm" action="menu" method="post">
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
		</div>
	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="resources/js/custom.js"></script>
  </body>
</html>