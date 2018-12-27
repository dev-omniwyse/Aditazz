package com.aditazz.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author      : Sreekhar Reddy.K
 * @createdOn   : 04-Dec-2018 2:24:34 PM
 * @description : The class Aditazz.java used for
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Aditazz {
	private String authToken;
	private String projectId;
	private String equipLibId;
	private String pipeLibId;
	private String optionId;
	private String planId;
	private String pfdId;
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getEquipLibId() {
		return equipLibId;
	}
	public void setEquipLibId(String equipLibId) {
		this.equipLibId = equipLibId;
	}
	public String getPipeLibId() {
		return pipeLibId;
	}
	public void setPipeLibId(String pipeLibId) {
		this.pipeLibId = pipeLibId;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getPfdId() {
		return pfdId;
	}
	public void setPfdId(String pfdId) {
		this.pfdId = pfdId;
	}
	
}
