package com.aditazz.validator;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aditazz.constants.AditazzConstants;
import com.aditazz.dto.AditazzStatsDTO;
import com.aditazz.enums.JsonFields;
import com.aditazz.model.PlanEquipment;
import com.aditazz.service.EquipmentService;
import com.aditazz.util.DistanceUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 26-Nov-2018 10:08:07 AM
 * @description : The class Validator.java used for validating pfd and plan
 */
public class Validator {
	
	private static final Logger logger = LoggerFactory.getLogger(Validator.class);
	
	/**
	 * 
	 * @name : validatePlanAndPfd
	 * @description : The Method "validatePlanAndPfd" is used for compare pfd and plan object values. 
	 * @date : 26-Nov-2018 9:52:40 AM
	 * @param pfdObject
	 * @param planObject
	 * @param equimentLib
	 * @return : Map<String,Boolean>
	 *
	 */
	public Map<String,Boolean> validatePlanAndPfd(JsonObject pfdObject,JsonObject planObject,JsonObject equimentLib,AditazzStatsDTO aditazzStatsDTO) {
		Map<String, Boolean> result=null;
		try {
			result=new HashMap<>();
			JsonObject planPayloadObject=getPayloadFromPlan(planObject);
	    	JsonObject pfdPayloadObject=pfdObject.getAsJsonObject(JsonFields.PAYLOAD.getValue());
	    	
	    	//Lines validation
	    	boolean isLinesEqual=validateLineObjects(pfdPayloadObject, planPayloadObject,aditazzStatsDTO);
	    	if(isLinesEqual) {
				logger.info("Number of lines are equal");
				aditazzStatsDTO.setEquivalencyStatus(Arrays.asList("Number of lines are equal"));
			}else {
				logger.info("Number of lines are not equal");
				aditazzStatsDTO.setEquivalencyStatus(Arrays.asList("Number of lines are not equal"));
			}
	    	
	    	//Equipment Validation
	    	boolean isEquipmentEqual=validateEquipments(pfdPayloadObject, planPayloadObject,aditazzStatsDTO);
	    	if(isEquipmentEqual) {
				logger.info("Number of equipments are equal");
				aditazzStatsDTO.setEquivalencyStatus(Arrays.asList("Number of equipments are equal"));
			}else {
				logger.info("Number of equipments are not equal");
				aditazzStatsDTO.setEquivalencyStatus(Arrays.asList("Number of equipments are not equal"));
			}
	    	boolean isValidDistance=false;
	    	if(isEquipmentEqual && isLinesEqual) {
	    		isValidDistance=validateDistance(equimentLib, planPayloadObject,pfdPayloadObject,aditazzStatsDTO);
	    		if(isValidDistance){
	    			logger.info("Valid distance found between source and target");
	    			aditazzStatsDTO.setEquivalencyStatus(Arrays.asList("Valid distance found between source and target"));
	    		}
	    		else{
	    			logger.info("Invalid distance found between source and target");
	    			aditazzStatsDTO.setEquivalencyStatus(Arrays.asList("Invalid distance found between source and target"));
	    		}
	    	}
	    	result.put(AditazzConstants.LINES_EQUAL, isLinesEqual);
	    	result.put(AditazzConstants.EQUIPMENT_EQUAL, isEquipmentEqual);
	    	result.put(AditazzConstants.VALID_DISTANCE, isValidDistance);
		} catch (Exception e) {
			logger.error("Exception occurred while comparing pfd and plan "+e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * 
	 * @name : validateLineObjects
	 * @description : The Method "validateLineObjects" is used for compare pfd lines and plan lines.  
	 * @date : 26-Nov-2018 9:54:14 AM
	 * @param pfdPayload
	 * @param planPayload
	 * @param aditazzStatsDTO
	 * @return
	 * @return : boolean
	 *
	 */
	private boolean validateLineObjects(JsonObject pfdPayload,JsonObject planPayload,AditazzStatsDTO aditazzStatsDTO) {
		boolean isEqual=true;
		try {
			JsonObject pfdLinesbject=pfdPayload.getAsJsonObject(JsonFields.LINES.getValue());
			JsonObject planPathsObject=planPayload.getAsJsonObject(JsonFields.PATHS.getValue());
			Set<Map.Entry<String,JsonElement>> pfdChildset=pfdLinesbject.entrySet();
			Set<Map.Entry<String,JsonElement>> planChildset=planPathsObject.entrySet();
			int counter=0;
			for (Entry<String, JsonElement> pfdEntry : pfdChildset) {
				counter++;
				JsonObject lineObject=pfdEntry.getValue().getAsJsonObject();
				String lineID=lineObject.get(JsonFields.ID.getValue()).getAsString();
				boolean isExistLine=false;
				for (Entry<String, JsonElement> planEntry : planChildset) {
					if(lineID.equals(planEntry.getKey())) {
						logger.info("Pfd line id :: {} and Plan line id  ::{}",lineID,planEntry.getKey());
						isExistLine=true;
						break;
					}
				}
				if(!isExistLine) {
					logger.info("Line is not exists in plan with id  :: {}",lineID);
					isEqual=false;
				}
			}
			aditazzStatsDTO.setNumberOfRulesChecked(aditazzStatsDTO.getNumberOfRulesChecked()+counter);
		} catch (Exception e) {
			logger.error("Exception occurred while validating lines "+e.getMessage(),e);
			isEqual=false;
		}
		return isEqual;
	}
	/**
	 * 
	 * @name : validateEquipments
	 * @description : The Method "validateEquipments" is used for compare pfd equipments and plan equipments. 
	 * @date : 26-Nov-2018 9:55:31 AM
	 * @param pfdPayloadObject
	 * @param planPayloadObject
	 * @param aditazzStatsDTO
	 * @return
	 * @return : boolean
	 *
	 */
	
	private boolean  validateEquipments(JsonObject pfdPayloadObject,JsonObject planPayloadObject,AditazzStatsDTO aditazzStatsDTO) {
		boolean isValid=true;
		try {
			JsonObject planEquipmentObj=planPayloadObject.getAsJsonObject(JsonFields.EQUIPMENT.getValue());
	    	
	    	JsonObject pfdEquipmentObject=pfdPayloadObject.getAsJsonObject(JsonFields.EQUIPMENT.getValue());
	    	Set<Map.Entry<String,JsonElement>> planChildset=planEquipmentObj.entrySet();
	    	Set<Map.Entry<String,JsonElement>> pfdChildset=pfdEquipmentObject.entrySet();
			// Equipment Size comparison
			logger.info("Number of equipments of plan is :: {}",planChildset.size());
			logger.info("Number of equipments of pfd is :: {}",pfdChildset.size());
			int counter=0;
			for (Entry<String, JsonElement> pfdEquipment: pfdChildset) {
				counter++;
				String pfdEquipmentId=pfdEquipment.getValue().getAsJsonObject().get(JsonFields.ID.getValue()).getAsString();
				boolean isExists=false;
				for (Entry<String, JsonElement> planEntry : planChildset) {
					if(pfdEquipmentId.equals(planEntry.getKey())) {
						logger.info("Pfd equipment id :: {} and Plan equipment id  ::{}",pfdEquipmentId,planEntry.getKey());
						isExists=true;
						break;
					}
				}
				if(!isExists) {
					logger.info("Equipment is not exists in plan with id  ::{}",pfdEquipmentId);
					isValid=false;
				}
				
			}
			aditazzStatsDTO.setNumberOfRulesChecked(aditazzStatsDTO.getNumberOfRulesChecked()+counter);
		}catch (Exception e) {
			logger.error("Exception occurred while comparing equipments and plan "+e.getMessage(),e);
			isValid=false;
		}
		return isValid;
	}
	
	
	/**
	 * 
	 * @name : getPayloadFromPlan
	 * @description : The Method "getPayloadFromPlan" is used for getting payload object from plan object.
	 * @date : 26-Nov-2018 9:59:14 AM
	 * @param planObject
	 * @return
	 * @return : JsonObject
	 *
	 */
	private JsonObject getPayloadFromPlan(JsonObject planObject) {
		JsonElement innerElement=planObject.getAsJsonArray(JsonFields.PLANS.getValue()).get(0);
    	JsonObject  innerObject = innerElement.getAsJsonObject();
    	return innerObject.getAsJsonObject(JsonFields.PAYLOAD.getValue());
	}
	
    /**
     * 
     * @name : validateDistance
     * @description : The Method "validateDistance" is used for validating space between two nodes.
     * @date : 06-Dec-2018 3:21:46 PM
     * @param equipmentLib
     * @param planPayload
     * @param pfdObject
     * @param aditazzStatsDTO
     * @return
     * @throws IOException
     * @return : boolean
     *
     */
    private boolean validateDistance(JsonObject equipmentLib,JsonObject planPayload,JsonObject pfdObject,AditazzStatsDTO aditazzStatsDTO) throws IOException {
    	ObjectMapper objectMapper = new ObjectMapper();
    	Gson gson=new Gson();
    	boolean isValid=true;
    	EquipmentService equipmentService=new EquipmentService();
    	JsonObject planEquipments=planPayload.getAsJsonObject(JsonFields.EQUIPMENT.getValue());
    	JsonObject pfdEquipment=pfdObject.getAsJsonObject(JsonFields.EQUIPMENT.getValue());
    	Map<String,JsonObject> spacingTable=equipmentService.getSpacingTableFromLib(equipmentLib);
    	DistanceUtil distanceUtil=new DistanceUtil();
    	Map<String,String> equipments=getListOfEquipmentTypes(pfdEquipment);
    	List<String>  equipmentIds=new LinkedList<>(equipments.keySet());
    	int counter=0;
    	//A -> B or B -> A same distance should be there. In spacing table consider either A->B or B->A.
    	for(int i=0;i<equipmentIds.size();i++) {
    		String sourceType=equipments.get(equipmentIds.get(i));
    		PlanEquipment sourceEquip=objectMapper.readValue(gson.toJson(planEquipments.get(equipmentIds.get(i))),PlanEquipment.class);
    		for (int j=i;j<equipmentIds.size();j++) {
    			counter++;
    			String targetType=equipments.get(equipmentIds.get(j));
    			PlanEquipment targetEquip=objectMapper.readValue(gson.toJson(planEquipments.get(equipmentIds.get(j))),PlanEquipment.class);
    			if(spacingTable.containsKey(sourceType) || spacingTable.containsKey(targetType)) {
        			double shortestDistance=distanceUtil.getShortestDistance(sourceType, targetType, spacingTable);
        			Map<String,Double> distances=distanceUtil.calculateDistance(sourceEquip, targetEquip);
        			double x=distances.get(JsonFields.X.getValue());
        			double y=distances.get(JsonFields.Y.getValue());
        			double z=distances.get(JsonFields.Z.getValue());
        			logger.info("Source type :: {} to target type :: {} spacing table distance is :: {} and plan distance X is :: {} Y is ::{} and Z is ::{} ",sourceType,targetType,shortestDistance,x,y,z);
        			
        			if(x < shortestDistance && y < shortestDistance && z < shortestDistance) {    			
        				logger.info("Invalid distance found between source type :: {} and target type :: {}",sourceType,targetType);
        				isValid=false;
        			}
        		}else {
        			logger.info("Invalid distance found between source type :: {} and target type :: {}",sourceType,targetType);
    				isValid=false;
    			}
    		}
    	}
    	aditazzStatsDTO.setNumberOfRulesChecked(aditazzStatsDTO.getNumberOfRulesChecked()+counter);
    	return isValid;
    }
    
    
    /**
     * 
     * @name : getListOfEquipmentTypes
     * @description : The Method "getListOfEquipmentTypes" is used for getting equipment Types and ids.
     * @date : 11-Dec-2018 1:07:29 PM
     * @param jsonObject
     * @return
     * @return : Map<String,String>
     *
     */
    private Map<String,String> getListOfEquipmentTypes(JsonObject jsonObject){
    	Map<String,String> types=new HashMap<>();
    	Set<Map.Entry<String,JsonElement>> planChildset=jsonObject.entrySet();
    	for(Entry<String , JsonElement> entry : planChildset) {
    		JsonObject equipment=entry.getValue().getAsJsonObject();
    		types.put(equipment.get(JsonFields.ID.getValue()).getAsString(),equipment.get(JsonFields.TYPE.getValue()).getAsString());
    	}
    	return types;
    }
    
    
}
