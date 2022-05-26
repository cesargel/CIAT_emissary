package com.cibergenius.emissary.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibergenius.emissary.components.ResponseObject;
import com.cibergenius.emissary.constants.ResponseType;
import com.cibergenius.emissary.entities.TagModel;
import com.cibergenius.emissary.service.IInventoryService;
import com.cibergenius.emissary.service.ITagService;

import io.swagger.annotations.ApiOperation;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/tag")
public class TagController {

    @Autowired
    ITagService tagService;
    @Autowired
    IInventoryService inventoryService;
    private static final Logger log = Logger.getLogger(TagController.class.getName());

    @GetMapping(value = "/{tagId}", produces = {"application/json; charset=UTF-8"})
    @ApiOperation(value = "Consulta un tag a partir de su codigo EPC")
    public ResponseEntity<ResponseObject> obtenerTag(@Nullable @PathVariable String tagId) {
        try {
            return ResponseEntity.ok(ResponseObject.success(tagService.obtenerRegisteredTag(tagId)));
        } catch (Exception e) {
            log.log(Level.WARNING, "Error en ejecuntando metodo obtenerTag {0}", e.getMessage());
            return new ResponseEntity<ResponseObject>(ResponseObject.error(ResponseType.SYSTEM_GENERAL_ERROR.getJson()), ResponseType.SYSTEM_GENERAL_ERROR.getHttpCode());
        }
    }

    @GetMapping(produces = {"application/json; charset=UTF-8"})
    @ApiOperation(value = "Consulta lista de tags (temporal)")
    public ResponseEntity<ResponseObject> obtenerTags() {
        try {
            log.log(Level.INFO, "Prueba de Info");
            log.log(Level.WARNING, "Prueba de Warnin");
            return ResponseEntity.ok(ResponseObject.success(tagService.obtenerRegisteredTag(null)));
        } catch (Exception e) {
            log.log(Level.WARNING, "Error en ejecuntando metodo obtenerTags {0}", e.getMessage());
            return new ResponseEntity<ResponseObject>(ResponseObject.error(e), ResponseType.SYSTEM_GENERAL_ERROR.getHttpCode());
        }
    }

    @PostMapping(produces = {"application/json; charset=UTF-8"})
    @ApiOperation(value = "Guarda lista de tags", notes = "Este proceso recibe un objeto de tags y los almacena en lote")
    public ResponseEntity<ResponseObject> guardarTag(@RequestBody Set<TagModel> registeredTagModel) {
        try {
            tagService.guardarRegisteredTags(registeredTagModel);
            String logicalDevices = "";
            for (TagModel tag : registeredTagModel) {
                logicalDevices = tag.getLogicalDevices();
                if(!logicalDevices.isEmpty())
                    break;
            }
            Set<TagModel> listTags = inventoryService.consultaTagInventoryLogicalDevices(logicalDevices);
            if (!listTags.isEmpty()) {
                inventoryService.initProcess(listTags);
            }
            return ResponseEntity.ok(ResponseObject.success());
        } catch (Exception e) {
            log.log(Level.WARNING, "Error en ejecuntando metodo guardarTag {0},", e.getMessage());            
            return new ResponseEntity<ResponseObject>(ResponseObject.error(e), ResponseType.SYSTEM_GENERAL_ERROR.getHttpCode());
        }
    }
}
