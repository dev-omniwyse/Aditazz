package com.aditazz.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aditazz.validator.Validator;

/**
 * 
 * @author      : Sreekhar Reddy.K
 * @version     : Java 1.8 
 * @createdOn   : 10-Dec-2018 11:35:15 AM
 * @description : The class FileUtil.java used for
 */
public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(Validator.class);
	/**
	 * 
	 * @name : createFile
	 * @description : The Method "createFile" is used for creating file in specified location. 
	 * @date : 15-Dec-2018 11:33:10 AM
	 * @param path
	 * @param json
	 * @param fileName
	 * @throws IOException
	 * @return : void
	 *
	 */
	public void createFile(String path,String json,String fileName) throws IOException {
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_hh:mm:ss");  
	    String strDate= formatter.format(date);  
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter(new StringBuilder().append(path).append(File.separator).append(fileName).append("_").append(strDate+".txt").toString()))){
			writer.write(json);
		    
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw e;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		FileUtil fileUtil=new FileUtil();
		fileUtil.createFile("C:\\Users\\shiva\\Documents", "Hello world", "abc.txt");
	}
}
