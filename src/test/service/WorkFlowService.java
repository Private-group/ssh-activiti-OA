package test.service;
 
import java.io.IOException;
import java.io.InputStream; 
import java.util.HashMap;
import java.util.List; 
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService; 
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task; 

public class WorkFlowService {

	//ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	  private RepositoryService repositoryService;
	  private RuntimeService runtimeService;
	  private TaskService taskService;
	  private HistoryService historyService;
	  private ManagementService managementService;
	  private IdentityService identityService;

	  
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}
	

	public void setManagementService(ManagementService managementService) {
		this.managementService = managementService;
	}
	

	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}
	
	
	/**
	 * 部署流程定义
	 */
	public void deploymentProcessDefinition_inputStream(){
		InputStream inputStreamBpmn = this.getClass().getResourceAsStream("helloworld.bpmn");
		InputStream inputStreamPng = this.getClass().getResourceAsStream("helloworld.png");
		Deployment deployment =repositoryService
						.createDeployment()
						.name("请假流程")
						.addInputStream("helloworld.bpmn", inputStreamBpmn)
						.addInputStream("helloworld.png", inputStreamPng)
						.deploy();
		System.out.println("部署ID:"+deployment.getId());
		System.out.println("部署名称"+deployment.getName());
	}
	
	/**启动流程*/ 
	public ProcessInstance startProcessInstance(String processDefinitionKey ){
		ProcessInstance pi = runtimeService
						.startProcessInstanceByKey(processDefinitionKey);
		System.out.println("流程实例ID"+pi.getId());//流程实例ID
		System.out.println("流程定义ID:"+pi.getProcessDefinitionId());//流程定义ID
		return pi;
	}
	
	/**
	 * 根据流程实例ID获取历史表的流程定义实体
	 * 当流程走完时，要查询流程定义图只能通过在历史表找到历史流程实例对象
	 * @param instanceId
	 * @return
	 */
	public  HistoricProcessInstance  getHistoricActivityInstanceByInstanceId(String instanceId){
		 	return historyService.createHistoricProcessInstanceQuery().processInstanceId(instanceId).list().get(0);
	}
	
	/**查询个人任务*/ 
	public List<Task> findMyPersonalTask(String assignee ){
		List<Task> list = taskService
						.createTaskQuery()//创建查询
						.taskAssignee(assignee)//根据一个人来查询
						//.taskCandidateGroup(assignee)根据组来查询
//						.taskCandidateUser(candidateUser)//根据多个人来查询
//						.processDefinitionId(processDefinitionId)
//						.processInstanceId(processInstanceId)
//						.executionId(executionId)
						.orderByTaskCreateTime().asc()//根据任务时间升序排序
//						.singleResult()//
//						.count()//
//						.listPage(firstResult, maxResults);//分页查询
						.list();//
//		if(list!=null && list.size()>0){
//			for(Task task:list){
//				System.out.println("任务ID:"+task.getId());
//				System.out.println("任务名称:"+task.getName());
//				System.out.println("任务创建时间:"+task.getCreateTime());
//				System.out.println("任务当前执行人:"+task.getAssignee());
//				System.out.println("任务所属的流程实例ID:"+task.getProcessInstanceId());
//				System.out.println("任务当前执行人ID:"+task.getExecutionId());
//				System.out.println("任务所属的流程定义ID:"+task.getProcessDefinitionId());
//				System.out.println("########################################################");
//			}
//		}
		return list;
	}
	/**
	 * 根据任务ID获取任务实体
	 * @param taskId
	 * @return
	 */
	public Task getTaskById(String taskId){
		 Task t= taskService
				.createTaskQuery().taskId(taskId).singleResult();
		 return t;
	}
	
	/**
	 * 根据流程实例ID获取任务实体
	 * @param taskId
	 * @return
	 */
	public Task getTaskByProcessInstanceId(String processInstanceId){
		 Task t= taskService
				.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		 return t;
	}
	
	/**
	 * 获取流程定义图
	 * @param task
	 * @return
	 */
	public InputStream getProcessImgByProcessDefinitionId(String processDefinitionId){
		ProcessDefinition procDef = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        String diagramResourceName = procDef.getDiagramResourceName();
        InputStream imageStream = repositoryService.getResourceAsStream(
        		procDef.getDeploymentId(), diagramResourceName);
       return imageStream;
	}
	
	/**
	 * 获取当前活动节点
	 * @param task
	 * @return
	 */
	public ActivityImpl getActivityImpl(Task task){
		ProcessDefinition processDefinition =repositoryService .createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();

        ProcessDefinitionImpl pdImpl = (ProcessDefinitionImpl) processDefinition;
        String processDefinitionId = pdImpl.getId();// 流程定义ID

        RepositoryServiceImpl repositoryServiceImpl =  (RepositoryServiceImpl) repositoryService ;
        ProcessDefinitionEntity def = (ProcessDefinitionEntity) repositoryServiceImpl.getDeployedProcessDefinition(processDefinitionId);
        List<ActivityImpl> activitiList = def.getActivities();//获取当前活动节点
        ActivityImpl actImpl = null; 
        ExecutionEntity execution =null ; 
        if(task.getExecutionId()!=null){
              execution = (ExecutionEntity) runtimeService .createExecutionQuery().executionId(task.getExecutionId()).singleResult();//获取唯一记录
        }
   
      //  endevent1
        if(execution==null){
        	 for (ActivityImpl activityImpl : activitiList) {
        		    if(activityImpl.getId().equals("endevent1")){
        		    	  actImpl = activityImpl;
      	                break;
        		    }
        	 }
        	
        }else{
        	  String activitiId = execution.getActivityId();//获取活动节点的ID
  	        for (ActivityImpl activityImpl : activitiList) {
  	            String id = activityImpl.getId();
  	            if (id.equals(activitiId)) {//循环遍历流程定义图，如果活动节点的id==遍历节点的id，此节点就是活动节点，获取的相关的坐标等信息。
  	                actImpl = activityImpl;
  	                break;
  	            }
  	        } 
        }
        return actImpl; 
	}
	
	/**
	 * 获取结束节点
	 * @param task
	 * @return
	 */
	public ActivityImpl getEndNode(String processDefinitionId){ 
        RepositoryServiceImpl repositoryServiceImpl =  (RepositoryServiceImpl) repositoryService ;
        ProcessDefinitionEntity def = (ProcessDefinitionEntity) repositoryServiceImpl.getDeployedProcessDefinition(processDefinitionId);
        List<ActivityImpl> activitiList = def.getActivities();//获取当前活动节点
        ActivityImpl actImpl = null;  
       for (ActivityImpl activityImpl : activitiList) {
        		    if(activityImpl.getId().equals("endevent1")){
        		    	  actImpl = activityImpl;
      	                break;
        		    }
        	 }
        return actImpl; 
	}
	
	/**办结任务*/ 
	public void completeMyPersonalTask(String taskId ){
		//String taskId = "1204";
		taskService
					.complete(taskId);
		System.out.println("办结任务："+taskId);
	}
	
	/**办结任务*/ 
	public void completeMyPersonalTaskMap(String taskId ,Map<String,Object> map){
		//String taskId = "1204";
		taskService
					.complete(taskId,map);
		System.out.println("办结任务："+taskId);
	}
	
	/**
	 * 获取用户所在组的任务集合
	 */
	public List<Task> getTaskListByUserId(String userId){
		 List<Task> tasks = null;
		 if(userId.equals("1")||userId.equals("2")||userId.equals("3")||userId.equals("4")){
			 tasks = taskService.createTaskQuery().taskCandidateUser(userId).orderByTaskCreateTime().desc().list();// 用户所在组的任务
			 
		 }else if(userId.equals("5")){
			 tasks = taskService.createTaskQuery().taskCandidateGroup("zuzhang").orderByTaskCreateTime().desc().list();// 用户所在组的任务
		 }else if(userId.equals("6")){
			 tasks = taskService.createTaskQuery().taskCandidateGroup("bumenjingli").orderByTaskCreateTime().desc().list();// 用户所在组的任务
		 }else if(userId.equals("7")){
			 tasks = taskService.createTaskQuery().taskCandidateGroup("zongjinli").orderByTaskCreateTime().desc().list();// 用户所在组的任务
		 }
		 return tasks;
	}
	
	/**
	 * 新增用户组
	 */
	public void newGroup(){
		//newGroup(组id)
		    Group group = identityService.newGroup("testGroup");
		    group.setName(group.getId().toUpperCase());
	        group.setType("assignment");
	        identityService.saveGroup(group); 
	}
	/**
	 * 新增用户
	 */
	public void newUser(){
		//newUser(用户id);
		User u= identityService.newUser("testUser");
		u.setEmail("xxx@qq.com");
		u.setFirstName(u.getId().toUpperCase());
		u.setLastName(u.getId().toUpperCase());
		u.setPassword("123456");
		identityService.saveUser(u);
	}
	/**
	 * 新增用户组与用户关系
	 */
	public void createMembership(){
		//createMembership(用户id，组id)
		identityService.createMembership("testUser", "testGroup");
	}
	/**
	 * 根据人id获取此人唯一组对象信息
	 * @param userId
	 * @return
	 */
	public Group getGroupByUserId(String userId){
	  Group group = identityService.createGroupQuery().groupMember(userId).singleResult();  //获取唯一一个组信息
	  return group;
	}
	/**
	 * 根据人id获取此人组集合对象信息
	 * @param userId
	 * @return
	 */
	public List<Group>  findGroupByUserId(String userId){
		List<Group> list = identityService.createGroupQuery().groupMember(userId).list();//获取组集合
		return list;
	}
	
	 /**
     * 部署流程
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deployProcDefForZip()
    {
        String zip = "com/s2sh/diagrams/leave.zip"; 
        ZipInputStream inputStream = null;
        try
        {
            inputStream = new ZipInputStream(ClassLoader.getSystemResourceAsStream(zip));
            System.out.println(inputStream);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        repositoryService.createDeployment().name("请假流程定义部署")// act_re_deployment.NAME
                .addZipInputStream(inputStream).deploy();
 
    }
    
	/**
	 * 删除流程定义
	 */
	   public void deleteProcDef()
	    {
	        String key = "";
	        
	        List<ProcessDefinition> procDefList = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key)// 获得指定流程的工作流
	                .orderByProcessDefinitionVersion().desc().list();
	        for (ProcessDefinition proceDefinition : procDefList)
	        {
	            repositoryService.deleteDeployment(proceDefinition.getDeploymentId(), true);
	        }
 
	    }
	   
	   /**
	    * 根据角色来办结任务
	    * @param userId
	    * @return
	    */
	    public void operateTaskForGroupId(String userId)
	    {
	        String taskId = "154";
	        String day ="2";
	        String operate = "";

	        Group group = identityService.createGroupQuery().groupMember(userId).singleResult();

	        if (group.getId().equals("leave_group"))
	        {
	            Map<String, Object> variables = new HashMap<String, Object>();
	            variables.put("day", day);
	            taskService.complete(taskId, variables);// 执行通过 有参
	        }
	        else if (group.getId().equals("dept_check_group"))
	        {
	            Map<String, Object> variables = new HashMap<String, Object>();
	            if (operate.equals("1"))
	            {
	                variables.put("iscorrect", true);// 通过
	            }
	            else if (operate.equals("0"))
	            {
	                variables.put("iscorrect", false);// 驳回
	            }
	            taskService.complete(taskId, variables);// 执行 有参
	        }
	        else if (group.getId().equals("boss_check_group"))
	        {
	         // 标识完成任务在该节点的操作
	            Map<String, Object> variables = new HashMap<String, Object>();
	            if (operate.equals("1"))
	            {
	                variables.put("iscorrect", true);// 通过
	            }
	            else if (operate.equals("0"))
	            {
	                variables.put("iscorrect", false);// 驳回
	            }
	            taskService.complete(taskId, variables);// 执行 有参
	        }
	       
	    }
}
