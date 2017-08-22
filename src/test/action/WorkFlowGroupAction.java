package test.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import test.dao.QingJiaDAO;
import test.dao.QingJiaHisDAO;
import test.entity.QingJia;
import test.entity.QingJiaHis;
import test.service.WorkFlowService;
public class WorkFlowGroupAction extends MyBaseAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QingJia qingJia;
	private QingJiaHis qingJiaHis;
	private WorkFlowService workFlowService;
	private QingJiaDAO qingJiaDAO;
	private QingJiaHisDAO qingJiaHisDAO;
	private  List<QingJia>  allList=new ArrayList<QingJia>();
	private List<QingJiaHis> hisList = new ArrayList<QingJiaHis>();
	private String taskId;
	private String xiangqing; //是否是查看详情，不为空就是是查看详情
	 
	 private Long qid;  //请假表的主键ID
	
 
	 /**
	  * 启动流程
	  * @return
	  * @throws Exception
	  */
	public String startProcessInstance() throws Exception {
		    Date date=new Date(); 
		    String userName =  this.getCurrentUserName();
		   qingJia.setUserName(userName);
		   qingJia.setDataTm(date);
		    
		   String processDefinitionKey = "testgroup"; //helloworld.bpmn 定义的ID
		  
		  //启动流程，返回工作流实例ID
		  ProcessInstance pi = workFlowService.startProcessInstance(processDefinitionKey);
		  qingJia.setFlowId(pi.getId());
		  //保存请假信息
		  qingJiaDAO.save(qingJia);
		  //保存请假历史信息
		  QingJiaHis his=new QingJiaHis();
		  his.setDataTm(date);
		  his.setQid(qingJia.getQid());
		  his.setShenpiAction("提交");
		  his.setShenpiRemark("提交申请");
		  his.setExecutor(userName);
		  qingJiaHisDAO.save(his);
		  System.out.println("请假成功，流程启动成功！！流程实例ID:"+pi.getId());
	       return SUCCESS;
	}
	/**
	 * 查询当前登录用户的所有任务
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
	public String findMyPersonalTask() throws Exception {
		  List<QingJia>  allListTmp =new ArrayList<QingJia>();
		  String userName =   this.getCurrentUserName();
		  List<Task> tasks  = workFlowService.getTaskListByUserId(userName);
		   if(tasks!=null&&!tasks.isEmpty()){
			   for (Task t : tasks) {
				  List<QingJia> l =  qingJiaDAO.findByProperty("flowId", t.getProcessInstanceId());
				  for (QingJia qingJia : l) {
					  qingJia.setTaskId(t.getId());
				}
				  allListTmp.addAll(l);
			}
			   
		   }
		   this.allList = allListTmp;
	       return SUCCESS;
	}
	
	/**
	 * 我审批的列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findMyShenpi(){
		 String userName =   this.getCurrentUserName();
		  this.allList  = qingJiaDAO.findMyShenpi(userName);
		  for (QingJia q : allList) {
			  Task task = workFlowService.getTaskByProcessInstanceId(q.getFlowId());
			  //流程没走完
			  if(task!=null){
				  q.setTaskId(task.getId());
			  }else{
				  q.setTaskId(null);
				  HistoricProcessInstance hpi = workFlowService.getHistoricActivityInstanceByInstanceId(q.getFlowId());
				  q.setProcessDefinitionId( hpi.getProcessDefinitionId());
			  }
		}
		return SUCCESS;
	}
	
	
	/**
	 * 审批
	 */
	public String shenpi(){
		//办结任务
		Task task = workFlowService.getTaskByProcessInstanceId(qingJia.getFlowId());
		workFlowService.completeMyPersonalTask(task.getId());
		  Date date=new Date(); 
		   String userName =  this.getCurrentUserName();
		//保存审批历史记录信息 、
	   this.qingJiaHis.setDataTm(date); 
	   this.qingJiaHis.setExecutor(userName);
	  qingJiaHisDAO.save(  this.qingJiaHis);
		 return SUCCESS;
	}
 
	
	/**
	 * 审批处理
	 */
	public String shenpiMap(){
		//办结任务
		Task task = workFlowService.getTaskByProcessInstanceId(qingJia.getFlowId());
		Map<String, Object> map=new HashMap<String, Object>();
		if(qingJiaHis.getShenpiAction().equals("同意")){
			map.put("action", "Y");
		}else{
			map.put("action", "N");
		}
		workFlowService.completeMyPersonalTaskMap(task.getId(),map);
		  Date date=new Date(); 
		   String userName = this.getCurrentUserName();
		//保存审批历史记录信息 、
	   this.qingJiaHis.setDataTm(date); 
	   this.qingJiaHis.setExecutor(userName);
	  qingJiaHisDAO.save(  this.qingJiaHis);
		 return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String chuliJsp(){
		List<QingJia> qingjiaList =  qingJiaDAO.findByProperty("qid", qid);
		if(qingjiaList!=null&&!qingjiaList.isEmpty()){
			//获取请假对象 
			this.qingJia=qingjiaList.get(0);
		}
	
		//获取请假审批历史
		this.hisList = qingJiaHisDAO.findByProperty("qid", qid); 
		return SUCCESS;
				
	}
	
	
	public String shenqingJsp(){
		return SUCCESS;
	}

	public QingJia getQingJia() {
		return qingJia;
	}

	public void setQingJia(QingJia qingJia) {
		this.qingJia = qingJia;
	}

	public void setQingJiaDAO(QingJiaDAO qingJiaDAO) {
		this.qingJiaDAO = qingJiaDAO;
	}

	 
	public void setQingJiaHisDAO(QingJiaHisDAO qingJiaHisDAO) {
		this.qingJiaHisDAO = qingJiaHisDAO;
	}
	public void setWorkFlowService(WorkFlowService workFlowService) {
		this.workFlowService = workFlowService;
	}

 

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public List<QingJia> getAllList() {
		return allList;
	}
	public void setAllList(List<QingJia> allList) {
		this.allList = allList;
	}
	public Long getQid() {
		return qid;
	}
	public void setQid(Long qid) {
		this.qid = qid;
	}
	public List<QingJiaHis> getHisList() {
		return hisList;
	}
	public void setHisList(List<QingJiaHis> hisList) {
		this.hisList = hisList;
	}
	public QingJiaHis getQingJiaHis() {
		return qingJiaHis;
	}
	public void setQingJiaHis(QingJiaHis qingJiaHis) {
		this.qingJiaHis = qingJiaHis;
	}
	public String getXiangqing() {
		return xiangqing;
	}
	public void setXiangqing(String xiangqing) {
		this.xiangqing = xiangqing;
	}
  
	 
}
