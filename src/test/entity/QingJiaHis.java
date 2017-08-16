package test.entity;
 
import java.util.Date;

/**
 * TsChartsInfo entity. @author MyEclipse Persistence Tools
 */
public class QingJiaHis   implements
		java.io.Serializable { 
	private static final long serialVersionUID = 1L;
	private Long hid;
	private Long qid; 
	private String shenpiAction;
	private String  shenpiRemark;
	private Date dataTm;
	private String executor;
	public Long getHid() {
		return hid;
	}
	public void setHid(Long hid) {
		this.hid = hid;
	}
	public Long getQid() {
		return qid;
	}
	public void setQid(Long qid) {
		this.qid = qid;
	}
	public String getShenpiAction() {
		return shenpiAction;
	}
	public void setShenpiAction(String shenpiAction) {
		this.shenpiAction = shenpiAction;
	}
	public String getShenpiRemark() {
		return shenpiRemark;
	}
	public void setShenpiRemark(String shenpiRemark) {
		this.shenpiRemark = shenpiRemark;
	}
	public Date getDataTm() {
		return dataTm;
	}
	public void setDataTm(Date dataTm) {
		this.dataTm = dataTm;
	}
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
	} 
	
}
