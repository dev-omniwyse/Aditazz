package com.aditazz.model;

import java.util.List;

/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 06-Dec-2018 10:28:25 AM
 * @description : The class PlanLine.java used for
 */
public class PlanLine {
	private String status;
	private PlanMapping source;
	private PlanMapping target;
	private String pipeID;
	private List<String[]> path; 
	public List<String[]> getPath() {
		return path;
	}
	public void setPath(List<String[]> path) {
		this.path = path;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public PlanMapping getSource() {
		return source;
	}
	public void setSource(PlanMapping source) {
		this.source = source;
	}
	public PlanMapping getTarget() {
		return target;
	}
	public void setTarget(PlanMapping target) {
		this.target = target;
	}
	public String getPipeID() {
		return pipeID;
	}
	public void setPipeID(String pipeID) {
		this.pipeID = pipeID;
	}
	
	
}