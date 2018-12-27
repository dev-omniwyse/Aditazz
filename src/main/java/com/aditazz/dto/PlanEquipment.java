package com.aditazz.dto;

import java.util.Map;
/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 29-Nov-2018 10:43:01 AM
 * @description : The class PlanEquipment.java used for
 */
public class PlanEquipment {
	private double[] dimensions;
	private Lines lines;
	private Nodes nodes;
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	private double[] position;
	private String type; 
	private Map<String,Map<String,Object>> nozzles;
	private Integer length;
	
	
	public double[] getDimensions() {
		return dimensions;
	}
	public void setDimensions(double[] dimensions) {
		this.dimensions = dimensions;
	}
	public Lines getLines() {
		return lines;
	}
	public void setLines(Lines lines) {
		this.lines = lines;
	}
	public Nodes getNodes() {
		return nodes;
	}
	public void setNodes(Nodes nodes) {
		this.nodes = nodes;
	}
	public double[] getPosition() {
		return position;
	}
	public void setPosition(double[] position) {
		this.position = position;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, Map<String, Object>> getNozzles() {
		return nozzles;
	}
	public void setNozzles(Map<String, Map<String, Object>> nozzles) {
		this.nozzles = nozzles;
	}
	
}
