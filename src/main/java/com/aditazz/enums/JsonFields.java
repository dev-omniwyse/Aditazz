package com.aditazz.enums;

/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 26-Nov-2018 11:05:09 AM
 * @description : The enum JsonFields.java used to declare json fields. 
 */
public enum JsonFields {
	ID("id"),
	STATUS("status"),
	PFDS("pfds"),
	PLANS("plans"),
	PAYLOAD("payload"),
	LINES("lines"),
	PATHS("paths"),
	ACCESS_TOKEN("accessToken"),  
    EQUIPMENT("equipment"),
    OPTIONS("options"),
    PFD_ID("pfdid"),
    PLAN_ID("planid"),
    PFD("pfd"),
    PLAN("plan"),
    IDERR("idErr"),
    PROJECTS("projects"),
    EQUIPMENT_LIBRARY("equipment_library"),
    PIPE_LIBRARY("pipe_library"),
    EQUIPMENT_LIBRARIES("equipment_libraries"),
    ROW_ORDER("rowOrder"),
    SPACING("spacing"),
    PIPE_LIBRARIES("pipe_libraries"),
    PIPES("pipes"),
    REVISON("revision"),
    TYPES("types"),
    TYPE("type"),
    X("x"),
    Y("y"),
    Z("z"),
    PLACE("place"),
    ROUTE("route");
	

    private final String value;

    private JsonFields(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}
}
