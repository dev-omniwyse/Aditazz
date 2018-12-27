package com.aditazz.model;

public class LineTarget {
	private String uuid;
	private String side;
	private String type;
	private Double[] position;
	private String nozzle;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double[] getPosition() {
		return position;
	}
	public void setPosition(Double[] position) {
		this.position = position;
	}
	public String getNozzle() {
		return nozzle;
	}
	public void setNozzle(String nozzle) {
		this.nozzle = nozzle;
	}
}
