<%@page import="java.io.InputStream"%>
<%@page import="org.activiti.engine.impl.*"%>
<%@page import="org.activiti.engine.impl.pvm.*"%>
<%@page import="org.activiti.engine.impl.pvm.process.*"%>
<%@page import="org.activiti.engine.repository.*"%>
<%@page import="org.activiti.engine.*"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title></title>
</head>
<body>

<div>
 		<img src="getProcessPic.action?processDefinitionId=${processDefinitionId}&taskId=${taskId}" style="position:absolute; left:0px; top:0px;">
 		<!-- 给执行的节点加框 -->
 		<div style="position:absolute; border:2px solid pink;left:${coordinateObj.x-1 }px;top:${coordinateObj.y-1 }px;width:${coordinateObj.width }px;height:${coordinateObj.height }px;"></div>
</div>
 	
 </body>
</html>
