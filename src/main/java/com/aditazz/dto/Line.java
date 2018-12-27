package com.aditazz.dto;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 03-Dec-2018 10:34:24 AM
 * @description : The class Line.java used for
 */
public class Line {
	private String uuid;
	private String id;
	private boolean straight;
	private Map<String,Object> errors;
	private LineSource source;
	private boolean drawing;
	private LineTarget target;
	private List<Integer[]> path;
	private long length;
	private String pipeID;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isStraight() {
		return straight;
	}
	public void setStraight(boolean straight) {
		this.straight = straight;
	}
	public Map<String, Object> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, Object> errors) {
		this.errors = errors;
	}
	public LineSource getSource() {
		return source;
	}
	public void setSource(LineSource source) {
		this.source = source;
	}
	public boolean isDrawing() {
		return drawing;
	}
	public void setDrawing(boolean drawing) {
		this.drawing = drawing;
	}
	public LineTarget getTarget() {
		return target;
	}
	public void setTarget(LineTarget target) {
		this.target = target;
	}
	public List<Integer[]> getPath() {
		return path;
	}
	public void setPath(List<Integer[]> path) {
		this.path = path;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	public String getPipeID() {
		return pipeID;
	}
	public void setPipeID(String pipeID) {
		this.pipeID = pipeID;
	}
	
}
