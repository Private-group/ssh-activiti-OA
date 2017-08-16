<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
 <head>
    <title>我审批的页面</title>
 </head>
 
 <body>
  <h2>我审批的</h2>
  <hr>
     <h2>我审批的列表</h2>
	  <table bordr="0">
	    <tr><td>序号</td><td>申请人</td><td>请假天数</td><td>请假事由</td><td>操作</td></tr>
	     <s:iterator value="allList" status="item"> 
	    <tr><td> ${item.index+1}</td><td> <s:property value="userName"/></td><td><s:property value="days"/></td><td><s:property value="remark"/></td><td><a href="<%=basePath%>chuliJsp.action?xiangqing=yes&qid=<s:property value="qid"/>">详情</a>--<a href="<%=basePath%>getProcessMap.action?processDefinitionId=<s:property value="processDefinitionId"/>&taskId=<s:property value="taskId"/>">查看流程图</a></td></tr>
	      </s:iterator> 
	  </table>
 </body>
</html>