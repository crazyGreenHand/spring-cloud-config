<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<base href="<%= basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>demo展示</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">

function testAjax() {
	$.get("demo/ajax", function(data) {
		alert("Data: " + data);
	});
}

var ajaxJson = function() {
	var demo = {};
	demo.id = 1;
	demo.name = 'robert';
	demo.no = 5;	
	$.ajax({
		type: 'POST',
		url: "demo/ajaxJson",
		contentType : 'application/json',
		data: JSON.stringify(demo),
		success: function(data){
			alert("Data: " + data);
		}		
	});
}
</script>
</head>
<body>
    <center>
	    <table border="1">
	        <tr>
	            <td>序号</td>
	            <td>编号</td>
	            <td>姓名</td>
	        </tr>
	        <c:forEach var="demo" items="${demos}" varStatus="status">
	            <tr onclick="location.href='demo/toUpdateDemo/${demo.id}'">
		            <td>${status.index+1 }</td>
		            <td>${demo.no }</td>
		            <td>${demo.name }</td>                    
	            </tr>
	        </c:forEach>                            
	    </table>
    </center> 
    <hr>
    <h4>测试ajax</h4>
    <button onclick="testAjax()">测试1</button>
    <button onclick="ajaxJson()">测试2</button>
</body>
</html>