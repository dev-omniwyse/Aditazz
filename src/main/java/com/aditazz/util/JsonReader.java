package com.aditazz.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aditazz.enums.JsonFields;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * Author : Sreekhar Reddy.K
 * Java version : Java 1.8 
 * Created On : 22-Nov-2018 12:32:53 PM
 *
 */
public class JsonReader {
	
	private static final Logger logger = LoggerFactory.getLogger(JsonReader.class);
	
	/**
	 * 
	 * @name : getEquipmentFromPlan
	 * @description : The Method "getEquipmentFromPlan" is used for reading plan json from file and parse the json and returns the response as json object.
	 * @date : 26-Nov-2018 2:01:09 PM
	 * @param path
	 * @return
	 * @return : JsonObject
	 *
	 */
	public JsonObject getEquipmentFromPlan(String path) {
		JsonObject  equipmentObject=new JsonObject();
		InputStream is = getClass().getResourceAsStream(path);
		try(Reader reader = new InputStreamReader(is)) {
			JsonElement jelement = new JsonParser().parse(reader);
			JsonObject  jobject = jelement.getAsJsonObject();
		     JsonArray jsonArray = jobject.getAsJsonArray(JsonFields.PLANS.getValue());
			    for (int i = 0; i < jsonArray.size();){
			    	JsonElement innerElement=jsonArray.get(i);
			    	JsonObject  innerObject = innerElement.getAsJsonObject();
			    	JsonObject payloadObject=innerObject.getAsJsonObject(JsonFields.PAYLOAD.getValue());
			    	if (payloadObject.has(JsonFields.EQUIPMENT.getValue()))
			    		equipmentObject.add(JsonFields.EQUIPMENT.getValue(), payloadObject.getAsJsonObject(JsonFields.EQUIPMENT.getValue()));
			    	break;
			    }
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return equipmentObject;
	}
	/**
	 * 
	 * @name : getEquipmentFromPfds
	 * @description : The Method "getEquipmentFromPfds" is used for reading pfd json from file and parse the json object and returns the response as json object.  
	 * @date : 26-Nov-2018 2:03:54 PM
	 * @param path
	 * @return
	 * @return : JsonObject
	 *
	 */
	
	public JsonObject getEquipmentFromPfds(String path) {
		JsonObject  equipmentObject=new JsonObject();
		InputStream is = getClass().getResourceAsStream(path);
		try(Reader reader = new InputStreamReader(is)) {
			JsonElement jelement = new JsonParser().parse(reader);
			JsonObject  jobject = jelement.getAsJsonObject();
		     JsonArray jsonArray = jobject.getAsJsonArray(JsonFields.PFDS.getValue());
			    for (int i = 0; i < jsonArray.size(); ){
			    	JsonElement innerElement=jsonArray.get(i);
			    	JsonObject  innerObject = innerElement.getAsJsonObject();
			    	JsonObject payloadObject=innerObject.getAsJsonObject(JsonFields.PAYLOAD.getValue());
			    	if (payloadObject.has(JsonFields.EQUIPMENT.getValue()))
			    		equipmentObject.add(JsonFields.EQUIPMENT.getValue(), payloadObject.getAsJsonObject(JsonFields.EQUIPMENT.getValue()));
			    	break;
			    }
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return equipmentObject;
	}
	/**
	 * 
	 * @name : getPfdObject
	 * @description : The Method "getPfdObject" is used for reading pfd json from file and return response as json object.
	 * @date : 26-Nov-2018 2:03:09 PM
	 * @param path
	 * @return
	 * @return : JsonObject
	 *
	 */
	public JsonObject getPfdObject(String path) {
		JsonObject  jobject=new JsonObject();
		InputStream is = getClass().getResourceAsStream(path);
		try(Reader reader = new InputStreamReader(is)) {
			JsonElement jelement = new JsonParser().parse(reader);
			jobject = jelement.getAsJsonObject();
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return jobject;
	}
	/**
	 * 
	 * @name : getPfdRevision
	 * @description : The Method "getPfdRevision" is used for getting revision number. 
	 * @date : 15-Dec-2018 11:34:10 AM
	 * @param jsonObject
	 * @return
	 * @return : int
	 *
	 */
	public int getPfdRevision(JsonObject jsonObject) {
		JsonObject revisonObj=jsonObject.get(JsonFields.REVISON.getValue()).getAsJsonObject();
		return Integer.parseInt(revisonObj.get(JsonFields.ID.getValue()).getAsString());
	}
	
	
}
