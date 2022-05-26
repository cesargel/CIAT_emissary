package com.cibergenius.emissary.service;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cibergenius.emissary.entities.InventorySaveTagModel;
import com.cibergenius.emissary.entities.InventoryTagModel;
import com.cibergenius.emissary.entities.TagModel;
import com.cibergenius.emissary.entities.TokenModel;
import com.cibergenius.emissary.repository.ITagRepository;
import com.cibergenius.emissary.utils.ContentObject;
import com.cibergenius.emissary.utils.ContentUpdateObject;
import com.cibergenius.emissary.utils.Site;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class InventoryService implements IInventoryService {

    private static final Logger log = Logger.getLogger(InventoryService.class.getName());

    private Gson gson = new Gson();

    @Autowired
    ITagRepository repository;

    @Autowired
    AuthService as;

    @Value("${cibergenius.server}")
    String server;

    @Value("${cibergenius.server.origin}")
    String origin;

    @Value("${url.cibergenius.search}")
    String urlSearch;

    @Value("${url.cibergenius.search.method}")
    String urlMethodSearch;

    @Value("${url.cibergenius.insert}")
    String urlInsert;

    @Value("${url.cibergenius.insert.method}")
    String urlMethodInsert;

    @Value("${url.cibergenius.update}")
    String urlUpdate;

    @Value("${url.cibergenius.update.method}")
    String urlMethodUpdate;

    @Value("${cibergenius.auth.username}")
    String aUser;

    @Value("${cibergenius.auth.password}")
    String aPass;

    @Override
    public Set<TagModel> consultaTagInventory() {
        try {
            return repository.findBypublishDateIsNull();
        } catch (Exception e) {
            log.log(Level.WARNING, "Fallo consulta tags");
            throw e;
        }
    }

    @Override
    public Set<TagModel> consultaTagInventoryLogicalDevices(String logical_devices) {
        try {
            Set<TagModel> list = repository.getLogicalDevicesPublishDateNull(logical_devices);
            return list;
        } catch (Exception e) {
            log.log(Level.WARNING, "Fallo consulta tags");
            throw e;
        }
    }

    @Override
    public Object BusquedaInventory(InventoryTagModel tag) {
        Map<String, Object> resp = null;
        try {
            log.log(Level.INFO, "Consulta EPC {0}", new Object[]{tag.getBarcode()});
            resp = this.consumeService(urlSearch, urlMethodSearch, tag);
            @SuppressWarnings("unchecked")
            ArrayList<Object> lista = (ArrayList<Object>) resp.get("content");
            if (lista.isEmpty()) {
                resp.put("existe", false);
            } else {
                resp.put("existe", true);
                resp.put("data", this.mappingInventory(gson.fromJson(gson.toJson(lista.get(0)), ContentObject.class)));
            }            
        } catch (Exception e) {
            log.log(Level.WARNING, "Error consultando tag en inventory, Exception {0}", new Object[]{e.getMessage()});
            throw e;
        }
        return resp;
    }

    @Override
    public Object GuardarInventory(InventorySaveTagModel tag) {
        try {
            return this.consumeService(urlInsert, urlMethodInsert, tag);
        } catch (Exception e) {
            log.log(Level.WARNING, "Error insertando tag en inventory, Exception {0}", new Object[]{e.getMessage()});
            throw e;
        }
    }

    @Override
    public Object ActualizaInventory(ContentUpdateObject tag) {
        try {
            return this.consumeService(urlUpdate, urlMethodUpdate, tag);
        } catch (Exception e) {
            log.log(Level.WARNING, "Error actualizando tag en inventory, Exception {0}", new Object[]{e.getMessage()});
            throw e;
        }
    }

    private Map<String, Object> consumeService(String accion, String method, Object body) {
        try {
            String plainCreds = aUser + ":" + aPass;
            byte[] plainCredsBytes = plainCreds.getBytes();
            byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
            String base64Creds = new String(base64CredsBytes);
            TokenModel auth = as.getToken();
            MultivaluedMap<String, Object> headers = new MultivaluedHashMap<String, Object>();
            headers.add("Authorization", "bearer " + auth.getAccess_token());
            headers.add("Authorization", "basic " + base64Creds);
            headers.add("Origin", origin);
            String url = server + accion;
            Client c = ClientBuilder.newClient();
            WebTarget wt = c.target(url);
            Invocation.Builder sol = wt.request();
            sol.headers(headers);
            Response response = null;
            if (method.equals("POST")) {
                response = sol.post(Entity.json(gson.toJson(body)));
            } else {
                response = sol.put(Entity.json(gson.toJson(body)));
            }

            @SuppressWarnings("unchecked")
            Map<String, Object> resp = gson.fromJson(response.readEntity(String.class), Map.class);
            return resp;
        } catch (Exception ex) {
            log.log(Level.WARNING, "Error Consuminedo Servicio, Exception {0}", new Object[]{ex.getMessage()});
            throw ex;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initProcess(Set<TagModel> listTags) {
        InventoryTagModel itm = new InventoryTagModel();
        InventoryProcess objInventoryProcess = new InventoryProcess();
        String barcodeUUID = "";
        String error = "";
        for (TagModel tagModel : listTags) {
            try {
                byte[] data = null;
                data = Hex.decodeHex(tagModel.getEPC().toCharArray());
                try {
                    barcodeUUID = new UUID(ByteBuffer.wrap(data, 0, 8).getLong(), ByteBuffer.wrap(data, 8, 8).getLong()).toString();
                    itm.setBarcode(barcodeUUID.toUpperCase());
                } catch (Exception ex) {
                    log.log(Level.WARNING, "Fallo Estructura EPC {0}", new Object[]{tagModel.getEPC()});
                    error = " EPC debe tener 32 caracteres";
                    throw ex;
                }
                
                Map<String, Object> m = (Map<String, Object>) this.BusquedaInventory(itm);
                if ((Boolean) m.get("existe")) {
                    m = (Map<String, Object>) m.get("data");
                    objInventoryProcess.ExcuteInventoryProcess(m, tagModel, this, repository, gson.fromJson(gson.toJson(m.get("save")), InventorySaveTagModel.class), gson.fromJson(gson.toJson(m.get("update")), ContentUpdateObject.class));
                } else {
                    objInventoryProcess.NoExisteInventario(tagModel, repository, "Not found", "Error Tag no Encontrado");
                }
            } catch (JsonSyntaxException e) {
                log.log(Level.WARNING, "Fallo para epc {0}", new Object[]{tagModel.getEPC()});
                objInventoryProcess.NoExisteInventario(tagModel, repository, "Error Consumiendo Servcio ", e.toString());
            } catch (DecoderException e) {
                log.log(Level.WARNING, "Fallo Encode epc {0}", new Object[]{tagModel.getEPC()});
                objInventoryProcess.NoExisteInventario(tagModel, repository, "Error Encode ", e.toString());
            } catch (Exception e) {
                log.log(Level.WARNING, "Fallo epc {0}", new Object[]{tagModel.getEPC()});
                objInventoryProcess.NoExisteInventario(tagModel, repository, "Error ", error + " " + e.toString());
            }
        }
    }

    private Map<String, Object> mappingInventory(ContentObject obj) {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String fecha = simpleDateFormat.format(new Date());
                
        Map<String, Object> respuesta = new HashMap<String, Object>();
        InventorySaveTagModel istm = new InventorySaveTagModel(obj.getId(), 0);
        istm.setActionNameCode("CHANGED_STORAGE_LOC");
        
        istm.setStartedDate(fecha);                        
        istm.setStartedDateCode("dd/MM/yyyy");
        
        istm.setCompletedDate(fecha);
        istm.setCompletedDateCode("dd/MM/yyyy");
        
        istm.setCreatedDate(obj.getCreatedDate());
        istm.setCreatedBy(obj.getCreatedBy());
        
        istm.setOwnedBy(obj.getOwnedBy());
        istm.setOwnedDate(obj.getOwnedDate());
        respuesta.put("save", istm);
        ContentUpdateObject cuo = new ContentUpdateObject();
        cuo = gson.fromJson(gson.toJson(obj), ContentUpdateObject.class);
        if (obj.getSite().getClass() == Double.class) {
            Double sit = (Double) obj.getSite();
            cuo.setSite(new Site(sit.intValue()));
        } else {
            cuo.setSite(gson.fromJson(gson.toJson(obj.getSite()), Site.class));
        }
        respuesta.put("update", cuo);
        respuesta.put("barcode", obj.getBarcode());
        respuesta.put("location", obj.getStorageLocationPart2());
        return respuesta;
    }

}
