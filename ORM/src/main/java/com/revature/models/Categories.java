package com.revature.models;

import java.util.Objects;

public class Categories {
	
	private String category;
	private int inventory;
	
	public Categories(String category, int inventory) {
		super();
		this.category = category;
		this.inventory = inventory;
	}

	public Categories() {
		super();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, inventory);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categories other = (Categories) obj;
		return Objects.equals(category, other.category) && inventory == other.inventory;
	}

	@Override
	public String toString() {
		return "Categories [category=" + category + ", inventory=" + inventory + "]";
	}

}
