package com.nikhil.cloth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Shirt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serialNumber;

	private String skuId;

	private String brandName;

	private Double price;

	private String color;

	private int size;

	private int totalQuantity;

	public Shirt(String skuId, String brandName, Double price, String color, int size, int totalQuantity) {

		this.skuId = skuId;
		this.brandName = brandName;
		this.price = price;
		this.color = color;
		this.size = size;
		this.totalQuantity = totalQuantity;
	}

	public Shirt() {

	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	@Override
	public String toString() {
		return "Shirt [serialNumber=" + serialNumber + ", skuId=" + skuId + ", brandName=" + brandName + ", price="
				+ price + ", color=" + color + ", size=" + size + ", totalQuantity=" + totalQuantity + "]";
	}

}
