package com.aditazz.service;
/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 30-Nov-2018 5:00:50 PM
 * @description : The class EquipmentService.java used for
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aditazz.constants.UrlConstants;
import com.aditazz.enums.JsonFields;
import com.aditazz.model.Aditazz;
import com.aditazz.model.Grid;
import com.aditazz.model.PfdEquipment;
import com.aditazz.util.RestUtil;
import com.google.gson.JsonObject;

@Service
public class EquipmentService {
	private static final Logger logger = LoggerFactory.getLogger(AditazzService.class);
	
	/**
	 * 
	 * @name : preparePfdEquipment
	 * @description : The Method "preparePfdEquipment" is used for preparing the equipment object.
	 * @date : 03-Dec-2018 3:10:00 PM
	 * @param pfdEquipment
	 * @param type
	 * @param uuid
	 * @param id
	 * @param location
	 * @param x
	 * @param y
	 * @return
	 * @return : PfdEquipment
	 *
	 */
	public PfdEquipment preparePfdEquipment(PfdEquipment pfdEquipment,String type,String uuid,String location,int x,int y) {
		pfdEquipment.setType(type);
		pfdEquipment.setId(uuid);
		pfdEquipment.setDimensions(new Integer[] {2,2,6});
		pfdEquipment.setX(x);
		pfdEquipment.setY(y);
		Map<String,Object> errors=new HashMap<>();
		errors.put(JsonFields.IDERR.getValue(), false);
		pfdEquipment.setErrors(errors);
		pfdEquipment.setIsResizing(false);
		Grid grid=new Grid();
		grid.setDimensions(new int[] { 5,20});
		grid.setLastDimensions(new int[] { 5, 20});
		pfdEquipment.setGrid(grid);
		pfdEquipment.setDragging(false);
		pfdEquipment.setArea(0);
		pfdEquipment.setElevation(2);
		pfdEquipment.setGrouping(true);
		pfdEquipment.setNozzlesToReconciliate(new String[] {});
		pfdEquipment.setOccupiedNozzles(new String[] {uuid+location});
		return pfdEquipment;
	}
	
	/**
	 * 
	 * @name : getEquipments
	 * @description : The Method "getEquipments" is used for getting payload from equipment library. 
	 * @date : 06-Dec-2018 9:35:18 AM
	 * @param aditazz
	 * @return
	 * @return : JsonObject
	 *
	 */
	public JsonObject getEquipments(Aditazz aditazz) {
		return RestUtil.getObject(aditazz.getAuthToken(), null, UrlConstants.EQUIPMENT_LIB_URL+aditazz.getEquipLibId());
	}
	
	/**
	 * 
	 * @name : getRevisonNumber
	 * @description : The Method "getRevisonNumber" is used for 
	 * @date : 06-Dec-2018 4:17:41 PM
	 * @param aditazz
	 * @return
	 * @return : int
	 *
	 */
	public int getRevisonNumber(Aditazz aditazz) {
		JsonObject jsonObject=RestUtil.getObject(aditazz.getAuthToken(), null, UrlConstants.EQUIPMENT_LIB_URL+aditazz.getEquipLibId());
		JsonObject revison=jsonObject.get(JsonFields.EQUIPMENT_LIBRARIES.getValue()).getAsJsonArray().get(0).getAsJsonObject().get(JsonFields.REVISON.getValue()).getAsJsonObject();
		return revison.get(JsonFields.ID.getValue()).getAsInt();
	}
	
	/**
	 * 
	 * @name : updateEquipmentLibrary
	 * @description : The Method "updateEquipmentLibrary" is used for updating equipment payload in equipment library. 
	 * @date : 06-Dec-2018 3:19:03 PM
	 * @param aditazz
	 * @param payloadObj
	 * @param updatedLib
	 * @return
	 * @return : boolean
	 *
	 */
	public boolean updateEquipmentLibrary(Aditazz aditazz,JsonObject payloadObj,JsonObject updatedLib) {
		JsonObject libraryObj=updatedLib.get(JsonFields.EQUIPMENT_LIBRARIES.getValue()).getAsJsonArray().get(0).getAsJsonObject();
		libraryObj.add(JsonFields.PAYLOAD.getValue(), payloadObj);
		logger.info("Updating equipment library.............!");
		JsonObject response=RestUtil.putObject(aditazz.getAuthToken(), libraryObj, UrlConstants.EQUIPMENT_LIB_URL+aditazz.getEquipLibId());
		logger.info("Updated successfully equipment library.............!");
		return response !=null;
	}
	
	/**
	 * 
	 * @name : jsonStringToArray
	 * @description : The Method "jsonStringToArray" is used for getting list of equipment types from payload.
	 * @date : 06-Dec-2018 9:35:50 AM
	 * @param jsonObject
	 * @return
	 * @return : List<String>
	 *
	 */
	public List<String> jsonStringToArray(JsonObject jsonObject) {
	    JsonObject equipmentTypes=jsonObject.get(JsonFields.TYPES.getValue()).getAsJsonObject();
		return new LinkedList<>(equipmentTypes.keySet());
	}
	/**
	 * 
	 * @name : getSpacingTableFromLib
	 * @description : The Method "getPossibleEquipmentMapping" is used for getting spacing from equipment library payload.  
	 * @date : 06-Dec-2018 9:39:20 AM
	 * @param jsonObject
	 * @return
	 * @return : Map<String,JsonObject>
	 *
	 */
	public Map<String,JsonObject> getSpacingTableFromLib(JsonObject jsonObject) {
		JsonObject spacing=jsonObject.get(JsonFields.SPACING.getValue()).getAsJsonObject();
		Map<String,JsonObject> mapping=new HashMap<>();
		Set<String> keySet=spacing.keySet(); 
		for (String key : keySet) {
			JsonObject mappingObject=spacing.get(key).getAsJsonObject();
			mapping.put(key, mappingObject);
		}
		return mapping;
	}
	
	/**
	 * 
	 * @name : createDuplacateEquipments
	 * @description : The Method "createDuplacateEquipments" is used for 
	 * @date : 19-Dec-2018 1:40:23 PM
	 * @param equipments
	 * @param size
	 * @return
	 * @return : List<String>
	 *
	 */
	public List<String> createDuplacateEquipments(List<String> equipments,int size){
		while( size > equipments.size() ) {
			equipments.addAll(equipments);
		}
		return equipments;
	}
	
}
