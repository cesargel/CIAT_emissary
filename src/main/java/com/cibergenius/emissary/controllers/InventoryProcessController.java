package com.cibergenius.emissary.controllers;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibergenius.emissary.components.ResponseObject;
import com.cibergenius.emissary.constants.ResponseType;
import com.cibergenius.emissary.entities.TagModel;
import com.cibergenius.emissary.service.IInventoryService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/inventoryProcess")
public class InventoryProcessController 
{
	
	@Autowired
	IInventoryService inventoryService;
	
	
	@GetMapping(produces = {"application/json; charset=UTF-8"})
	@ApiOperation(value = "", notes = "Endpoint de proceso de consulta, actualizacion e insercion de tag a partir de un barcode")
	public ResponseEntity<ResponseObject> inventoryProcess()
	{
		try {
			Set<TagModel> listTags = inventoryService.consultaTagInventory();
			if (!listTags.isEmpty()) 
			{
                            inventoryService.initProcess(listTags);                                                     
			}			
			return ResponseEntity.ok(ResponseObject.success());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseObject>(ResponseObject.error(ResponseType.SYSTEM_GENERAL_ERROR.getJson()), ResponseType.SYSTEM_GENERAL_ERROR.getHttpCode());
		}
		
	}
}
