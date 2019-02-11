package com.aditazz.service;
/**
 * 
 * @author      : Sreekhar Reddy.K
 * @createdOn   : 03-Dec-2018 11:43:32 AM
 * @description : The class RandomGraphGenerator.java used for
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jgrapht.Graph;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.util.SupplierUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditazz.dto.InputDTO;
import com.aditazz.enums.JsonFields;
import com.aditazz.model.Aditazz;
import com.aditazz.model.Line;
import com.aditazz.model.PfdEquipment;
import com.aditazz.util.DistanceUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service
public class RandomGraphGenerator {
	private static final Logger logger = LoggerFactory.getLogger(AditazzService.class);
	private static final AtomicLong seedUniquifier = new AtomicLong(8682522807148012L);
	
	@Autowired
	PipeService pipeService;
	
	@Autowired
	EquipmentService equipmentService;
	
	/**
	 * 
	 * @name : generateRandomGraph
	 * @description : The Method "generateRandomGraph" is used for generate random graph and prepare the valid equipments and lines. 
	 * @date : 03-Dec-2018 3:08:01 PM
	 * @param aditazz
	 * @param numberOfNodes
	 * @param numberOfEdges
	 * @param inputDTO 
	 * @return
	 * @return : JsonObject
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 *
	 */
	public JsonObject generateRandomGraph(Aditazz aditazz,int numberOfNodes,int numberOfEdges, InputDTO inputDTO) throws   IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		LineService lineService=new LineService();
		Gson gson=new Gson();
		DistanceUtil distanceUtil=new DistanceUtil();
		JsonObject equipmentJson=new JsonObject();
		JsonObject lineJson=new JsonObject();
		LinkedHashMap<String, String> uuids=new LinkedHashMap<>();
		JsonParser parser = new JsonParser();
		logger.info("Generating random graph.........!");
		logger.info("Number of equipments ::{} And Number of lines ::{}",numberOfNodes,numberOfEdges);
		Graph<Integer, DefaultEdge> graph =generateGraph(numberOfNodes, numberOfEdges);
		int counter=0;
		String lineId="L000";
		JsonObject equipmentLib=equipmentService.getEquipments(aditazz);
		JsonObject payloadLib=equipmentLib.get(JsonFields.EQUIPMENT_LIBRARIES.getValue()).getAsJsonArray().get(0).getAsJsonObject().get(JsonFields.PAYLOAD.getValue()).getAsJsonObject();
		List<String> equipOrder=equipmentService.jsonStringToArray(payloadLib);
		equipOrder=equipmentService.createDuplacateEquipments(equipOrder, numberOfNodes);
		List<String> pipeIds=pipeService.getListOfPipes(aditazz);
		pipeIds=pipeService.createDuplacatePipes(pipeIds, numberOfEdges);
		
		int pipeCounter=0;
		int x=102;
		int y=112;
		Map<String,JsonObject> spacingTable=equipmentService.getSpacingTableFromLib(payloadLib);
		for (DefaultEdge edge: graph.edgeSet()) {
			List<Integer[]> path=new ArrayList<>();
			PfdEquipment sourceEquipment=null;
			PfdEquipment targetEquipment=null;
			String[] edges=edge.toString().replaceAll("[^a-zA-Z0-9:]", "").trim().split(":");
			
			counter++;
			String sourceUuid=UUID.randomUUID().toString();
			String targetUuid=UUID.randomUUID().toString();
			String sourceSide="left";
			String targetSide="right";
			if(counter == 10) {
				lineId="L00";
			}else if(counter == 100) {
				lineId="L0";
			}else if(counter == 1000) {
				lineId="L";
			}
			
			if(!uuids.containsKey(edges[0])) {
				x+=10;y+=10;
				sourceEquipment=new PfdEquipment();
				sourceEquipment=equipmentService.preparePfdEquipment(sourceEquipment, equipOrder.get(Integer.parseInt(edges[0])-1), sourceUuid, "left",x,y);
				uuids.put(edges[0], sourceUuid);
				equipmentJson.add(sourceUuid, parser.parse(gson.toJson(sourceEquipment)).getAsJsonObject());
			}else {
				sourceEquipment=objectMapper.readValue(gson.toJson(equipmentJson.get(uuids.get(edges[0]))), PfdEquipment.class);
				sourceUuid=uuids.get(edges[0]);
			}
			if(!uuids.containsKey(edges[1])) {
				x+=10;y+=10;
				targetEquipment=new PfdEquipment();
				targetEquipment=equipmentService.preparePfdEquipment(targetEquipment,  equipOrder.get(Integer.parseInt(edges[1])-1), targetUuid,  "right",x,y);
				uuids.put(edges[1], targetUuid);
				equipmentJson.add(targetUuid, parser.parse(gson.toJson(targetEquipment)).getAsJsonObject());
			}else {
				targetEquipment=objectMapper.readValue(gson.toJson(equipmentJson.get(uuids.get(edges[1]))), PfdEquipment.class);
				targetUuid=uuids.get(edges[1]);
			}
			path.add(new Integer[] {sourceEquipment.getX(),sourceEquipment.getY()});
			path.add(new Integer[] {targetEquipment.getX(),targetEquipment.getY()});
			if(pipeCounter >= pipeIds.size()) 
				pipeCounter=0;
			
			spacingTable=distanceUtil.updateShortestDistance(equipOrder.get(Integer.parseInt(edges[0])-1), equipOrder.get(Integer.parseInt(edges[1])-1), spacingTable);
			String lineUuid=UUID.randomUUID().toString();
			Line line=new Line();
			line.setId(lineId+counter);
			
			line=lineService.preparePfdLine(line, lineUuid, sourceSide, targetSide, sourceUuid, targetUuid,path);
			line.setPipeID(pipeIds.get(counter-1));
			lineJson.add(lineUuid,  parser.parse(gson.toJson(line)).getAsJsonObject());
			pipeCounter++;
			
		}
		payloadLib.add(JsonFields.SPACING.getValue(), new Gson().toJsonTree(spacingTable));
		if (inputDTO.getSpacing())
		equipmentService.updateEquipmentLibrary(aditazz, payloadLib,equipmentLib);
		logger.info("Graph is :: {} ",graph);
		JsonObject jsonObject=new JsonObject();
		jsonObject.add(JsonFields.EQUIPMENT.getValue(), equipmentJson);
		jsonObject.add(JsonFields.LINES.getValue(), lineJson);
		return jsonObject;
	}
	/**
	 * 
	 * @name : gnerateGraph
	 * @description : The Method "gnerateGraph" is used for 
	 * @date : 04-Dec-2018 5:04:25 PM
	 * @param numberOfNodes
	 * @param numberOfEdges
	 * @return
	 * @return : Graph<Integer,DefaultEdge>
	 *
	 */
	private Graph<Integer, DefaultEdge> generateGraph(int numberOfNodes,int numberOfEdges) {
		long seed=seedUniquifier() ^ System.nanoTime();
		GraphGenerator<Integer, DefaultEdge, Integer> gen =  new GnmRandomGraphGenerator<>(numberOfNodes, numberOfEdges,seed,false,false);
		Graph<Integer, DefaultEdge> graph = new DirectedPseudograph<>(SupplierUtil.createIntegerSupplier(1), SupplierUtil.createDefaultEdgeSupplier(), false);
		gen.generateGraph(graph);
		return graph;
	}
	
	/**
	 * 
	 * @name : seedUniquifier
	 * @description : The Method "seedUniquifier" is used for generating some sequence to generate random graph 
	 * @date : 03-Dec-2018 3:09:21 PM
	 * @return
	 * @return : long
	 *
	 */
	private long seedUniquifier() {
        // L'Ecuyer, "Tables of Linear Congruential Generators of
        // Different Sizes and Good Lattice Structure", 1999
        for (;;) {
            long current = seedUniquifier.get();
            long next = current * 181783497276652981L;
            if (seedUniquifier.compareAndSet(current, next))
                return next;
        }
	 }
}
