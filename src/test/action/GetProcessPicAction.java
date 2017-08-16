package test.action;
 
import java.io.InputStream;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;
import test.service.WorkFlowService;
public   class GetProcessPicAction  extends MyBaseAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	
//	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	 
	  private String taskId;//  
	  private InputStream inputStream;//
	  private ActivityImpl coordinateObj;// 
	  private WorkFlowService workFlowService;
	  private String processDefinitionId;//流程定义ID
	  
	 
	/**
	 *  查看流程定义图
	 * @return
	 * @throws Exception 
	 */
	    public String getProcessPic() throws Exception {
	    	//根据当前的任务ID,或者任务
	    	if(taskId!=null&&!taskId.trim().equals("")){
	    		Task task = workFlowService.getTaskById(taskId); 
		    	this.inputStream= workFlowService.getProcessImgByProcessDefinitionId(task.getProcessDefinitionId());
	    	}else{
	    		this.inputStream= workFlowService.getProcessImgByProcessDefinitionId(processDefinitionId);
	    	}
	        return SUCCESS;
	    }
	/**
	 * 查看当前流程图
	 * @return
	 * @throws Exception
	 */
	    public String getProcessMap() throws Exception {
	         if(taskId!=null&&!taskId.trim().equals("")){
	        	//根据当前的任务ID,或者任务
	 	    	Task task = workFlowService.getTaskById(taskId); 
	 	    	this.coordinateObj = workFlowService.getActivityImpl(task);
	         }else{
	        	 this.coordinateObj =  workFlowService.getEndNode(processDefinitionId);
	         }
	        return SUCCESS;
	    }
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public ActivityImpl getCoordinateObj() {
		return coordinateObj;
	}
	public void setWorkFlowService(WorkFlowService workFlowService) {
		this.workFlowService = workFlowService;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	
}
