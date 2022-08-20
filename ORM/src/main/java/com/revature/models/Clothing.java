package com.revature.models;

import java.util.Objects;

public class Clothing {

	private int itemNum;
	private String category;
	private String itemName;
	private String color;
	private String itemSize;
	private double wholesalePrice;
	private double retailPrice;
	
	public Clothing(int itemNum, String category, String itemName, String color, String itemSize, double wholesalePrice,
			double retailPrice) {
		super();
		this.itemNum = itemNum;
		this.category = category;
		this.itemName = itemName;
		this.color = color;
		this.itemSize = itemSize;
		this.wholesalePrice = wholesalePrice;
		this.retailPrice = retailPrice;
	}

	public Clothing(String category, String itemName, String color, String itemSize, double wholesalePrice,
			double retailPrice) {
		super();
		this.category = category;
		this.itemName = itemName;
		this.color = color;
		this.itemSize = itemSize;
		this.wholesalePrice = wholesalePrice;
		this.retailPrice = retailPrice;
	}

	public Clothing() {
		super();
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

	public double getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, color, itemName, itemNum, itemSize, retailPrice, wholesalePrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clothing other = (Clothing) obj;
		return Objects.equals(category, other.category) && Objects.equals(color, other.color)
				&& Objects.equals(itemName, other.itemName) && itemNum == other.itemNum
				&& Objects.equals(itemSize, other.itemSize)
				&& Double.doubleToLongBits(retailPrice) == Double.doubleToLongBits(other.retailPrice)
				&& Double.doubleToLongBits(wholesalePrice) == Double.doubleToLongBits(other.wholesalePrice);
	}

	@Override
	public String toString() {
		return "Clothing [itemNum=" + itemNum + ", category=" + category + ", itemName=" + itemName + ", color=" + color
				+ ", itemSize=" + itemSize + ", wholesalePrice=" + wholesalePrice + ", retailPrice=" + retailPrice
				+ "]";
	}
	
}
