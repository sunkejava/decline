<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>搜索</title>
	<meta http-equiv = "X-UA-Compatible" content = "IE=edge">
		<script type="text/javascript">
		function show(){
         var random_bg=parseInt(Math.random()*66);
<!-- 		 alert(random_bg); -->
         var bg='url(img/bg-'+random_bg+'.jpg)';
         $("body").css("background-image",bg);
		}
		function checkForm(){
			var q=document.getElementById("search-input");
			if(q.value==null || q.value==""){
				alert("骚年，请输入需要搜索的信息！");
				return false;
			}
			return true;
		}
		</script>
		<style type = "text/css">
			body{
				background-color:#333;
			}
			#showimg{
				left:0;
				top:0;
				position:absolute;
			}
			.logo{
				margin:-25px 18px 0 0;
				background-image:url(img/logo.png);
				width:370px;
				height:100px;
				float:left;
				top:0px;
				border-radius: 66px;
				-webkit-border-radius: 66px;
				-moz-border-radius: 66px;
			}
			form{
				float:left;
				padding:2px;
				border:1px solid #fd0;
				overflow: hidden;
				border-radius: 66px;
				-webkit-border-radius: 66px;
				-moz-border-radius: 66px;
			}
			.search-input-text{
				border:0;
				float:left;
				font-size:25px;
				height:44px;
				line-height:44px;
				outline:none;
				width:800px;
				background: rgba(255, 255, 255, 0.4); 
				padding-left:5px;
				border-radius: 66px;
				-webkit-border-radius: 66px;
				-moz-border-radius: 66px;
			}
			.search-input-button{
				border:0;
				cursor:pointer;
				cursor:hand;
				background-image:url(img/search45.png);
				width:45px;
				height:45px;
				float:left;
				border-radius: 66px;
				-webkit-border-radius: 66px;
				-moz-border-radius: 66px;
			}
			.search-box{
				position:absolute;
				margin:250px  0  0  200px;
				
			}
		</style>
</head>
<body onload="show()">
	<div class = "bg-div">
			<div class = "search-box">
			<div class = "logo"></div>
			<form class = "search-form" id="search-form" action="mains.jsp" method="get" target="_blank"  onsubmit="return checkForm()">
				<input type = "text" id = "search-input" class ="search-input-text" placeholder="输入要搜索内容" name="q" autocomplete="off"/>
				<input type = "submit" id = "search-button" class = "search-input-button" value="" />
			</form>
			</div>
		</div>
		<script src="js/jquery-1.10.2.min.js"></script>
</body>
</html>