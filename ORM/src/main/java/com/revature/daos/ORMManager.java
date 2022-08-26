package com.revature.daos;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ORMManager {
	
	//C//
	public void insertNewItem(Object o);
	//R//
	public <T> List<T> getInfoByItemNo(Class <?> unknownClass, Class <?> unknownClass2, int itemNum);
	public <T> List<T> getInventoryOfAll(Class <T> unkownClass);
	//U//
	public void updateByItemNo(Object o, int itemNum, String column, String value);
	//D//
	public void removeByItemNo(Object o, int itemNum);
	
	//Carolyn's convert string to fieldtype method 
	public Object convertStringToFieldType(String input, Class<?> type) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;

}
