package com.aditazz.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.aditazz.constants.UrlConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 26-Nov-2018 1:50:12 PM
 * @description : The class RestUtil.java used for
 */
public class RestUtil {
	
	private static final RestTemplate restTemplate=new RestTemplate();
	
	static {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.create();
		GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
		converter.setGson(gson);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);
	}
	
	/**
	 * 
	 * @name : getAuthenticationObject
	 * @description : The Method "getAuthenticationObject" is used for get authentication object.
	 * @date : 26-Nov-2018 1:50:36 PM
	 * @param key
	 * @return
	 * @return : JsonObject
	 *
	 */
	public static JsonObject getAuthenticationObject(String key) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",key);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<JsonObject> entity = new HttpEntity<>(null,headers);
		return restTemplate.exchange(UrlConstants.AUTH_URL, HttpMethod.POST, entity, JsonObject.class).getBody();
	}
	
	/**
	 * 
	 * @name : putObject
	 * @description : The Method "putObject" is used to creates a new resource by PUTting the given object to URL and returns the response as json object.
	 * @date : 26-Nov-2018 1:51:06 PM
	 * @param authToken
	 * @param jsonObject
	 * @param url
	 * @return
	 * @return : JsonObject
	 *
	 */
	public static JsonObject putObject(String authToken,JsonObject jsonObject,String url) {
		HttpHeaders httpHeaders=getHttpHeaders(authToken);
		HttpEntity<JsonObject> httpEntity=new HttpEntity<>(jsonObject, httpHeaders);
		return restTemplate.exchange(url, HttpMethod.PUT, httpEntity, JsonObject.class).getBody();
	}
	/**
	 * 
	 * @name : getObject
	 * @description : The Method "getObject" is used to retrieve a representation by doing a GET on the URL. The response returned as json object. 
	 * @date : 26-Nov-2018 1:53:18 PM
	 * @param authToken
	 * @param jsonObject
	 * @param url
	 * @return
	 * @return : JsonObject
	 *
	 */
	public static JsonObject getObject(String authToken,JsonObject jsonObject,String url) {
		HttpHeaders httpHeaders=getHttpHeaders(authToken);
		HttpEntity<JsonObject> httpEntity=new HttpEntity<>(jsonObject, httpHeaders);
		return restTemplate.exchange(url, HttpMethod.GET, httpEntity, JsonObject.class).getBody();
	}
	/**
	 * 
	 * @name : postObject
	 * @description : The Method "postObject" is used for create a new resource by POSTing the given object to the URL, and returns the representation found in the response as json object.
	 * @date : 26-Nov-2018 1:56:42 PM
	 * @param authToken
	 * @param jsonObject
	 * @param url
	 * @return
	 * @return : JsonObject
	 *
	 */
	public static JsonObject postObject(String authToken,JsonObject jsonObject,String url) {
		HttpHeaders httpHeaders=getHttpHeaders(authToken);
		HttpEntity<JsonObject> httpEntity=new HttpEntity<>(jsonObject, httpHeaders);
		return restTemplate.exchange(url, HttpMethod.POST, httpEntity, JsonObject.class).getBody();
	}
	
	/**
	 * 
	 * @name : getHttpHeaders
	 * @description : The Method "getHttpHeaders" is used for getting http headers. 
	 * @date : 26-Nov-2018 1:59:00 PM
	 * @param token
	 * @return
	 * @return : HttpHeaders
	 *
	 */
	private static HttpHeaders getHttpHeaders(String token){
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}
