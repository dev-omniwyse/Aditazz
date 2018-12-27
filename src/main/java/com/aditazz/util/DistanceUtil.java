package com.aditazz.util;

import java.util.HashMap;
import java.util.Map;

import com.aditazz.enums.JsonFields;
import com.aditazz.model.PlanEquipment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 06-Dec-2018 3:22:11 PM
 * @description : The class DistanceUtil.java used for
 */
public class DistanceUtil {
	/**
	 * 
	 * @name : getShortestDistance
	 * @description : The Method "getShortestDistance" is used for getting shortest distance between two nodes from space table.
	 * @date : 06-Dec-2018 3:22:16 PM
	 * @param sourceType
	 * @param targetType
	 * @param spacingTable
	 * @return
	 * @return : double
	 *
	 */
	public double getShortestDistance(String sourceType,String targetType,Map<String,JsonObject> spacingTable) {
		double shortestDistance;
		JsonObject spaces=spacingTable.get(sourceType);
		if(spaces.has(targetType)) {
			shortestDistance= spaces.get(targetType).getAsDouble();
		} else {
			spaces =spacingTable.get(targetType);
			shortestDistance=spaces.get(sourceType).getAsDouble();
		}
		return shortestDistance;
	}
	/**
	 * 
	 * @name : updateShortestDistance
	 * @description : The Method "updateShortestDistance" is used for updating shortest distance in spacing table 
	 * @date : 06-Dec-2018 3:22:50 PM
	 * @param sourceType
	 * @param targetType
	 * @param spacingTable
	 * @return
	 * @return : Map<String,JsonObject>
	 *
	 */
	public Map<String,JsonObject> updateShortestDistance(String sourceType,String targetType,Map<String,JsonObject> spacingTable) {
		Gson gson=new Gson();
		double shortestDistance;
		JsonObject spaces=spacingTable.get(sourceType);
		boolean fromSource=false;
		if(spaces.has(targetType)) {
			shortestDistance= spaces.get(targetType).getAsDouble();
			fromSource=true;
		} else {
			spaces =spacingTable.get(targetType);
			shortestDistance=spaces.get(sourceType).getAsDouble();
		}
		
		shortestDistance=Math.round((shortestDistance+((shortestDistance/100)*15)) * 100D) / 100D;
		if(fromSource) {
			spaces.add(targetType, gson.toJsonTree(shortestDistance));
			spacingTable.put(sourceType, spaces);
		}else {
			spaces.add(sourceType, gson.toJsonTree(shortestDistance));
			spacingTable.put(targetType, spaces);
		}
		return spacingTable;
	}
	
	/**
	 * 
	 * @name : getManhattanDistance
	 * @description : The Method "getManhattanDistance" is used for calculating manhatton distance. 
	 * @date : 06-Dec-2018 3:23:26 PM
	 * @param cord1
	 * @param cord2
	 * @return
	 * @return : double
	 *
	 */
	public double getManhattanDistance(double[] cord1,double[] cord2) {
        //computing Manhattan Distance
	    double x=Math.abs(cord1[0]-cord2[0]);
	    double y=Math.abs(cord1[1]-cord2[1]);
	    double z=Math.abs(cord1[2]-cord2[2]);
	    double distance=(x+y+z);
	    //to find the shortest distance between the coordinates
	    //double shortest=Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2))
	    //Displaying Manhattan Distance, Shortest Distance, and Coordinates
	    //logger.info("Manhattan distance is :: {}",distance)
	    
    	return distance;
    }
	/**
	 * 
	 * @name : calculateDistance
	 * @description : The Method "calculateDistance" is used for 
	 * @date : 10-Dec-2018 12:53:13 PM
	 * @param sourceEquip
	 * @param targetEquip
	 * @return
	 * @return : Map<String,Double>
	 *
	 */
	public Map<String,Double> calculateDistance(PlanEquipment sourceEquip,PlanEquipment targetEquip) {
		Map<String,Double> distances=new HashMap<>();
		double[] sourcePosition=sourceEquip.getPosition();
		double[] sourceDime=sourceEquip.getDimensions();
		double[] targetPosition=targetEquip.getDimensions();
		distances.put(JsonFields.X.getValue(), calculateDistance(sourcePosition[0],sourceDime[0],  targetPosition[0]));
		distances.put(JsonFields.Y.getValue(), calculateDistance(sourceDime[1], sourcePosition[1], targetPosition[1]));
		distances.put(JsonFields.Z.getValue(), calculateDistance(sourceDime[2], sourcePosition[2], targetPosition[2]));
		return distances;
	}
	/**
	 * 
	 * @name : calculateDistance
	 * @description : The Method "calculateDistance" is used for 
	 * @date : 10-Dec-2018 12:43:52 PM
	 * @param sourcePosi
	 * @param sourceDimension
	 * @param targetPosition
	 * @return
	 * @return : double
	 *
	 */
	private double calculateDistance(double sourcePosi,double sourceDimension,double targetPosition) {
		return Math.abs((sourcePosi+sourceDimension)-targetPosition);
	}
}
