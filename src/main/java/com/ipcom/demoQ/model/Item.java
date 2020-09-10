package com.ipcom.demoQ.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    protected String operationType;
    protected String sku;
    protected String itemDescription;
    protected BigDecimal price;
    
    public String getOperationType() {
		return operationType;
	}
    
    public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
    
    public String getSku() {
		return sku;
	}
    public void setSku(String sku) {
		this.sku = sku;
	}
    
    public String getItemDescription() {
		return itemDescription;
	}
    
    public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
    
    public BigDecimal getPrice() {
		return price;
	}
    
    public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [operationType=" + operationType + ", sku=" + sku + ", itemDescription=" + itemDescription
				+ ", price=" + price + "]";
	}
    
	
}
