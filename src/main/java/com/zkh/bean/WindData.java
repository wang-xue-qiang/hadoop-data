package com.zkh.bean;
public class WindData {
	private String windField;//风场
	private String fan;//风机
	private Double windSpeed;//风速m/s
	private Double eletc;// 转化电能
	private String createTime;//创建时间
	public  String getWindField() {
		return windField;
	}
	public void setWindField(String windField) {
		this.windField = windField;
	}
	public String getFan() {
		return fan;
	}
	public void setFan(String fan) {
		this.fan = fan;
	}
	public Double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public Double getEletc() {
		return eletc;
	}
	public void setEletc(Double eletc) {
		this.eletc = eletc;
	}
	public WindData() {}
	public WindData(String windField, String fan, Double windSpeed,
			Double eletc,String createTime) {
		super();
		this.windField = windField;
		this.fan = fan;
		this.windSpeed = windSpeed;
		this.eletc = eletc;
		this.createTime = createTime;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public static void main(String[] args) {
		WindData vo = new WindData();
		System.out.println(vo);
	}
}
