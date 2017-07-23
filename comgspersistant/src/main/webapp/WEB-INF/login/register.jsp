<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/login/loginregister.do" method="post">
		<input name="name" type="text" maxlength="6"/>	
		<input name="remark" type="text" maxlength="6"/>
		<input type="button" value="注册" id="register">
		<input type="reset" value="重置"/>
	</form>
</body>
</html>
<script type="text/javascript" src="../../scriptmodule/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	$("#register").on("click",function(){
		 var params = $("#register").parent("form").serialize();  
		 $.ajax({
			 	async : true,
				type : "POST",
				url : "login/login4json.do",
				timeout : 10000,
				dataType : "json",
				data : params,
			    success : function(data) {
			    	console.log(data);
				},
				error : function() {
					alert("系统错误！");
					return false;
				}
        });	
	});
</script>