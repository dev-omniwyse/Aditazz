/**
 * 
 */
package com.aditazz.dto;

/**
 * @author shiva
 *
 */
public class InputDTO {
	
	private Integer minNodes;
	
	private Integer maxNodes;
	
	private Integer incrementSize;
	
	private String projectName;
	
	private String url;
	
	private Integer iterationTimeLimit;
	
	private Integer overAllTimeLimit;
	
	private String userName;
	
	private Boolean place;
	
	private Boolean route;
	
	private Boolean spacing;

	public Integer getIterationTimeLimit() {
		return iterationTimeLimit;
	}

	public void setIterationTimeLimit(Integer iterationTimeLimit) {
		this.iterationTimeLimit = iterationTimeLimit;
	}

	public Integer getOverAllTimeLimit() {
		return overAllTimeLimit;
	}

	public void setOverAllTimeLimit(Integer overAllTimeLimit) {
		this.overAllTimeLimit = overAllTimeLimit;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getMinNodes() {
		return minNodes;
	}

	public void setMinNodes(Integer minNodes) {
		this.minNodes = minNodes;
	}

	public Integer getMaxNodes() {
		return maxNodes;
	}

	public void setMaxNodes(Integer maxNodes) {
		this.maxNodes = maxNodes;
	}

	public Integer getIncrementSize() {
		return incrementSize;
	}

	public void setIncrementSize(Integer incrementSize) {
		this.incrementSize = incrementSize;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getPlace() {
		return place;
	}

	public void setPlace(Boolean place) {
		this.place = place;
	}

	public Boolean getRoute() {
		return route;
	}

	public void setRoute(Boolean route) {
		this.route = route;
	}

	public Boolean getSpacing() {
		return spacing;
	}

	public void setSpacing(Boolean spacing) {
		this.spacing = spacing;
	}
	
}
