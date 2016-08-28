<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>搜索结果：主播ID为:${param.q}</title>
	<meta http-equiv = "X-UA-Compatible" content = "IE=edge">
		<link rel="stylesheet" href="css/as2.css" type="text/css">
		<script src="js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript">
		function show(){
         var random_bg=parseInt(Math.random()*66);
<!-- 		 alert(random_bg); -->
         var bg='url(img/bg-'+random_bg+'.jpg)';
         $("body").css("background-image",bg,"width:","100%");
		showLeftTime();
		}
		function checkForm(){
			var q=document.getElementById("search-input");
			if(q.value==null || q.value==""){
				alert("骚年，请输入需要搜索的信息！");
				return false;
			}
			return true;
		}
		
		function loadData(q,start){
			$.getJSON("http://localhost:8080/Decline/indexn?jsoncallback=?",{q:q,start:start},function(result){
				if(result.length==0){
					alert("温馨提示：未搜索到结果，请换个ID！");
				}else{
					var tbody = $("#video-model").find("ul");
					var sumpage;
					$("#video-model").find("ul").children().remove();
					$.each(result,function(i,val){
						sumpage = val.maxpage;
							tbody.append(
								"<li class='video-user-item'><a class='video-user-box' href='./videoindex.html?name="+val.songname+"&url="
								+val.resurl+"&img="+val.snapshoturl+"' target='_blank' method='post'><div class='video-user-pic'><div class='video-pic'><div class='video-pic-inner'><div class='pic-default1'><img src='"
								+val.snapshoturl+"' alt=&quot&quot/></div></div><div class='op-time'>"
								+val.duration+"</div><div class='mask'></div><i class='icon-play'></i></div></div><div class='video-user-info'><p class='user-i-title'>"
								+val.songname+"</p><p class='user-video-op'><span class='op-like'><i class='icon-user-like'></i>"
								+val.likeCount+"</span><span class='aud-count'><i class='icon-eye'></i>"
								+val.watchCount+"</span></p></div></a></li>");
					});
					$("#cse2").remove();
					var inse = "";   
					//alert(sumpage);
					for(var i=0;i<sumpage;i++){
							inse = inse+"<li><a href='javascript:loadData(${param.q },"+(i+1)+")'>第"+(i+1)+"页</a></li>";
					
					}
					$("#paed").find("ul").children().remove();
					$(document).ready(function(){
						$("#paed").find("ul").append(inse);
					});
					$("#paed").show();
					
				}
			},"json");
		}
		
		loadData("${param.q }",1);
		function showLeftTime()
					{
					var Week = ['日','一','二','三','四','五','六']
					var now=new Date();
					var year=now.getFullYear();
					var month=now.getMonth()+1;
					var day=now.getDate();
					var weekday = now.getDay();
					var hours=now.getHours();
					var minutes=now.getMinutes();
					var seconds=now.getSeconds();
					var nowa;
					if(hours <= 3 ){
						nowa = "拂晓";
					}else if (hours <= 6){
						nowa = "黎明";
					}else if(hours <= 9){
						nowa = "清晨";
					}else if(hours <=12){
						nowa = "上午";
					}else if(hours  <= 15){
						nowa = "中午";
					}else if(hours  <= 18){
						nowa = "下午";
					}else if(hours  <= 21){
						nowa = "傍晚";
					}else{
						nowa = "深夜";
					}
					var varcharone = ""+year+"年"+month+"月"+day+"日";
					hours < 10 ? hours="0"+hours:hours=hours;
					minutes < 10 ? minutes="0"+minutes:minutes=minutes;
					seconds < 10 ? seconds="0"+seconds:seconds=seconds;
					var varchartwo =  "星期"+Week[weekday]+"--"+hours+":"+minutes+":"+seconds+"--"+nowa;
					document.getElementById("nianyueri").innerText = varcharone;
					document.getElementById("shijian").innerText = varchartwo;
					//一秒刷新一次显示时间
					//alert(varcharone);
					var timeID=setTimeout(showLeftTime,1000);
					}
		</script>
		<style type = "text/css">
			.logo{
				margin:2px 18px 0 0;
				background-image:url(img/logo2.png);
				width:185px;
				height:50px;
				float:left;
				top:0px;
				margin-left:10%;
				border-radius: 66px;
				-webkit-border-radius: 66px;
				-moz-border-radius: 66px;
			}
			body{
				background-attachment:fixed;
				background-repeat:no-repeat;
				background-size:cover;
				background-position: center center;
				-moz-background-size:cover;
				-webkit-background-size:cover;
			}
			form{
				margin:15px 18px 0 0;
				float:left;
				padding:2px;
				border:1px solid #fd0;
				overflow: hidden;
				width:425;
				border-radius: 66px;
				-webkit-border-radius: 66px;
				-moz-border-radius: 66px;
			}
			
			.search-input-text{
				border:0;
				float:left;
				font-size:18px;
				height:25px;
				line-height:25px;
				width:400px;
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
				background-image:url(img/search24-1.png);
				width:25px;
				height:25px;
				float:left;
				border-radius: 66px;
				-webkit-border-radius: 66px;
				-moz-border-radius: 66px;
			}
			.search-box{
				position:absolute;
				left:0;
				top:0;
				width: 100%;
				background: rgba(255, 223, 255, 0.4);
			}
			
			.search-input-button:hover{
				background-image:url(img/search24-2.png);
			}
			div.page-box{
				  width: 100%;
				  margin-left: auto;
				  margin-right: auto;
				  background: rgba(255, 223, 255, 0.4);
			}
			DIV.jogger { 
				width: 500px;
				margin-left: auto;
				margin-right: auto;
				position: relative;
				overflow: hidden;
				FONT-FAMILY: "Lucida Sans Unicode", "Lucida Grande", LucidaGrande, "Lucida Sans", Geneva, Verdana, sans-serif;
			}
			DIV.jogger A {
				float:left;
				position: relative;
				text-align: center;
				PADDING-RIGHT: 0.64em; 
				PADDING-LEFT: 0.64em; 
				PADDING-BOTTOM: 0.43em; 
				MARGIN: 2px; COLOR: #fff; 
				PADDING-TOP: 0.5em; 
				BACKGROUND-COLOR: #ee4e4e; 
				TEXT-DECORATION: none;
			}
			DIV.jogger A:hover {
				PADDING-RIGHT: 0.64em; PADDING-LEFT: 0.64em; PADDING-BOTTOM: 0.43em; MARGIN: 2px; COLOR: #fff; PADDING-TOP: 0.5em; BACKGROUND-COLOR: #de1818
			}
			DIV.jogger A:active {
				PADDING-RIGHT: 0.64em; PADDING-LEFT: 0.64em; PADDING-BOTTOM: 0.43em; MARGIN: 2px; COLOR: #fff; PADDING-TOP: 0.5em; BACKGROUND-COLOR: #de1818
			}
			DIV.jogger SPAN.current {
				PADDING-RIGHT: 0.64em; PADDING-LEFT: 0.64em; PADDING-BOTTOM: 0.43em; MARGIN: 2px; COLOR: #6d643c; PADDING-TOP: 0.5em; BACKGROUND-COLOR: #f6efcc
			}
			DIV.jogger SPAN.disabled {
				DISPLAY: none
			}
			
		</style>
</head>
<body onload="show()">
	<div class = "bg-div">
			<div class = "search-box">
			<div class = "logo"></div>
			<form class = "search-form" id="search-form" onsubmit="return checkForm()">
				<input type = "text" id = "search-input" class ="search-input-text" value = "${param.q }" placeholder="毒药" name="q" autocomplete="off"/>
				<input type = "submit" id = "search-button" class = "search-input-button" value=""/>
			</form>
			</div>
		</div>
	 <!-- 歌手介绍背景框 -->
        <div class="user-head">
            <div class="user-content">
                <div class="user-basic">
                    <!-- 头像 -->
					<div class="avatar"><img src="http://downhdlogo.yy.com/hdlogo/640640/640/640/86/1092868475/u1092868475UwlzMsy.jpeg?20160815145126" onerror="this.src='http://yyweb.yystatic.com/pc/images/default_portrait-8dde4521cf.jpg?20160815145126'" alt="" />
                        <div class="mask"></div>
                    </div>
					<!-- 简介 -->
                    <div class="motto">
                        少点套路，多点真诚</div>
                    <!-- 关注按钮 -->
					<div class="interact">
                        <a href="javascript:;" data-yy="1163847368" title="洛瑶" class="follow-btn">洛瑶</a>
                        </div>
                </div>
                <div class="user-info user-info-incomplete user-info-incomplete-other">
                    <!-- 分享 -->
					<div class="info-hd">
                        <h1 class="nick">主播信息：</h1>
                        <div class="share-wrapper"><a href="javascript:;" class="share-btn"><i></i>分享</a>
                            <div class="layout share-layout hidden"><i class="enterstage"></i><i class="tri"></i><i class="tri2"></i>
                                <div class="layout-inner bdsharebuttonbox">
                                    <a href="#" title="分享到新浪微博" data-cmd="tsina" data-type="sina" class="sina"></a>
                                    <a href="#" title="分享到QQ空间" data-cmd="qzone" data-type="zone" class="zone"></a>
                                    <a href="#" title="分享到QQ好友" data-cmd="sqq" data-type="qq" class="qq"></a>
                                    <a href="#" title="分享到百度贴吧" data-cmd="tieba" data-type="tieba" class="tieba"></a>
                                </div>
                            </div>
                        </div>
                    </div>
					<!-- 个人信息 -->
                <div class="info">
                        <ul>
                            <li>生日：
                                <span>
                                12月15日</span>
                            </li>
                            <li>关注：<a class="follow-link" href="http://www.yy.com/u/follow/1163847368#follow">95</a></li>
                            <li>所在地：
                                <span>
                                中国</span>
                            </li>
                            <li>粉丝：<a class="fans-link" href="http://www.yy.com/u/follow/1163847368#fans">197,994</a></li>
                            <li>直播间ID：
                                <span>
                                <a class="zhibo-link" target="_blank" href="http://www.yy.com/50511">50511<i class="icon-zhibo hidden"></i></a></span>
                            </li>
                            <li>上次直播时间：
                                <span>
                                2016年08月17日 17:09</span>
                            </li>
                        </ul>
                    </div>
                </div>
				<!-- 打卡信息 -->
               <div class="clickin-wrapper">
                        <a href="javascript:;" class="clickin-btn "><span class="click">收藏</span>
                            <p class="notice"><span id= "nianyueri" class="day">08月17日</span><span id="shijian" class="txt">今天已有<i class="nums">4847</i>位游客收藏</span></p>
                        </a>
               </div>
			</div>
        </div>
<div class="wrapper">
    <div class="works-tab-body">
      <ul class="cf">
			<!-- MV -->
        <li class="works-tc-item shenqu active">
            <div id = "video-model" class="shenqu-non-empty"><div class="col-list">
            		<div id="cse2"><div id="loading" style="text-align:center; font-size:15px; width:auto; height:60px; width:100%;"><img src="images/loading.gif" /> 正在加载数据，请稍后....请不要离开...</div></div>
                    <ul class="video-user-list">               
					</ul>
                  </div>
                  <div class="shenqu-foot">
                    <div id="paginator" class="paginator"> </div>
                  </div>
                </div>
              </li>
              </div>
      </ul>
    </div>
					<div class="page-box">
					<div id="paed" class="jogger">
						  <ul>
						  </ul>
					</div>
					</div>
</body>
</html>