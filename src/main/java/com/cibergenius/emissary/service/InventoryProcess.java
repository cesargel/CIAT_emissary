package com.cibergenius.emissary.service;

import com.cibergenius.emissary.entities.InventorySaveTagModel;
import com.cibergenius.emissary.entities.TagModel;
import com.cibergenius.emissary.repository.ITagRepository;
import com.cibergenius.emissary.utils.ContentUpdateObject;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InventoryProcess {

    private static final Logger log = Logger.getLogger(InventoryProcess.class.getName());
    private Gson gson = new Gson();

    public void ExcuteInventoryProcess(Map<String, Object> req, TagModel tag, IInventoryService inventoryService, ITagRepository tagRepository, InventorySaveTagModel istm, ContentUpdateObject iutm) {
        try {
            tag.setPublishDate(new Date());
            tag.setLog(null);
            if (!tag.getLocation().equalsIgnoreCase(req.get("location").toString())) {
                String pattern = "yyyy-MM-dd HH:mm:ss:SSS";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String fecha = simpleDateFormat.format(tag.getPublishDate());
                String note = "storageLocationPart2=" + tag.getLocation()+";logical_devices="+tag.getLogicalDevices()+";publish_date="+fecha; 
                log.log(Level.INFO,note);
                
                istm.setNote(note);
                
                log.log(Level.INFO,"Request a /api/v1/i/action --"+ gson.toJson(istm).toString());
                Map<String, Object> response = (Map<String, Object>)inventoryService.GuardarInventory(istm);
                log.log(Level.INFO,"Response de /api/v1/i/action --"+ response.toString());
                
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
            throw e;
        }
    }
    
    public void NoExisteInventario(TagModel tag, ITagRepository tagRepository,String error,String mensajeError) {
        try {                        
            switch(error)
            {
                case "Not found":
                    tag.setPublishDate(new Date());                    
                    tag.setLog(error+" "+mensajeError);
                    break;
                case "Error":
                    tag.setPublishDate(new Date());                    
                    tag.setLog(error+" "+mensajeError);
                    break;
                default:                    
                    tag.setLog(error+" "+mensajeError);
                    break;
            }
            tagRepository.save(tag);
            
        } catch (Exception e) {
            log.log(Level.WARNING, "Error ejecutando proceso de tag {0}", new Object[]{ e.getMessage()});
        }
    }

}
