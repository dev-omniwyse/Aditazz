package com.aditazz.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aditazz.constants.UrlConstants;
import com.aditazz.enums.JsonFields;
import com.aditazz.model.Aditazz;
import com.aditazz.util.RestUtil;
import com.google.gson.JsonObject;



/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 05-Dec-2018 9:36:21 AM
 * @description : The class PipeService.java used for
 */
@Service
public class PipeService {
	/**
	 * 
	 * @name : getListOfPipes
	 * @description : The Method "getListOfPipes" is used for getting the list of pipes from pipe library. 
	 * @date : 05-Dec-2018 11:39:05 AM
	 * @param aditazz
	 * @return
	 * @return : List<String>
	 *
	 */
	public List<String> getListOfPipes(Aditazz aditazz) {
		JsonObject jsonObject=RestUtil.getObject(aditazz.getAuthToken(), null, UrlConstants.PIPE_LIB_URL+aditazz.getPipeLibId());
		JsonObject payloadObj=jsonObject.get(JsonFields.PIPE_LIBRARIES.getValue()).getAsJsonArray().get(0).getAsJsonObject().get(JsonFields.PAYLOAD.getValue()).getAsJsonObject();
		JsonObject pipeJson=payloadObj.get(JsonFields.PIPES.getValue()).getAsJsonObject();
		return new LinkedList<>(pipeJson.keySet());
	}
	/**
	 * 
	 * @name : createDuplacatePipes
	 * @description : The Method "createDuplacatePipes" is used for 
	 * @date : 19-Dec-2018 1:56:30 PM
	 * @param pipeIds
	 * @param size
	 * @return
	 * @return : List<String>
	 *
	 */
	public List<String> createDuplacatePipes(List<String> pipeIds,int size){
		while( size > pipeIds.size() ) {
			pipeIds.addAll(pipeIds);
		}
		return pipeIds;
	}

}
