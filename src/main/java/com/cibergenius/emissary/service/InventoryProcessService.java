package com.cibergenius.emissary.service;

import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.cibergenius.emissary.entities.InventorySaveTagModel;
import com.cibergenius.emissary.entities.TagModel;
import com.cibergenius.emissary.repository.ITagRepository;
import com.cibergenius.emissary.utils.ContentUpdateObject;

public class InventoryProcessService extends Thread {

    private static final Logger log = Logger.getLogger(InventoryProcessService.class.getName());

    public InventoryProcessService(Map<String, Object> req, TagModel tag, IInventoryService inventoryService, ITagRepository tagRepository, InventorySaveTagModel istm, ContentUpdateObject iutm) {
        try {
            tag.setPublishDate(new Date());
            if (!tag.getLocation().equalsIgnoreCase(req.get("location").toString())) {
                istm.setNote("storageLocationPart2=" + tag.getLocation());
                
                log.info("Request a /api/v1/i/action --"+ istm.toString());
                Map<String, Object> response = (Map<String, Object>)inventoryService.GuardarInventory(istm);
                log.info("Response de /api/v1/i/action --"+ response.toString());
                
                iutm.setModifiedBy("cibergenius");
                iutm.setStorageLocationPart2(tag.getLocation());
                inventoryService.ActualizaInventory(iutm);                
                tagRepository.save(tag);
            } else {
                tag.setCreateDate(new Date());
                tagRepository.save(tag);
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Error ejecutando proceso de tag {0} {1}", new Object[]{req.get("barcode"), e.getMessage()});
        }
    }   
}
