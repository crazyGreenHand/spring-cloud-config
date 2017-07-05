<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加demo</title>
</head>
<body>
    <center>
        <form action="demo/addDemo" method="post">
            <input type="hidden" name="token" value="${token }">
            <table>
                <tr>
                    <td>编号</td>
                    <td>姓名</td>
                </tr>
                <tr>
                    <td><input type="text" name="no"></td>
                    <td><input type="text" name="name"></td>                    
                </tr>               
            </table>
            <input type="submit" value="提交">
        </form>
    </center>    
</body>
</html>