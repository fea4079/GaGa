<%-- <%@ page contentType="text/html; charset=EUC-KR" %> --%>
<%@ page pageEncoding="UTF-8"%>
<!-- 네이버 -->
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="UTF-8">
	
	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<!-- <script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js"charset="utf-8"></script> -->
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
    	 body >  div.container{ 
        	border: 3px solid #D6CDB7;
            margin-top: 10px;
        }
    </style>
    
    <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">

		//============= "로그인"  Event 연결 =============
		$( function() {
			
			$("#userId").focus();
			
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$("button").on("click" , function() {
				var id=$("input:text").val();
				var pw=$("input:password").val();
				
				if(id == null || id.length <1) {
					alert('ID 를 입력하지 않으셨습니다.');
					$("#userId").focus();
					return;
				}
				
				if(pw == null || pw.length <1) {
					alert('패스워드를 입력하지 않으셨습니다.');
					$("#password").focus();
					return;
				}
				
				$("form").attr("method","POST").attr("action","/user/login").attr("target","_parent").submit();
			});
		});	
		
		
		//============= 회원원가입화면이동 =============
		$( function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$("a[href='#' ]").on("click" , function() {
				self.location = "/user/addUser"
			});
		});
		
	</script>		
					
	
</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<div class="navbar  navbar-default">
        <div class="container">
        	<a class="navbar-brand" href="/index.jsp">Model2 MVC Shop</a>
   		</div>
   	</div>
   	<!-- ToolBar End /////////////////////////////////////-->	
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
		<!--  row Start /////////////////////////////////////-->
		<div class="row">
		
			<div class="col-md-6">
					<img src="/images/logo-spring.png" class="img-rounded" width="100%" />
			</div>
	   	 	
	 	 	<div class="col-md-6">
	 	 	
		 	 	<br/><br/>
				
				<div class="jumbotron">	 	 	
		 	 		<h1 class="text-center">로 &nbsp;&nbsp;그 &nbsp;&nbsp;인</h1>

			        <form class="form-horizontal">
		  
					  <div class="form-group">
					    <label for="userId" class="col-sm-4 control-label">아 이 디</label>
					    <div class="col-sm-6">
					      <input type="text" class="form-control" name="userId" id="userId"  placeholder="아이디" >
					    </div>
					  </div>
					  
					  <div class="form-group">
					    <label for="password" class="col-sm-4 control-label">패 스 워 드</label>
					    <div class="col-sm-6">
					      <input type="password" class="form-control" name="password" id="password" placeholder="패스워드" >
					    </div>
					  </div>
					  
					  <div class="form-group">
					    <div class="col-sm-offset-4 col-sm-6 text-center">
					      <button type="button" class="btn btn-primary"  >로 &nbsp;그 &nbsp;인</button>
					      <a class="btn btn-primary btn" href="#" role="button">회 &nbsp;원 &nbsp;가 &nbsp;입</a>
					      <a href="https://kauth.kakao.com/oauth/authorize?client_id=5d88ee6131a76417bcf8e0d0dc852d91&scope=profile_nickname,profile_image,account_email&redirect_uri=http://192.168.0.159:8080/user/kakaoLogin&response_type=code">
					      <img class="btn-img" src="/images/kakao_login_medium_narrow.png"></a>
					      <!-- <a href="https://kauth.kakao.com/oauth/authorize?client_id=04012ee167a54fddf374766087a27fea&scope=profile_nickname,profile_image,account_email&redirect_uri=http://127.0.0.1:8080/user/kakaoLogin&response_type=code"><img class="btn-img" src="/images/kakao_login_medium_narrow.png"></a> -->
					     <!--  <a href="https://nid.naver.com/oauth2.0/authorize?client_id={클라이언트 아이디}&response_type=code&redirect_uri={http://127.0.0.1:8080/index.jsp}&state={상태 토큰}"><img class="btn-img" src="/images/kakao_login_medium_narrow.png"></a>
					      
					      <div id="naver_id_login"></div>
					      <script type="text/javascript">
					        var naver_id_login = new naver_id_login("FzMGbETEgw2xNeSUlIIF", "http://127.0.0.1:8080/index.jsp");
					        var state = naver_id_login.getUniqState();
					        naver_id_login.setButton("white", 2,40);
					        naver_id_login.setDomain("http://localhost:8080");
					        naver_id_login.setState(state);
					        naver_id_login.setPopup();
					        naver_id_login.init_naver_id_login();
					    </script> -->
					    <!-- 네이버 -->
					   
					       <%-- <%
						    String clientId = "FzMGbETEgw2xNeSUlIIF";//애플리케이션 클라이언트 아이디값";
						    String redirectURI = URLEncoder.encode("http://192.168.0.159:8080/user/naverLogin", "UTF-8");
						    SecureRandom random = new SecureRandom();
						    String state = new BigInteger(130, random).toString();
						    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
						         + "&client_id=" + clientId
						         + "&redirect_uri=" + redirectURI
						         + "&state=" + state;
						    session.setAttribute("state", state);
						 %>
						   <a href="<%=apiURL%>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>  -  --%> 
						   
						  <!--  <a href="https://nid.naver.com/oauth2.0/authorize?client_id=FzMGbETEgw2xNeSUlIIF&response_type=code&redirect_uri=http://192.168.0.159:8080/user/naverLogin&state=url_parameter"> -->
						  <!-- <a href="https://nid.naver.com/oauth2.0/authorize?client_id=FzMGbETEgw2xNeSUlIIF&response_type=code&redirect_uri=http://192.168.0.159:8080/user/naverLogin&state=test"> -->
						  <a href="https://nid.naver.com/oauth2.0/authorize?client_id=FzMGbETEgw2xNeSUlIIF&response_type=code&redirect_uri=http://192.168.0.159:8080/user/naverLogin&state=test">
							  <img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/>	</a>
					    
					    </div>
					  </div>
					  <!-- 카카오 로그인 -->
					<!--   <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
					  	<ul>
							<li onclick="kakaoLogin();">
						      <a href="javascript:void(0)">
						          <span>카카오 로그인</span>
						      </a>
							</li>
							<li onclick="kakaoLogout();">
						      <a href="javascript:void(0)">
						          <span>카카오 로그아웃</span>
						      </a>
							</li>
						</ul> -->
						
			
					</form>
			   	 </div>
			
			</div>
			
  	 	</div>
  	 	<!--  row Start /////////////////////////////////////-->
  	 	
 	</div>
 	<!--  화면구성 div end /////////////////////////////////////-->

</body>

</html>