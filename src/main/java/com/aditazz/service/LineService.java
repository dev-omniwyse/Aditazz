package com.aditazz.service;
/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 03-Dec-2018 10:33:05 AM
 * @description : The class LineService.java used for
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aditazz.enums.JsonFields;
import com.aditazz.model.Line;
import com.aditazz.model.LineSource;
import com.aditazz.model.LineTarget;

@Service
public class LineService {
	/**
	 * 
	 * @name : preparePfdLine
	 * @description : The Method "preparePfdLine" is used for pfd line objects.
	 * @date : 03-Dec-2018 3:10:44 PM
	 * @param line
	 * @param uuid
	 * @param sourceSide
	 * @param targetSide
	 * @param sourceUuid
	 * @param targetUuid
	 * @return
	 * @return : Line
	 *
	 */
	public Line preparePfdLine(Line line,String uuid,String sourceSide,String targetSide,String sourceUuid,String targetUuid,List<Integer[]> path) {
		line.setUuid(uuid);
		line.setStraight(false);
		Map<String,Object> errors=new HashMap<>();
		errors.put(JsonFields.IDERR.getValue(), false);
		line.setErrors(errors);
        LineSource lineSource=getLineSource(sourceUuid, sourceSide);
        line.setSource(lineSource);
        line.setDrawing(false);
        line.setPath(path);
        LineTarget lineTarget=getLineTarget(targetSide, targetUuid);
        line.setTarget(lineTarget);
        line.setLength(46);
        return line;
	}
	/**
	 * 
	 * @name : getLineSource
	 * @description : The Method "getLineSource" is used for preparing line source
	 * @date : 03-Dec-2018 3:35:21 PM
	 * @param sourceUuid
	 * @param sourceSide
	 * @return
	 * @return : LineSource
	 *
	 */
	private LineSource getLineSource(String sourceUuid,String sourceSide) {
		LineSource lineSource=new LineSource();
        lineSource.setUuid(sourceUuid);
        lineSource.setSide(sourceSide);
        lineSource.setType(JsonFields.EQUIPMENT.getValue());
        lineSource.setPosition(new Double[] {0d,0d});
        lineSource.setNozzle(sourceUuid+sourceSide+"1");
        return lineSource;
	}
	/**
	 * 
	 * @name : getLineTarget
	 * @description : The Method "getLineTarget" is used for preparing line target 
	 * @date : 03-Dec-2018 3:35:36 PM
	 * @param targetSide
	 * @param targetUuid
	 * @return
	 * @return : LineTarget
	 *
	 */
	private LineTarget getLineTarget(String targetSide,String targetUuid) {
		LineTarget lineTarget=new LineTarget();
		lineTarget.setUuid(targetUuid);
		lineTarget.setSide(targetSide);
		lineTarget.setType(JsonFields.EQUIPMENT.getValue());
		lineTarget.setPosition(new Double[] {0d,0d});
		lineTarget.setNozzle(targetUuid+targetSide+"1");
		return lineTarget;
	}
	
	
}

