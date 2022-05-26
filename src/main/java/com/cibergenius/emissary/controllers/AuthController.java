package com.cibergenius.emissary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibergenius.emissary.components.ResponseObject;
import com.cibergenius.emissary.entities.AuthModel;
import com.cibergenius.emissary.service.IAuthService;
import com.cibergenius.emissary.service.InventoryService;

import io.swagger.annotations.ApiOperation;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/oauth")
public class AuthController {
    
    @Autowired
    IAuthService authService;

    @PostMapping(value = "/token", produces = {"application/json; charset=UTF-8"})
    @ApiOperation(value = "", notes = "Endpoint para obtener token")
    public ResponseEntity<?> getToken(@RequestBody AuthModel body) {        
        return ResponseEntity.ok(ResponseObject.success(authService.getToken()));
    }

}
