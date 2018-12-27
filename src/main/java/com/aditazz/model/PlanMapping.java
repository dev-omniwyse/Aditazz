package com.aditazz.model;

import java.util.Map;
/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 06-Dec-2018 10:28:59 AM
 * @description : The class PlanMapping.java used for
 */
public class PlanMapping {
	private String id;
	private String type;
	private String endpoint;
	private String side;
	private double[] position;
	private double[] coordinates;
	private Map<String,double[]> routeTo;
	private PlanReach reachTo;
	private double yRackBoundary;
	private  double reachToOffset;
	private double routeToOffset;
	public double getReachToOffset() {
		return reachToOffset;
	}
	public void setReachToOffset(double reachToOffset) {
		this.reachToOffset = reachToOffset;
	}
	public double getRouteToOffset() {
		return routeToOffset;
	}
	public void setRouteToOffset(double routeToOffset) {
		this.routeToOffset = routeToOffset;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public double[] getPosition() {
		return position;
	}
	public void setPosition(double[] position) {
		this.position = position;
	}
	public double[] getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}
	public Map<String, double[]> getRouteTo() {
		return routeTo;
	}
	public void setRouteTo(Map<String, double[]> routeTo) {
		this.routeTo = routeTo;
	}
	public PlanReach getReachTo() {
		return reachTo;
	}
	public void setReachTo(PlanReach reachTo) {
		this.reachTo = reachTo;
	}
	public double getyRackBoundary() {
		return yRackBoundary;
	}
	public void setyRackBoundary(double yRackBoundary) {
		this.yRackBoundary = yRackBoundary;
	}
	


}
