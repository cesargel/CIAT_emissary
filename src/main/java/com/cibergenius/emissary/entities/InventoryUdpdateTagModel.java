package com.cibergenius.emissary.entities;


import com.cibergenius.emissary.utils.Accession;
import com.cibergenius.emissary.utils.InventoryMaintenancePolicy;
import com.cibergenius.emissary.utils.Site;

public class InventoryUdpdateTagModel {
	private Integer id;
	private String createdBy;
	private Object createdDate;
	private String ownedBy;
	private Object ownedDate;
	private Accession accession;
	private Site site;
	private Object availabilityEndDate;
	private Object availabilityStartDate;
	private String availabilityStatusCode;
	private String availabilityStatusNote;
	private String distributionCriticalQuantity = null;
	private String distributionDefaultQuantity = null;
	private String distributionUnitCode = null;
	private String formTypeCode;
	private String hundredSeedWeight = null;
	private String inventoryNumber;
	private String inventoryNumberPart1;
	private int inventoryNumberPart2;
	private String inventoryNumberPart3 = null;
	private String isAutoDeducted;
	private String isAvailable;
	private String isDistributable;
	private String latitude = null;
	private String longitude = null;
	private String note = null;
	private String pathogenStatusCode = null;
	private String plantSexCode = null;
	private String pollinationMethodCode = null;
	private String propagationDate = null;
	private String propagationDateCode = null;
	private String quantityOnHand = null;
	private String quantityOnHandUnitCode = null;
	private String regenerationCriticalQuantity = null;
	private String rootstock = null;
	private String barcode;
	private String storageLocationPart1;
	private String storageLocationPart3 = null;
	private String storageLocationPart4 = null;
	private String webAvailabilityNote = null;
	private String names = null;
	private String generation = null;
	private boolean systemInventory;
	private InventoryMaintenancePolicy inventoryMaintenancePolicy;
	private String distributionDefaultFormCode = null;
	private String pollinationVectorCode;
	private String storageLocationPart2;
	private String modifiedBy;
	private String modifiedDate;
	
	public InventoryUdpdateTagModel(Integer accesion, Integer site, Integer inventoryPolicy) {
		this.accession = new Accession(accesion);
		this.site = new Site(site);
		this.inventoryMaintenancePolicy = new InventoryMaintenancePolicy(inventoryPolicy);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getOwnedBy() {
		return ownedBy;
	}
	public void setOwnedBy(String ownedBy) {
		this.ownedBy = ownedBy;
	}
	public Accession getAccession() {
		return accession;
	}
	public void setAccession(Accession accession) {
		this.accession = accession;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public String getAvailabilityStatusCode() {
		return availabilityStatusCode;
	}
	public void setAvailabilityStatusCode(String availabilityStatusCode) {
		this.availabilityStatusCode = availabilityStatusCode;
	}
	public String getAvailabilityStatusNote() {
		return availabilityStatusNote;
	}
	public void setAvailabilityStatusNote(String availabilityStatusNote) {
		this.availabilityStatusNote = availabilityStatusNote;
	}
	public String getDistributionCriticalQuantity() {
		return distributionCriticalQuantity;
	}
	public void setDistributionCriticalQuantity(String distributionCriticalQuantity) {
		this.distributionCriticalQuantity = distributionCriticalQuantity;
	}
	public String getDistributionDefaultQuantity() {
		return distributionDefaultQuantity;
	}
	public void setDistributionDefaultQuantity(String distributionDefaultQuantity) {
		this.distributionDefaultQuantity = distributionDefaultQuantity;
	}
	public String getDistributionUnitCode() {
		return distributionUnitCode;
	}
	public void setDistributionUnitCode(String distributionUnitCode) {
		this.distributionUnitCode = distributionUnitCode;
	}
	public String getFormTypeCode() {
		return formTypeCode;
	}
	public void setFormTypeCode(String formTypeCode) {
		this.formTypeCode = formTypeCode;
	}
	public String getHundredSeedWeight() {
		return hundredSeedWeight;
	}
	public void setHundredSeedWeight(String hundredSeedWeight) {
		this.hundredSeedWeight = hundredSeedWeight;
	}
	public String getInventoryNumber() {
		return inventoryNumber;
	}
	public void setInventoryNumber(String inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}
	public String getInventoryNumberPart1() {
		return inventoryNumberPart1;
	}
	public void setInventoryNumberPart1(String inventoryNumberPart1) {
		this.inventoryNumberPart1 = inventoryNumberPart1;
	}
	public String getInventoryNumberPart3() {
		return inventoryNumberPart3;
	}
	public void setInventoryNumberPart3(String inventoryNumberPart3) {
		this.inventoryNumberPart3 = inventoryNumberPart3;
	}
	public String getIsAutoDeducted() {
		return isAutoDeducted;
	}
	public void setIsAutoDeducted(String isAutoDeducted) {
		this.isAutoDeducted = isAutoDeducted;
	}
	public String getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getIsDistributable() {
		return isDistributable;
	}
	public void setIsDistributable(String isDistributable) {
		this.isDistributable = isDistributable;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPathogenStatusCode() {
		return pathogenStatusCode;
	}
	public void setPathogenStatusCode(String pathogenStatusCode) {
		this.pathogenStatusCode = pathogenStatusCode;
	}
	public String getPlantSexCode() {
		return plantSexCode;
	}
	public void setPlantSexCode(String plantSexCode) {
		this.plantSexCode = plantSexCode;
	}
	public String getPollinationMethodCode() {
		return pollinationMethodCode;
	}
	public void setPollinationMethodCode(String pollinationMethodCode) {
		this.pollinationMethodCode = pollinationMethodCode;
	}
	public String getPropagationDate() {
		return propagationDate;
	}
	public void setPropagationDate(String propagationDate) {
		this.propagationDate = propagationDate;
	}
	public String getPropagationDateCode() {
		return propagationDateCode;
	}
	public void setPropagationDateCode(String propagationDateCode) {
		this.propagationDateCode = propagationDateCode;
	}
	public String getQuantityOnHand() {
		return quantityOnHand;
	}
	public void setQuantityOnHand(String quantityOnHand) {
		this.quantityOnHand = quantityOnHand;
	}
	public String getQuantityOnHandUnitCode() {
		return quantityOnHandUnitCode;
	}
	public void setQuantityOnHandUnitCode(String quantityOnHandUnitCode) {
		this.quantityOnHandUnitCode = quantityOnHandUnitCode;
	}
	public String getRegenerationCriticalQuantity() {
		return regenerationCriticalQuantity;
	}
	public void setRegenerationCriticalQuantity(String regenerationCriticalQuantity) {
		this.regenerationCriticalQuantity = regenerationCriticalQuantity;
	}
	public String getRootstock() {
		return rootstock;
	}
	public void setRootstock(String rootstock) {
		this.rootstock = rootstock;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getStorageLocationPart1() {
		return storageLocationPart1;
	}
	public void setStorageLocationPart1(String storageLocationPart1) {
		this.storageLocationPart1 = storageLocationPart1;
	}
	public String getStorageLocationPart3() {
		return storageLocationPart3;
	}
	public void setStorageLocationPart3(String storageLocationPart3) {
		this.storageLocationPart3 = storageLocationPart3;
	}
	public String getStorageLocationPart4() {
		return storageLocationPart4;
	}
	public void setStorageLocationPart4(String storageLocationPart4) {
		this.storageLocationPart4 = storageLocationPart4;
	}
	public String getWebAvailabilityNote() {
		return webAvailabilityNote;
	}
	public void setWebAvailabilityNote(String webAvailabilityNote) {
		this.webAvailabilityNote = webAvailabilityNote;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getGeneration() {
		return generation;
	}
	public void setGeneration(String generation) {
		this.generation = generation;
	}
	public boolean isSystemInventory() {
		return systemInventory;
	}
	public void setSystemInventory(boolean systemInventory) {
		this.systemInventory = systemInventory;
	}
	public InventoryMaintenancePolicy getInventoryMaintenancePolicy() {
		return inventoryMaintenancePolicy;
	}
	public void setInventoryMaintenancePolicy(InventoryMaintenancePolicy inventoryMaintenancePolicy) {
		this.inventoryMaintenancePolicy = inventoryMaintenancePolicy;
	}
	public String getDistributionDefaultFormCode() {
		return distributionDefaultFormCode;
	}
	public void setDistributionDefaultFormCode(String distributionDefaultFormCode) {
		this.distributionDefaultFormCode = distributionDefaultFormCode;
	}
	public String getPollinationVectorCode() {
		return pollinationVectorCode;
	}
	public void setPollinationVectorCode(String pollinationVectorCode) {
		this.pollinationVectorCode = pollinationVectorCode;
	}
	public String getStorageLocationPart2() {
		return storageLocationPart2;
	}
	public void setStorageLocationPart2(String storageLocationPart2) {
		this.storageLocationPart2 = storageLocationPart2;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Object getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Object createdDate) {
		this.createdDate = createdDate;
	}
	public Object getOwnedDate() {
		return ownedDate;
	}
	public void setOwnedDate(Object ownedDate) {
		this.ownedDate = ownedDate;
	}
	public Object getAvailabilityEndDate() {
		return availabilityEndDate;
	}
	public void setAvailabilityEndDate(Object availabilityEndDate) {
		this.availabilityEndDate = availabilityEndDate;
	}
	public Object getAvailabilityStartDate() {
		return availabilityStartDate;
	}
	public void setAvailabilityStartDate(Object availabilityStartDate) {
		this.availabilityStartDate = availabilityStartDate;
	}
	public int getInventoryNumberPart2() {
		return inventoryNumberPart2;
	}
	public void setInventoryNumberPart2(int inventoryNumberPart2) {
		this.inventoryNumberPart2 = inventoryNumberPart2;
	}
}
