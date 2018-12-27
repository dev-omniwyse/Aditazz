package com.aditazz.dto;
/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 18-Dec-2018 6:58:34 PM
 * @description : The class AditaazStatsDTO.java used for
 */
public class AditazzStatsDTO {
	private int equipments;
	private int lines;
	private int numberOfObjects;
	private int numberOfRulesChecked;
	private String equivalency;
	private String isTimedOut;
	private double equipmentPlacementTime;//secs
	private double pipeRouterTime;//secs
	private double equivalencyVerifiedTime;//secs
	private double totalElpsedTime;//minutes
	private int throughput;
	
	public int getNumberOfObjects() {
		return numberOfObjects;
	}
	public void setNumberOfObjects(int numberOfObjects) {
		this.numberOfObjects = numberOfObjects;
	}
	public int getNumberOfRulesChecked() {
		return numberOfRulesChecked;
	}
	public void setNumberOfRulesChecked(int numberOfRulesChecked) {
		this.numberOfRulesChecked = numberOfRulesChecked;
	}
	public String getEquivalency() {
		return equivalency;
	}
	public void setEquivalency(String equivalency) {
		this.equivalency = equivalency;
	}
	public String getIsTimedOut() {
		return isTimedOut;
	}
	public void setIsTimedOut(String isTimedOut) {
		this.isTimedOut = isTimedOut;
	}
	public double getPipeRouterTime() {
		return pipeRouterTime;
	}
	public void setPipeRouterTime(double pipeRouterTime) {
		this.pipeRouterTime = pipeRouterTime;
	}
	public double getEquivalencyVerifiedTime() {
		return equivalencyVerifiedTime;
	}
	public void setEquivalencyVerifiedTime(double equivalencyVerifiedTime) {
		this.equivalencyVerifiedTime = equivalencyVerifiedTime;
	}
	public int getThroughput() {
		return throughput;
	}
	public void setThroughput(int throughput) {
		this.throughput = throughput;
	}
	public int getEquipments() {
		return equipments;
	}
	public void setEquipments(int equipments) {
		this.equipments = equipments;
	}
	public int getLines() {
		return lines;
	}
	public void setLines(int lines) {
		this.lines = lines;
	}
	
	public double getEquipmentPlacementTime() {
		return equipmentPlacementTime;
	}
	public void setEquipmentPlacementTime(double equipmentPlacementTime) {
		this.equipmentPlacementTime = equipmentPlacementTime;
	}
	public double getTotalElpsedTime() {
		return totalElpsedTime;
	}
	public void setTotalElpsedTime(double totalElpsedTime) {
		this.totalElpsedTime = totalElpsedTime;
	}
	
	
	
}
