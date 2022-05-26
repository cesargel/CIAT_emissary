package com.cibergenius.emissary.entities;


import com.cibergenius.emissary.utils.CooperatorId;
import com.cibergenius.emissary.utils.Inventory;

public class InventorySaveTagModel {
	private Inventory inventory;
	private String actionNameCode;
	private Object startedDate;
	private Object startedDateCode;
	private Object completedDate;
	private Object completedDateCode;
	private String quantity = null;
	private String quantityUnitCode = null;
	private String formCode = null;
	private CooperatorId cooperator;
	private String methodId = null;
	private String note;
	private Object createdDate;
	private String createdBy;
	private Object ownedDate;
	private String ownedBy;
	
	public InventorySaveTagModel(Integer idInv, Integer coop) {
		this.inventory = new Inventory(idInv);
		this.cooperator = new CooperatorId(coop);
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public String getActionNameCode() {
		return actionNameCode;
	}
	public void setActionNameCode(String actionNameCode) {
		this.actionNameCode = actionNameCode;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getQuantityUnitCode() {
		return quantityUnitCode;
	}
	public void setQuantityUnitCode(String quantityUnitCode) {
		this.quantityUnitCode = quantityUnitCode;
	}
	public String getFormCode() {
		return formCode;
	}
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}
	public CooperatorId getCooperatorId() {
		return cooperator;
	}
	public void setCooperatorId(CooperatorId cooperatorId) {
		this.cooperator = cooperatorId;
	}
	public String getMethodId() {
		return methodId;
	}
	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Object getStartedDate() {
		return startedDate;
	}
	public void setStartedDate(Object startedDate) {
		this.startedDate = startedDate;
	}
	public Object getStartedDateCode() {
		return startedDateCode;
	}
	public void setStartedDateCode(Object startedDateCode) {
		this.startedDateCode = startedDateCode;
	}
	public Object getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(Object completedDate) {
		this.completedDate = completedDate;
	}
	public Object getCompletedDateCode() {
		return completedDateCode;
	}
	public void setCompletedDateCode(Object completedDateCode) {
		this.completedDateCode = completedDateCode;
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
	public String getOwnedBy() {
		return ownedBy;
	}
	public void setOwnedBy(String ownedBy) {
		this.ownedBy = ownedBy;
	}
	
}
