<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

String apiKey = "APIR6tuOHX";

System.out.println("apiKey: " + apiKey);

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="HandheldFriendly" content="True">
	<meta name="MobileOptimized" content="320">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta http-equiv="cleartype" content="on">
	<meta name="description" content="MSGHUB API">
	<meta name="author" content="yub">
	<title>MSGHUB API</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.css">
	<style>
		table td{
			vertical-align: middle;
			font-size:13px;
		}
		.page-wrapper{
			padding: 0;
			margin: 0;
			min-height: 100vh;
			background: linear-gradient(to top right, #08aeea 0%, #b721ff 100%);
			padding-top: 45px;
			padding-bottom: 50px;
		}
		.wrapper--790{
			padding: 0;
			box-sizing: inherit;
			margin: 0 auto;
			max-width: 790px;
		}
		.card-5{
			padding: 0;
			margin: 0;
			box-sizing: inherit;
			background: #fff;
			border-radius: 10px;
			box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
		}
		.card-heading{
			font-weight: 400;
			font-size: 14px;
			margin: 0;
			box-sizing: inherit;
			padding: 20px 0;
			background: #1a1a1a;
			border-top-left-radius: 10px;
			border-top-right-radius: 10px;
		}
		.title{
			box-sizing: inherit;
			margin: 0;
			padding: 0;
			font-size: 24px;
			font-weight: 700;
			text-align: center;
			color: #fff;
		}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
	
	$(function(){
		var randomStr = makeRandomStr();
		var apiPwd = "${apiPwd}";
		$("#apiPwd").val(apiPwd+"."+randomStr);
		
		var response = ${response};
		$("#result").val(JSON.stringify(response));
		
		
		
		/*
		var response = "${response}";
		console.log("apiPwd=>"+apiPwd);
		console.log("response=>"+response);
		
		var data = $("#inputFrm").serialize();
		console.log("data1==>"+data);
		
		var data2 = $("#inputFrm").serializeArray();
		var data3 = JSON.stringify(data2);
		console.log("data stringify==>"+JSON.stringify(data2));
		console.log("data parse==>"+JSON.parse(data3));
		
		var jsonObj = JSON.parse("${jsonObj}");
		console.log("jsonObj===>"+jsonObj);
		console.log("jsonObj===>"+jsonObj.code);
		console.log("jsonObj===>"+jsonObj.message);
		
		
		var obj = {"code":"29001","message":"must not be empty property=apiKey"};
		
		var obj = {"code":"29001","message":"test"};
		
		*/
		
		// 인증값 요청
		/*
		$.ajax({
			url : "https://api.msghub.uplus.co.kr/auth/v1/" + randomStr
			, type : "POST"
			, method : "POST"
			, dataType : "json"
			, data : $("#inputFrm").serialize()
			, beforeSend : function(xhr) {
				xhr.setRequestHeader("accept", "*삭제/*");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
			, success : function(data) {
				$("#result").val(data);
			}
			, error : function(request,status,error){
				$("#result").val("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"status:"+status);
		        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"status:"+status);
		    }
		});
		*/
		
		$('#submitBtn').click(function(){
			var request = $("#request").val();
			var contentType = $("#contentType").val();
			var methodType = $("#methodType").val();
			
			if(request == "mms2" || request == "mms3"){
				request = "mms";
			}
			console.log(request+"==="+contentType+"=="+methodType);
			
			$.ajax({
				url : "${pageContext.request.contextPath}/" + "test"
				, type : "POST"
				, method : "POST"
				, dataType : "json"
				, data : $("#inputFrm").serialize()
				, beforeSend : function(xhr) {
					xhr.setRequestHeader("accept", "*/*");
					xhr.setRequestHeader("Content-Type", "application/json");
				}
				, success : function(data) {
					console.log("success==>"+data);
					console.log("success==>"+JSON.stringify(data));
					console.log("success==>"+data.isMarried);
					$("#result").val(JSON.stringify(data));
				}
				, error : function(request,status,error){
					$("#result").val("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"status:"+status);
			        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"status:"+status);
			    }
			});
			
			//ajaxSend(request, contentType, methodType);
		});
		
		// 토큰 인증
		/* SHA512.encryptToBase64( SHA512.encryptToBase64(apiPwd) + "." + randomStr ))
		
		curl -X POST "https://api.msghub.uplus.co.kr/auth/v1/12345"
		  -H "accept: *'/*"
		  -H  "Content-Type: application/json"
		  -d '{
		      "apiKey": "1",
		      "apiPwd": "fwxbxKFf7RjMUoSGVJML6YTx..."
		  }'
		  
		// 토큰 갱신
		curl -X PUT "https://api.msghub.uplus.co.kr/auth/v1/refresh"
		-H  "accept: *'/*"
		-H  "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwi..."
		
		// sms 전송
		/* curl -X POST "https://api.msghub.uplus.co.kr/msg/v1/sms"
	    -H  "accept: *'/*"
	    -H  "Authorization: Bearer eyJhbGciOiJIUzI1NiJ..."
	    -H  "Content-Type: application/json" */
	    
		function ajaxSend(reqUrl, contentType, methodType) {
			
	    	var token = $("#token").val();
			
			$.ajax({
				url : "https://api.msghub.uplus.co.kr/auth/v1/" + reqUrl
				, type : "POST"
				, method : "POST"
				, dataType : "json"
				, data : $("#inputFrm").serialize()
				, beforeSend : function(xhr) {
					xhr.setRequestHeader("accept", "*/*");
					xhr.setRequestHeader("Authorization", "Bearer " + token);
					xhr.setRequestHeader("Content-Type", contentType);
				}
				, success : function(data) {
					$("#result").val(data);
				} 
				, error : function(data) {
					alert(data);
				}
			});
			
			
			
			$.ajax({
				url : "https://api.msghub.uplus.co.kr/auth/v1/" + reqUrl
				, type : "POST"
				, method : "POST"
				, dataType : "json"
				, data : $("#inputFrm").serialize()
				, beforeSend : function(xhr) { 
				      xhr.setRequestHeader("Authorization", "Bearer " + (apikey)); 
				      //xhr.setRequestHeader("Authorization", "Basic dGVzdF9ja19PRVA1OUx5Ylo4QmR2NkExSnhrVjZHWW83cFJlOg=="); 
				}
				, success : function(data) {
					$("#result").val(data);
				} 
				, error : function(data) {
					alert(data);
				}
			});
		}
		
		
		$('#request').on("change", function(){
			var request = $('#request').val()
			if(request == "mms2" || request == "mms3"){
				$("#contentType").val("multipart/form-data");
				$("#methodType").val("POST");
				
			}else if(request == "rpt"){
				$("#contentType").val("multipart/form-data");
				$("#methodType").val("GET");
			}else{
				$("#contentType").val("application/json");
				$("#methodType").val("POST");
			}
		});
		
		function makeRandomStr() {
			var now = new Date();
			var years = now.getFullYear();
			var months = LPad(now.getMonth() + 1, 2, "0");
			var dates = LPad(now.getDate(), 2, "0");
			var hours = LPad(now.getHours(), 2, "0");
			var randomValue = "dong" + years + months + dates + hours; 
			document.getElementById("randomStr").value = randomValue;
			return randomValue;
		}
		
		function LPad(digit, size, attatch) {
		    var add = "";
		    digit = digit.toString();

		    if (digit.length < size) {
		        var len = size - digit.length;
		        for (i = 0; i < len; i++) {
		            add += attatch;
		        }
		    }
		    return add + digit;
		}
	});	
	
	</script>
</head>
<body>
<div class="page-wrapper">
	<div class="wrapper--790">
		<div class="card-5">
			<div class="card-heading">
				<h2 class="title">MSGHUB API</h2>
			</div>
			<form id="inputFrm" name="inputFrm" action="" method="post">
				<input type="hidden" name="contentType" id="contentType" value="application/json">
				<input type="hidden" name="methodType" id="methodType" value="POST">
				<input type="hidden" name="token" id="token" value="">
				<input type="hidden" name="refreshToken" id="refreshToken" value="">
				<p>
				<div class="container">
					<table class="table-sm table-bordered table-hover" style="width:100%;">
					<colgroup>
						<col width="30%">
						<col width="80%">
					</colgroup>
					<thead>
					</thead>
					<tbody>
						<tr>
							<td>Request</td>
							<td>
								<select class="form-control" id="request" name="request">
									<option value="refresh">인증갱신</option>
									<option value="sms">SMS 발송</option>
									<option value="mms2">이미지 사전등록</option>
									<option value="mms3">MMS 발송</option>
									<option value="mms">MMS 발송(첨부파일 사전등록시)</option>
									<option value="rcs">RCS 발송</option>
									<option value="alimtalk">카카오 알림톡</option>
									<option value="friendtalk">카카오 친구톡</option>
									<option value="push">PUSH 발송</option>
									<option value="smartMsg">통합메시지 발송</option>
									<option value="rpt">리포트조회</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>randomStr</td>
							<td data-toggle="tooltip">
								<label for="mertid" class="sr-only"></label>
								<input type="text" id="randomStr" name="randomStr" class="form-control form-control-sm" readonly value="">
							</td>
						</tr>
						<tr>
							<td>apiKey</td>
							<td data-toggle="tooltip">
								<label for="mertid" class="sr-only"></label>
								<input type="text" id="apiKey" name="apiKey" class="form-control form-control-sm" placeholder="apiKey" required value="">
							</td>
						</tr>
						<tr>
							<td>apiPwd</td>
							<td data-toggle="tooltip">
								<input type="text" id="apiPwd" name="apiPwd" class="form-control" readonly value="">
							</td>
						</tr>
						<tr id="tr_callback">
							<td colspan="2">
								<input type="button" class="btn btn-default pull-right" id="submitBtn" name="submitBtn" value="요청" />
							</td>
						</tr>
					</tbody>
				</table>
				</div>
			</form>
			<div class="container">
				<table class="table-sm table-bordered table-hover" style="width:100%;">
				<colgroup>
					<col width="30%">
					<col width="80%">
				</colgroup>
				<thead>
				</thead>
				<tbody>
					<tr>
						<td>결과</td>
						<td><textarea name="result" id="result" style="width:100%;border:1;overflow:visible;text-overflow:ellipsis;" rows=5></textarea></td>
					</tr>
				</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
</html>