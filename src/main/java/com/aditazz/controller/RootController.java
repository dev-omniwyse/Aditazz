/**
 * 
 */
package com.aditazz.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditazz.dto.AditazzStatsDTO;
import com.aditazz.dto.InputDTO;
import com.aditazz.model.JSONResultEntity;
import com.aditazz.service.AditazzService;

/**
 * @author shiva
 *
 */
@RestController
@RequestMapping("/report")
public class RootController {
	private static final Logger logger = LoggerFactory.getLogger(AditazzService.class);
	@Autowired
	AditazzService aditazzService;

	@PutMapping("/generateStatistics")
	public ResponseEntity<?> generateReport(@RequestBody InputDTO inputDTO){
		try {
			
			List<AditazzStatsDTO> stats=aditazzService.generateStats(inputDTO);
			JSONResultEntity<AditazzStatsDTO> response = new JSONResultEntity<AditazzStatsDTO>(
	                true, "Success", null,
	                stats);
	        return new ResponseEntity<JSONResultEntity<?>>(response,
	                HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			JSONResultEntity<InputDTO> response = new JSONResultEntity<InputDTO>(
	                true, "Error",Arrays.asList(e.getMessage()), null);
			return new ResponseEntity<JSONResultEntity<?>>(response,HttpStatus.BAD_REQUEST);
		}
		
	}
}
