package com.cibergenius.emissary.service;

import java.util.Set;

import com.cibergenius.emissary.entities.InventorySaveTagModel;
import com.cibergenius.emissary.entities.InventoryTagModel;
import com.cibergenius.emissary.entities.TagModel;
import com.cibergenius.emissary.utils.ContentUpdateObject;

public interface IInventoryService {
	public Set<TagModel> consultaTagInventory();        
        public Set<TagModel> consultaTagInventoryLogicalDevices(String logical_devices);
	public Object BusquedaInventory(InventoryTagModel tag);
	public Object GuardarInventory(InventorySaveTagModel tag);
	public Object ActualizaInventory(ContentUpdateObject tag);
	public void initProcess(Set<TagModel> listTags);
}
