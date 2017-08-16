package test.entity;
 
import java.util.Date;

/**
 * TsChartsInfo entity. @author MyEclipse Persistence Tools
 */
public class QingJia   implements
		java.io.Serializable { 
	private static final long serialVersionUID = 1L;
	private Long qid;
	private Long days;
	private String userName;
	private String  remark;
	private Date dataTm;
	private String flowId;
	private String taskId;
	private String processDefinitionId; 
	public Long getQid() {
		return qid;
	}
	public void setQid(Long qid) {
		this.qid = qid;
	}
	public Long getDays() {
		return days;
	}
	public void setDays(Long days) {
		this.days = days;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getDataTm() {
		return dataTm;
	}
	public void setDataTm(Date dataTm) {
		this.dataTm = dataTm;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	  
}
