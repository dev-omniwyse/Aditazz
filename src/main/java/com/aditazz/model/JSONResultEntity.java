package com.aditazz.model;

import java.util.ArrayList;
import java.util.List;

public class JSONResultEntity<T> {

	private boolean success;
    private String message;
    private List<String> errors = new ArrayList<String>();
    private boolean isTotal;
    public List<T> results;

    public JSONResultEntity(boolean success, String message,
            List<String> errors, boolean isTotal, List<T> results) {
        this.success = success;
        this.message = message;
        this.errors = errors;
        this.isTotal = isTotal;
        this.results = results;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

	public boolean isTotal() {
		return isTotal;
	}

	public void setTotal(boolean isTotal) {
		this.isTotal = isTotal;
	}
}
