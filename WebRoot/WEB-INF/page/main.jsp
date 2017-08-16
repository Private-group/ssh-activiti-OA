<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
 <head>
    <title>用户主页页面</title>
 </head>
 
 <body>
  <h2>欢迎你：${sessionScope.LOGIN_USER}</h2> <a href="<%=basePath %>logout.action" >退出</a>
   <hr/>
   <div style="width:20%;height:80% ; float: left">
       <ul>
    <li><a href="<%=basePath %>shenqingJsp.action" target="myIframe">我要申请</a></li>
    <li><a href="<%=basePath %>findMyPersonalTask.action" target="myIframe">待我审批</a></li>
    <li><a href="<%=basePath %>findMyShenpi.action" target="myIframe">我审批的</a></li>
  </ul> 
     
   </div>
   <div style="width:80%;height:80% ;float: left">
     <iframe name="myIframe"  src="<%=basePath %>shenqingJsp.action"  width="900" height="800">您的浏览器不支持iframe框架，请更换浏览器试试！！！</iframe>
   </div>
 </body>
</html>