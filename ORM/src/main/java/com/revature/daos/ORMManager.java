package com.revature.daos;

public interface ORMManager {
	
	public void getInfoByItemNum(); //goal : take in a clothing object's int item number. return : clothing object
	public void getInventoryOfAllItems(); //goal : take in nothing. return : list of clothing objects'inventories
	public void insertNewItem(); //goal : take in a clothing object to create a new item record. return : nothing
	public void updateItemInfo(); //goal : take in a clothing object to update its info using certain criteria. return : nothing
	public void removeItemByItemNum(); //goal : take in a clothing object to delete the record. return : nothing

}
