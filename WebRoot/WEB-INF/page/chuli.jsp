<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
 <head>
    <title>处理页面</title>
 </head>
 
 <body>
  <s:if test="xiangqing==null">
  <h2>待我处理</h2> 
  </s:if>
  <s:else>
  <h2>我已处理</h2>
  </s:else>
  <hr>
   <h2>请假详细信息</h2>
      申请人：${qingJia.userName}</br>
      申请天数：${qingJia.days}</br>
      申请事由：${qingJia.remark}</br>
      创建时间：${qingJia.dataTm}</br>
      <hr>
      <s:if test="xiangqing==null">
      
      <h2>审批信息</h2>
       <form action="shenpiMap.action" method="post">
       <input  type="hidden"  name="qingJiaHis.qid" value="${qingJia.qid}"/>
       <input type="hidden"   name="qingJia.flowId" value="${qingJia.flowId}"/>
    <table border="1">
         <tr>
             <td>审批动作：</td>
             <td><select name="qingJiaHis.shenpiAction">
               <option value="同意">同意</option>
               <option value="不同意">不同意</option>
             </select>
             </td>
         </tr>
          <tr>
             <td>备注：</td>
               <td>
               <input name="qingJiaHis.shenpiRemark">
               </td>
         </tr>
         
          <tr>
             <td colspan="2">
                 <input type="submit" value=" 提交"/>
             </td>
         </tr>
         
    </table>
    </form>
    <hr>
    </s:if>
    <h2>审批历史</h2>
    
    <table bordr="0">
	    <tr><td>序号</td><td>申请人（审批人）</td><td>审批动作</td><td>审批备注</td><td>时间</td></tr>
	     <s:iterator value="hisList" status="item"> 
	    <tr><td> ${item.index+1}</td><td> <s:property value="executor"/></td><td><s:property value="shenpiAction"/></td><td><s:property value="shenpiRemark"/></td><td><s:property value="dataTm"/></td></tr>
	      </s:iterator> 
	  </table>
	  
 </body>
</html>