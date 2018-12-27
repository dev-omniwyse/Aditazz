package com.aditazz.model;
/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 03-Dec-2018 10:39:07 AM
 * @description : The class LineSource.java used for
 */
public class LineSource {
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
	
    /*"uuid": "69ad42cd-a524-fe62-3177-f5c23270fadb",
    "side": "right",
    "type": "equipment",
    "position": [
        1,
        0.5
    ],
    "nozzle": "69ad42cd-a524-fe62-3177-f5c23270fadbright1"*/
}
