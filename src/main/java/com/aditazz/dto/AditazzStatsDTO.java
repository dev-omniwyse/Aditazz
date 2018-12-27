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
	private long equipmentPlacementTime;//secs
	private long pipeRouterTime;//secs
	private long equivalencyVerifiedTime;//secs
	private long totalElpsedTime;//minutes
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
	public long getPipeRouterTime() {
		return pipeRouterTime;
	}
	public void setPipeRouterTime(long pipeRouterTime) {
		this.pipeRouterTime = pipeRouterTime;
	}
	public long getEquivalencyVerifiedTime() {
		return equivalencyVerifiedTime;
	}
	public void setEquivalencyVerifiedTime(long equivalencyVerifiedTime) {
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
	
	public long getEquipmentPlacementTime() {
		return equipmentPlacementTime;
	}
	public void setEquipmentPlacementTime(long equipmentPlacementTime) {
		this.equipmentPlacementTime = equipmentPlacementTime;
	}
	public long getTotalElpsedTime() {
		return totalElpsedTime;
	}
	public void setTotalElpsedTime(long totalElpsedTime) {
		this.totalElpsedTime = totalElpsedTime;
	}
	
	
	
}
