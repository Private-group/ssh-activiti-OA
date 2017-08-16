<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
 <head>
    <title>用户申请页面</title>
 </head>
 
 <body>
  <h2>我要申请</h2>
  <hr>
    <form action="startProcessInstance.action" method="post">
    <table border="0" >
         <tr>
             <td>申请人：</td>
             <td>${sessionScope.LOGIN_USER}</td>
         </tr> 
          <tr>
             <td>请假天数：</td>
             <td><input name="qingJia.days"/></td>
         </tr> 
            <tr>
             <td>请假事由：</td>
             <td><input name="qingJia.remark"/></td>
         </tr> 
         <tr>
             <td colspan="2">
                 <input type="submit" value=" 申请 "/>
             </td>
         </tr>
    </table>
    </form>
 </body>
</html>