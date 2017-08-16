package test.entity;

import java.math.BigDecimal;

/**
 * TsChartsInfo entity. @author MyEclipse Persistence Tools
 */
public class TsChartsInfo   implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long chartId;
	private String chartName;
	private BigDecimal chartType;
	private String chartDesc;
	private BigDecimal belongLink;
	private String chartUse;
	private String keyField;
	private BigDecimal frequenceTm;
	private String jsName;
	private String xtype;
	
	public Long getChartId() {
		return chartId;
	}
	public void setChartId(Long chartId) {
		this.chartId = chartId;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public BigDecimal getChartType() {
		return chartType;
	}
	public void setChartType(BigDecimal chartType) {
		this.chartType = chartType;
	}
	public String getChartDesc() {
		return chartDesc;
	}
	public void setChartDesc(String chartDesc) {
		this.chartDesc = chartDesc;
	}
	public BigDecimal getBelongLink() {
		return belongLink;
	}
	public void setBelongLink(BigDecimal belongLink) {
		this.belongLink = belongLink;
	}
	public String getChartUse() {
		return chartUse;
	}
	public void setChartUse(String chartUse) {
		this.chartUse = chartUse;
	}
	public String getKeyField() {
		return keyField;
	}
	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}
	public BigDecimal getFrequenceTm() {
		return frequenceTm;
	}
	public void setFrequenceTm(BigDecimal frequenceTm) {
		this.frequenceTm = frequenceTm;
	}
	public String getJsName() {
		return jsName;
	}
	public void setJsName(String jsName) {
		this.jsName = jsName;
	}
	public String getXtype() {
		return xtype;
	}
	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	
	 

}
