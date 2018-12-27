package com.aditazz.model;

import java.util.Map;

/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 29-Nov-2018 10:43:54 AM
 * @description : The class PfdEquipment.java used for
 */
public class PfdEquipment {
	private long area;
	private Integer[] dimensions;
	private Long elevation;
	private Map<String,Object> errors;
	private Grid grid; 
	private String id;
	private boolean isResizing;
	private String[] nozzlesToReconciliate;
	private String[] occupiedNozzles;
	private String type;
	private Integer x;
	private Integer y;
	private boolean dragging;
	private Integer lastX;
	private Integer lastY;
	private boolean grouping; 
	
	public boolean isGrouping() {
		return grouping;
	}
	public void setGrouping(boolean grouping) {
		this.grouping = grouping;
	}
	public boolean isDragging() {
		return dragging;
	}
	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}
	public Integer getLastX() {
		return lastX;
	}
	public void setLastX(Integer lastX) {
		this.lastX = lastX;
	}
	public Integer getLastY() {
		return lastY;
	}
	public void setLastY(Integer lastY) {
		this.lastY = lastY;
	}
	public long getArea() {
		return area;
	}
	public void setArea(long area) {
		this.area = area;
	}
	public Integer[] getDimensions() {
		return dimensions;
	}
	public void setDimensions(Integer[] dimensions) {
		this.dimensions = dimensions;
	}
	public long getElevation() {
		return elevation;
	}
	public void setElevation(long elevation) {
		this.elevation = elevation;
	}
	public Map<String, Object> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, Object> errors) {
		this.errors = errors;
	}
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean getIsResizing() {
		return isResizing;
	}
	public void setIsResizing(boolean isResizing) {
		this.isResizing = isResizing;
	}
	public String[] getNozzlesToReconciliate() {
		return nozzlesToReconciliate;
	}
	public void setNozzlesToReconciliate(String[] nozzlesToReconciliate) {
		this.nozzlesToReconciliate = nozzlesToReconciliate;
	}
	public String[] getOccupiedNozzles() {
		return occupiedNozzles;
	}
	public void setOccupiedNozzles(String[] occupiedNozzles) {
		this.occupiedNozzles = occupiedNozzles;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	
}
