package com.revature.models;

import java.util.Objects;

public class Shoes {
	
	private int itemNum;
	private String itemName;
	private String color;
	private double size;
	private int inventory = 500;
	private double wholesalePrice = 14.95;
	private double retailPrice = 44.99;
	
	public Shoes(int itemNum, String itemName, String color, double size, int inventory, double wholesalePrice,
			double retailPrice) {
		super();
		this.itemNum = itemNum;
		this.itemName = itemName;
		this.color = color;
		this.size = size;
		this.inventory = inventory;
		this.wholesalePrice = wholesalePrice;
		this.retailPrice = retailPrice;
	}

	public Shoes(String itemName, String color, double size, int inventory, double wholesalePrice, double retailPrice) {
		super();
		this.itemName = itemName;
		this.color = color;
		this.size = size;
		this.inventory = inventory;
		this.wholesalePrice = wholesalePrice;
		this.retailPrice = retailPrice;
	}

	public Shoes(String itemName, String color, double size) {
		super();
		this.itemName = itemName;
		this.color = color;
		this.size = size;
	}

	public Shoes() {
		super();
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
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

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
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
		return Objects.hash(color, inventory, itemName, itemNum, retailPrice, size, wholesalePrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shoes other = (Shoes) obj;
		return Objects.equals(color, other.color) && inventory == other.inventory
				&& Objects.equals(itemName, other.itemName) && itemNum == other.itemNum
				&& Double.doubleToLongBits(retailPrice) == Double.doubleToLongBits(other.retailPrice)
				&& Double.doubleToLongBits(size) == Double.doubleToLongBits(other.size)
				&& Double.doubleToLongBits(wholesalePrice) == Double.doubleToLongBits(other.wholesalePrice);
	}

	@Override
	public String toString() {
		return "Shoes [itemNum=" + itemNum + ", itemName=" + itemName + ", color=" + color + ", size=" + size
				+ ", inventory=" + inventory + ", wholesalePrice=" + wholesalePrice + ", retailPrice=" + retailPrice
				+ "]";
	}

}
