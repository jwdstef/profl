package com.radixdigit.entity;

import java.util.List;

public class NetTree {
	
	//线路ID
	private String id;
	//节点名称
	private String name;
	
	private String baseCode;
	
	
	
	public String getBaseCode() {
		return baseCode;
	}

	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}

	public NetTree(){
	}
	
	public NetTree(String name){
		this.name=name;
	}
	
	//塔集合
	private List<NetTree> towerList;
	
	//杆集合
	private List<NetTree> pointList;
	
	
	public List<NetTree> getTowerList() {
		return towerList;
	}
	public void setTowerList(List<NetTree> towerList) {
		this.towerList = towerList;
	}
	public List<NetTree> getPointList() {
		return pointList;
	}
	public void setPointList(List<NetTree> pointList) {
		this.pointList = pointList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
