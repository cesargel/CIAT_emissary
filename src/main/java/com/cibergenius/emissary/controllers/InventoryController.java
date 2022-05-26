package com.cibergenius.emissary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibergenius.emissary.components.ResponseObject;
import com.cibergenius.emissary.constants.ResponseType;
import com.cibergenius.emissary.entities.InventorySaveTagModel;
import com.cibergenius.emissary.entities.InventoryTagModel;
import com.cibergenius.emissary.entities.InventoryUdpdateTagModel;
import com.cibergenius.emissary.service.IInventoryService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	IInventoryService inventoryService;
	
	@PostMapping(path = "/search", produces = {"application/json; charset=UTF-8"})
	@ApiOperation(value = "", notes = "Endpoint consulta de inventario, unicamente de todo el objeto recibe el barcode")
	public ResponseEntity<ResponseObject> searchInventory(@RequestBody InventoryTagModel tag)
	{
		try {
			return ResponseEntity.ok(ResponseObject.success(inventoryService.BusquedaInventory(tag)));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseObject>(ResponseObject.error(e), ResponseType.SYSTEM_GENERAL_ERROR.getHttpCode());
		}
	}
	
	@PostMapping(path = "/save", produces = {"application/json; charset=UTF-8"})
	@ApiOperation(value = "", notes = "Endpoint para guardar inventario")
	public ResponseEntity<ResponseObject> saveInventory(@RequestBody InventorySaveTagModel tag)
	{
		return ResponseEntity.ok(ResponseObject.success());
	}
	
	@PutMapping(path = "/update", produces = {"application/json; charset=UTF-8"})
	@ApiOperation(value = "", notes = "Endpoint para hacer update de inventario")
	public ResponseEntity<ResponseObject> updateInventory(@RequestBody InventoryUdpdateTagModel tag)
	{
		return ResponseEntity.ok(ResponseObject.success());
	}

}
