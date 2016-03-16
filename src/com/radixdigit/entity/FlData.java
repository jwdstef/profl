package com.radixdigit.entity;

public class FlData {
	
	private String yaxis;
	private String xdate;
	private String baseCode;
	private String type;
	private String defmean;
	private String depepre;
	private String changeAvalue;
	private String changeShot;
	private String fdateTime;
	private String iflag;
	
	
	
	public String getIflag() {
		return iflag;
	}
	public void setIflag(String iflag) {
		this.iflag = iflag;
	}
	public String getFdateTime() {
		return fdateTime;
	}
	public void setFdateTime(String fdateTime) {
		this.fdateTime = fdateTime;
	}
	public String getChangeAvalue() {
		return String.valueOf(Double.parseDouble(changeAvalue));
	}
	public void setChangeAvalue(String changeAvalue) {
		this.changeAvalue = changeAvalue;
	}
	public String getChangeShot() {
		return String.valueOf(Double.parseDouble(changeShot));
	}
	public void setChangeShot(String changeShot) {
		this.changeShot = changeShot;
	}
	public String getDefmean() {
		return defmean;
	}
	public void setDefmean(String defmean) {
		this.defmean = defmean;
	}
	public String getDepepre() {
		return depepre;
	}
	public void setDepepre(String depepre) {
		this.depepre = depepre;
	}
	public String getBaseCode() {
		return baseCode;
	}
	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getYaxis() {
		String str = yaxis;
		if( !"0.0".equals(yaxis) && yaxis != null && !"0".equals(yaxis)){
			str = yaxis.substring(0, 6);
		}
		return str;
	}
	public void setYaxis(String yaxis) {
			this.yaxis = yaxis;
	}
	public String getXdate() {
		String str = xdate.substring(2);
		return str.substring(0, 2) + ":" + str.substring(2,4);
	}
	public void setXdate(String xdate) {
		this.xdate = xdate;
	}

	public static void main(String args[]){
		FlData fd = new FlData();
		fd.setYaxis("195245.00");
	}
}
